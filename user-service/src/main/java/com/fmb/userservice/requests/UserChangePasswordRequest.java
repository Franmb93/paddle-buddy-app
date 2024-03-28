package com.fmb.userservice.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserChangePasswordRequest {

    private String email;
    private String currentPassword;
    private String newPassword;
}
