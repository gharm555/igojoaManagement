package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.AdminRoles;
import com.itwill.igojoamanagement.domain.QAdmin;
import com.itwill.igojoamanagement.domain.QAdminRoles;
import com.itwill.igojoamanagement.dto.LoginAdminDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AdminQueryDslImpl implements AdminQueryDsl {

    private final JPAQueryFactory queryFactory;

    public AdminQueryDslImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    // 관리자 로그인 정보(아이디, 비번, 직책)
    @Override
    public LoginAdminDto findByAdminIdWithRole(String adminId) {
        QAdmin admin = QAdmin.admin;
        QAdminRoles role = QAdminRoles.adminRoles;

        return queryFactory
                .select(Projections.constructor(LoginAdminDto.class,
                        admin.adminId,
                        admin.password,
                        role.roleName))
                .from(admin)
                .leftJoin(admin.role, role)
                .where(admin.adminId.eq(adminId))
                .fetchOne();
    }


}
