package com.my;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.List;

@WebListener()
public class Listener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public Listener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        List<String> list = Arrays.asList(sce.getServletContext().getInitParameter("list").split(" "));
        sce.getServletContext().setAttribute("myList", list);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
