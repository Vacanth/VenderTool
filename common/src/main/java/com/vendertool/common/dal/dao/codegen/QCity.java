package com.vendertool.common.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCity is a Querydsl query type for QBeanCity
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QCity extends com.mysema.query.sql.RelationalPathBase<QBeanCity> {

    private static final long serialVersionUID = -918294566;

    public static final QCity city = new QCity("city");

    public final NumberPath<Long> cityId = createNumber("city_id", Long.class);

    public final StringPath cityName = createString("city_name");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath iso3CountryCode = createString("iso3_country_code");

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Long> state = createNumber("state", Long.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanCity> primary = createPrimaryKey(cityId);

    public QCity(String variable) {
        super(QBeanCity.class, forVariable(variable), "null", "city");
    }

    @SuppressWarnings("all")
    public QCity(Path<? extends QBeanCity> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "city");
    }

    public QCity(PathMetadata<?> metadata) {
        super(QBeanCity.class, metadata, "null", "city");
    }

}

