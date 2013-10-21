package com.vendertool.common.dal.fieldset;


import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.codegen.QImage;

public class ImageReadSet {
	private static final QImage IMAGE = QImage.image;
	
	private static class ImageReadSetHolder {
		private static final ImageReadSet INSTANCE = new ImageReadSet();
	}
	
	public static ImageReadSet getInstance() {
		return ImageReadSetHolder.INSTANCE;
	}
	
	
	public final Path<?>[] ALL = {
			IMAGE.accountId,
			IMAGE.createdDate,
			IMAGE.hostedUrl,
			IMAGE.hash,
			IMAGE.imageFormat,
			IMAGE.imageId,
			IMAGE.imageName,
			IMAGE.lastModifiedDate,
			IMAGE.refId,
			IMAGE.refType,
			IMAGE.size,
			IMAGE.sortOrderId		
	};
	
	
}
