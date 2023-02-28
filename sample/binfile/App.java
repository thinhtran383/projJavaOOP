package binfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.stream.FileImageInputStream;

public class App {
    public static void main(String[] args) {
        var srcFile = "C:\\Users\\ThinhTran\\Downloads\\thinhTran.pdf";
        var desFile = "D:\\Tools\\sub2.pdf";
        File myFile = new File(srcFile);
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(desFile);
            var start = new Date().getTime();
            final int size = 8 * 1024;
            byte[] buffer = new byte[size];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer);
            }
            var end = new Date().getTime();
            fis.close();
            fos.close();
            System.out.println("File size: " + (myFile.length() / 1024) + "KB");
            System.out.println("Time use: " + (end - start) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
