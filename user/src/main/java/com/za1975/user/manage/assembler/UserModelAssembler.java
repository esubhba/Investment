package com.za1975.user.manage.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.za1975.user.manage.controller.UserController;
import com.za1975.user.manage.entity.User;
import com.za1975.user.manage.models.UserModel;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

	@Autowired
	private Argon2PasswordEncoder argon2PasswordEncoder;
	
	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
		
	}

	@Override
	public UserModel toModel(User entity) {
		
		return UserModel.builder()
				.email(entity.getEmail())
				.password(entity.getPassword())
				.userId(entity.getUserId())
				.phoneNumber(entity.getPhone())
				.build()
				.add(linkTo(methodOn(UserController.class, entity.getUid())
						.getDetails(entity.getUid()))
						.withSelfRel()
						.andAffordance(afford(methodOn(UserController.class)
								.updateDetails(null, entity.getUid())
								))
						.andAffordance(afford(methodOn(UserController.class)
								.updatePassword(entity.getUid(), null)
						)));
				
						
				
	}

	public User toEntity(UserModel model) {
		User entity=new User();
		entity.setEmail(model.getEmail());
		entity.setPassword(argon2PasswordEncoder.encode(model.getPassword()));
		entity.setPhone(model.getPhoneNumber());
		entity.setUserId(model.getUserId());
		return entity;
	}

}
