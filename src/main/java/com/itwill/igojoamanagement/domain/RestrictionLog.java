package com.itwill.igojoamanagement.domain;

import com.itwill.igojoamanagement.domain.key.RestrictionLogPK;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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