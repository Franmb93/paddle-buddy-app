package com.fmb.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserDto {

    private Long id;
    private String name;
    private String email;
}
