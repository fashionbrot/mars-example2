package com.github.fashionbrot.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 日志表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2020-09-14
 */
@Data
@ApiModel(value = "日志表")
public class LogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "自增id")
	private Long id;

	@ApiModelProperty(value = "请求描述")
	private String requestDesc;

	@ApiModelProperty(value = "请求url")
	private String requestUrl;

	@ApiModelProperty(value = "请求方法")
	private String requestMethod;

	@ApiModelProperty(value = "请求ip")
	private String requestIp;

	@ApiModelProperty(value = "请求参数")
	private String requestParam;

	@ApiModelProperty(value = "接口方法")
	private String targetMethod;

	@ApiModelProperty(value = "1 请求日志 2:异常日志")
	private Integer logType;

	@ApiModelProperty(value = "接口类型 1:后台日志 2:机构后台日志 3:web 端日志")
	private Integer interfaceType;

	@ApiModelProperty(value = "创建者id")
	private Long createId;

	@ApiModelProperty(value = "创建者名字")
	private String createName;

	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

	private String exception;


}