package vn.edu.iuh.fit.lab01.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.lab01.entities.Account;
import vn.edu.iuh.fit.lab01.entities.AccountStatus;
import vn.edu.iuh.fit.lab01.entities.GrantAccess;
import vn.edu.iuh.fit.lab01.entities.Log;
import vn.edu.iuh.fit.lab01.repositories.LogRepository;
import vn.edu.iuh.fit.lab01.services.AccountService;
import vn.edu.iuh.fit.lab01.services.GrantAccessService;
import vn.edu.iuh.fit.lab01.services.RoleService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/Controller")
public class ControllerServlet extends HttpServlet {
    private AccountService accountService = new AccountService();
    private LogRepository logRepository = new LogRepository();
    private GrantAccessService grantAccessService = new GrantAccessService();
    private RoleService roleService = new RoleService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("Login")){
            String email = req.getParameter("Email");
            String pass = req.getParameter("Pass");
            Account account = accountService.get(email, pass);
            if(account == null){
                resp.getWriter().println("Email or Password is wrong!!!");
            }else{
                logRepository.add(new Log(1, account,null,null,"The first login"));
                ArrayList<GrantAccess> grantAccesses = grantAccessService.findGrantByAccountId(account.getId());
                if(grantAccesses.isEmpty()){
                    resp.getWriter().println("You don't have access!!!");
                }else{
                    req.setAttribute("loginAccount", account);
                    for (int i = 0; i < grantAccesses.size(); i++) {
                        System.out.println(grantAccesses.get(i).getRole().getId());
                        if(grantAccesses.get(i).getRole().getId().equalsIgnoreCase("admin")){
                            req.setAttribute("accounts", accountService.get());
                            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
                            return;
                        }
                    }
                    req.getRequestDispatcher("userProfile.jsp").forward(req, resp);
                }
            }
        } else if (action.equalsIgnoreCase("Logout")) {
            logRepository.update(1, LocalDateTime.now());
            resp.sendRedirect("http://localhost:8080/week01/");
        } else if (action.equalsIgnoreCase("add")) {

            String accountId = req.getParameter("accountId");
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");

            String loginAccId = req.getParameter("loginAccId");
            Account loginAccount = accountService.get(loginAccId);

            if(!accountId.equals("")|| !fullName.equals("")||!email.equals("")){
                if(accountService.add(new Account(accountId, fullName, email))){
                    req.setAttribute("loginAccount", loginAccount);
                    req.setAttribute("accounts", accountService.get());
                    req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
                }
            }
        } else if (action.equalsIgnoreCase("grant")) {
            String accountId = req.getParameter("accIdInList");
            req.setAttribute("loginAccId", req.getParameter("loginAccId"));
            req.setAttribute("accountGrant", accountService.get(accountId));
            req.setAttribute("roles", roleService.getAllRoleActive());
            req.getRequestDispatcher("grant-form.jsp").forward(req, resp);
        } else if (action.equalsIgnoreCase("finishGrant")) {
            String accountId = req.getParameter("AccIdGrant");
            String roleId = req.getParameter("roleId");
            int isGrant = Integer.parseInt(req.getParameter("isGrant"));
            String note = req.getParameter("note");
            if(!grantAccessService.checkGrantAccess(accountId, roleId)){
                grantAccessService.add(new GrantAccess(accountService.get(accountId), roleService.get(roleId), isGrant == 1, note));
                String loginAccId = req.getParameter("loginAccId");
                req.setAttribute("loginAccount", accountService.get(loginAccId));
                req.setAttribute("accounts", accountService.get());
                req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
            }else{
                resp.getWriter().println("Grant is exists!!!");
            }
        } else if (action.equalsIgnoreCase("delete")) {
            String loginAccId = req.getParameter("loginAccId");
            Account loginAccount = accountService.get(loginAccId);

            String accountId = req.getParameter("accIdInList");
            System.out.println(accountService.update(accountId, AccountStatus.REMOVE));
            req.setAttribute("loginAccount", loginAccount);
            req.setAttribute("accounts", accountService.get());
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        }
    }
}
