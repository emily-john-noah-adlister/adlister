<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>


</head>
<body>

    <jsp:include page="partials/navbar.jsp" />
    <div class="container">
        <jsp:include page="/WEB-INF/partials/errorMessages.jsp"/>
        <h1 id="login-message">Please Log In</h1>
        <h2>${message}</h2>
        <form action="/login" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text" placeholder=${username}>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Log In">

        </form>


    </div>

</body>
</html>
