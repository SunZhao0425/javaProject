package com.example.springdemo.dto.resp;

import lombok.Data;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-06-09 16:00
 * @Describe
 */

@Data
public class BaseRespDto<T> {
    public Integer error  = 0;
    public String message = "成功";
    public T data;

    /**
     * 出错
     * @param message
     * @return
     */
    public static BaseRespDto errorResp(String message) {
        BaseRespDto baseRespDto = new BaseRespDto();
        baseRespDto.setError(-1);
        baseRespDto.setMessage(message);
        return baseRespDto;
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public  static BaseRespDto successResp(Object data){
        BaseRespDto baseRespDto = new BaseRespDto();
        baseRespDto.setData(data);
        return baseRespDto;
    }
}
