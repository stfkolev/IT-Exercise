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

<div class="row">
    <div class="col-md-6">
        <div class="card">
            <h5 class="card-header">Register</h5>
            <div class="card-body">
                <form action="" method="post">

                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                    </div>

                    <button type="submit" name="register" class="btn btn-primary">Register</button>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-6 ml-auto">
        <div class="card">
            <h5 class="card-header">Login</h5>
            <div class="card-body">
                <form action="" method="post">

                    <div class="form-group">
                        <label for="loginUsername">Username</label>
                        <input type="text" class="form-control" id="loginUsername" name="username">
                    </div>

                    <div class="form-group">
                        <label for="loginPassword">Password</label>
                        <input type="password" class="form-control" id="loginPassword" name="password">
                    </div>

                    <button type="submit" name="login" class="btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../includes/footer.jsp"/>