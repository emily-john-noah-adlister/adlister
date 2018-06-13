<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 6/12/18
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${not empty error}">

    <p class="text-danger">${error}</p>

    <%
        request.getSession().removeAttribute("error");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("email");
        request.getSession().removeAttribute("title");
        request.getSession().removeAttribute("description");
        request.getSession().removeAttribute("category");

    %>

</c:if>



