package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config){
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


    @Override
    public List<Ad> all(){
        try {
            String sql = "SELECT * FROM ads";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            List<Ad> adsFromDB = createAdsFromDB(resultSet);
            return adsFromDB;
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }

    private List<Ad> createAdsFromDB(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while(rs.next()) {
            long id = rs.getLong("id");
            long user_id = rs.getLong("user_id");
            String title = rs.getString("title");
            String description = rs.getString("description");

            Ad ad = new Ad(id, user_id, title, description);
            ads.add(ad);
        }
        return ads;

    }

    @Override
    public Long insert(Ad ad) {
        try {
            String sql = createInsertQuery(ad);

            PreparedStatement stmt = connection.prepareStatement(sql);

            System.out.println("Preparing to run query: " + sql);

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Could not create ad.", e);
        }
    }

    private String createInsertQuery(Ad ad) {
        String sql = "INSERT INTO ads(user_id, title, description) VALUES(%d, '%s', '%s')";
        return String.format(
                sql,
                ad.getUserId(),
                ad.getTitle(),
                ad.getDescription()
        );
    }

//    public void update(Ad ad) {
//        try {
//            String sql = "UPDATE ads SET user_id=?, title=?, description=? WHERE id=?";
//            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setLong(1, ad.getUserId());
//            statement.setString(2,ad.getTitle());
//            statement.setString(3, ad.getDescription());
//        } catch (SQLException e) {
//            throw new RuntimeException("Could not update ad.", e);
//        }
//    }

//    public List<Ad> search(String searchTerm) throws SQLException {
//        String sql = "SELECT * FROM ads WHERE title LIKE '%?%' OR description LIKE '%?%'";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        stmt.setString(1, searchTerm);
//        stmt.setString(2, searchTerm);
//
//        stmt.executeUpdate();
//
//        ResultSet rs = stmt.getResultSet();
//
//        return createAdsFromDB(rs);

//    }
}
