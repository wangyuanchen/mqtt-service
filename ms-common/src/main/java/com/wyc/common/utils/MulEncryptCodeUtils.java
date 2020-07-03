package com.wyc.common.utils;

/**
 * @author: wangyuanchen
 * @date: 2020-3-17 13:37
 * @description:
 */
public class MulEncryptCodeUtils {

    /**
     * 3des
     */
    private static String TRIPLE_DES = "1";
    /**
     * CA
     */
    private static String CA = "2";
    /**
     * AES
     */
    private static String AES = "3";
    /**
     * SM2
     */
    private static String SM2 = "4";


    /**
     * 3des加密
     * @param contentMingWen
     * @param clientSecretKey
     * @return
     */
    public static String encode3Des(String contentMingWen, String clientSecretKey){
        return encode(TRIPLE_DES, contentMingWen, clientSecretKey);
    }

    /**
     *CA加密
     * @param contentMingWen
     * @param clientSecretKey
     * @return
     */
    public static String encodeCa(String contentMingWen, String clientSecretKey){
        return encode(CA, contentMingWen, clientSecretKey);
    }

    /**
     *AES加密
     * @param contentMingWen
     * @param clientSecretKey
     * @return
     */
    public static String encodeAes(String contentMingWen, String clientSecretKey){
        return encode(AES, contentMingWen, clientSecretKey);
    }

    /**
     *SM2加密
     * @param contentMingWen
     * @param clientSecretKey
     * @return
     */
    public static String encodeSm2(String contentMingWen, String clientSecretKey){
        return encode(SM2, contentMingWen, clientSecretKey);
    }

    public static String encode(String encryptCode, String contentMingWen, String clientSecretKey) {
        String miWen = "";
        switch (encryptCode) {
            case "1":
                //3DES
                miWen = TriPleDesUtil.encode3Des(contentMingWen, clientSecretKey);
                break;
            case "2":
                //CA
                break;
            case "3":
                //AES
                break;
            case "4":
                //SM4
                miWen = SM4DesUtil.smEncryptEcb(contentMingWen, clientSecretKey);
                break;
            default:
                miWen = "";
        }
        return miWen;
    }

    /**
     * 3des解密
     * @param contentMiWen
     * @param clientSecretKey
     * @return
     */
    public static String decode3Des(String contentMiWen, String clientSecretKey){
        return decode(TRIPLE_DES, contentMiWen, clientSecretKey);
    }

    /**
     *CA解密
     * @param contentMiWen
     * @param clientSecretKey
     * @return
     */
    public static String decodeCa(String contentMiWen, String clientSecretKey){
        return decode(CA, contentMiWen, clientSecretKey);
    }

    /**
     *AES解密
     * @param contentMiWen
     * @param clientSecretKey
     * @return
     */
    public static String decodeAes(String contentMiWen, String clientSecretKey){
        return decode(AES, contentMiWen, clientSecretKey);
    }

    /**
     *SM2解密
     * @param contentMiWen
     * @param clientSecretKey
     * @return
     */
    public static String decodeSm2(String contentMiWen, String clientSecretKey){
        return decode(SM2, contentMiWen, clientSecretKey);
    }

    public static String decode(String encryptCode, String contentMiWen, String clientSecretKey) {
        String plaintext = "";
        switch (encryptCode) {
            case "1":
                //3DES
                plaintext = TriPleDesUtil.decode3Des(contentMiWen, clientSecretKey);
                break;
            case "2":
                //CA
                break;
            case "3":
                //AES
                break;
            case "4":
                //SM4
                plaintext = SM4DesUtil.smDecryptEcb(contentMiWen, clientSecretKey);
                break;
            default:
                plaintext = "";
        }
        return plaintext;
    }
}
