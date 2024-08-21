package com.itwill.igojoamanagement.Domain;

import java.time.LocalDateTime;
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
public class UserBlackListId {

	private int userId;
	private LocalDateTime banStartAt;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserBlackListId that = (UserBlackListId) o;
		return userId == that.userId && Objects.equals(banStartAt, that.banStartAt);
	}

	// hashCode() 메서드
	@Override
	public int hashCode() {
		return Objects.hash(userId, banStartAt);
	}
}
