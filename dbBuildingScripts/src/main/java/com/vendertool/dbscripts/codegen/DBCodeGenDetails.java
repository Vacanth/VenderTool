package com.vendertool.dbscripts.codegen;

public class DBCodeGenDetails {

	private String packageName;
	private String targetFolder;
	private String tableNamePattern;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getTargetFolder() {
		return targetFolder;
	}
	public void setTargetFolder(String targetFolder) {
		this.targetFolder = targetFolder;
	}
	public String getTableNamePattern() {
		return tableNamePattern;
	}
	public void setTableNamePattern(String tablePattern) {
		this.tableNamePattern = tablePattern;
	}
}