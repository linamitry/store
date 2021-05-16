<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
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
<jsp:include page="nav.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="<%=request.getContextPath()%>/admin" method="post">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Create </h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
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
                            </div>
                            <div class="modal-footer">
                                <button formaction="login" type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Close
                                </button>
                                <button formaction="register" type="submit" class="btn btn-primary">Save changes
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Add
                </button>
            </div>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <a href="edit?id=<c:out value='${user.id}'/>">
                            Edit
                        </a>
                        <a href="delete?id=<c:out value='${user.id}'/>">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
