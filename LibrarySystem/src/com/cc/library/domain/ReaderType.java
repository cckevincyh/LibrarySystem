package com.cc.library.domain;

public class ReaderType {

	private Integer readerTypeId;
	private Integer maxNum;	//最大借书量
	private Double penalty;	//每日罚金
	private Integer bday;	//可借天数
	
	public Integer getReaderTypeId() {
		return readerTypeId;
	}
	public void setReaderTypeId(Integer readerTypeId) {
		this.readerTypeId = readerTypeId;
	}
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	public Double getPenalty() {
		return penalty;
	}
	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}
	public Integer getBday() {
		return bday;
	}
	public void setBday(Integer bday) {
		this.bday = bday;
	}
	public ReaderType(Integer readerTypeId, Integer maxNum, Double penalty,
			Integer bday) {
		super();
		this.readerTypeId = readerTypeId;
		this.maxNum = maxNum;
		this.penalty = penalty;
		this.bday = bday;
	}
	public ReaderType() {

	}
	
	@Override
	public String toString() {
		return "ReaderType [readerTypeId=" + readerTypeId + ", maxNum="
				+ maxNum + ", penalty=" + penalty + ", bday=" + bday + "]";
	}

	
	
	
	

}
