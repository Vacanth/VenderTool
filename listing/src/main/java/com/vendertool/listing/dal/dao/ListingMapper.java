package com.vendertool.listing.dal.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.DALMapper;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.listing.dal.dao.codegen.QBeanListing;
import com.vendertool.listing.dal.dao.codegen.QListing;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.Product;

public class ListingMapper implements DALMapper<Listing> {

	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	protected ListingMapper(Path<?>[] paths) throws DatabaseException {
		if (VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			Listing listing) {
		if (VUTIL.isNull(listing)) {
			return null;
		}

		QListing a = QListing.listing;

		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		for (Path<?> rpath : paths) {
			if (a.listingId.equals(rpath)) {
				map.put(a.listingId, listing.getListingId());
			}

			if (a.auctionCurrentPrice.equals(rpath)) {
				Amount acutionCurrentPrice = listing.getAuctionCurrrentPrice();
				if (acutionCurrentPrice != null) {
					map.put(a.auctionCurrentPrice, acutionCurrentPrice.getValue());
				}
			}

			if (a.auctionReservedPrice.equals(rpath)) {
				Amount auctionReservePrice = listing.getAuctionReservedPrice();
				if (auctionReservePrice != null) {
					map.put(a.auctionReservedPrice,
							auctionReservePrice.getValue());
				}
			}

			if (a.auctionStartPrice.equals(rpath)) {
				Amount auctionStartPrice = listing.getAuctionStartPrice();
				if (auctionStartPrice != null) {
					map.put(a.auctionStartPrice, auctionStartPrice.getValue());
				}
			}

			if (a.categoryId.equals(rpath)) {
				List<Classification> classifications = listing
						.getClassifications();
				if (classifications != null && classifications.size() > 0) {
					for (Classification classification : classifications) {
						if (classification == null) {
							continue;
						}
						if (ClassificationTypeEnum.CATEGORY == classification
								.getClassificationType()) {
							map.put(a.categoryId,
									classification.getClassifierId());
							break;
						}
					}
				}
			}

			if (a.condition.equals(rpath)) {
				map.put(a.condition, listing.getCondition());
			}

			if (a.createdDate.equals(rpath)) {

				Date date = listing.getCreateDate();
				if (date == null) {
					date = new Date();
				}
				map.put(a.createdDate, new Timestamp(date.getTime()));
			}

			if (a.currencyCodeIso3.equals(rpath)) {
				Currency currency = listing.getListingCurrency();
				if (currency != null) {
					map.put(a.currencyCodeIso3, currency.getCurrencyCode());
				}
			}

			if (a.fixedPrice.equals(rpath)) {
				Amount fixedPrice = listing.getFixedPrice();
				if (fixedPrice != null) {
					map.put(a.fixedPrice, fixedPrice.getValue());
				}
			}

			if (a.itemEndTime.equals(rpath)) {
				Date date = listing.getListingEndTime();
				if (date != null) {
					map.put(a.itemEndTime, new Timestamp(date.getTime()));
				}
			}

			if (a.itemStartTime.equals(rpath)) {
				Date date = listing.getListingStartTime();
				if (date != null) {
					map.put(a.itemStartTime, new Timestamp(date.getTime()));
				}
			}

			if (a.lastModifiedApp.equals(rpath)) {
				map.put(a.lastModifiedApp, new Byte(listing.getLastModifiedApp()+""));
			}
			
			if (a.lastModifiedDate.equals(rpath)) {
				Date date = listing.getLastModifiedDate();
				if (date != null) {
					map.put(a.lastModifiedDate, new Timestamp(date.getTime()));
				}
			}
			if (a.marketplaceId.equals(rpath)) {
				MarketEnum market = listing.getMarket();
				if (market != null) {
					map.put(a.marketplaceId, new Byte(market.getId()+""));
				}
			}

			if (a.marketplaceItemId.equals(rpath)) {
				map.put(a.marketplaceItemId, listing.getMarketPlaceListingId());
			}

			if (a.masterTemplateId.equals(rpath)) {
				map.put(a.masterTemplateId, listing.getMasterTemplateId());
			}

			if (a.parentItemId.equals(rpath)) {
				map.put(a.parentItemId, listing.getParentListingId());
			}

			if (a.productId.equals(rpath)) {
				Product product = listing.getProduct();
				if (product != null) {
					map.put(a.productId, product.getProductId());
				}
			}

			if (a.quantity.equals(rpath)) {
				map.put(a.quantity, listing.getQuantity());
			}

			if (a.warranty.equals(rpath)) {
				map.put(a.warranty, listing.getWarranty());
			}

		}
		return map;
	}

	@Override
	public Object populateBean(Listing listing) {
		if (VUTIL.isNull(listing)) {
			return null;
		}
		QBeanListing bean = new QBeanListing();

		bean.setAccountId(listing.getCreateOwnerId());

		Amount auctionCurrentPrice = listing.getAuctionCurrrentPrice();
		if (auctionCurrentPrice != null) {
			bean.setAuctionCurrentPrice(auctionCurrentPrice.getValue());
		}

		Amount auctionReservedPrice = listing.getAuctionReservedPrice();
		if (auctionReservedPrice != null && auctionReservedPrice.getValue() != null) {
			bean.setAuctionReservedPrice(auctionReservedPrice.getValue());
		}

		Amount auctionStartPrice = listing.getAuctionStartPrice();
		if (auctionStartPrice != null && auctionStartPrice.getValue() != null) {
			bean.setAuctionStartPrice(auctionStartPrice.getValue());
		}

		List<Classification> classifications = listing.getClassifications();
		if (classifications != null && classifications.size() > 0) {
			for (Classification classification : classifications) {
				if (classification == null) {
					continue;
				}
				if (ClassificationTypeEnum.CATEGORY == classification
						.getClassificationType()) {
					bean.setCategoryId(classification.getClassifierId());
					break;
				}
			}
		}

		bean.setCondition(listing.getCondition());

		Date createDate = listing.getCreateDate();
		if (createDate != null) {
			bean.setCreatedDate(new Timestamp(createDate.getTime()));
		}

		Currency currency = listing.getListingCurrency();
		if (currency != null) {
			bean.setCurrencyCodeIso3(currency.getCurrencyCode());
		}
		Amount fixedPrice = listing.getFixedPrice();
		if (fixedPrice != null && fixedPrice.getValue()!= null) {
				bean.setFixedPrice(fixedPrice.getValue());
		}

		Date endTime = listing.getListingEndTime();
		if (endTime != null) {
			bean.setItemEndTime(new Timestamp(endTime.getTime()));
		}
		Date startTime = listing.getListingStartTime();
		if (startTime != null) {
			bean.setItemStartTime(new Timestamp(startTime.getTime()));
		}

		bean.setLastModifiedApp(new Byte(listing.getLastModifiedApp()+""));
		
		Date lastModifiedDate = listing.getLastModifiedDate();
		if (lastModifiedDate != null) {
			bean.setLastModifiedDate(new Timestamp(lastModifiedDate.getTime()));
		}

		bean.setListingId(listing.getListingId());
		MarketEnum market = listing.getMarket();
		if (market != null) {
			bean.setMarketplaceId(new Byte(market.getId()+""));
		}
		
		bean.setMarketplaceItemId(listing.getMarketPlaceListingId());
		bean.setMasterTemplateId(listing.getMasterTemplateId());
		bean.setParentItemId(listing.getParentListingId());
		
		Product product = listing.getProduct();
		if (product != null) {
			bean.setProductId(product.getProductId());
		}
		
		bean.setQuantity(listing.getQuantity());
		bean.setWarranty(listing.getWarranty());
		
		return bean;
	}

	@Override
	public Listing convert(Tuple row, Path<?>[] paths) {
		if (VUTIL.isNull(row)) {
			return null;
		}

		if (VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}

		if (VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}

		QListing l = QListing.listing;

		Listing listing = new Listing();
		for (Path<?> rpath : paths) {

			if (l.listingId.equals(rpath)) {
				listing.setListingId(row.get(l.listingId));
			}

			if (l.accountId.equals(rpath)) {
				listing.setListingId(row.get(l.accountId));
			}

			if (l.currencyCodeIso3.equals(rpath)) {
				String code = row.get(l.currencyCodeIso3);
				if(code != null) {
					Currency currency = Currency.getInstance(code);
					if(currency != null) {
						listing.setListingCurrency(currency);
					}
				}
			}

			if (l.auctionCurrentPrice.equals(rpath)) {
				if(row.get(l.auctionCurrentPrice) != null) {
					Amount auctionCurrentPrice = new Amount();
					auctionCurrentPrice.setValue(row.get(l.auctionCurrentPrice));
					listing.setAuctionCurrrentPrice(auctionCurrentPrice);
				}
			}

			if (l.fixedPrice.equals(rpath)) {
				if(row.get(l.fixedPrice) != null) {
					Amount fixedPrice = new Amount();
					fixedPrice.setValue(row.get(l.fixedPrice));
					listing.setFixedPrice(fixedPrice);
				}
			}

			if (l.auctionReservedPrice.equals(rpath)) {
				if(row.get(l.auctionCurrentPrice) != null) {
					Amount auctionReservedPrice = new Amount();
					auctionReservedPrice.setValue(row.get(l.auctionCurrentPrice));
					listing.setAuctionReservedPrice(auctionReservedPrice);
				}
			}

			if (l.auctionStartPrice.equals(rpath)) {
				if(row.get(l.auctionStartPrice) != null) {
					Amount auctionStartPrice = new Amount();
					auctionStartPrice.setValue(row.get(l.auctionStartPrice));
					listing.setAuctionStartPrice(auctionStartPrice);
				}
			}

			if (l.categoryId.equals(rpath)) {
				if(row.get(l.categoryId) != null) {
					List<Classification> classifications = new ArrayList<Classification>();
					Classification classification = new Classification();
					classification
							.setClassificationType(ClassificationTypeEnum.CATEGORY);
					classification.setClassifierId(row.get(l.categoryId));
					listing.setClassifications(classifications);
				}
			}

			if (l.condition.equals(rpath)) {
				listing.setCondition(row.get(l.condition));
			}

			if (l.createdDate.equals(rpath)) {
				Timestamp ts = row.get(l.createdDate);
				if(ts != null) {
					listing.setCreateDate(new Date(ts.getTime()));
				}
			}

			if (l.itemEndTime.equals(rpath)) {
				Timestamp ts = row.get(l.itemEndTime);
				if(ts != null) {
					listing.setListingEndTime(new Date(ts.getTime()));
				}
			}

			if (l.itemStartTime.equals(rpath)) {
				Timestamp ts = row.get(l.itemStartTime);
				if(ts != null) {
					listing.setListingStartTime(new Date(ts.getTime()));
				}
			}

			if (l.lastModifiedApp.equals(rpath)) {
				if(row.get(l.lastModifiedApp) != null) {
					listing.setLastModifiedApp(row.get(l.lastModifiedApp).intValue());
				}
			}

			if (l.lastModifiedDate.equals(rpath)) {
				Timestamp ts = row.get(l.lastModifiedDate);
				if(ts != null) {
					listing.setLastModifiedDate(new Date(ts.getTime()));
				}
			}

			if (l.marketplaceId.equals(rpath)) {
				if(row.get(l.marketplaceId) != null) {
					int id = row.get(l.marketplaceId).intValue();
					MarketEnum market = MarketEnum.get(id);
					if(market != null) {
						listing.setMarket(market);
					}
				}
			}

			if (l.marketplaceItemId.equals(rpath)) {
				listing.setMarketPlaceListingId(row.get(l.marketplaceItemId));
			}

			if (l.masterTemplateId.equals(rpath)) {
				listing.setMasterTemplateId(row.get(l.masterTemplateId));
			}

			if (l.parentItemId.equals(rpath)) {
				listing.setParentListingId(row.get(l.parentItemId));
			}

			if (l.productId.equals(rpath)) {
				if(row.get(l.productId) != null) {
					Product product = new Product();
					product.setProductId(row.get(l.productId));
					listing.setProduct(product);
				}
			}

			if (l.quantity.equals(rpath)) {
				listing.setQuantity(row.get(l.quantity));
			}
			if (l.warranty.equals(rpath)) {
				listing.setWarranty(row.get(l.warranty));
			}
		}
		return listing;
	}
}