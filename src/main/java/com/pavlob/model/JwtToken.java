package com.pavlob.model;

import lombok.Data;

@Data
public class JwtToken {

    private String jwt;
    private String type;
    private TokenLevel tokenLevel;
}
