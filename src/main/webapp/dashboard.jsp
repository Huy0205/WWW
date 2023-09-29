<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/29/2023
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-3 bg-light">
                <img src="img/user.png" class="w-100">
                <form action="Controller" method="post" class="col-12">
                    <div class="row">
                        <h3 class="fw-bold text-center">User</h3>
                    </div>
                    <div class="row p-2 ps-0">
                        <label class="fw-bold col-4">Acount ID:</label>
                        <input type="text" disabled class="col-8 border-top-0 border-start-0 border-end-0">
                        <%

                        %>
                        %>
                    </div>
                    <div class="row p-2 ps-0">
                        <label class="fw-bold col-4">Full name:</label>
                        <input type="text" disabled class=" col-8 border-top-0 border-start-0 border-end-0">
                    </div>
                    <div class="row p-2 ps-0">
                        <label class="fw-bold col-4">Password:</label>
                        <input type="text" disabled class=" col-8 border-top-0 border-start-0 border-end-0">
                    </div>
                    <div class="row p-2 ps-0">
                        <label class="fw-bold col-4">Email:</label>
                        <input type="text" disabled class=" col-8 border-top-0 border-start-0 border-end-0">
                    </div>
                    <div class="row p-2 ps-0">
                        <label class="fw-bold col-4">Status:</label>
                        <input type="text" disabled class=" col-8 border-top-0 border-start-0 border-end-0">
                    </div>
                    <div class="row p-2">
                        <button type="submit" name="action" value="Logout" class="form-control  btn btn-danger">Logout</button>
                    </div>
                </form>
            </div>
            <div class="col-9">
                <div class="row">
                    <form action="Controller" method="post" class="col-12 text-end pe-0">
                        <button class="btn btn-success m-2" value="Add">Add</button>
                        <button class="btn btn-warning m-2" value="Update">Update</button>
                        <button class="btn btn-primary m-2" value="Grant">Grant</button>
                        <button class="btn btn-danger m-2 me-0" value="Delete">Delete</button>
                    </form>
                </div>
                <div class="row">
                    <table class="table table-responsive table-hover table-striped table-borderless">
                        <thead>
                        <tr>
                            <th>Account ID</th>
                            <th>Full name</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
