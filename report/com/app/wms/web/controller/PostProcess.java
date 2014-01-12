package com.app.wms.web.controller;

import java.util.List;
import java.util.Map;

public interface PostProcess {
	@SuppressWarnings("rawtypes")
	public void process(List data, Map beans);
}
