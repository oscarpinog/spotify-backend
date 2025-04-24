package com.seek.codificacion.security;




import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class AuthResponse {
    private String token;
    private String rol;

//    public AuthResponse(String token) {
//        this.token = token;
//    }
   
}
