package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("categories", DaoFactory.getCatDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Ad ad = new Ad(
            (long)request.getSession().getAttribute("id"),
            request.getParameter("title"),
            request.getParameter("description")
        );

        Long adId = DaoFactory.getAdsDao().insert(ad);

        System.out.println("Ad id is: " + adId);

        ArrayList<String> checkedCategories = new ArrayList<>();

        if(request.getParameter("category") == null) {
            System.out.println("checkbox is not checked");
        }
        else {
            checkedCategories.add(request.getParameter("category"));
        }


        for (String category : checkedCategories) {
            System.out.println("Categories are: " + category);
//            DaoFactory.getCatDao().insert(adId, category.getId());
        }
        response.sendRedirect("/ads");
    }
}
