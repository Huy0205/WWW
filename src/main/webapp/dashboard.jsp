<%@ page import="java.util.ArrayList" %>
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
    Account loginAccount = (Account) request.getAttribute("loginAccount");
%>
<div class="container">
    <div class="row">
        <div class="col-3 bg-light pt-5 pb-1">
            <div class="row align-items-center justify-content-center p-2">
                <img src="img/user.png" class="w-75" />
            </div>
            <form action="Controller" method="post">
                <div class="row pt-4">
                    <h3 class="fw-bold text-center">Hello <%=loginAccount.getFullName()%></h3>
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
                                value="<%=loginAccount.getId()%>"
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
                                value="<%=loginAccount.getFullName()%>"
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
                                value="<%=loginAccount.getPassword()%>"
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
                                value="<%=loginAccount.getEmail()%>"
                        />
                    </div>
                </div>
                <div class="row pt-5 pb-5 align-items-center">
                    <div class="col-12">
                        <button
                                name="action"
                                value="Logout"
                                class="form-control btn btn-danger"
                        >
                            Logout
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-9">

            <form action="Controller" method="post" class="row ps-3 mb-3">
                <div class="col-12 bg-light p-2">
                    <h3 class="fw-bold text-center">MANAGER USER</h3>
                </div>
                <div class="col-3 bg-light pb-3">
                    <input type="text" name="accountId" class="form-control" placeholder="AccountID" />
                </div>
                <div class="col-3 bg-light pb-3">
                    <input type="text" name="fullName" class="form-control" placeholder="Full name" />
                </div>
                <div class="col-4 bg-light pb-3">
                    <input type="text" name="email" class="form-control" placeholder="Email" />
                </div>
                <div class="col-2 bg-light pb-3">
                    <button   name="action" value="add" class="form-control btn btn-success">Add</button>
                    <input type="hidden" name="loginAccId" class="form-control"   value="<%=loginAccount.getId()%>" placeholder="Full name" />
                </div>
            </form>

            <div class="row ps-3">
                <div class="col-12 bg-light p-2">
                    <h3 class="fw-bold text-center">LIST USER</h3>
                </div>
                <div class="col-12 p-3 pt-0 bg-light">
                    <table class="table table-responsive table-hover">
                        <thead>
                        <tr>
                            <th class="text-center">Full name</th>
                            <th class="text-center">Email</th>
                            <th class="text-center">Phone</th>
                            <th class="text-center">Status</th>
                            <th class="text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts");
                            System.out.println(accounts==null);
                            for (Account account: accounts) {
                        %>
                        <tr>
                            <td><%=account.getFullName()%></td>
                            <td><%=account.getEmail()%></td>
                            <%
                                String phone = account.getPhone()==null?"":account.getPhone();
                            %>
                            <td class="text-center"><%=phone%></td>
                            <td class="text-center"><%=account.getStatus()%></td>
                            <th class="text-end">
                                <form action="Controller" method="post">
                                    <input name="loginAccId" hidden value="<%=loginAccount.getId()%>">
                                    <input name="accIdInList" hidden value="<%=account.getId()%>">
                                    <button name="action" value="grant" class="btn btn-primary">Grant</button>
                                    <button name="action" value="delete" class="btn btn-danger">Delete</button>
                                </form>
                            </th>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
