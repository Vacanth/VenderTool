package com.vendertool.registration.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAccountConfirmation is a Querydsl query type for QBeanAccountConfirmation
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAccountConfirmation extends com.mysema.query.sql.RelationalPathBase<QBeanAccountConfirmation> {

    private static final long serialVersionUID = -60288283;

    public static final QAccountConfirmation accountConfirmation = new QAccountConfirmation("account_confirmation");

    public final NumberPath<Long> accountConfirmationId = createNumber("account_confirmation_id", Long.class);

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final NumberPath<Integer> confirmationCode = createNumber("confirmation_code", Integer.class);

    public final DateTimePath<java.sql.Timestamp> confirmationDate = createDateTime("confirmation_date", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath emailAddr = createString("email_addr");

    public final DateTimePath<java.sql.Timestamp> expiryDate = createDateTime("expiry_date", java.sql.Timestamp.class);

    public final NumberPath<Integer> lastModifiedApp = createNumber("last_modified_app", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Integer> numberOfAttempts = createNumber("number_of_attempts", Integer.class);

    public final StringPath sessionId = createString("session_id");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanAccountConfirmation> primary = createPrimaryKey(accountConfirmationId);

    public QAccountConfirmation(String variable) {
        super(QBeanAccountConfirmation.class, forVariable(variable), "null", "account_confirmation");
    }

    @SuppressWarnings("all")
    public QAccountConfirmation(Path<? extends QBeanAccountConfirmation> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "account_confirmation");
    }

    public QAccountConfirmation(PathMetadata<?> metadata) {
        super(QBeanAccountConfirmation.class, metadata, "null", "account_confirmation");
    }

}

