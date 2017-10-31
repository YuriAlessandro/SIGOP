package br.ufrn.sigestagios.models;

/**
 * Created by yurialessandro on 27/10/17.
 */

public class User {
    private long userId;
    private long unityId;
    private long photoId;
    private boolean active;
    private String login;
    private String name;
    private String cpf;
    private String email;
    private String photoKey;

    public User(long userId, long unityId, long photoId, boolean active, String login, String name,
                String cpf, String email, String photoKey) {
        this.userId = userId;
        this.unityId = unityId;
        this.photoId = photoId;
        this.active = active;
        this.login = login;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.photoKey = photoKey;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUnityId() {
        return unityId;
    }

    public void setUnityId(long unityId) {
        this.unityId = unityId;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }
}
