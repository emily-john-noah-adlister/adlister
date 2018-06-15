<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../partials/navbar.jsp" %>
<%@ include file="../partials/head.jsp" %>
<html>
<head>


</head>
<body>



<c:forEach var="ad" items="${ads}">
    <div class="card">
        <div class="card-body">
            <a href="<c:url value="/ads/showad?id=${ad.id}"/>"><c:out value="${ad.title}"/></a>
        </div>
    </div>
</c:forEach>


</body>
</html>



