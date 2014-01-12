package com.app.wms.web.controller;

import java.util.List;
import java.util.Map;

import com.report.test.ReportModel;
import com.report.test.ReportModel.Pair;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Extras implements PostProcess {
	private Map map;

	public Extras(Pair... extras){
		map = ReportModel.asMap(extras);
	}

	@Override
	public void process(List data, Map beans) {
		beans.putAll(map);
	}

}
