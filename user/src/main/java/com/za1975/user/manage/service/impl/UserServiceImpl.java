package com.za1975.user.manage.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.za1975.user.manage.entity.User;
import com.za1975.user.manage.models.Credentials;
import com.za1975.user.manage.repository.UserRepository;
import com.za1975.user.manage.service.UserService;
import com.za1975.user.manage.util.UserDetails;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> getUserById(String uid) {
		
		return userRepository.findById(uid);
	}

	@Override
	public User createUser(User user) {
		log.info(user);
		return userRepository.save(user);
	}

	@Override
	public User updateDetails(User entity) {
		log.info("Update Details: {0}",entity);
		entity.setUpdatedOn(LocalDateTime.now());
		return userRepository.save(entity);
	}

	@Override
	public Optional<User> getCredential(Credentials crd) {		
			return userRepository.findByUserId(crd.getUsername());
		
	}

	@Override
	public boolean isLoggedIn(String uid) {
		
		User entity=userRepository.findById(uid).get();
		entity.setLastLoggedIn(LocalDateTime.now());
		userRepository.save(entity);		
		return true;
	}

	@Override	
	public Page<User> getAll(Pageable pageable) {
		
		return userRepository.findAll(pageable);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userO=userRepository.findByUserId(username);
		if (!userO.isPresent())
			userO=userRepository.findByEmail(username);
		if(!userO.isPresent())
			throw new UsernameNotFoundException(username);
		User entity=userO.get();
		return UserDetails.builder()
		.accountNonExpired(true)
		.accountNonLocked(true)
		.authorities(new ArrayList<>())
		.credentialsNonExpired(true)
		.enabled(true)
		.password(entity.getPassword())
		.uid(entity.getUid())
		.userId(username)
		.build();
		
	}

}
