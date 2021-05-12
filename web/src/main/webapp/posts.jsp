<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"></script>
</head>
<body>
<%
    String path = request.getContextPath();
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=path+"/profile"%>">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=path+"/admin"%>">Admin</a>
                </li>
            </ul>

            <div class="d-flex">
                <button class="btn btn-outline-success"
                        type="button"
                >Sign out</button>
            </div>
        </div>
    </div>
</nav>
        <div class="container">
            <div class="row">
                <div class="col-2">
                    <h1>filter</h1>
                </div>
                <div class="col-7">
                    <h1>posts.jsp</h1>
                </div>
                <div class="col-3">
                    <h1>profile</h1>
                </div>
            </div>
        </div>







</body>
</html>
