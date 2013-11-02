package com.vendertool.inventory.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProductAttribute is a Querydsl query type for QBeanProductAttribute
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QProductAttribute extends com.mysema.query.sql.RelationalPathBase<QBeanProductAttribute> {

    private static final long serialVersionUID = -1465348353;

    public static final QProductAttribute productAttribute = new QProductAttribute("product_attribute");

    public final StringPath attrCharValue = createString("attr_char_value");

    public final DateTimePath<java.sql.Timestamp> attrDateValue = createDateTime("attr_date_value", java.sql.Timestamp.class);

    public final StringPath attrName = createString("attr_name");

    public final NumberPath<java.math.BigDecimal> attrNumValue = createNumber("attr_num_value", java.math.BigDecimal.class);

    public final NumberPath<Integer> attrValueType = createNumber("attr_value_type", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Long> productAttributeId = createNumber("product_attribute_id", Long.class);

    public final NumberPath<Long> refId = createNumber("ref_id", Long.class);

    public final NumberPath<Integer> refType = createNumber("ref_type", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanProductAttribute> primary = createPrimaryKey(productAttributeId);

    public QProductAttribute(String variable) {
        super(QBeanProductAttribute.class, forVariable(variable), "null", "product_attribute");
    }

    @SuppressWarnings("all")
    public QProductAttribute(Path<? extends QBeanProductAttribute> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "product_attribute");
    }

    public QProductAttribute(PathMetadata<?> metadata) {
        super(QBeanProductAttribute.class, metadata, "null", "product_attribute");
    }

}

