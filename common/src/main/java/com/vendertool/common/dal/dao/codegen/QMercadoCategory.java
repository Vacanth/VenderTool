package com.vendertool.common.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMercadoCategory is a Querydsl query type for QBeanMercadoCategory
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMercadoCategory extends com.mysema.query.sql.RelationalPathBase<QBeanMercadoCategory> {

    private static final long serialVersionUID = -499552622;

    public static final QMercadoCategory mercadoCategory = new QMercadoCategory("mercado_category");

    public final SimplePath<byte[]> attributes = createSimple("attributes", byte[].class);

    public final StringPath categoryId = createString("category_id");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final NumberPath<Integer> groupId = createNumber("group_id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Long> mercadoCategoryId = createNumber("mercado_category_id", Long.class);

    public final StringPath parentCategoryId = createString("parent_category_id");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanMercadoCategory> primary = createPrimaryKey(mercadoCategoryId);

    public QMercadoCategory(String variable) {
        super(QBeanMercadoCategory.class, forVariable(variable), "null", "mercado_category");
    }

    @SuppressWarnings("all")
    public QMercadoCategory(Path<? extends QBeanMercadoCategory> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "mercado_category");
    }

    public QMercadoCategory(PathMetadata<?> metadata) {
        super(QBeanMercadoCategory.class, metadata, "null", "mercado_category");
    }

}

