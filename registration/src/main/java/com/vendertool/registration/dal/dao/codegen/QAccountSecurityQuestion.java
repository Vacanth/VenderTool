package com.vendertool.registration.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAccountSecurityQuestion is a Querydsl query type for QBeanAccountSecurityQuestion
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAccountSecurityQuestion extends com.mysema.query.sql.RelationalPathBase<QBeanAccountSecurityQuestion> {

    private static final long serialVersionUID = 1114277366;

    public static final QAccountSecurityQuestion accountSecurityQuestion = new QAccountSecurityQuestion("account_security_question");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final NumberPath<Long> accountSecurityQuestionId = createNumber("account_security_question_id", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final NumberPath<Integer> lastModifiedApp = createNumber("last_modified_app", Integer.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final StringPath securityAnswer = createString("security_answer");

    public final StringPath securityQuestionCode = createString("security_question_code");

    public final com.mysema.query.sql.PrimaryKey<QBeanAccountSecurityQuestion> primary = createPrimaryKey(accountSecurityQuestionId);

    public QAccountSecurityQuestion(String variable) {
        super(QBeanAccountSecurityQuestion.class, forVariable(variable), "null", "account_security_question");
    }

    @SuppressWarnings("all")
    public QAccountSecurityQuestion(Path<? extends QBeanAccountSecurityQuestion> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "account_security_question");
    }

    public QAccountSecurityQuestion(PathMetadata<?> metadata) {
        super(QBeanAccountSecurityQuestion.class, metadata, "null", "account_security_question");
    }

}

