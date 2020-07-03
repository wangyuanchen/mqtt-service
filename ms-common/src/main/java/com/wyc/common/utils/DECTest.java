package com.wyc.common.utils;


import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

/**
　* @Description: zlib压缩解压缩
　* @author lidanyang
　* @date 2020/5/14 15:56
　*/
public class DECTest {

    private static Logger log = LoggerFactory.getLogger(DECTest.class);

    /**
     * zlib解压+base64
     */
    public static String decompressData(String encdata) {
        if (encdata == null) {
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InflaterOutputStream zos = new InflaterOutputStream(bos);
            zos.write(Base64.decodeBase64(encdata.getBytes()));
            zos.close();
            return new String(bos.toByteArray());
        } catch (Exception ex) {
            log.error("zlib解压缩异常：" + ex);
        }
        return null;
    }


    /**
     * zlib压缩+base64
     */
    public static String compressData(String data) {
        ByteArrayOutputStream bos;
        DeflaterOutputStream zos;
        try {
            bos = new ByteArrayOutputStream();
            zos = new DeflaterOutputStream(bos);
            zos.write(data.getBytes());
            zos.close();
            return new String(Base64.encodeBase64(bos.toByteArray()));
        } catch (IOException e) {
            log.error("zlib压缩异常：" + e);
        }
        return null;
    }

}
