package com.itwill.igojoamanagement.domain.key;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AuthorityButtonId implements Serializable {
	private Integer authorityId;
	private Integer btnId;

	// Constructors, equals, and hashCode methods
}
