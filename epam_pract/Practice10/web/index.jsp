<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Part1_1</title>
      <style type="text/css">
          TABLE {
              border-collapse: collapse;
              width: 300px;
          }
          TH {
              background: #fc0;
              text-align: center;
          }
          TD {
              background: #fff;
              text-align: center;
              min-width: 20px;
          }
          TH, TD {
              border: 1px solid black;
              padding: 4px;
          }
      </style>
  </head>
  <body>

  <table>
    <% for (int j = 0; j <= 9; j++) { %>
      <% if(j == 0) { %>
      <th> </th>
      <% continue;} %>
      <th><%=j %></th>
      <% } %>
      <% for (int j = 1; j <= 9; j++) { %>
    <tr>
        <td style="font-weight: bold; background: #fc0;"><%=j %></td>
        <% for (int i = 1; i <= 9; i++) { %>
        <td><%=j * i%></td>
        <% } %>
    </tr>
    <% } %>
  </table>

  </body>
</html>
