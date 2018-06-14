<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/partials/head.jsp">
    <jsp:param name="title" value="View a single ad" />
</jsp:include>
<%@ include file="../partials/navbar.jsp" %>
<body>

<div class="card">
    <div class = "card-header">
    <h1><c:out value="${ad.title}"/></h1>
</div>
    <div class="card-body">
    <p><c:out value="${ad.description}"/></p>
    </div>
        <div class="card-footer"</div>
    <p>User Id <c:out value="${user.id}"/></p>
    <p>Username: <c:out value="${user.username}"/></p>
    <p>Categories:
        <c:forEach var="category" items="${categories}">
        ${category.category}
        </c:forEach>
    </p>

    </div>





</div>

</body>
</html

