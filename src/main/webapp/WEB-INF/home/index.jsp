<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stefan.kolev
  Date: 18.3.2020 г.
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>

<jsp:include page="../includes/header.jsp"/>

<div class="row">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Information</h5>
                <p class="card-text">
                    <c:choose>
                        <c:when test="${not empty username}">
                            Welcome back, <strong>${username}</strong>!
                        </c:when>
                        <c:otherwise>
                            This is some example index file.
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>