package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.sql.SQLException;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    Ad findAd(Long id);

    List<Ad> resultSearch(String title);

    List<Ad> displayUsersAds(Long userId);

    void delete(Long id);

    void update(Ad ad);

    Ad getAdFromId(Long id);

    List<Ad> adWithCat(String category);

}
