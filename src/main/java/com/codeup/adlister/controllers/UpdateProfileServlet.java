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
        User user = DaoFactory.getUsersDao().findByUserId(userid);
        boolean allFields = (util.isNotBlank(username)) || util.isNotBlank(email) || util.isNotBlank(password);
        boolean validation = email.contains("@");

        try {
        String existingUsers = DaoFactory.getUsersDao().findByUsername(username).getUsername();
            if (existingUsers != null) {
                request.setAttribute("error", "This username already exists.");
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("email", email);
                request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);

            } else if (!allFields){
                request.setAttribute("error", "All fields are required.");
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("email", email);
                request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);

            } else if(!validation) {
                request.setAttribute("error", "Email requires @");
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("email", email);
                request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
            }



        } catch (NullPointerException e) {

            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(Password.hash(password));


            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("email", email);
            DaoFactory.getUsersDao().replace(user);
            response.sendRedirect("/profile");


        }
    }

}
