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

@WebServlet(name="UpdateProfileServlet", urlPatterns = "/update")
public class UpdateProfileServlet extends HttpServlet {
    StringUtils util = new StringUtils();
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
        boolean allFields = (util.isNotBlank(username)) && util.isNotBlank(email) && util.isNotBlank(password);
        User user = DaoFactory.getUsersDao().findByUserId(userid);

        try {
            String existingUser = DaoFactory.getUsersDao().findByUsername(username).getUsername();
            if (existingUser != null) {
                request.setAttribute("error", "This username already exists.");
            } else if (!allFields){
                request.setAttribute("error", "All fields are required.");

            }

            if (!allFields || existingUser != null){

                request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
            }




        } catch (NullPointerException e) {

            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(Password.hash(password));

            DaoFactory.getUsersDao().replace(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        }
    }

}
