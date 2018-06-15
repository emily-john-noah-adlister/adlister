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

@WebServlet(name = "AdsWithCategoryServlet", urlPatterns = "/ads/category")
public class AdsWithCategoryServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("cat");
        request.setAttribute("currentCategory", category);

        System.out.println("category is: " + category);

        request.setAttribute("adsWithCat", DaoFactory.getAdsDao().adWithCat(category));

        long user_id = Ad.getUserId();
        User user = DaoFactory.getUsersDao().findByUserId(user_id);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/ads/adsWithCat.jsp").forward(request, response);
    }
}

