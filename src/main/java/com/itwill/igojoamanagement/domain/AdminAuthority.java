package com.itwill.igojoamanagement.domain;

import com.itwill.igojoamanagement.domain.key.AdminAuthorityId;
import jakarta.persistence.*;

@Entity
@Table(name = "admin_Authority")
public class AdminAuthority {

	@EmbeddedId
	private AdminAuthorityId id;

	@ManyToOne
	@MapsId("adminId")
	@JoinColumn(name = "adminId")
	private Admin admin;

	@ManyToOne
	@MapsId("authorityId")
	@JoinColumn(name = "authorityId")
	private Authority authority;

}