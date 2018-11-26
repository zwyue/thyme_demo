package com.zhu.thyme_demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Auther: Joanne
 * @Date: 2018/11/20 09:50
 * @Description:
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String downLoadWord(HttpServletResponse response) {
        try {
            GenerateWord generateWord = new GenerateWord();
            String filePath = generateWord.createWord();
            // path是指欲下载的文件的路径。
            File file = new File(filePath);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            return filePath;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null ;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir
     *            将要删除的文件目录
     * @return
     */
    public static void deleteDirChild(File dir) {
        if (!dir.exists()) return;
        if (dir.isDirectory()) {
            String[] childrens = dir.list();
            // 递归删除目录中的子目录下
            for (String child : childrens) {
                logger.info("======删除文件：{}======",child);
                File file = new File(dir +"\\"+ child);
                file.delete();
            }
        }
    }
}
