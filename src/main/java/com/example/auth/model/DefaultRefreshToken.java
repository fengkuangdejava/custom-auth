package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class DefaultRefreshToken implements RefreshToken {
    private static final long serialVersionUID = 8349970621900575838L;
    private String value;

    @JsonCreator
    public DefaultRefreshToken(String value) {
        this.value = value;
    }

    private DefaultRefreshToken() {
        this((String)null);
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof DefaultRefreshToken)) {
            return false;
        } else {
            DefaultRefreshToken that = (DefaultRefreshToken)o;
            if (this.value != null) {
                if (!this.value.equals(that.value)) {
                    return false;
                }
            } else if (that.value != null) {
                return false;
            }

            return true;
        }
    }
    @Override
    public int hashCode() {
        return this.value != null ? this.value.hashCode() : 0;
    }
}
