package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
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

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    StringUtils util = new StringUtils();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
          String title;
          String description;
          Ad ad = new Ad(
                  (long)request.getSession().getAttribute("id"),
                  title = request.getParameter("title"),
              description = request.getParameter("description")
        );
                boolean blankFields = title.isEmpty() || description.isEmpty();
                boolean titleTooLong = title.length() > 100;


                if(blankFields) {
                    request.getSession().setAttribute("error", "Title and Description required!");

                }else if (titleTooLong){
                    request.getSession().setAttribute("error", "Title is too long");

                }
                    if (blankFields || titleTooLong){
                    request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);

                    return;
                }





    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        boolean validAttempt = (util.isNotBlank(title) && util.isNotBlank(description));


        if(validAttempt) {
            Ad ad = new Ad(
                    (long) request.getSession().getAttribute("id"),
                    request.getParameter("title"),
                    request.getParameter("description")
            );

        DaoFactory.getAdsDao().insert(ad);
        response.sendRedirect("/ads");
        } else {
            request.getSession().setAttribute("title", title);
            request.getSession().setAttribute("description", description);
            response.sendRedirect("/ads/create");
            }
        }
}













//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String title = request.getParameter("title");
//        String description = request.getParameter("description");
//        Category category = new Category(request.getParameter("category"));
//        String url = request.getParameter("url");
//        User user = (User) request.getSession().getAttribute("user");
//
//        request.getSession().setAttribute("title", title);
//        request.getSession().setAttribute("description", description);
//        request.getSession().setAttribute("category", category.getCategory());
//        request.getSession().setAttribute("url", url);
//
//        boolean inputHasErrors = title.isEmpty() || description.isEmpty();
//        boolean titleLengthCheck = title.length() > 240;
//        boolean descriptionLengthCheck = title.length() > 1000;
//        boolean urlLengthCheck = url.length() > 255;
//        boolean categoryLengthCheck = category.getCategory().length() > 100;
//
//
//        if(inputHasErrors) {
//            request.getSession().setAttribute("message", "Title and Description required!");
//        } else if (titleLengthCheck) {
//            request.getSession().setAttribute("message", "Title too long!");
//        } else if (descriptionLengthCheck) {
//            request.getSession().setAttribute("message", "Description too long!");
//        } else if (categoryLengthCheck) {
//            request.getSession().setAttribute("message", "Category too long!");
//        } else if (urlLengthCheck) {
//            request.getSession().setAttribute("message", "Url too long!");
//        }
//        if (inputHasErrors || titleLengthCheck || descriptionLengthCheck || urlLengthCheck || categoryLengthCheck) {
//            response.sendRedirect("/ads/create");
//            return;
//        }
//
//        Ad ad = new Ad(
//                user.getId(),
//                title,
//                description,
//                url
//        );
//
//        Long id = DaoFactory.getAdsDao().insert(ad);
//        Long category_id = DaoFactory.getCategoriesDao().insertCategory(category);
//        DaoFactory.getCategoriesDao().insertCatAndAdId(category_id, id);
//        response.sendRedirect("/ads");
//    }
//
//}
//
//
//
//
//
