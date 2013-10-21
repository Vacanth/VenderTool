package com.vendertool.common.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.codegen.QImage;

public class ImageUpdateSet {
	private static final QImage IMAGE = QImage.image;
	
	private static class ImageUpdateSetHolder {
		private static final ImageUpdateSet INSTANCE = new ImageUpdateSet();
	}
	
	public static ImageUpdateSet getInstance() {
		return ImageUpdateSetHolder.INSTANCE;
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
