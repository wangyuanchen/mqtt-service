package com.wyc.common.utils;

import com.wyc.gm.GM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
　* @Description: 与客户端报文加解密-国密算法
　* @author lidanyang
　* @date 2020/3/13 14:32
　*/
public class SM4DesUtil {

    private static Logger logger = LoggerFactory.getLogger(SM4DesUtil.class);

    /**
     * GM加密方法
     */
    public static String smEncryptEcb(String str, String key){
        GM gm = null;
        try {
            gm = new GM();
        } catch (Exception e) {
            logger.error("SM4加密失败");
        }
        byte[] key_b = (key + "\0").getBytes();
        byte[] plaintext = (str + "\0").getBytes();
        int plaintext_len = plaintext.length;
        int len=plaintext_len/16;
        if(plaintext_len % 16!=0){
            len+=1;
        }
        len*=16;
        //密文
        byte enc_outbuf[] = new byte[len/3*4+128];
        int mode = 1;
        gm.SM4_encrypt_ecb(key_b, mode, plaintext, plaintext_len, enc_outbuf);
        return new String(enc_outbuf).trim();
    }

    /**
     * GM解密算法
     */
    public static String smDecryptEcb(String str, String key){
        GM gm = null;
        try {
            gm = new GM();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int mode = 1;
        byte[] key_b = (key + "\0").getBytes();
        byte[] enc_outbuf = (str + "\0").getBytes();
        int len = (enc_outbuf.length) * 3 / 4 + 128;
        byte[] dec_outbuf = new byte[len];
        gm.SM4_decrypt_ecb(key_b, mode, enc_outbuf, enc_outbuf.length, dec_outbuf);
        return new String(dec_outbuf).trim();
    }

    public static void main(String args[]) throws Exception {


//        System.out.println("ldy encypt");
//
//        System.out.println(smEncryptEcb("{\n" +
//                " \"cTaxNo\":\"12345678901234567\",\n" +
//                " \"cAreaCode\":\"111111111111\",\n" +
//                " \"iMachineNo\":0,\n" +
//                " \"iSource\":\"0\"\n" +
//                "}", "cf73f209555e4d59"));
//
//        System.out.println("ldy decypt");

//        System.out.println(smDecryptEcb(smEncryptEcb("test", "cf73f209555e4d59"), "cf73f209555e4d59"));

        for (int i = 0; i <= 1000; i++) {
            Thread.sleep(1 * 1000);
            System.out.println(smDecryptEcb("uYseAtMsVLTdF90fOcvamA==", "QWERTYUIOPASDFGHJKLZXCVB"));
        }

    }

}
