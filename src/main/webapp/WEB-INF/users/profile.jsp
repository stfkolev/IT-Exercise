<%--
  Created by IntelliJ IDEA.
  User: stefan.kolev
  Date: 18.3.2020 г.
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>

<jsp:include page="../includes/header.jsp"/>

<div class="row">
    <div class="col-md-12">
        <a href="account/edit" class="btn btn-primary float-right" style="margin-bottom: .725rem">Edit profile</a>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="jumbotron">
            <h1 class="display-4">Hello, ${user.getName()}!</h1>
            <p class="lead">This is an example text you can use</p>
            <hr class="my-4">
            <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
            <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>