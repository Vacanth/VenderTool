package com.vendertool.fps.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QJob is a Querydsl query type for QBeanJob
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QJob extends com.mysema.query.sql.RelationalPathBase<QBeanJob> {

    private static final long serialVersionUID = 1810560062;

    public static final QJob job = new QJob("job");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath error = createString("error");

    public final StringPath isoCountryCode = createString("iso_country_code");

    public final NumberPath<Long> jobId = createNumber("job_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final StringPath requestFileGroupId = createString("request_file_group_id");

    public final StringPath responseFileGroupId = createString("response_file_group_id");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> totalRequestFileSize = createNumber("total_request_file_size", Long.class);

    public final NumberPath<Long> totalResponseFileSize = createNumber("total_response_file_size", Long.class);

    public final NumberPath<Integer> usecase = createNumber("usecase", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QBeanJob> primary = createPrimaryKey(jobId);

    public QJob(String variable) {
        super(QBeanJob.class, forVariable(variable), "null", "job");
    }

    @SuppressWarnings("all")
    public QJob(Path<? extends QBeanJob> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "job");
    }

    public QJob(PathMetadata<?> metadata) {
        super(QBeanJob.class, metadata, "null", "job");
    }

}

