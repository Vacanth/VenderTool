package com.vendertool.common.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QState is a Querydsl query type for QBeanState
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QState extends com.mysema.query.sql.RelationalPathBase<QBeanState> {

    private static final long serialVersionUID = 1612725250;

    public static final QState state = new QState("state");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath displayName = createString("display_name");

    public final StringPath iso3CountryCode = createString("iso3_country_code");

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final StringPath stateCode = createString("state_code");

    public final NumberPath<Long> stateId = createNumber("state_id", Long.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanState> primary = createPrimaryKey(stateId);

    public QState(String variable) {
        super(QBeanState.class, forVariable(variable), "null", "state");
    }

    @SuppressWarnings("all")
    public QState(Path<? extends QBeanState> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "state");
    }

    public QState(PathMetadata<?> metadata) {
        super(QBeanState.class, metadata, "null", "state");
    }

}

