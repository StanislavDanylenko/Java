package main;

import sessions.AccountManager;
import sessions.AccountServiceControllerMBean;
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
import servlets.*;
import sessions.AccountManagerService;
import sessions.AccountServiceController;
import sessions.WebContext;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

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
        AccountManager accountManagerService = new AccountManagerService();

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName userStatistic = new ObjectName("Admin:type=AccountServiceController");
        AccountServiceControllerMBean serverStatistics = new AccountServiceController(accountManagerService);
        mbs.registerMBean(serverStatistics, userStatistic);

        Context webContext = new WebContext();
        webContext.add(DBService.class, dbService);
        webContext.add(AccountManager.class, accountManagerService);

        dbService.printConnectInfo();

        SignInServlet signInServlet = new SignInServlet((WebContext) webContext);
        SignUpServlet signUpServlet = new SignUpServlet((WebContext) webContext);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("static_html");

        Server server = new Server(port);

        context.addServlet(new ServletHolder(signInServlet), SignInServlet.PAGE_URL);
        context.addServlet(new ServletHolder(signUpServlet), SignUpServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new ChatServlet(accountManagerService)), ChatServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new WebSocketChatServlet(accountManagerService)), WebSocketChatServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new LogoutServlet(accountManagerService)), LogoutServlet.PAGE_URL);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        LOGGER.info("Server started");
        server.join();
    }
}
