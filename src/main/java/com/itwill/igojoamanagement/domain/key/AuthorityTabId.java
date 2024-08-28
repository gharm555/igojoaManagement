package com.itwill.igojoamanagement.domain.key;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AuthorityTabId implements Serializable {
	private Integer authorityId;
	private Integer tabId;

}
