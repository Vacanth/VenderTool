package com.vendertool.registration.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QForgotPassword is a Querydsl query type for QBeanForgotPassword
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QForgotPassword extends com.mysema.query.sql.RelationalPathBase<QBeanForgotPassword> {

    private static final long serialVersionUID = 2062190875;

    public static final QForgotPassword forgotPassword = new QForgotPassword("forgot_password");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath emailAddr = createString("email_addr");

    public final NumberPath<Long> forgotPasswordId = createNumber("forgot_password_id", Long.class);

    public final StringPath ipAddr = createString("ip_addr");

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanForgotPassword> primary = createPrimaryKey(forgotPasswordId);

    public QForgotPassword(String variable) {
        super(QBeanForgotPassword.class, forVariable(variable), "null", "forgot_password");
    }

    @SuppressWarnings("all")
    public QForgotPassword(Path<? extends QBeanForgotPassword> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "forgot_password");
    }

    public QForgotPassword(PathMetadata<?> metadata) {
        super(QBeanForgotPassword.class, metadata, "null", "forgot_password");
    }

}

