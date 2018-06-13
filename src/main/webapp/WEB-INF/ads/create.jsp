<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../partials/navbar.jsp" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
    <div class="container">
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" placeholder="${title}">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text" placeholder="${description}"></textarea>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</body>
</html>
