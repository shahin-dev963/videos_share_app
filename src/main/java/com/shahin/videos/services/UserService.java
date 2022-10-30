package com.shahin.videos.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shahin.videos.entities.User;
import com.shahin.videos.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDto registrationDto);
	
}
