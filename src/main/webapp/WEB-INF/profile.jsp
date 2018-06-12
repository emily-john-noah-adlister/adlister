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
        <h1 id="welcome-banner">Welcome, <c:out value="${sessionScope.user.username}"/>!</h1>
    </div>

    <h2>Here are your ads</h2>
    <c:forEach var="ad" items="${userAds}">
        <div class="card">
            <div class="card-header">
                Ad # <c:out value="${ad.id}"/>
            </div>
            <div class="card-body">
                <h5><c:out value="${ad.title}"/></h5>
                <p><c:out value="${ad.description}"/></p>
                <%--<p><c:out value="${ad.userId}"/></p>--%>
                <form action="/ads/edit" method="POST">
                    <button type="submit" id="edit" name="edit" value="${ad.id}" class="btn btn-warning">Edit</button>
                </form>
                <form action="/ads/delete" method="POST">
                    <button type="submit" id="delete" name="delete" value="${ad.id}" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </c:forEach>

</body>
</html>