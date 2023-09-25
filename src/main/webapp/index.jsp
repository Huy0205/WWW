<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
  <div style="padding: 168px;" class="d-flex justify-content-center">
    <form action="Controller" method="post" style="width: 350px; box-shadow: 2px 2px 15px 2px #E8E8E8;" class="form-group p-5">
      <h3 class="text-center p-2">Login</h3>
      <div class="p-2">
        <label class="pt-1 pb-1">Email:</label>
        <input type="text" placeholder="Enter email" class="form-control">
      </div>
      <div class="p-2">
        <label class="pt-1 pb-1">Password:</label>
        <input type="password" placeholder="Enter password" class="form-control">
      </div>
      <div class="p-2 mt-3">
        <button class="btn btn-primary form-control">Login</button>
      </div>
    </form>
  </div>
</body>
</html>