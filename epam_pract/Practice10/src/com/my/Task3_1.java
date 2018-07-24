package com.my;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Task3_1")
public class Task3_1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String curName = request.getParameter("name");
        System.out.println(curName);

        if ("cleanList".equals(curName)) {
                List<String> cleanList = new ArrayList<>();
                System.out.println(cleanList);
                session.setAttribute("list", cleanList);
                request.getRequestDispatcher("index3.jsp").forward(request, response);
        } else {

            List<String> list = new ArrayList<>();
            if (!session.isNew()) {
                List<String> list1 = (List) session.getAttribute("list");
                if (list1 != null) {
                    list = list1;
                }
            }
            System.out.println(list);
            list.add(curName);

            session.setAttribute("list", list);
            request.getRequestDispatcher("index3.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
