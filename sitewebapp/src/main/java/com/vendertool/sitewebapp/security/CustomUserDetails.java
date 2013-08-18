package com.vendertool.sitewebapp.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountStatusEnum;

public class CustomUserDetails implements UserDetails, Serializable {

	private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

	private Account account;
	
	public CustomUserDetails(Account account) {
		this.account = account;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		logger.debug("Getting authorities");

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority role = new SimpleGrantedAuthority(account.getRole().getValue());

        authorities.add(role);

        return authorities;
	}

	@Override
	public String getPassword() {
        logger.debug("Calling getPassword()");
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getEmailId();
	}

	@Override
	public boolean isAccountNonExpired() {
		AccountStatusEnum status = account.getAccountStatus();
		return (status != AccountStatusEnum.CLOSED);
	}

	@Override
	public boolean isAccountNonLocked() {
		AccountStatusEnum status = account.getAccountStatus();
		return (status != AccountStatusEnum.SUSPENDED);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		AccountStatusEnum status = account.getAccountStatus();
		return (status != AccountStatusEnum.PASSWORD_EXPIRED);
	}

	@Override
	public boolean isEnabled() {
		AccountStatusEnum status = account.getAccountStatus();
		return (!isAccountNonExpired() && (status != AccountStatusEnum.DELINQUENT));
	}
	
	public Account getAccount() {
		return account;
	}

	public String getPasswordSalt() {
		return account.getPasswordSalt();
	}
	
	private static final long serialVersionUID = -3507911827432478943L;
}