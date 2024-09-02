package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlaceImage is a Querydsl query type for PlaceImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceImage extends EntityPathBase<PlaceImage> {

    private static final long serialVersionUID = 1993480098L;

    public static final QPlaceImage placeImage = new QPlaceImage("placeImage");

    public final StringPath firstImgName = createString("firstImgName");

    public final StringPath firstUrl = createString("firstUrl");

    public final StringPath placeName = createString("placeName");

    public final StringPath secondImgName = createString("secondImgName");

    public final StringPath secondUrl = createString("secondUrl");

    public final StringPath thirdImgName = createString("thirdImgName");

    public final StringPath thirdUrl = createString("thirdUrl");

    public QPlaceImage(String variable) {
        super(PlaceImage.class, forVariable(variable));
    }

    public QPlaceImage(Path<? extends PlaceImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceImage(PathMetadata metadata) {
        super(PlaceImage.class, metadata);
    }

}

