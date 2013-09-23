package com.vendertool.listing.dal.dao.codegen;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QListing is a Querydsl query type for QBeanListing
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QListing extends com.mysema.query.sql.RelationalPathBase<QBeanListing> {

    private static final long serialVersionUID = -1687420086;

    public static final QListing listing = new QListing("listing");

    public final NumberPath<Long> accountId = createNumber("account_id", Long.class);

    public final NumberPath<java.math.BigDecimal> auctionCurrentPrice = createNumber("auction_current_price", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auctionReservedPrice = createNumber("auction_reserved_price", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auctionStartPrice = createNumber("auction_start_price", java.math.BigDecimal.class);

    public final StringPath categoryId = createString("category_id");

    public final StringPath condition = createString("condition");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("created_date", java.sql.Timestamp.class);

    public final StringPath currencyCodeIso3 = createString("currency_code_iso3");

    public final NumberPath<java.math.BigDecimal> fixedPrice = createNumber("fixed_price", java.math.BigDecimal.class);

    public final DateTimePath<java.sql.Timestamp> itemEndTime = createDateTime("item_end_time", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> itemStartTime = createDateTime("item_start_time", java.sql.Timestamp.class);

    public final NumberPath<Byte> lastModifiedApp = createNumber("last_modified_app", Byte.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("last_modified_date", java.sql.Timestamp.class);

    public final NumberPath<Long> listingId = createNumber("listing_id", Long.class);

    public final NumberPath<Byte> marketplaceId = createNumber("marketplace_id", Byte.class);

    public final StringPath marketplaceItemId = createString("marketplace_item_id");

    public final NumberPath<Long> masterTemplateId = createNumber("master_template_id", Long.class);

    public final NumberPath<Long> parentItemId = createNumber("parent_item_id", Long.class);

    public final NumberPath<Long> productId = createNumber("product_id", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath warranty = createString("warranty");

    public final com.mysema.query.sql.PrimaryKey<QBeanListing> primary = createPrimaryKey(listingId);

    public QListing(String variable) {
        super(QBeanListing.class, forVariable(variable), "null", "listing");
    }

    @SuppressWarnings("all")
    public QListing(Path<? extends QBeanListing> path) {
        super((Class)path.getType(), path.getMetadata(), "null", "listing");
    }

    public QListing(PathMetadata<?> metadata) {
        super(QBeanListing.class, metadata, "null", "listing");
    }

}

