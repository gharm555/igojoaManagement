package com.itwill.igojoamanagement.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {

	@Id
	@Column(name="authorityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorityId;

	private String authorityName;
}
