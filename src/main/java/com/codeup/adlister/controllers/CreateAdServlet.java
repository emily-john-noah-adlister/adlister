package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    StringUtils util = new StringUtils();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("categories", DaoFactory.getCatDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        boolean validAttempt = (util.isNotBlank(title) && util.isNotBlank(description));

        boolean blankFields = title.isEmpty() || description.isEmpty();
        boolean titleTooLong = title.length() > 100;


         try {
             if (blankFields) {
                 request.getSession().setAttribute("error", "Title and Description required!");
                 request.getSession().setAttribute("title", title);
                 request.getSession().setAttribute("description", description);

             } else if (titleTooLong) {
                 request.getSession().setAttribute("error", "Title is too long");
                 request.getSession().setAttribute("title", title);
                 request.getSession().setAttribute("description", description);

             }
             if (blankFields || titleTooLong) {
                 request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
                 return;
             }

             if (validAttempt) {
                 Ad ad = new Ad(
                     (long) request.getSession().getAttribute("id"),
                     request.getParameter("title"),
                     request.getParameter("description")
                 );
                 Long adId = DaoFactory.getAdsDao().insert(ad);
                 String[] checkedCategories = request.getParameterValues("category");

                 for (String category : checkedCategories) {
                     DaoFactory.getCatDao().insert(adId, Long.parseLong(category));
                 }
                 response.sendRedirect("/ads");

             } else {
                 request.getSession().setAttribute("title", title);
                 request.getSession().setAttribute("description", description);
                 response.sendRedirect("/ads/create");
             }
         } catch (NullPointerException e){
             response.sendRedirect("/ads");
         }
    }

}













