package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UpdateAdServlet", urlPatterns = "/ads/update")
public class UpdateAdServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long adId = Long.parseLong(request.getParameter("update"));
        System.out.println("Ad id: " + adId);

        String title = request.getParameter("title");
        System.out.println("title is: " + title);
        String description = request.getParameter("description");
        System.out.println("Description is: " + description);

        Ad ad = DaoFactory.getAdsDao().findAd(adId);
        System.out.println(ad.getTitle());

        ad.setTitle(title);

        ad.setDescription(description);


        DaoFactory.getAdsDao().update(ad);
        response.sendRedirect("/profile");
    }


}