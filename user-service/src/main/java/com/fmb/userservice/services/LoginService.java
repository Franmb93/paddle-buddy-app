package com.fmb.userservice.services;

import com.fmb.userservice.dtos.LoginDto;
import com.fmb.userservice.responses.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginDto loginDto);
}
