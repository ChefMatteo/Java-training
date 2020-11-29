package it.treebackend.exercisespring.models;

import java.util.Objects;

public class EncryptedUser {
    private String username;
    private int encryptedPassword;

    public EncryptedUser(String username, String password) {
        this.username = username;
        encryptedPassword = password.hashCode();
    }

    //getter
    public String getUsername() {
        return username;
    }
    public int getEncryptedPassword() {
        return encryptedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncryptedUser that = (EncryptedUser) o;
        return Objects.equals(username, that.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
