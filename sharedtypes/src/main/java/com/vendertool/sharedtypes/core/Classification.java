package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

@XmlAccessorType(XmlAccessType.FIELD)
public class Classification {
	
	@XmlEnum
	public enum ClassificationTypeEnum {
		CATEGORY(1),PRODUCT(2),KEYWORD(3),TITLE(4);
		
		private int id;

		private ClassificationTypeEnum(int id) {
			this.id = id; 
		}

		public int getId() {
			return id;
		}

		public static ClassificationTypeEnum get(int id) {
			ClassificationTypeEnum[] values = ClassificationTypeEnum.values();
			for (ClassificationTypeEnum typeEnum : values) {
				if (typeEnum.getId() == id) {
					return typeEnum;
				}
			}
			return null;
		}
	}
	
	private ClassificationTypeEnum classificationType;
	private String classifierId;
	
	public Classification(){}

	public ClassificationTypeEnum getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(ClassificationTypeEnum classificationType) {
		this.classificationType = classificationType;
	}

	public String getClassifierId() {
		return classifierId;
	}

	public void setClassifierId(String classifierId) {
		this.classifierId = classifierId;
	}
}
