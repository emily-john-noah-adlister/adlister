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
    public void main(String[] args) {
        System.out.println(resultSearch("xbox"));
    }
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

    @Override
    public List<Ad> resultSearch(String title) {
        String query = "SELECT * FROM ads WHERE title LIKE ?";
        String searchTitleWithWildCards = "%" + title + "%";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, searchTitleWithWildCards);
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a ad by title", e);
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

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()){
            ads.add(extractAd(rs));
        }
        return ads;
    }

}
