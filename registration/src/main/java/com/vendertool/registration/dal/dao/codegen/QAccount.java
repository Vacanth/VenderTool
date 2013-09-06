package com.vendertool.registration.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;


/**
 * QAccount is a Querydsl query type for QAccount
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAccount extends com.mysema.query.sql.RelationalPathBase<QAccount> {

    private static final long serialVersionUID = 1562705284;

    public static final QAccount account = new QAccount("account");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final StringPath alternateEmail = createString("alternate_email");

    public final NumberPath<Long> billingAddrId = createNumber("billing_addr_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath currencyCodeIso3 = createString("currency_code_iso3");

    public final StringPath emailAddr = createString("email_addr");

    public final StringPath firstName = createString("first_name");

    public final StringPath language = createString("language");

    public final NumberPath<Byte> lastModifiedApp = createNumber("last_modified_app", Byte.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final StringPath lastName = createString("last_name");

    public final StringPath middleName = createString("middle_name");

    public final StringPath password = createString("password");

    public final SimplePath<byte[]> picture = createSimple("picture", byte[].class);

    public final NumberPath<Long> registrationAddrId = createNumber("registration_addr_id", Long.class);

    public final StringPath roles = createString("roles");

    public final StringPath salt = createString("salt");

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public final com.mysema.query.sql.PrimaryKey<QAccount> primary = createPrimaryKey(accountId);

    public QAccount(String variable) {
        super(QAccount.class, forVariable(variable), "null", "account");
    }

    @SuppressWarnings("all")
    public QAccount(Path<? extends QAccount> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "account");
    }

    public QAccount(PathMetadata<?> metadata) {
        super(QAccount.class, metadata, "null", "account");
    }

}

