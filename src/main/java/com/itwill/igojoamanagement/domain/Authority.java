package com.itwill.igojoamanagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "authority")
public class Authority {

	@Id
	@Column(name="authorityId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorityId;

	private String authorityName;
}
