<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: stefan.kolev
  Date: 18.3.2020 г.
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>


<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>${pageName}</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ${empty pageName ? "active" : ""}">
                <a class="nav-link" href="${pageContext.request.contextPath}">Home <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${not empty username}">
            <li class="nav-item ${pageName eq "users" ? "active" : ""}">
                <a class="nav-link" href="${pageContext.request.contextPath}/users">Users</a>
            </li>
            </c:if>
        </ul>
        <c:choose>
            <c:when test="${not empty username}">
                <ul class="navbar-nav ml-auto">
                   <li class="nav-item ${pageName eq "account" ? "active" : ""}">
                       <a class="nav-link" href="${pageContext.request.contextPath}/account">${username}</a>
                   </li>
                  <li class="nav-item">
                      <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                  </li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item ${pageName eq "account" ? "active" : ""}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/account">Account</a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

<div class="container-fluid" style="margin-top: 0.725rem;">