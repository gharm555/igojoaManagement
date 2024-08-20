package com.itwill.igojoamanagement.domain;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class AuthorityId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String adminId;
	private String authorityCategory;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AuthorityId that = (AuthorityId) o;
		return Objects.equals(adminId, that.adminId) && Objects.equals(authorityCategory, that.authorityCategory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminId, authorityCategory);
	}

}