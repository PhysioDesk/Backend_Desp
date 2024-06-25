package physiodesk.physiodesk_backend.Users.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse<T> {
    @JsonProperty("token")
    private final String token;

    @JsonProperty("user")
    private final T user;


    public AuthResponse(String token, T user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public T getUser() {
        return user;
    }}

