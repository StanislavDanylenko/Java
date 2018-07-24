<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Part2_1</title>
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

<table border='1'>
    <c:forEach var="j" begin="0" end="9">
        <c:if test="${j == 0}">
        <th> </th>
        </c:if>
        <c:if test="${j > 0}">
            <th>${j}</th>
        </c:if>
    </c:forEach>
    <c:forEach var="j" begin="1" end="9">
    <tr>
        <td style="font-weight: bold; background: #fc0;">${j}</td>
        <c:forEach var="i" begin="1" end="9">
        <td>${j * i}</td>
        </c:forEach>
    </tr>
    </c:forEach>
</table>

</body>
</html>
