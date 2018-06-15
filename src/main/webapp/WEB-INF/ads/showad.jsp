<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 6/11/18
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="View a single ad" />
</jsp:include>
<body>

<div class="card">
    <div class = "card-header">
    <h1><c:out value="${ad.title}"/></h1>
</div>
    <div class="card-body">
    <p><c:out value="${ad.description}"/></p>
    </div>
        <div class="card-footer">


Ad Owner: <a href="/adOwner?username=<c:out value='${user.username}'/>"><c:out value="${user.username}"/></a>

    </div>





</div>

</body>
</html

