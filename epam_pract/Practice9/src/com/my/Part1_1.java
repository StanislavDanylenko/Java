package com.my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Part1_1")
public class Part1_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int x = Integer.parseInt(req.getParameter("x"));
        int y = Integer.parseInt(req.getParameter("y"));
        String oper = req.getParameter("op");
        int res = 0;
        switch (oper) {
            case "minus":
                res = x - y;
                break;
            case "plus":
                res = x + y;
                break;
            case "dev":
                res = x / y;
                break;
            case "mult":
                res = x * y;
                break;
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("RESULT:" + res);
    }
}