package com.vendertool.tools.db.codegen;

import java.util.List;

public class DBConnectionData {

	private String url;
	private String user;
	private String password;
	private String host;
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private List<DBCodeGenDetails> codeGenDetails;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DBCodeGenDetails> getCodeGenDetails() {
		return codeGenDetails;
	}

	public void setCodeGenDetails(List<DBCodeGenDetails> codeGenDetails) {
		this.codeGenDetails = codeGenDetails;
	}
}