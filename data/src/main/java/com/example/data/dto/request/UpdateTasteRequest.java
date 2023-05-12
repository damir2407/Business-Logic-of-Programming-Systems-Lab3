package com.example.data.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTasteRequest {
    private String taste;

    public UpdateTasteRequest(String taste) {
        this.taste = taste;
    }
}
