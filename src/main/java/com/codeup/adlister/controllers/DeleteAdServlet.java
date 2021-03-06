package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteAdServlet", urlPatterns = "/ads/delete")
public class DeleteAdServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        long adId = Long.parseLong(request.getParameter("delete"));
        DaoFactory.getCatDao().delete(adId);
        DaoFactory.getAdsDao().delete(adId);
        response.sendRedirect("/profile");
    }
}

