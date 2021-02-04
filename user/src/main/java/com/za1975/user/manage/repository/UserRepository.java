package com.za1975.user.manage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.za1975.user.manage.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByUserId(String userId);
	
	
}
