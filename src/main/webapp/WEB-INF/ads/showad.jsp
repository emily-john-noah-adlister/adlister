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

        <div class="card-footer">


Ad Owner: <a href="/adOwner?username=<c:out value='${user.username}'/>"><c:out value="${user.username}"/></a>


       
    
   <p>Categories:
        <c:forEach var="category" items="${categories}">
            <a href="<c:url value="/ads/category?cat=${category.category}"/>">  ${category.category}  </a>
        </c:forEach>
    </p>

    </div>
</div>

</body>
</html

