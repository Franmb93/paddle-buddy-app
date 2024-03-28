package com.fmb.userservice.services;

import com.fmb.userservice.dtos.LoginDto;

public interface LoginService {

    boolean login(LoginDto loginDto);
}
