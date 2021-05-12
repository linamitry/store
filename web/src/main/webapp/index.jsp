<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
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
                    <a class="nav-link" href="<%=path+"/posts.jsp"%>">Posts</a>
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
                        data-bs-toggle="modal"
                        data-bs-target="#exampleModal"
                >Sign in</button>
            </div>
        </div>
    </div>
</nav>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Login or register</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form class="modal-body" method="post" >

                <div class="form-floating mb-3">
                    <input type="email"
                           class="form-control"
                           id="floatingInput"
                           placeholder="name@example.com"
                           name="email">
                    <label for="floatingInput">Email</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password"
                           class="form-control"
                           id="floatingPassword"
                           placeholder="password"
                           name="password">
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="text-end">
                    <button formaction="login" type="submit" class="btn btn-dark me-2">Sign in</button>
                    <button formaction="register" type="submit" class="btn btn-warning">Registration</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container">
    <h1>index.jsp</h1>
</div>
</body>
</html>
