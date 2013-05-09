package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * 对rar或者zip进行解压缩
 * 
 * @author yKF41624
 * 
 */
public class Decompress {
    private static String fileName = "";

    /**
     * 对rar文件解压
     * 
     * @param rarFileName
     * @param extPlace
     * @return
     */
    public static boolean unrarFiles(String rarFileName, String extPlace) {
        boolean flag = false;
        Archive archive = null;
        File out = null;
        File file = null;
        File dir = null;
        FileOutputStream os = null;
        FileHeader fh = null;
        String path, dirPath = "";
        try {
            file = new File(rarFileName);
            archive = new Archive(file);
        } catch (RarException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (file != null) {
                file = null;
            }
        }
        if (archive != null) {
            try {
                fh = archive.nextFileHeader();
                while (fh != null) {
                    fileName=  fh.getFileNameW().trim();
                    if(!existZH(fileName)){
                        fileName = fh.getFileNameString().trim();
                    }
                    path = (extPlace + fileName).replaceAll("\\\\", "/");
                    int end = path.lastIndexOf("/");
                    if (end != -1) {
                        dirPath = path.substring(0, end);
                    }
                    try {
                        dir = new File(dirPath);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                    } catch (RuntimeException e1) {
                        e1.printStackTrace();
                    } finally {
                        if (dir != null) {
                            dir = null;
                        }
                    }
                    if (fh.isDirectory()) {
                        fh = archive.nextFileHeader();
                        continue;
                    }
                    out = new File(extPlace + fileName);
                    try {
                        os = new FileOutputStream(out);
                        archive.extractFile(fh, os);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (RarException e) {
                        e.printStackTrace();
                    } finally {
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (out != null) {
                            out = null;
                        }
                    }
                    fh = archive.nextFileHeader();
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                fh = null;
                if (archive != null) {
                    try {
                        archive.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            flag = true;
        }
        return flag;
    }

    public static boolean existZH(String str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            return true;
        }
        return false;
    }
}