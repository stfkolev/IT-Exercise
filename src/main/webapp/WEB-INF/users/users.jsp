<%--

  Created by IntelliJ IDEA.
  User: stefan.kolev
  Date: 18.3.2020 г.
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"/>

<div class="row">
    <h2 style="margin: 0.725rem">Users list</h2>
</div>

<div class="row">
    <div class="col">
        <c:choose>
            <c:when test="${not empty username}">
                <c:choose>
                    <c:when test="${not empty users}">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Username</th>
                                <th scope="col">Email</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.getId()}</td>
                                    <td>${user.getName()}</td>
                                    <td>${user.getEmail()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-warning" role="alert">
                            No users available at the moment!
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning">
                    You have no access to the users!
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>