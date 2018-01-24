package com.gsk.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsk.quiz.model.Role;
import com.gsk.quiz.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role findByName(String roleName) {
		return roleRepository.findByName(roleName);
	}

}
