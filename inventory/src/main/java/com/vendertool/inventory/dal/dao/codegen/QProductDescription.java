package com.vendertool.inventory.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProductDescription is a Querydsl query type for QBeanProductDescription
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QProductDescription extends com.mysema.query.sql.RelationalPathBase<QBeanProductDescription> {

    private static final long serialVersionUID = -865150049;

    public static final QProductDescription productDescription = new QProductDescription("product_description");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Long> productDescriptionId = createNumber("product_description_id", Long.class);

    public final StringPath productDescriptionText = createString("product_description_text");

    public final StringPath productDescriptionTitle = createString("product_description_title");

    public final NumberPath<Long> productId = createNumber("product_id", Long.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanProductDescription> primary = createPrimaryKey(productDescriptionId);

    public QProductDescription(String variable) {
        super(QBeanProductDescription.class, forVariable(variable), "null", "product_description");
    }

    @SuppressWarnings("all")
    public QProductDescription(Path<? extends QBeanProductDescription> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "product_description");
    }

    public QProductDescription(PathMetadata<?> metadata) {
        super(QBeanProductDescription.class, metadata, "null", "product_description");
    }

}

