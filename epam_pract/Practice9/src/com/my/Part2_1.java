package com.my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/Part2_1")
public class Part2_1 extends HttpServlet {
    List<String> list;
    Map<String, Integer> map;

    private String getPrintInfo(){
        StringBuilder sb = new StringBuilder("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Part2_1</title>" +
                "</head>" +
                "<body>" +
                "    <form action=\"Part2_1\">" + "<table style='border-collapse: collapse; border: 1px solid black;'>");

        for (String str : list) {
            sb.append("<tr><td style='border: 1px solid black'><p><input type=\"radio\" name=\"group1\" value=\"" + str + "\">" + str + "</p></td></tr>");
        }
        sb.append(
                 "</table>"  +
                         "<input type=\"submit\" name=\"submit\" value=\"submit\"> " +
                        "</form>" +
                        "</body>" +
                        "</html>");
        return sb.toString();
    }

    private String getPrintInfoVal(){
        StringBuilder sb = new StringBuilder("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "    <form action=\"Part2_1\">"+
                "    <table style='border-collapse: collapse; border: 1px solid black;'>");
        for (String str : list) {
            sb.append("<tr>" +
                    "<td style='border: 1px solid black'>" + "<p><input type=\"radio\" name=\"group1\" value=\"" + str +
                    "\">" + str + "</p>" + "</td> <td style='border: 1px solid black'>" +
                    map.get(str) +
                    "</td>" +
                    "</tr>");
        }
        sb.append(
                "    </table>\n" + "<input type=\"submit\" name=\"submit\" value=\"submit\">\n" +
                "    </form>\n" +
                        "</body>\n" +
                        "</html>");
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        list = (List) getServletContext().getAttribute("myList");
        if (map == null) {
            map = new TreeMap<>();
            for (String str : list) {
                map.put(str, 0);
            }
        }

        String curChar = req.getParameter("group1");
        System.out.println();

        PrintWriter printWriter = resp.getWriter();

        if ("".equals(curChar) || curChar == null) {
            String res = getPrintInfo();
            printWriter.write(res);
        } else {
            int oldVal = map.get(curChar);
            map.put(curChar, ++oldVal);
            String res = getPrintInfoVal();
            printWriter.write(res);
        }
    }
}
