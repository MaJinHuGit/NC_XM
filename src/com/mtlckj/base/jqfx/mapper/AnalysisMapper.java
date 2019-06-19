package com.mtlckj.base.jqfx.mapper;

import java.util.List;
import java.util.Map;

import com.mtlckj.base.jqfx.vo.AnalysisParam;

public interface AnalysisMapper {
	List<Map<String, Object>> analysis(AnalysisParam analysisParam);
}
