<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Task3_1</title>
    <meta charset="UTF-8">
</head>
<body>

    <form action="Task3_1" method="post">
        <input type="text" name="name"/>
        <input type="submit" name="submit">
    </form>

    <c:forEach var="name" items="${sessionScope.list}">
        <p>${name}</p>
    </c:forEach>

   <a href="Task3_1?name=cleanList&submit=Отправить">remove</a>

</body>
</html>
