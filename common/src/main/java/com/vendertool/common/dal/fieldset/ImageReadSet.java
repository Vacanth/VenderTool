package com.vendertool.common.dal.fieldset;


import com.mysema.query.types.Path;
import com.vendertool.inventory.dal.dao.codegen.QImage;

public class ImageReadSet {
	private static final QImage IMAGE = QImage.image;
	
	private static class ImageReadSetHolder {
		private static final ImageReadSet INSTANCE = new ImageReadSet();
	}
	
	public static ImageReadSet getInstance() {
		return ImageReadSetHolder.INSTANCE;
	}
	
	
	public final Path<?>[] ALL = {
		
	};
	
	
}
