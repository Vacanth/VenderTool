package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dimension {
	
	@XmlEnum
	public enum DimensionUnitEnum {
		UNKNOWN(-1),MM(1),CMS(2),INCHES(3),FEET(4);
		
		private int id;

		private DimensionUnitEnum(int id) {
			this.id = id; 
		}

		public int getId() {
			return id;
		}

		public static DimensionUnitEnum get(int id) {
			DimensionUnitEnum[] values = DimensionUnitEnum.values();
			for (DimensionUnitEnum typeEnum : values) {
				if (typeEnum.getId() == id) {
					return typeEnum;
				}
			}
			return UNKNOWN;
		}
	}
	
	private Double length;
	private Double width;
	private Double height;
	private DimensionUnitEnum dimensionUnit;
	
	public Dimension() {
	}
	
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public DimensionUnitEnum getDimensionUnit() {
		return dimensionUnit;
	}
	public void setDimensionUnit(DimensionUnitEnum dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}
}
