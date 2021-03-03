package com.github.fashionbrot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fashionbrot.core.entity.RoleInfo;
import com.github.fashionbrot.core.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo>{


    @Update("UPDATE sys_user_info SET last_login_time=#{lastLoginTime} WHERE id=#{id}")
    int updateLastLoginTime(@Param("id") Long id, @Param("lastLoginTime") Date timestamp);

    @Select("SELECT a.super_admin,a.id,a.user_name,a.real_name,a.`password`,a.create_date,a.`status`,a.last_login_time,a.update_date,c.role_name,c.id as roleId " +
            " from sys_user_info a " +
            " left JOIN sys_user_role_relation b on a.id=b.user_id" +
            " left JOIN sys_role_info c on c.id=b.role_id where a.del_flag=0 ")
    List<UserInfo> queryAll();

    @Select("select r.role_name " +
            "from sys_user_info u " +
            "inner join sys_user_role_relation ur on ur.user_id = u.id " +
            "INNER JOIN sys_role_info r on r.id= ur.role_id  where  a.del_flag=0 and u.id = #{userId}")
    RoleInfo findByUserId(@Param("userId") Long userId);
}
