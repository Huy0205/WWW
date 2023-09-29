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

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/Controller")
public class ControllerServlet extends HttpServlet {
    private AccountRepository accountRepository;
    private LogRepository logRepository;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        accountRepository = new AccountRepository();
        logRepository = new LogRepository();
        if(action.equalsIgnoreCase("Login")){
            String email = req.getParameter("Email");
            String pass = req.getParameter("Pass");
            Account account = accountRepository.get(email, pass);
            if(account == null){
                resp.getWriter().println("Email or Password is wrong!!!");
            }else{
                logRepository.add(new Log(1, account,null,null,"The first login"));
                req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
            }
        } else if (action.equalsIgnoreCase("Logout")) {
            logRepository.update(1, LocalDateTime.now());
            resp.sendRedirect("http://localhost:8080/week01/");
        }
    }
}
