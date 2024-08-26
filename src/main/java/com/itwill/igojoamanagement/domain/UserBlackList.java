package com.itwill.igojoamanagement.domain;

import java.time.LocalDateTime;

import com.itwill.igojoamanagement.domain.key.UserBlackListPK;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "userBlackList")
public class UserBlackList {

    @EmbeddedId
    UserBlackListPK userBlackListPK;

    @Column(name = "reasonCode")
    @NotNull(message = "사유 코드는 필수 입력 항목입니다.")
    private int reasonCode;

    @Column(name = "adminId")
    private String adminId;

}
