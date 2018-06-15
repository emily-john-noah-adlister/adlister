# create user with permissions on database;

# create user with permissions on database;

CREATE DATABASE IF NOT EXISTS adlister_db;

USE adlister_db;

CREATE TABLE IF NOT EXISTS users(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  email VARCHAR(100),
  password VARCHAR(100),
  username VARCHAR(100),
  PRIMARY KEY (id),
  UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS ads(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(100),
  description TEXT,
  image_url VARCHAR(500),
  user_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS categories(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  category VARCHAR(100),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ad_category(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  ad_id INT UNSIGNED NOT NULL,
  category_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (ad_id) REFERENCES ads(id),
  FOREIGN KEY (category_id) REFERENCES categories(id)
);
