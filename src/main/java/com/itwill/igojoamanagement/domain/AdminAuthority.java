package com.itwill.igojoamanagement.domain;

import com.itwill.igojoamanagement.domain.key.AdminAuthorityId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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