package com.wuhulala.quartz.pojo;

public class JobDto extends TimerJob{
	
	
	public String getJobDetailKey(){
		return new StringBuilder().append("jobDetail")
								  .append("_").append(this.getExchangeId())
								  .append("_").append(this.getFunctionId())
								  .toString();
	}
	
	public String getTriggerKey(){
		return new StringBuilder().append("trigger")
								  .append("_").append(this.getExchangeId())
								  .append("_").append(this.getFunctionId())
								  .toString();
	}

}
