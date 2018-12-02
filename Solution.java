package pl.rkiservices;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream(args[1]);
        FileOutputStream fos = new FileOutputStream(args[2]);

        byte k[] = "HignDlPs".getBytes();
        SecretKeySpec key = new SecretKeySpec(k,"DES/ECB/PKCS5Padding".split("/")[0]);
        Cipher crypt =  Cipher.getInstance("DES/ECB/PKCS5Padding");

        byte[] buf = new byte[1024];
        int read;


        if (args[0].equals("-e")) {
            crypt.init(Cipher.ENCRYPT_MODE, key);
            CipherOutputStream cout=new CipherOutputStream(fos, crypt);
            while((read=fis.read(buf))!=-1)  //reading data
                cout.write(buf,0,read);  //writing encrypted data
            //closing streams

            cout.flush();
            cout.close();

        }
        else if (args[0].equals("-d")) {
            crypt.init(Cipher.DECRYPT_MODE, key);
            CipherInputStream cin=new CipherInputStream(fis, crypt);
            while((read=cin.read(buf))!=-1)  //reading encrypted data
                fos.write(buf,0,read);  //writing decrypted data
            //closing streams
            cin.close();
            fos.flush();

        }
        fis.close();
        fos.close();
    }

}

