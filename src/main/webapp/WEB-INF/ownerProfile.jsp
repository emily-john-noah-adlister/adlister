<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
    <jsp:include page="partials/navbar.jsp" >
        <jsp:param name="title" value="Your Profile"></jsp:param>
    </jsp:include>

</head>
<body>

<div class="container">
    <h1 id="welcome-banner"><c:out value="${owner.username}"/></h1>
</div>

<h2> Ads </h2>
<c:forEach var="ad" items="${ownerAds}">
    <div class="card">
        <div class="card-header">
            Ad # <c:out value="${ad.id}"/>
        </div>
        <div class="card-body">

            <a href="<c:url value="/ads/showad?id=${ad.id}"/>"><c:out value="${ad.title}"/></a>
            <p><c:out value="${ad.description}"/></p>

        </div>
    </div>
</c:forEach>

</body>
</html>


