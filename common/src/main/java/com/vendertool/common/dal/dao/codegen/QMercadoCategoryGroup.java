package com.vendertool.common.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMercadoCategoryGroup is a Querydsl query type for QBeanMercadoCategoryGroup
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMercadoCategoryGroup extends com.mysema.query.sql.RelationalPathBase<QBeanMercadoCategoryGroup> {

    private static final long serialVersionUID = 1270670093;

    public static final QMercadoCategoryGroup mercadoCategoryGroup = new QMercadoCategoryGroup("mercado_category_group");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> groupDate = createDateTime("group_date", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Integer> mercadoCategoryGroupId = createNumber("mercado_category_group_id", Integer.class);

    public final NumberPath<Integer> siteId = createNumber("site_id", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanMercadoCategoryGroup> primary = createPrimaryKey(mercadoCategoryGroupId);

    public QMercadoCategoryGroup(String variable) {
        super(QBeanMercadoCategoryGroup.class, forVariable(variable), "null", "mercado_category_group");
    }

    @SuppressWarnings("all")
    public QMercadoCategoryGroup(Path<? extends QBeanMercadoCategoryGroup> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "mercado_category_group");
    }

    public QMercadoCategoryGroup(PathMetadata<?> metadata) {
        super(QBeanMercadoCategoryGroup.class, metadata, "null", "mercado_category_group");
    }

}

