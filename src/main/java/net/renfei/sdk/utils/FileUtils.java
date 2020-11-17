package net.renfei.sdk.utils;

import java.io.File;

/**
 * <p>Title: FileUtils</p>
 * <p>Description: 文件工具</p>
 *
 * @author RenFei
 * @date : 2020-09-27 20:45
 */
public class FileUtils {
    public static File newFile(String path, String fileName) throws Exception {
        if (BeanUtils.isEmpty(path)) {
            throw new Exception("path is empty.");
        }
        if (BeanUtils.isEmpty(fileName)) {
            throw new Exception("fileName is empty.");
        }
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            // 如果路径不存在就创建
            file.mkdir();
        }
        String filePath;
        if (path.endsWith("/") || path.endsWith("\\")) {
            filePath = path + fileName;
        } else {
            filePath = path + "/" + fileName;
        }
        file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
