package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "create_id",fill = FieldFill.INSERT)
    private Long createId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="create_date",fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(value = "update_id",fill = FieldFill.UPDATE)
    private Long updateId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
    private Date updateDate;


    @TableLogic(value = "0", delval = "1")
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    private int delFlag;

}
