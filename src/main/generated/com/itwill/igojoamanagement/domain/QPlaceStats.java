package com.itwill.igojoamanagement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlaceStats is a Querydsl query type for PlaceStats
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceStats extends EntityPathBase<PlaceStats> {

    private static final long serialVersionUID = 2002924262L;

    public static final QPlaceStats placeStats = new QPlaceStats("placeStats");

    public final NumberPath<Integer> easyTransport = createNumber("easyTransport", Integer.class);

    public final NumberPath<Integer> freeEntry = createNumber("freeEntry", Integer.class);

    public final StringPath highestBadge = createString("highestBadge");

    public final NumberPath<Integer> iScore = createNumber("iScore", Integer.class);

    public final NumberPath<Integer> nightView = createNumber("nightView", Integer.class);

    public final NumberPath<Integer> parkingAvailable = createNumber("parkingAvailable", Integer.class);

    public final NumberPath<Integer> placeFavorite = createNumber("placeFavorite", Integer.class);

    public final StringPath placeName = createString("placeName");

    public final NumberPath<Integer> placeVerified = createNumber("placeVerified", Integer.class);

    public final NumberPath<Integer> review = createNumber("review", Integer.class);

    public final StringPath secondHighestBadge = createString("secondHighestBadge");

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QPlaceStats(String variable) {
        super(PlaceStats.class, forVariable(variable));
    }

    public QPlaceStats(Path<? extends PlaceStats> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceStats(PathMetadata metadata) {
        super(PlaceStats.class, metadata);
    }

}

