package com.my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Part1_2")
public class Part1_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int x = Integer.parseInt(req.getParameter("x"));
        int y = Integer.parseInt(req.getParameter("y"));
        String oper = req.getParameter("op");
        String URL = req.getRequestURL().toString();
        int pos = URL.lastIndexOf(req.getRequestURI());
        URL = URL.substring(0, pos);
        int res = 0;
        switch (oper) {
            case "+":
                res = x + y;
                break;
            case "-":
                res = x - y;
                break;
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<p>RESULT: " + res+"</p>");
        printWriter.write("<p><a href=" + URL + ">GO BACK</a></p>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}