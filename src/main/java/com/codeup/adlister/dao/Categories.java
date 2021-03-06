package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;

import java.util.List;

public interface Categories {
    List<Category> all();

    Long insert(long ad_id, long cat_id);

    List<Category> getAdCategories(Long id);

    void delete(Long id);
}
