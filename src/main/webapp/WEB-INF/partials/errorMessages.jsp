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

</c:if>


