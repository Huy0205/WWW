package vn.edu.iuh.fit.lab01.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.lab01.entities.Account;
import vn.edu.iuh.fit.lab01.entities.Log;
import vn.edu.iuh.fit.lab01.repositories.AccountRepository;
import vn.edu.iuh.fit.lab01.repositories.LogRepository;
import vn.edu.iuh.fit.lab01.services.AccountService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/Controller")
public class ControllerServlet extends HttpServlet {
    private AccountService accountService;
    private LogRepository logRepository;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        accountService = new AccountService();
        logRepository = new LogRepository();
        if(action.equalsIgnoreCase("Login")){
            String email = req.getParameter("Email");
            String pass = req.getParameter("Pass");
            Account account = accountService.get(email, pass);
            if(account == null){
                resp.getWriter().println("Email or Password is wrong!!!");
            }else{
                logRepository.add(new Log(1, account,null,null,"The first login"));
                req.setAttribute("loginAccount", account);
                req.setAttribute("accounts", accountService.getAll());
                req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
            }
        } else if (action.equalsIgnoreCase("Logout")) {
            logRepository.update(1, LocalDateTime.now());
            resp.sendRedirect("http://localhost:8080/week01/");
        } else if (action.equalsIgnoreCase("add")) {
            String accountId = req.getParameter("accountId");
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");

            String loginAccId = req.getParameter("loginAccId");
            String loginAccFullName = req.getParameter("loginAccFullName");
            String loginAccPass = req.getParameter("loginAccPass");
            String loginAccEmail = req.getParameter("loginAccEmail");
            Account loginAccount = new Account(loginAccId, loginAccFullName, loginAccPass, loginAccEmail);
            if(!accountId.equals("")|| !fullName.equals("")||!email.equals("")){
                if(accountService.add(new Account(accountId, fullName, email))){
                    req.setAttribute("loginAccount", loginAccount);
                    req.setAttribute("accounts", accountService.getAll());
                    req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
                }
            }
        }
    }
}
