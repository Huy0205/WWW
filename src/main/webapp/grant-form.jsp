<%@ page import="vn.edu.iuh.fit.lab01.entities.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.lab01.entities.GrantAccess" %>
<%@ page import="vn.edu.iuh.fit.lab01.entities.Role" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Grant</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
  />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%
  Account account = (Account) request.getAttribute("accountGrant");
%>
<div class="d-flex justify-content-center align-items-center mt-5">
  <div class="w-25 shadow p-3 mt-5">
    <h2 class="text-center pb-3">Grant access</h2>
    <form action="Controller" method="post">
      <div class="row">
        <div class="col-4">
          <label class="fw-bold form-label">Acount ID:</label>
        </div>
        <div class="col-8">
          <input
                  type="text"
                  disabled
                  value="<%=account.getId()%>"
                  class="form-control border-top-0 border-start-0 border-end-0"
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
                  disabled
                  value="<%=account.getFullName()%>"
                  class="form-control border-top-0 border-start-0 border-end-0"
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
                  disabled
                  value="<%=account.getEmail()%>"
                  class="form-control border-top-0 border-start-0 border-end-0"
          />
        </div>
      </div>
      <div class="row pt-4 align-items-center">
        <div class="col-4">
          <label class="fw-bold form-label">Grant:</label>
        </div>
        <div class="col-8">
          <select name="roleId" class="form-select" aria-label="Default select example">
            <option selected>Open this select menu</option>
            <%
              ArrayList<Role> roles = (ArrayList<Role>) request.getAttribute("roles");
              for (int i = 0; i < roles.size(); i++) {
                %>
                  <option value="<%=roles.get(i).getId()%>"><%=roles.get(i).getName()%></option>
                <%
              }
            %>
            <option value="">Other</option>
          </select>
        </div>
      </div>
      <div class="row pt-4 align-items-center">
        <div class="col-4">
          <label class="fw-bold form-label">Status:</label>
        </div>
        <div class="col-8">
          <select name="isGrant" class="form-select" aria-label="Default select example">
            <option selected>Open this select menu</option>
            <option value="1">Enable</option>
            <option value="0">Disable</option>
          </select>
        </div>
      </div>
      <div class="row pt-4 align-items-center">
        <div class="col-4">
          <label class="fw-bold form-label">Note </label>
        </div>
        <div class="col-8">
              <textarea
                      name="note"
                      class="form-control"
                      id="exampleFormControlTextarea1"
                      rows="3"
              ></textarea>
        </div>
      </div>
      <div class="row pt-3 align-items-center">
        <div class="col-12">
          <input name="loginAcc" type="hidden" value="<%=request.getAttribute("loginAccId")%>">
          <input name="AccIdGrant" type="hidden" value="<%=account.getId()%>">
          <button
                  type="submit"
                  name="action"
                  value="finishGrant"
                  class="form-control btn btn-success"
          >
            Finish
          </button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>
