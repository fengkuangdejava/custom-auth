package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonValue;

public interface RefreshToken {
    @JsonValue
    String getValue();
}
