package com.itwill.igojoamanagement.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tabs")
public class Tab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tabId;

	private String tabName;

}
