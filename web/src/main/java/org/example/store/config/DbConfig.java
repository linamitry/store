package org.example.store.config;

public class DbConfig {
    private final String url;
    private final String userName;
    private final String password;

    public DbConfig(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
