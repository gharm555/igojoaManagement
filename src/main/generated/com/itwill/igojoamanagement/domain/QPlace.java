package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = 1679818041L;

    public static final QPlace place = new QPlace("place");

    public final StringPath largeAddress = createString("largeAddress");

    public final StringPath midiumAddress = createString("midiumAddress");

    public final StringPath operatingHours = createString("operatingHours");

    public final StringPath placeDescription = createString("placeDescription");

    public final NumberPath<Double> placeLatitude = createNumber("placeLatitude", Double.class);

    public final NumberPath<Double> placeLongitude = createNumber("placeLongitude", Double.class);

    public final StringPath placeName = createString("placeName");

    public final NumberPath<Integer> placeRadius = createNumber("placeRadius", Integer.class);

    public final StringPath smallAddress = createString("smallAddress");

    public QPlace(String variable) {
        super(Place.class, forVariable(variable));
    }

    public QPlace(Path<? extends Place> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlace(PathMetadata metadata) {
        super(Place.class, metadata);
    }

}

