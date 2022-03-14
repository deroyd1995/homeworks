package org.example;

public abstract class AbstractUser {

    private String password;
    public String login;

    public AbstractUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
