package com.example.springdemo.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

/**
 * @version V1.0
 * @ClassName
 * @Author zhaohongyan
 * @Date: 2022-02-08 10:22
 * @Describe
 */

@Slf4j
public class WxUtils {
    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String wxDecrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            log.info("解密组合resultByte: {}",resultByte);
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.fromObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }
}
