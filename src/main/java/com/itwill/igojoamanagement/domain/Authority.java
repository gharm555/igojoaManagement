package com.itwill.igojoamanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@EqualsAndHashCode
@Table(name="authority")
@IdClass(AuthorityId.class) 
public class Authority {

    @Id
    @Column(length = 64, nullable = false)
    @NotBlank(message = "관리자 ID는 필수 입력 항목입니다.")
    @Size(max = 64, message = "관리자 ID는 64자 이내로 입력해야 합니다.")
    private String adminId;

    @Id
    @Column(length = 10, nullable = false)
    @NotBlank(message = "권한 카테고리는 필수 입력 항목입니다.")
    @Size(max = 10, message = "권한 카테고리는 10자 이내로 입력해야 합니다.")
    private String authorityCategory;

    @Column(length = 4)
    @Size(max = 4, message = "권한 코드는 4자 이내로 입력해야 합니다.")
    private String authorityCode;
}
