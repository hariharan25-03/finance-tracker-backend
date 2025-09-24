package org.example.constant;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public enum FinanceResponse {

    API_KEY("your_api_key_here");
    private final String value;

    public String getValue() {
        return this.value;
    }
}
