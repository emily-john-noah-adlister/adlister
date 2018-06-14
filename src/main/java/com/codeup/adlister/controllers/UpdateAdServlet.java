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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long adId = Long.parseLong(request.getParameter("update"));

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        boolean blankFields = title.isEmpty() || description.isEmpty();
        boolean titleTooLong = title.length() > 100;

        if(blankFields) {
            request.getSession().setAttribute("error", "Title and Description required!");

        }else if (titleTooLong){
            request.getSession().setAttribute("error", "Title is too long");

        }
        if (blankFields || titleTooLong){
            request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);

            return;
        }

        Ad ad = DaoFactory.getAdsDao().findAd(adId);
        System.out.println(ad.getTitle());

        ad.setTitle(title);

        ad.setDescription(description);

        request.getSession().setAttribute("editableAd.title", title);
        request.getSession().setAttribute("description", description);
        DaoFactory.getAdsDao().update(ad);
        response.sendRedirect("/profile");


    }
}