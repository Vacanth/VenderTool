package com.vendertool.inventory.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProductVariation is a Querydsl query type for QBeanProductVariation
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QProductVariation extends com.mysema.query.sql.RelationalPathBase<QBeanProductVariation> {

    private static final long serialVersionUID = -1560377738;

    public static final QProductVariation productVariation = new QProductVariation("product_variation");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath currencyCodeIso3 = createString("currency_code_iso3");

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Long> productId = createNumber("product_id", Long.class);

    public final NumberPath<Long> productVariationId = createNumber("product_variation_id", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath sku = createString("sku");

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public final com.mysema.query.sql.PrimaryKey<QBeanProductVariation> primary = createPrimaryKey(productVariationId);

    public QProductVariation(String variable) {
        super(QBeanProductVariation.class, forVariable(variable), "null", "product_variation");
    }

    @SuppressWarnings("all")
    public QProductVariation(Path<? extends QBeanProductVariation> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "product_variation");
    }

    public QProductVariation(PathMetadata<?> metadata) {
        super(QBeanProductVariation.class, metadata, "null", "product_variation");
    }

}

