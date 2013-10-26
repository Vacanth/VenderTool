package com.vendertool.inventory.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QImage is a Querydsl query type for QBeanImage
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QImage extends com.mysema.query.sql.RelationalPathBase<QBeanImage> {

    private static final long serialVersionUID = -2003870711;

    public static final QImage image = new QImage("image");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath hash = createString("hash");

    public final StringPath hostedUrl = createString("hosted_url");

    public final NumberPath<Integer> imageFormat = createNumber("image_format", Integer.class);

    public final NumberPath<Long> imageId = createNumber("image_id", Long.class);

    public final StringPath imageName = createString("image_name");

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Long> refId = createNumber("ref_id", Long.class);

    public final NumberPath<Integer> refType = createNumber("ref_type", Integer.class);

    public final StringPath size = createString("size");

    public final NumberPath<Integer> sortOrderId = createNumber("sort_order_id", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanImage> primary = createPrimaryKey(imageId);

    public QImage(String variable) {
        super(QBeanImage.class, forVariable(variable), "null", "image");
    }

    @SuppressWarnings("all")
    public QImage(Path<? extends QBeanImage> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "image");
    }

    public QImage(PathMetadata<?> metadata) {
        super(QBeanImage.class, metadata, "null", "image");
    }

}

