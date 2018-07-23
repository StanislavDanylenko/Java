package main;

import base.Account;
import base.Context;
import base.DBService;
import dbService.DBServiceImpl;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import sessions.AccountService;
import sessions.WebContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImpl();
        Account accountService = new AccountService();

        Context webContext = new WebContext();
        webContext.add(DBService.class, dbService);
        webContext.add(Account.class, accountService);

        dbService.printConnectInfo();

        SignInServlet signInServlet = new SignInServlet((WebContext) webContext);
        SignUpServlet signUpServlet = new SignUpServlet((WebContext) webContext);

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
