package com.my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Part2_3")
public class Part2_3 extends HttpServlet {
    List<String> list;
    List<Vote> mapVote;

    /*private String getPrintInfo(String error) {
        StringBuilder sb = new StringBuilder("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Part2_2</title>" +
                "</head>" +
                "<body>" +
                "    <form action=\"Part2_3\">" + "<table style='border-collapse: collapse; border: 1px solid black;'>");

        for (String str : list) {
            sb.append("<tr><td style='border: 1px solid black'><p><input type=\"radio\" name=\"group1\" value=\"" + str + "\">" + str + "</p></td></tr>");
        }
        sb.append(
                "</table>" +
                        "<input type=\"text\" name=\"text\"> " +
                        "<input type=\"submit\" name=\"submit\" value=\"submit\"> " +
                        "</form>" + "<p style='color:red';>" + error + "</p>" +
                        "</body>" +
                        "</html>");
        return sb.toString();
    }
*/
    private String getPrintInfoVal(String error) {
        StringBuilder sb = new StringBuilder("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "    <form action=\"Part2_3\">" +
                "    <table style='border-collapse: collapse; border: 1px solid black;'>");
        for (int i = 0; i < list.size(); i++) {
            Vote Val = null;
            for (int j = 0; j < mapVote.size(); j++) {
                if (mapVote.get(j).getName().equals(list.get(i))) {
                    Val = mapVote.get(j);
                }
            }
            sb.append("<tr>" +
                    Val.toString() +
                    "</tr>");
        }
        sb.append(
                "</table>\n" +
                        "<input type=\"text\" name=\"text\"> " +
                        "<input type=\"submit\" name=\"submit\" value=\"submit\">" +
                        "    </form>\n" + "<p style='color:red';>" + error + "</p>" +
                        "</body>\n" +
                        "</html>");
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = "";
        HttpSession s = req.getSession();
        List<String> votersInSession = new ArrayList<>();
        boolean isNew = s.isNew();

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

        if (isNew) {
            s.setAttribute("voters", votersInSession);
            String res = getPrintInfoVal(error);
            printWriter.write(res);
        } else {
            if ("".equals(curChar)) {
                error = "Please select the section!";
                String res = getPrintInfoVal(error);
                printWriter.write(res);
                return;
            } else if ("".equals(curVoter)) {
                error = "Invalid user name!";
                String res = getPrintInfoVal(error);
                printWriter.write(res);
                return;
            }

            List<String> listCur = (List)s.getAttribute("voters");
            for (String vots : listCur){
                if(vots.equals(curVoter)){
                    error = "You have already voted!";
                    String res = getPrintInfoVal(error);
                    printWriter.write(res);
                    return;
                }
            }

            Vote oldVal = null;
            for (int i = 0; i < mapVote.size(); i++) {
                if (mapVote.get(i).getName().equals(curChar)) {
                    oldVal = mapVote.get(i);
                    break;
                }
            }
            if (oldVal != null) {
                List<String> oldList = oldVal.getVoters();

                oldList.add(curVoter);
                oldVal.incrementCount();
                listCur.add(curVoter);
                s.setAttribute("voters", listCur);
            }
            String res = getPrintInfoVal(error);
            printWriter.write(res);
        }
    }
}


