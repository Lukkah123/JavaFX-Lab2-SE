package org.example;

public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private AccountModel loggedIn;

    public void setLoggedIn(AccountModel loggedIn) {
        this.loggedIn = loggedIn;
    }

    public AccountModel getLoggedIn() {
        return loggedIn;
    }
}