<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Index</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"></script>
</head>
<body>
<%
    String path = request.getContextPath();
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#">Posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=path+"/profile"%>">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=path+"/admin"%>">Admin</a>
                </li>
            </ul>

            <c:if test="${loggedIn == true}">
                <form class="d-flex" method="post">
                    <button class="btn btn-outline-success"
                            type="submit"
                            formaction="logout"
                    >Sign out
                    </button>
                </form>
            </c:if>
            <c:if test="${loggedIn == false}">
                <form class="d-flex">
                    <button class="btn btn-outline-success"
                            type="button"
                            data-bs-toggle="modal"
                            data-bs-target="#exampleModal">
                            Sign in
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</nav>
</body>
</html>
