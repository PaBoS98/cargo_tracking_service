package com.pavlob.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClientToken {

    private String jwt;

    private TokenLevel tokenLevel;

    private String clientId;

    private Date expired;
}
