package com.mtlckj.base.jqfx.service;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.vo.AnalysisParam;

public interface AnalysisService {
	List<List<Object>> analysis(AnalysisParam analysisParam);
}
