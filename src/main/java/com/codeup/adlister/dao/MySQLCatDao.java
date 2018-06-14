package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCatDao implements Categories {
    private Connection connection = null;

    public MySQLCatDao(Config config){
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    public List<Category> returnCatList(ResultSet rs) throws SQLException {
        List<Category> catsFromDB = new ArrayList<>();
        while(rs.next()) {
            String category = rs.getString("category");

            Category cat = new Category(category);
            catsFromDB.add(cat);
        }

        return catsFromDB;
    }

    @Override
    public List<Category> all() {
        try {
            String sql = "SELECT * FROM categories";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            return returnCatList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Could not retrieve categories", e);
        }
    }



    @Override
    public Long insert(long adId, long catId) {
        try {
            String sql = "INSERT INTO ad_category(ad_id, category_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, adId);
            stmt.setLong(2, catId);
            stmt.executeUpdate();
            ResultSet rs = stmt. getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Could not perform insert query");
        }
    }

    public List<Category> getAdCategories(Long Adid) {
        String sql = "SELECT c.category " +
                "FROM categories c " +
                "JOIN ad_category ac ON c.id = ac.category_id " +
                "JOIN ads a ON ac.ad_id = a.id WHERE a.id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, Adid);
            ResultSet rs = stmt.executeQuery();
            return returnCatList(rs);
        } catch(SQLException e) {
            throw new RuntimeException("Could not get categories", e);
        }

    }


}


