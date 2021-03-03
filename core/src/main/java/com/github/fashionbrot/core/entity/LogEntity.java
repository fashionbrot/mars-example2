package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 日志表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2020-09-14
 */
@ApiModel(value = "日志表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_log")
public class LogEntity {

	@TableId(type = IdType.AUTO)
	@ApiModelProperty("自增id，新增不需要传入，修改必须传入")
	private Long id;

	@ApiModelProperty(value = "请求描述")
	@TableField("request_desc")
	private String requestDesc;

	@ApiModelProperty(value = "请求url")
	@TableField("request_url")
	private String requestUrl;

	@ApiModelProperty(value = "请求方法")
	@TableField("request_method")
	private String requestMethod;

	@ApiModelProperty(value = "请求ip")
	@TableField("request_ip")
	private String requestIp;

	@ApiModelProperty(value = "请求参数")
	@TableField("request_param")
	private String requestParam;

	@ApiModelProperty(value = "接口方法")
	@TableField("target_method")
	private String targetMethod;

	@ApiModelProperty(value = "1 请求日志 2:异常日志")
	@TableField("log_type")
	private Integer logType;

	@ApiModelProperty(value = "接口类型 1:后台日志 2:机构后台日志 3:web 端日志")
	@TableField("interface_type")
	private Integer interfaceType;

	@TableField("exception")
	private String exception;

	@ApiModelProperty(hidden = true)
	@TableField(value = "create_id",fill = FieldFill.INSERT)
	private Long createId;

	@ApiModelProperty(hidden = true)
	@TableField(value="create_date",fill = FieldFill.INSERT)
	private Date createDate;

	@ApiModelProperty(hidden = true)
	@TableLogic(value = "0", delval = "1")
	@TableField(value = "del_flag",fill = FieldFill.INSERT)
	private int delFlag;

	@ApiModelProperty(value = "创建者名字")
	@TableField(exist = false)
	private  String createName;
}