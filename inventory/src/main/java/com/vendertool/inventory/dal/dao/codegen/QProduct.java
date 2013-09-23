package com.vendertool.inventory.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProduct is a Querydsl query type for QBeanProduct
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QProduct extends com.mysema.query.sql.RelationalPathBase<QBeanProduct> {

    private static final long serialVersionUID = 499158045;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath currencyCodeIso3 = createString("currency_code_iso3");

    public final NumberPath<Byte> dimensionUnit = createNumber("dimension_unit", Byte.class);

    public final NumberPath<Double> height = createNumber("height", Double.class);

    public final NumberPath<Byte> lastModifiedApp = createNumber("last_modified_app", Byte.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Double> length = createNumber("length", Double.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final StringPath productCode = createString("product_code");

    public final NumberPath<Byte> productCodeType = createNumber("product_code_type", Byte.class);

    public final NumberPath<Long> productId = createNumber("product_id", Long.class);

    public final StringPath productUrl = createString("product_url");

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath sku = createString("sku");

    public final StringPath title = createString("title");

    public final NumberPath<Double> weight = createNumber("weight", Double.class);

    public final NumberPath<Byte> weightUnit = createNumber("weight_unit", Byte.class);

    public final NumberPath<Double> width = createNumber("width", Double.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanProduct> primary = createPrimaryKey(productId);

    public QProduct(String variable) {
        super(QBeanProduct.class, forVariable(variable), "null", "product");
    }

    @SuppressWarnings("all")
    public QProduct(Path<? extends QBeanProduct> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "product");
    }

    public QProduct(PathMetadata<?> metadata) {
        super(QBeanProduct.class, metadata, "null", "product");
    }

}

