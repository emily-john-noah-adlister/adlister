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
        boolean allFields = (util.isNotBlank(username)) || util.isNotBlank(email) || util.isNotBlank(password);
        boolean validEmail = email.contains("@");
        User user = DaoFactory.getUsersDao().findByUserId(userid);

        try {

                if(username.equals(user.getUsername())){
                    request.setAttribute("error", "Username exists");
                    response.sendRedirect("/update");
                    return;
                }
                if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                    request.setAttribute("error", "All fields must be filled");
                    response.sendRedirect("/update");
                    return;
                }


            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(Password.hash(password));

            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("id", user.getId());

            DaoFactory.getUsersDao().replace(user);

            request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request,response);

        } catch (NullPointerException e) {

            response.sendRedirect("/profile");
        }
    }

}
