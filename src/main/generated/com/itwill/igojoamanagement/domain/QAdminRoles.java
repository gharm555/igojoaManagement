package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminRoles is a Querydsl query type for AdminRoles
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminRoles extends EntityPathBase<AdminRoles> {

    private static final long serialVersionUID = -1339031620L;

    public static final QAdminRoles adminRoles = new QAdminRoles("adminRoles");

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public final StringPath roleName = createString("roleName");

    public QAdminRoles(String variable) {
        super(AdminRoles.class, forVariable(variable));
    }

    public QAdminRoles(Path<? extends AdminRoles> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminRoles(PathMetadata metadata) {
        super(AdminRoles.class, metadata);
    }

}

