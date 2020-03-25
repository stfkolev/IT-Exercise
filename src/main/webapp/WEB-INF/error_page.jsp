<%--
  Created by IntelliJ IDEA.
  User: stefan.kolev
  Date: 18.3.2020 г.
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@page isErrorPage="true" %>
<jsp:include page="includes/header.jsp"/>

<div class="alert alert-danger" role="alert">
    <strong>Error: </strong><br/>
    <%= exception.getMessage() %>

    <br/><br/>
    <strong>Stack Trace: </strong>
    <br/>
    <%= exception.getStackTrace() %>
</div>

<jsp:include page="includes/footer.jsp"/>