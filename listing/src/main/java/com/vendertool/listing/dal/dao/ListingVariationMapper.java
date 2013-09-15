package com.vendertool.listing.dal.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.DALMapper;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.listing.dal.dao.codegen.QBeanListingVariation;
import com.vendertool.listing.dal.dao.codegen.QListingVariation;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.ListingVariation;

public class ListingVariationMapper implements DALMapper<ListingVariation> {

	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	protected ListingVariationMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}
	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			ListingVariation listingVariation) {
		if (VUTIL.isNull(listingVariation)) {
			return null;
		}
		QListingVariation lv = QListingVariation.listingVariation;

		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();

		for (Path<?> rpath : paths) {
			if (lv.condition.equals(rpath)) {
				map.put(lv.condition, listingVariation.getCondition());
			}

			if (lv.createdDate.equals(rpath)) {
				Date date = listingVariation.getCreatedDate();
				if (date == null) {
					date = new Date();
				}
				map.put(lv.createdDate, new Timestamp(date.getTime()));
			}

			if (lv.lastModifiedApp.equals(rpath)) {
				map.put(lv.lastModifiedApp,
						(byte) listingVariation.getLastModifiedApp());
			}

			if (lv.lastModifiedDate.equals(rpath)) {
				Date date = listingVariation.getLastModifiedDate();
				if (date == null) {
					date = new Date();
				}
				map.put(lv.lastModifiedDate, new Timestamp(date.getTime()));
			}

			if (lv.listingId.equals(rpath)) {
				map.put(lv.listingId, listingVariation.getListingId());
			}

			if (lv.listingVariationId.equals(rpath)) {
				map.put(lv.listingVariationId,
						listingVariation.getListingVariationId());
			}

			if (lv.price.equals(rpath)) {
				Amount price = listingVariation.getPrice();
				if (price != null) {
					map.put(lv.price, price.getValue());
				}
			}

			if (lv.productVariationId.equals(rpath)) {
				map.put(lv.productVariationId,
						listingVariation.getProductVariationId());
			}

			if (lv.quantity.equals(rpath)) {
				map.put(lv.quantity, listingVariation.getQuantity());
			}

		}
		return map;
	}

	@Override
	public Object populateBean(ListingVariation listingVariation) {
		if (VUTIL.isNull(listingVariation)) {
			return null;
		}
		QBeanListingVariation bean = new QBeanListingVariation();
		bean.setCondition(listingVariation.getCondition());
		bean.setLastModifiedApp((byte) listingVariation.getLastModifiedApp());

		Date creationDate = listingVariation.getCreatedDate();
		if (creationDate != null) {
			bean.setCreatedDate(new Timestamp(creationDate.getTime()));
		}

		Date lastModifDate = listingVariation.getLastModifiedDate();
		if (lastModifDate != null) {
			bean.setLastModifiedDate(new Timestamp(lastModifDate.getTime()));
		}

		bean.setListingId(listingVariation.getListingId());
		bean.setListingVariationId(listingVariation.getListingVariationId());
		bean.setProductVariationId(listingVariation.getProductVariationId());

		bean.setQuantity(listingVariation.getQuantity());
		Amount price = listingVariation.getPrice();
		if (price != null) {
			bean.setPrice(price.getValue());
		}

		return bean;
	}

	@Override
	public ListingVariation convert(Tuple row, Path<?>[] paths) {
		if (VUTIL.isNull(row)) {
			return null;
		}

		if (VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}

		if (VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}

		QListingVariation lv = QListingVariation.listingVariation;

		ListingVariation listingVariation = new ListingVariation();
		for (Path<?> rpath : paths) {
			if (lv.listingId.equals(rpath)) {
				listingVariation.setListingId(row.get(lv.listingId));
			}

			if (lv.condition.equals(rpath)) {
				listingVariation.setCondition(row.get(lv.condition));
			}

			if (lv.createdDate.equals(rpath)) {
				listingVariation.setCreatedDate(row.get(lv.createdDate));
			}

			if (lv.lastModifiedApp.equals(rpath)) {
				listingVariation
						.setLastModifiedApp(row.get(lv.lastModifiedApp));
			}

			if (lv.lastModifiedDate.equals(rpath)) {
				listingVariation.setLastModifiedDate(row
						.get(lv.lastModifiedDate));
			}

			if (lv.listingVariationId.equals(rpath)) {
				listingVariation.setListingVariationId(row
						.get(lv.listingVariationId));
			}

			if (lv.price.equals(rpath)) {
				Amount price = new Amount();
				price.setValue(row.get(lv.price));
				listingVariation.setPrice(price);
			}

			if (lv.productVariationId.equals(rpath)) {
				listingVariation.setProductVariationId(row
						.get(lv.productVariationId));
			}

			if (lv.quantity.equals(rpath)) {
				listingVariation.setQuantity(row.get(lv.quantity));
			}
		}
		return listingVariation;
	}
}