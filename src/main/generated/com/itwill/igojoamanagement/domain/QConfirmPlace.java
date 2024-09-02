package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConfirmPlace is a Querydsl query type for ConfirmPlace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConfirmPlace extends EntityPathBase<ConfirmPlace> {

    private static final long serialVersionUID = -1588438603L;

    public static final QConfirmPlace confirmPlace = new QConfirmPlace("confirmPlace");

    public final DateTimePath<java.time.LocalDateTime> displayDate = createDateTime("displayDate", java.time.LocalDateTime.class);

    public final StringPath largeAddress = createString("largeAddress");

    public final StringPath mediumAddress = createString("mediumAddress");

    public final StringPath operatingHours = createString("operatingHours");

    public final StringPath placeDescription = createString("placeDescription");

    public final NumberPath<Double> placeLatitude = createNumber("placeLatitude", Double.class);

    public final NumberPath<Double> placeLongitude = createNumber("placeLongitude", Double.class);

    public final StringPath placeName = createString("placeName");

    public final NumberPath<Integer> radius = createNumber("radius", Integer.class);

    public final StringPath reporterId = createString("reporterId");

    public final StringPath smallAddress = createString("smallAddress");

    public QConfirmPlace(String variable) {
        super(ConfirmPlace.class, forVariable(variable));
    }

    public QConfirmPlace(Path<? extends ConfirmPlace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConfirmPlace(PathMetadata metadata) {
        super(ConfirmPlace.class, metadata);
    }

}

