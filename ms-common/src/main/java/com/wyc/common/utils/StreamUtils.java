package com.wyc.common.utils;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 流工具类
 */
public class StreamUtils {

    public static Map<String, Object> zip2FileConvert(String fileStr) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\" + System.currentTimeMillis();
        String fileName = "zzz.zip";
        byte[] midbytes = DatatypeConverter.parseBase64Binary(fileStr);
        StreamUtils streamUtils = new StreamUtils();
        String path = streamUtils.readFile(midbytes, filePath, fileName);
        if (StringUtils.isEmpty(path)){
            throw new IOException("流文件读取错误");
        }
        Map<String, Object> map = streamUtils.getFile(path);
        // 删除
        streamUtils.delFolder(filePath);
        return map;
    }
    /**
     * 获取文件
     * @param sourcefiles
     * @return
     */
    public  Map<String, Object> getFile(String sourcefiles){
        ZipFile readfile;
        readfile = null;
        byte[] buffer = null;
        String fileName = "";
        try {
            readfile =new ZipFile(sourcefiles);
            Enumeration takeentrie = readfile.getEntries();
            ZipEntry zipEntry = null;
            while (takeentrie.hasMoreElements()){
                zipEntry = (ZipEntry)takeentrie.nextElement();
                String entryName = zipEntry.getName();
                InputStream in = null;
                try{
                    if(zipEntry.isDirectory()){
                        String name = zipEntry.getName();
                    }else{
                        fileName = zipEntry.getName();
                        in = readfile.getInputStream(zipEntry);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        int ch;
                        byte[] by = new byte[1024];
                        while((ch = in.read(by)) != -1){
                            bos.write(by, 0, ch);
                        }
                        bos.flush();
                        buffer = bos.toByteArray();
                    }
                }catch(IOException ex){
                    ex.printStackTrace();
                }finally{
                    if(in != null){
                        try{
                            in.close();
                        }catch(IOException ex){
                        	ex.printStackTrace();
                        }
                    }
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            if(readfile != null){
                try{
                    readfile.close();
                }catch(IOException ex){
	                ex.printStackTrace();
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("registerDocName", fileName.substring(fileName.lastIndexOf("/") + 1));
        map.put("registerDocStream", DatatypeConverter.printBase64Binary(buffer));
        return map;
    }

    /**
     * 根据byte数组，生成文件
     */
    public  String readFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            return filePath + "\\" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 删除文件夹
     * @param folderPath 文件夹完整绝对路径
     */
    private void delFolder(String folderPath) {
        try {
            //删除完里面所有内容
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            //删除空文件夹
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件夹下所有文件
     * @param path 文件夹完整绝对路径
     */
    private boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) { return flag; }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            //是文件
            if (temp.isFile()) {
                System.gc();
                temp.delete();
                flag = true;
            }
            //是文件夹
            if (temp.isDirectory()) {
                //先删除文件夹里面的文件
                delAllFile(path + "/" + tempList[i]);
                //再删除空文件夹
                delFolder(path + "/" + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }
}