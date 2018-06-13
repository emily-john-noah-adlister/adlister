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

    @Override
    public List<Category> all() {
        try {
            String sql = "SELECT * FROM categories";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Category> catsFromDB = new ArrayList<>();
            while(rs.next()) {
                long id = rs.getLong("id");
                String category = rs.getString("category");

                Category cat = new Category(id, category);
                catsFromDB.add(cat);
            }
            return catsFromDB;
        } catch (SQLException e) {
            throw new RuntimeException("Could not retrieve categories", e);
        }
    }

}
