package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.codeup.adlister.util.Password;
import com.mysql.cj.api.Session;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    StringUtils util = new StringUtils();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);

        if (user == null) {
            request.setAttribute("error", "Invalid username or password.");
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("id", user.getId());
            response.sendRedirect("/profile");


        } else {
            request.getSession().setAttribute("username", username);
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

        }

    }
}



