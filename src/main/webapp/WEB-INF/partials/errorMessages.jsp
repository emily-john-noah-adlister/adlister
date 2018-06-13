<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 6/12/18
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${not empty error}">
    <div class="panel panel-danger">
        <div class="panel-heading">
            <h3 class="panel-title">Uh oh...</h3>
        </div>
        <div class="panel-body">
                ${error}
        </div>
    </div>
</c:if>


