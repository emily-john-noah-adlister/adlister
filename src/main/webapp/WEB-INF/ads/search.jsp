<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Update Profile" />
    </jsp:include>
    <title>${title}</title>
</head>
<body>
<jsp:include page="../partials/navbar.jsp" />
<c:forEach var="ad" items="${adsResults}">
    <div class="card">
        <div class="card-header">
            Ad # <c:out value="${ad.id}"/>
        </div>
        <div class="card-body">
            <h5><c:out value="${ad.title}"/></h5>
            <p><c:out value="${ad.description}"/></p>
            <p><c:out value="${ad.userId}"/></p>
        </div>
    </div>
</c:forEach>
</body>
</html>
