package com.vendertool.common.dal.dao;


import java.util.HashMap;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.codegen.QBeanImage;
import com.vendertool.common.dal.dao.codegen.QImage;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.core.Image;
import com.vendertool.sharedtypes.core.Image.ImageFormatEnum;

public class ImageMapper implements DALMapper<Image> {
//	private static final Logger logger = Logger.getLogger(ImageMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	public ImageMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			Image image) {
		if(VUTIL.isNull(image)) {
			return null;
		}
		
		QImage a = QImage.image;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(a.imageId.equals(rpath)) {
				if (image.getImageId() != null) 
				map.put(a.imageId, image.getImageId());
			}
			
			if(a.accountId.equals(rpath)) {
				if (image.getAccountId() != null) 
				map.put(a.accountId, image.getAccountId());
			}
			if(a.hash.equals(rpath)) {
				if (image.getHash() != null) 
				map.put(a.hash, image.getHash());
			}
			if(a.hostedUrl.equals(rpath)) {
				if (image.getImgurl() != null) 
				map.put(a.hostedUrl, image.getImgurl());
			}
			if(a.imageFormat.equals(rpath)) {
				if (image.getFormat() != null) 
				map.put(a.imageFormat, image.getFormat());
			}
			if(a.imageName.equals(rpath)) {
				if (image.getName() != null) 
				map.put(a.imageName, image.getName());
			}
			if(a.size.equals(rpath)) {
				if (image.getSize() != null) 
				map.put(a.size, image.getSize());
			}
			if(a.sortOrderId.equals(rpath)) {
				map.put(a.sortOrderId, image.getSortOrderId());
			}
			

		}
		
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	public QBeanImage populateBean(Image image) {
		if(VUTIL.isNull(image)) {
			return null;
		}
		
		QBeanImage bean = new QBeanImage();
		bean.setImageId(image.getImageId());
		bean.setAccountId(image.getAccountId());
		
		if(image.getFormat() != null) {
			bean.setImageFormat((byte)image.getFormat().getId());
		}
		if(image.getName() != null) {
		bean.setImageName(image.getName());
		}
		if(image.getSize() != null) {
			bean.setSize(image.getSize());
		}
			bean.setSortOrderId(image.getSortOrderId());

		
		
		
		return bean;
		
	}
	
	public Image convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QImage a = QImage.image;
		Image image = new Image();
		
		for(Path<?> rpath : paths) {
			if(a.imageId.equals(rpath)) {
				image.setImageId(row.get(a.imageId));
			}
			if(a.accountId.equals(rpath)) {
				image.setAccountId(row.get(a.accountId));
			}
			if(a.hash.equals(rpath)) {
				image.setHash(row.get(a.hash));
			}
			if(a.hostedUrl.equals(rpath)) {
				image.setImgurl(row.get(a.hostedUrl));
			}
			if(a.imageFormat.equals(rpath)) {
				image.setFormat(ImageFormatEnum.get(row.get(a.imageFormat)));
			}
			if(a.imageName.equals(rpath)) {
				image.setName(row.get(a.imageName));
			}
			if(a.sortOrderId.equals(rpath)) {
				image.setSortOrderId(row.get(a.sortOrderId));
			}

		}
		
		return image;
	}
}
