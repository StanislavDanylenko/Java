package com.my;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Task4_1")
public class Task4_1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        Context initContext = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        List<String> users = new ArrayList<>();
        try {
            initContext = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        DataSource ds = null;
        try {
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/task4");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement("select * from users where login=? and password=?");
            ps.setString(1, login);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if (rs.next()) {
                req.getRequestDispatcher("index3.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("index4.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
