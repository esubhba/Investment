package com.za1975.user.manage.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user",indexes = @Index(columnList = "user_id",unique = true))
public class User {
	
	@Id
	@GeneratedValue(generator = "UID")
	@GenericGenerator(name = "UID",strategy = "org.hibernate.id.UUIDGenerator")	
	@Column(name="id")
	private String uid;
	
	@Column(name="user_id",nullable = false,updatable = false)
	private String userId;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="phone",nullable = false,unique = true)
	private Long phone;
	
	@Column(name="email",nullable = false,unique = true)
	private String email;
	
	@ColumnDefault("now()")
	@Column(name="created_on",nullable = false,updatable = false,insertable = false)
	private LocalDateTime createdOn;
	
	@ColumnDefault("now()")
	@Column(name="updated_on",nullable = false,insertable = false)
	private LocalDateTime updatedOn;
	
	@Column(name="last_logged_on")
	private LocalDateTime lastLoggedIn;
	
	
	
	

}
