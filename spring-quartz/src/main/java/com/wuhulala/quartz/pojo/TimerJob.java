package com.wuhulala.quartz.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimerJob implements Serializable{

    private String exchangeId;

	private String functionId;

	private String jobName;
	
	private String remark;

	private String expression;

	private String jobState;

	private Date nextFireTime;

}