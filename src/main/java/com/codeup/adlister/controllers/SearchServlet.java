package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SearchServlet", urlPatterns = "/ads/search")
public class SearchServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        request.setAttribute("adsResults", DaoFactory.getAdsDao().resultSearch(title));
        request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }
}
