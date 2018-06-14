package com.codeup.adlister.dao;

public class DaoFactory {
    private static Ads adsDao;
    public static Users usersDao;
    public static Categories catDao;
    private static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static Categories getCatDao() {
        if (catDao == null) {
            catDao = new MySQLCatDao(config);
        }
        return catDao;
    }
}
