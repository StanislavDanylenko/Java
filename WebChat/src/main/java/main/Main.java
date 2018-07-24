package main;

import base.Account;
import base.Context;
import base.DBService;
import dbService.DBServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.ChatServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.WebSocketChatServlet;
import sessions.AccountService;
import sessions.WebContext;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            LOGGER.error("Use port as the first argument");
            System.exit(1);
        }
        String portString = args[0];
        int port = Integer.valueOf(portString);

        LOGGER.info("Starting at http://127.0.0.1:" + portString);

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

        Server server = new Server(port);

        context.addServlet(new ServletHolder(signInServlet), SignInServlet.PAGE_URL);
        context.addServlet(new ServletHolder(signUpServlet), SignUpServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new ChatServlet()), ChatServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), WebSocketChatServlet.PAGE_URL);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        LOGGER.info("Server started");
        //System.out.println("Server started");
        server.join();
    }
}
