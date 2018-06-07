# Adlister project

This is a Java Servlet Application that is a bare-bones craigslist clone.

#Setup

In order to run this application, you'll need to create a 'dao.Config" class. 
It should look like this:

```java
public class dao.Config {
    private String url = "jdbc:mysql://localhost/adlister_db?serverTimezone=UTC&useSSL=false";
    private String user = "USER";
    private String password = "PASSWORD";
    
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
```

Replace 'USER' and 'PASS' with values specific to your environment