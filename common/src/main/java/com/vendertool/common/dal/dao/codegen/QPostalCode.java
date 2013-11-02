package com.vendertool.common.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPostalCode is a Querydsl query type for QBeanPostalCode
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QPostalCode extends com.mysema.query.sql.RelationalPathBase<QBeanPostalCode> {

    private static final long serialVersionUID = -1527756665;

    public static final QPostalCode postalCode1 = new QPostalCode("postal_code");

    public final NumberPath<Long> city = createNumber("city", Long.class);

    public final StringPath iso3CountryCode = createString("iso3_country_code");

    public final StringPath postalCode = createString("postal_code");

    public final NumberPath<Integer> postalCodeId = createNumber("postal_code_id", Integer.class);

    public final NumberPath<Long> state = createNumber("state", Long.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanPostalCode> primary = createPrimaryKey(postalCodeId);

    public QPostalCode(String variable) {
        super(QBeanPostalCode.class, forVariable(variable), "null", "postal_code");
    }

    @SuppressWarnings("all")
    public QPostalCode(Path<? extends QBeanPostalCode> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "postal_code");
    }

    public QPostalCode(PathMetadata<?> metadata) {
        super(QBeanPostalCode.class, metadata, "null", "postal_code");
    }

}

