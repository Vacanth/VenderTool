package com.vendertool.mercadolibreadapter;

import com.vendertool.mercadolibreadapter.client.MLClientBeanFactory;


public class Module {

	private static class ModuleHolder {
		private static final Module INSTANCE = new Module();
	}

	public static Module getInstance() {
		return ModuleHolder.INSTANCE;
	}

	private Module() {
		
	}

	public void init() {
		MLClientBeanFactory.getInstance().init();
	}
}