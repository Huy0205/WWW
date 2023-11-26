<%@ page import="vn.edu.iuh.fit.lab01.entities.Account" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>Document</title>
</head>

<body>
<%
    Account account = (Account) request.getAttribute("loginAccount");
%>
<div class="container">
    <div class="row bg-light p-5">
        <div class="col-5 text-center align-self-center">
            <img src="img/user.png" class="w-50" />
            <h3>Hello <%=account.getFullName()%></h3>
            <div class="w-100 d-flex justify-content-center pt-2 pb-2">
                <div class="w-25">
                    <button
                            name="action"
                            value="Logout"
                            class="form-control btn btn-danger"
                    >
                        Logout
                    </button>
                </div>
            </div>
        </div>
        <form class="col-7" action="Controller" method="post">
            <div class="row pt-4">
                <h3 class="fw-bold text-center">PROFILE</h3>
            </div>

            <div class="row pt-4 align-items-center">
                <div class="col-4">
                    <label class="fw-bold form-label">Acount ID:</label>
                </div>
                <div class="col-8">
                    <input
                            type="text"
                            name="loginAccId"
                            disabled
                            class="form-control"
                            value="<%=account.getId()%>"
                    />
                </div>
            </div>
            <div class="row pt-4 align-items-center">
                <div class="col-4">
                    <label class="fw-bold form-label">Full name:</label>
                </div>
                <div class="col-8">
                    <input
                            type="text"
                            name="loginAccFullName"
                            disabled
                            class="form-control"
                            value="<%=account.getFullName()%>"
                    />
                </div>
            </div>
            <div class="row pt-4 align-items-center">
                <div class="col-4">
                    <label class="fw-bold form-label">Password:</label>
                </div>
                <div class="col-8">
                    <input
                            type="text"
                            name="loginAccPass"
                            disabled
                            class="form-control"
                            value="<%=account.getPassword()%>"
                    />
                </div>
            </div>
            <div class="row pt-4 align-items-center">
                <div class="col-4">
                    <label class="fw-bold form-label">Email:</label>
                </div>
                <div class="col-8">
                    <input
                            type="text"
                            name="loginAccEmail"
                            disabled
                            class="form-control"
                            value="<%=account.getEmail()%>"
                    />
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
