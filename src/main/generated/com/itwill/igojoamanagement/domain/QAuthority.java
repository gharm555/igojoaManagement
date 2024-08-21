package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthority is a Querydsl query type for Authority
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthority extends EntityPathBase<Authority> {

    private static final long serialVersionUID = 440779381L;

    public static final QAuthority authority = new QAuthority("authority");

    public final StringPath adminId = createString("adminId");

    public final StringPath authorityCategory = createString("authorityCategory");

    public final StringPath authorityCode = createString("authorityCode");

    public QAuthority(String variable) {
        super(Authority.class, forVariable(variable));
    }

    public QAuthority(Path<? extends Authority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthority(PathMetadata metadata) {
        super(Authority.class, metadata);
    }

}

