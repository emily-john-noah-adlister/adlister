package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="UpdateProfileServlet", urlPatterns = "/update")
public class UpdateProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        long userid = (long) request.getSession().getAttribute("id");
        User user = DaoFactory.getUsersDao().findByUserId(userid);

        try {
            String existingUser = DaoFactory.getUsersDao().findByUsername(username).getUsername();
            if (existingUser != null) {
                String message = "Username already exists";
                request.setAttribute("errorMessage", message);
                request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
            }
        } catch (NullPointerException e) {

            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(Password.hash(password));

            DaoFactory.getUsersDao().replace(user);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("email", email);
            response.sendRedirect("/profile");
        }
    }

}
