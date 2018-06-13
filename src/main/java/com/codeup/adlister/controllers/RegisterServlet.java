package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import com.codeup.adlister.util.Password;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    StringUtils util = new StringUtils();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        boolean validAttempt = (util.isNotBlank(username) && util.isNotBlank(email) && util.isNotBlank(password) & password.equals(passwordConfirmation));
        // validate input
        boolean passwordsMatch = password.equals(passwordConfirmation);


        if (!passwordsMatch) {
            request.setAttribute("error","Passwords do not match.");
        }else if (!validAttempt) {
            request.setAttribute("error", "Invalid input. Please try again.");
        }


        if (!validAttempt || !passwordsMatch) {
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);

            return;
        }

        try {
            String existingUser = DaoFactory.getUsersDao().findByUsername(username).getUsername();
            if (existingUser != null) {
                request.setAttribute("errorMessage", "Username already exists. Please use another username.");
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        } catch (NullPointerException e) {

            password = Password.hash(password);
            DaoFactory.getUsersDao().insert(new User(username, email, password));
            response.sendRedirect("/login");
        }
    }
}
