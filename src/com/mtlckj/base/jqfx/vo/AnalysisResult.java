package com.mtlckj.base.jqfx.vo;

import java.util.List;

public class AnalysisResult {
	private String wdm;
	private String wdzs;
	private List<String> wdVals;
	private List<ZbResult> zbResults;
	public String getWdm() {
		return wdm;
	}
	public void setWdm(String wdm) {
		this.wdm = wdm;
	}
	public List<String> getWdVals() {
		return wdVals;
	}
	public void setWdVals(List<String> wdVals) {
		this.wdVals = wdVals;
	}
	public String getWdzs() {
		return wdzs;
	}
	public void setWdzs(String wdzs) {
		this.wdzs = wdzs;
	}
	public List<ZbResult> getZbResults() {
		return zbResults;
	}
	public void setZbResults(List<ZbResult> zbResults) {
		this.zbResults = zbResults;
	}
	
	
}
