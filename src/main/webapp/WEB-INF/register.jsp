<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Please Register" />
    </jsp:include>
</head>
<body>

<jsp:include page="partials/navbar.jsp" />
<div class="container">
    <jsp:include page="partials/errorMessages.jsp"/>
    <h1 id="login-message">Please Create an Account</h1>
    <form action="/register" method="POST">
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="text" placeholder="${email}">
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text" placeholder="${username}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="confirm_password">Confirm Password</label>
            <input id="confirm_password" name="confirm_password" class="form-control" type="password">
        </div>
        <input type="submit" class="btn btn-primary btn-block" value="Create Account">
    </form>
</div>
</body>
</html>
