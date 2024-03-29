package com.fmb.userservice.services.impl;

import com.fmb.userservice.models.Role;
import com.fmb.userservice.repository.RoleRepository;
import com.fmb.userservice.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {

        Optional<Role> optRole = roleRepository.findByName(name);

        return optRole.orElse(null);
    }
}
