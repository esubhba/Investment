package com.za1975.user.manage.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonRootName(value = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "users",itemRelation = "user")
public class UserModel extends RepresentationModel<UserModel>{

	@JsonIgnore
	private String uid;
	@NotNull
	private String userId;	
	private String password;
	@Email
	private String email;
	private Long phoneNumber;
	
}
