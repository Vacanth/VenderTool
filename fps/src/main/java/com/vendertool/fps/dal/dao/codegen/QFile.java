package com.vendertool.fps.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFile is a Querydsl query type for QBeanFile
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QFile extends com.mysema.query.sql.RelationalPathBase<QBeanFile> {

    private static final long serialVersionUID = 292662555;

    public static final QFile file = new QFile("file");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath fileGroupId = createString("file_group_id");

    public final NumberPath<Long> fileId = createNumber("file_id", Long.class);

    public final StringPath fileName = createString("file_name");

    public final NumberPath<Byte> filesCountInGroup = createNumber("files_count_in_group", Byte.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final StringPath refUrl = createString("ref_url");

    public final NumberPath<Byte> status = createNumber("status", Byte.class);

    public final NumberPath<Byte> storageSource = createNumber("storage_source", Byte.class);

    public final NumberPath<Byte> useCase = createNumber("use_case", Byte.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanFile> primary = createPrimaryKey(fileId);

    public QFile(String variable) {
        super(QBeanFile.class, forVariable(variable), "null", "file");
    }

    @SuppressWarnings("all")
    public QFile(Path<? extends QBeanFile> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "file");
    }

    public QFile(PathMetadata<?> metadata) {
        super(QBeanFile.class, metadata, "null", "file");
    }

}

