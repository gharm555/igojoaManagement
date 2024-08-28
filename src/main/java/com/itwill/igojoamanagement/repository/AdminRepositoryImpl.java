package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.*;
import com.itwill.igojoamanagement.dto.AdminDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class AdminRepositoryImpl extends QuerydslRepositorySupport implements AdminRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AdminRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Admin.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<AdminDto> findByAdminDtoByAdminId(String adminId) {
        QAdmin admin = QAdmin.admin;
        QAdminAuthority adminAuthority = QAdminAuthority.adminAuthority;
        QAuthority authority = QAuthority.authority;
        QAuthorityTab authorityTab = QAuthorityTab.authorityTab;
        QTab tab = QTab.tab;
        QAuthorityButton authorityButton = QAuthorityButton.authorityButton;
        QButton button = QButton.button;

        List<AdminDto> result = queryFactory
                .select(Projections.constructor(AdminDto.class,
                        authority.authorityName,
                        tab.tabName,
                        button.btnName
                ))
                .from(admin)
                .leftJoin(adminAuthority).on(admin.adminId.eq(adminAuthority.admin.adminId)).fetchJoin()
                .leftJoin(authority).on(adminAuthority.authority.authorityId.eq(authority.authorityId)).fetchJoin()
                .leftJoin(authorityTab).on(authority.authorityId.eq(authorityTab.authority.authorityId)).fetchJoin()
                .leftJoin(tab).on(authorityTab.tab.tabId.eq(tab.tabId)).fetchJoin()
                .leftJoin(authorityButton).on(authority.authorityId.eq(authorityButton.authority.authorityId)).fetchJoin()
                .leftJoin(button).on(authorityButton.button.btnId.eq(button.btnId)).fetchJoin()
                .where(admin.adminId.eq(adminId))
                .fetch();

        return result;
    }
}
