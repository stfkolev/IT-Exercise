<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stefan.kolev
  Date: 18.3.2020 г.
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>

<jsp:include page="../../includes/header.jsp"/>

<c:if test="${not empty successMsg}">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-success text-center" role="alert">${successMsg}</div>
        </div>
    </div>
</c:if>

<c:if test="${not empty user}">
<div class="row">
    <div class="col-md-6 mx-auto">
        <div class="card">
            <h5 class="card-header">Edit your profile</h5>
            <div class="card-body">
                <form action="" method="post" class="justify-content-center">

                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" value="${user.getName()}" disabled>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${user.getEmail()}" required>
                    </div>

                    <button type="submit" name="save" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>
</c:if>

<jsp:include page="../../includes/footer.jsp"/>