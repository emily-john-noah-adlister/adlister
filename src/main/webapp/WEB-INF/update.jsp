
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Update Profile" />
    </jsp:include>
    <title>Title</title>
</head>
<body>
<jsp:include page="partials/navbar.jsp" />
<div class="container">
    <h1>Edit Profile</h1>
    <h2>${error}</h2>
    <form action="/update" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text" placeholder="${username}">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="text" placeholder="${email}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" class="form-control" type="password">
        </div>
        <button type="submit" id="id" name="id" class="btn btn-danger">Update Profile</button>

    </form>

</div>

</body>
</html>
