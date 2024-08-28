package com.itwill.igojoamanagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tabs")
public class Tab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tabId;

	private String tabName;
}
