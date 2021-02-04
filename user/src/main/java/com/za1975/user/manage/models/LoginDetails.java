package com.za1975.user.manage.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDetails {
	
	private LocalDateTime loggedOn;
	private LocalDateTime lastLogin;

}
