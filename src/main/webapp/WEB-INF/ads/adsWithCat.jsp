<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../partials/navbar.jsp" %>
<%@ include file="../partials/head.jsp" %>

<html>
<head>
    <title>Ads with Categories</title>
</head>
<body>

<h1>Here are your ads under category: ${currentCategory}</h1>
<c:forEach var="ad" items="${adsWithCat}">
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
</c:forEach>

</body>
</html>
