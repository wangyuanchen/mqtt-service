package com.wyc.gm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

public class GM {

    private static Logger log = LoggerFactory.getLogger(GM.class);

    //private static volatile GM gm;
    public String sigCert;
    public String subject;
    public String serial;
    public byte[] data;

    public GM() throws Exception {
        String os_name = System.getProperty("os.name");
        String os_arch = System.getProperty("os.arch");
        String libname = "";
        if (os_name.startsWith("Windows")) {
            if (os_arch.startsWith("x86")) {
                libname = "GM_JNI_x86.dll";
            } else {
                libname = "GM_JNI_x64.dll";
            }
            this.loadLib(libname, 1);
        } else if (os_name.startsWith("Linux")) {
            libname = "libGM_JNI.so";
            this.loadLib(libname, 2);
        }
    }

    private synchronized void loadLib(String libName, int os) throws Exception {
        String path;
        if (os == 1) {
            path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1, path.length());
        } else {
            path = "/app/client-service/security_Type";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        int endIndex = path.lastIndexOf("/");
        path = path.substring(0, endIndex);
        path = URLDecoder.decode(path, "utf-8");
        InputStream in = null;
        BufferedInputStream reader = null;
        FileOutputStream writer = null;
        File extractedLibFile = new File(path + File.separator + libName);
        if (!extractedLibFile.exists()) {
            try {
                in = GM.class.getResourceAsStream("/" + libName);
                if (in == null) {
                    in = GM.class.getResourceAsStream(libName);
                }

                reader = new BufferedInputStream(in);
                writer = new FileOutputStream(extractedLibFile);

                for(byte[] buffer = new byte[1024]; reader.read(buffer) > 0; buffer = new byte[1024]) {
                    writer.write(buffer);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }
        System.load(extractedLibFile.toString());
    }

    public native void SM4_encrypt_ecb(byte[] key, int mode, byte[] plaintext, int plaintext_len, byte[] enc_output);
    public native void SM4_decrypt_ecb(byte[] key, int mode, byte[] ciphertext, int ciphertext_len, byte[] den_output);

}
