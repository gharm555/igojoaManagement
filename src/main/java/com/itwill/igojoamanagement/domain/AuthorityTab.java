package com.itwill.igojoamanagement.domain;

import com.itwill.igojoamanagement.domain.key.AuthorityTabId;
import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "authority_tab")
public class AuthorityTab {
	@EmbeddedId
	private AuthorityTabId id;

	@ManyToOne
	@MapsId("authorityId")
	@JoinColumn(name = "authorityId")
	private Authority authority;

	@ManyToOne
	@MapsId("tabId")
	@JoinColumn(name = "tabId")
	private Tab tab;
}