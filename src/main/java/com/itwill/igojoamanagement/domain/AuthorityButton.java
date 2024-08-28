package com.itwill.igojoamanagement.domain;

import com.itwill.igojoamanagement.domain.key.AuthorityButtonId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "authority_button")
public class AuthorityButton {
	@EmbeddedId
	private AuthorityButtonId id;

	@ManyToOne
	@MapsId("authorityId")
	@JoinColumn(name = "authorityId")
	private Authority authority;

	@ManyToOne
	@MapsId("btnId")
	@JoinColumn(name = "btnId")
	private Button button;

}