package com.github.fashionbrot.common.vo;

import com.github.fashionbrot.common.constant.GlobalConst;
import com.github.fashionbrot.common.enums.RespCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespVo<T> implements Serializable{

    private static final long serialVersionUID = 359373498032407677L;

    public static final int SUCCESS = RespCode.SUCCESS.getCode();
    public static final int FAILED = RespCode.FAIL.getCode();

    private int code;
    private String msg;
    private T data;


    public static RespVo fail(String msg){
        return RespVo.builder().code(FAILED).msg(msg).build();
    }

    public static RespVo fail(String msg, int code){
        return RespVo.builder().code(code).msg(msg).build();
    }
    public static RespVo fail(String msg, int code, Object data){
        return RespVo.builder().code(code).msg(msg).data(data).build();
    }

    public static<T> RespVo success(T data){
        return RespVo.builder().code(SUCCESS).msg("成功").data(data).build();
    }

    public static RespVo success(){
        return GlobalConst.RESP_VO;
    }

    public static RespVo respCode(RespCode respCode){
        return RespVo.builder().code(respCode.getCode()).msg(respCode.getMsg()).build();
    }

}
