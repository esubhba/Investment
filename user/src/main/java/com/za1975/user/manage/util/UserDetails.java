package com.za1975.user.manage.util;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	private static final long serialVersionUID = 1L;
	private List<? extends GrantedAuthority> authorities;
	private String password;
	private String userId;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private String uid;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.authorities;
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "UserDetails [authorities=" + authorities + ", password=" + password + ", userId=" + userId
				+ ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + ", uid=" + uid + "]";
	}

}
