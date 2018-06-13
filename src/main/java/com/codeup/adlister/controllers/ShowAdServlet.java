package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.codeup.adlister.dao.DaoFactory.usersDao;

@WebServlet(name = "ShowAdServlet", urlPatterns = "/ads/showad")
public class ShowAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        Ads ad = DaoFactory.getAdsDao();
        req.setAttribute("ad", ad.findAd(id));



        long user_id = Ad.getUserId();
        User user = DaoFactory.getUsersDao().findByUserId(user_id);
        req.setAttribute("user", user);




;


        req.getRequestDispatcher("/WEB-INF/ads/showad.jsp").forward(req, resp);

    }
}







//    Ad show page
//
//        Create a page that shows the information about an individual ad. This page should show all the information about that ad, as well as the information about the user that posted the ad.
//
//        Your ads index page should contain links to each individual ad page.
//
//        You should have one page that displays the information for any arbitrary ad. Consider passing the id of the ad as a parameter in the GET request to this page.