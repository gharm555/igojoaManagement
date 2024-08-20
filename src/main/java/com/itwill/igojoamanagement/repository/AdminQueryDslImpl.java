package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.QAdmin;
import com.itwill.igojoamanagement.domain.QAdminRoles;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AdminQueryDslImpl extends QuerydslRepositorySupport implements AdminQueryDsl {

    public AdminQueryDslImpl() {
        super(Admin.class);
    }

    @Override
    public Admin findByAdminIdWithRole(String adminId) {
        QAdmin admin = QAdmin.admin;
        QAdminRoles role = QAdminRoles.adminRoles;

        JPQLQuery<Admin> query = from(admin)
                .leftJoin(admin.role, role).fetchJoin()
                .where(admin.adminId.eq(adminId));

        Admin entity = query.fetchOne();

        return entity;
      
    }
}
