package com.vendertool.registration.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPasswordHistory is a Querydsl query type for QBeanPasswordHistory
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QPasswordHistory extends com.mysema.query.sql.RelationalPathBase<QBeanPasswordHistory> {

    private static final long serialVersionUID = 741120732;

    public static final QPasswordHistory passwordHistory = new QPasswordHistory("password_history");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final StringPath password = createString("password");

    public final NumberPath<Long> passwordHistoryId = createNumber("password_history_id", Long.class);

    public final StringPath salt = createString("salt");

    public final com.mysema.query.sql.PrimaryKey<QBeanPasswordHistory> primary = createPrimaryKey(passwordHistoryId);

    public QPasswordHistory(String variable) {
        super(QBeanPasswordHistory.class, forVariable(variable), "null", "password_history");
    }

    @SuppressWarnings("all")
    public QPasswordHistory(Path<? extends QBeanPasswordHistory> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "password_history");
    }

    public QPasswordHistory(PathMetadata<?> metadata) {
        super(QBeanPasswordHistory.class, metadata, "null", "password_history");
    }

}

