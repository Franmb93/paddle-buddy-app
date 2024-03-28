package com.fmb.userservice.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private boolean success;
    private String message;
    private String _token;
}
