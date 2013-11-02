package com.vendertool.fps.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTask is a Querydsl query type for QBeanTask
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTask extends com.mysema.query.sql.RelationalPathBase<QBeanTask> {

    private static final long serialVersionUID = 293072164;

    public static final QTask task = new QTask("task");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath isoCountryCode = createString("iso_country_code");

    public final NumberPath<Long> jobId = createNumber("job_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Long> recordId = createNumber("record_id", Long.class);

    public final SimplePath<byte[]> request = createSimple("request", byte[].class);

    public final NumberPath<Long> requestFileId = createNumber("request_file_id", Long.class);

    public final StringPath requestGroupId = createString("request_group_id");

    public final SimplePath<byte[]> response = createSimple("response", byte[].class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Long> taskId = createNumber("task_id", Long.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanTask> primary = createPrimaryKey(taskId);

    public QTask(String variable) {
        super(QBeanTask.class, forVariable(variable), "null", "task");
    }

    @SuppressWarnings("all")
    public QTask(Path<? extends QBeanTask> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "task");
    }

    public QTask(PathMetadata<?> metadata) {
        super(QBeanTask.class, metadata, "null", "task");
    }

}

