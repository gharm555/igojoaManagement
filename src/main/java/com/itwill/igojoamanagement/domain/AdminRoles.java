package com.itwill.igojoamanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="adminRoles")
public class AdminRoles {
    @Id
    @Column(name = "roleId")
    private Integer roleId;

    @Column(name = "roleName")
    private String roleName;
}
