package com.itwill.igojoamanagement.domain;

import com.itwill.igojoamanagement.domain.key.RestrictionLogPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "restrictionLogs")
public class RestrictionLog {

    @EmbeddedId
    RestrictionLogPK restrictionLogPK;

    @Column(name = "adminId")
    private String adminId;

    @Column(name = "reasonCode")
    @NotNull(message = "사유 코드는 필수 입력 항목입니다.")
    private int reasonCode;

    @Column(name = "detail")
    private String detail;

}