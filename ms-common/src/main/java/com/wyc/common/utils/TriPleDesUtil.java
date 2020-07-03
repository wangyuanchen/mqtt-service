package com.wyc.common.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author duanwenhao
 * @version V1.0
 * @Package com.aisino.social.client.utils
 * @Description: 3DES加解密
 * @date 2019/9/16 -   15:06
 */
public class TriPleDesUtil {

    //------ZERO------
    //3DES算法
    private static final String Algorithm = "DESede";
    private static final String Algorithmm = "DESede/ECB/NoPadding";
    //编码
    public static final String CHARSET_GBK = "GBK";

    //------PKCS5------
    // 向量
    private final static String iv = "        ";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * @Description: (加密算法)
     * @Param: [context  明文, key 秘钥]
     * @return: java.lang.String
     * @Author: duanwenhao
     * @Date: 2019/9/19
     */
    public static String encode3Des(String context, String key) {
        try {
            //转换byte数组
            byte[] mingwenByte = context.getBytes(CHARSET_GBK);
            //不足8位补全0
            int len = mingwenByte.length + ((0==mingwenByte.length%8)?0:8-mingwenByte.length%8);
            byte[] enk = new byte[len];
            for (int i=0;i<mingwenByte.length;i++){
                enk[i] = mingwenByte[i];
            }
            //生成密钥21
            SecretKey deskey = new SecretKeySpec(key.getBytes(), Algorithm);
            //实例化负责加密/解密的Cipher工具类22
            Cipher c1 = Cipher.getInstance(Algorithmm);
            //初始化为加密模式23
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(c1.doFinal(enk));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: (解密)
     * @Param: [src  密文, key 秘钥]
     * @return: java.lang.String
     * @Author: duanwenhao
     * @Date: 2019/9/19
     */
    public static String decode3Des(String context, String key) {

        try {
            //转换成byte数组
            byte[] decode = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(context);
            SecretKey deskey = new SecretKeySpec(key.getBytes(), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithmm);
            //初始化为解密模式
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return new String(c1.doFinal(decode),CHARSET_GBK);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;

    }



    /**
     * 3DES加密PKCS5
     * @param plainText 需要加密的文本
     * @param secretKey 秘钥
     * @return
     * @throws Exception
     */
    public static String encode3DESPKCS5(String plainText, String secretKey) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encode(encryptData);
    }

    /**
     * 3DES解密PKCS5
     * @param encryptText 需要加密的文本
     * @param secretKey 秘钥
     * @return
     * @throws Exception
     */
    public static String decode3DESPKCS5(String encryptText, String secretKey) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData, encoding);
    }

    public static void main(String[] args) throws Exception {

//        String dataEncode = TriPleDesUtil.encode3DesPKCS5("");
//        String keyEncode = TriPleDesUtil.encode3DesPKCS5("");
//        System.out.println(dataEncode);
//        System.out.println(keyEncode);

    }
}
