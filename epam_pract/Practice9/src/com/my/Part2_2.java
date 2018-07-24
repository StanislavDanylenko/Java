package com.my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@WebServlet("/Part2_2")
public class Part2_2 extends HttpServlet {
    List<String> list;
    List<Vote> mapVote;

    private String getPrintInfo(){
        StringBuilder sb = new StringBuilder("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Part2_2</title>" +
                "</head>" +
                "<body>" +
                "    <form action=\"Part2_2\">" + "<table style='border-collapse: collapse; border: 1px solid black;'>");

        for (String str : list) {
            sb.append("<tr><td style='border: 1px solid black'><p><input type=\"radio\" name=\"group1\" value=\"" + str + "\">" + str + "</p></td></tr>");
        }
        sb.append(
                "</table>"  +
                        "<input type=\"text\" name=\"text\"> "+
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
                "    <form action=\"Part2_2\">"+
                "    <table style='border-collapse: collapse; border: 1px solid black;'>");
        for (int i = 0; i < list.size(); i++) {
            Vote Val = null;
            for (int j = 0; j < mapVote.size(); j++){
                if (mapVote.get(j).getName().equals(list.get(i))){
                    Val = mapVote.get(j);
                }
            }
            sb.append("<tr>" +
                    Val.toString()+
                    "</tr>");
        }
        sb.append(
                   "</table>\n" +
                                    "<input type=\"text\" name=\"text\"> "+
                           "<input type=\"submit\" name=\"submit\" value=\"submit\">" +
                           "    </form>\n" +
                        "</body>\n" +
                        "</html>");
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        list = (List) getServletContext().getAttribute("myList");
        if (mapVote == null) {
            mapVote = new ArrayList<>();
            for (String str : list) {
                mapVote.add(new Vote(str));
            }
        }

        String curChar = req.getParameter("group1");
        String curVoter = req.getParameter("text");

        PrintWriter printWriter = resp.getWriter();

        if ("".equals(curChar) || curChar == null) {
            String res = getPrintInfo();
            printWriter.write(res);
        } else {
            Vote oldVal = null;
            for (int i = 0; i < mapVote.size(); i++){
                if (mapVote.get(i).getName().equals(curChar)){
                    oldVal = mapVote.get(i);
                }
            }
            if (oldVal != null) {
                oldVal.incrementCount();
                List<String> oldList = oldVal.getVoters();
                oldList.add(curVoter);
            }
            String res = getPrintInfoVal();
            printWriter.write(res);
        }
    }
}
