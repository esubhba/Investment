package com.za1975.user.manage.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.za1975.user.manage.entity.User;
import com.za1975.user.manage.models.Credentials;

public interface UserService extends UserDetailsService{
	
	public Optional<User> getUserById(String uid);
	public User createUser(User user);
	public User updateDetails(User entity);
	public Optional<User> getCredential(Credentials crd);
	public boolean isLoggedIn(String userId);	
	public Page<User> getAll(Pageable pageable);

}
