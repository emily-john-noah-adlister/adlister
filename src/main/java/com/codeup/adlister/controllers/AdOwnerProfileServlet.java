package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdOwnerProfileServlet", urlPatterns = "/adOwner")
public class AdOwnerProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {


        String ownerUsername = (String) request.getParameter("username");
        User owner = DaoFactory.getUsersDao().findByUsername(ownerUsername);

        List<Ad> ownerAds = DaoFactory.getAdsDao().displayUsersAds(owner.getId());

        request.setAttribute("owner", owner);
        request.setAttribute("ownerAds", ownerAds);
        request.getRequestDispatcher("/WEB-INF/ownerProfile.jsp").forward(request, response);
    }
        response.sendRedirect("/login");
        }

}