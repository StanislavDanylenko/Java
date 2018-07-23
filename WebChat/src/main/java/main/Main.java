package main;

import base.Account;
import base.Context;
import base.DBService;
import dbService.DBServiceImpl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import sessions.AccountService;
import sessions.WebContext;

public class Main {

    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImpl();
        Account accountService = new AccountService();

        /*ResourceProviderImpl resourceProvider = new ResourceProviderImpl();
        resourceProvider.loadResources();
        H2Configuration cfg = resourceProvider.getResource(H2Configuration.class);
        System.out.println(cfg);*/

        Context webContext = new WebContext();
        webContext.add(DBService.class, dbService);
        webContext.add(Account.class, accountService);

        dbService.printConnectInfo();

        SignInServlet signInServlet = new SignInServlet((WebContext) webContext);
        SignUpServlet signUpServlet = new SignUpServlet((WebContext) webContext);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("static_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);

        server.setHandler(handlers);
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
