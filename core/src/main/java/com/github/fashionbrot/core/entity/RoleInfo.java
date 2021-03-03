package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Data
@TableName("sys_role_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleInfo extends BaseEntity {

    @TableField(value = "status")
    private int status;

    @TableField("role_code")
    private String roleCode;

    @TableField("role_name")
    private String roleName;


    @TableField(exist = false)
    private  String menuIds;

}
