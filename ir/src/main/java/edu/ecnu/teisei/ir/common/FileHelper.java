package edu.ecnu.teisei.ir.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileHelper {
    public String read(String filepath) {
        return read(filepath, "UTF-8");
    }

    public String read(File docDir) {
        return read(docDir, "UTF-8");
    }

    public String read(String filepath, String encode) {
        File docDir = new File(filepath);
        return read(docDir, encode);
    }

    public String read(File docDir, String encode) {
        FileInputStream fis;
        InputStreamReader isr;// 读流
        BufferedReader br;// 读字符串
        String line = null;
        String temp = null;
        StringBuffer buff = new StringBuffer();// 字符串缓存，存读入进来的字符串的
        try {
            fis = new FileInputStream(docDir);
            isr = new InputStreamReader(fis, encode);
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                // line = line.substring(line.indexOf("\t") + 1);
                // System.out.println(line);
                buff.append(line);
                buff.append("\r\n");
            }
            br.close();
            isr.close();
            fis.close();

            temp = buff.toString();
            buff.delete(0, buff.length());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return temp;
    }


    // 将字符串写入到filepath的路径下
    public static boolean write(File file, String str, boolean isAppend,
                                String encode) {
        OutputStreamWriter osw = null;
        FileOutputStream fileos = null;
        BufferedWriter bw = null;
        try {
            fileos = new FileOutputStream(file, isAppend);
            osw = new OutputStreamWriter(fileos, encode);
            bw = new BufferedWriter(osw);
            if (!str.equals("")) {
                bw.append(str);
                bw.newLine();
            }

            bw.close();
            osw.close();
            fileos.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    // 将字符串写入到filepath的路径下
    public static boolean write(String filepath, String str, boolean isAppend,
                                String encode) {
        File file = new File(filepath);
        return write(file, str, isAppend, encode);
    }

    // 默认编码方式为UTF-8
    public static boolean write(String filepath, String str, boolean isAppend) {
        String encode = "UTF-8";
        return write(filepath, str, isAppend, encode);
    }

    // 默认为追加模式
    public static boolean write(String filepath, String str, String encode) {
        return write(filepath, str, true, encode);
    }

    // 将字符串写入到filepath的路径下,默认为追加，默认为“UTF-8”模式
    public static boolean write(String filepath, String str) {
        return write(filepath, str, true);
    }

    // 将string的一个数组list书序写入到filepath的文件中，换行
    public static boolean write(String filepath, List<String> list,
                                boolean isAppend, String encode) {
        OutputStreamWriter osw = null;
        FileOutputStream fileos = null;
        BufferedWriter bw = null;
        try {
            fileos = new FileOutputStream(filepath, isAppend);
            osw = new OutputStreamWriter(fileos, encode);
            bw = new BufferedWriter(osw);
            for (String s : list) {
                bw.append(s);
                bw.newLine();
            }
            bw.close();
            osw.close();
            fileos.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    //
    public static boolean write(String filepath, List<String> list,
                                boolean isAppend) {
        String encode = "UTF-8";
        return write(filepath, list, isAppend, encode);
    }

    // 将string的一个数组list书序写入到filepath的文件中，换行
    public static boolean write(String filepath, List<String> list) {
        return write(filepath, list, true);
    }

    public static <T> boolean write(String filepath, Map<T, T> resultMap) {
        Iterator<Entry<T, T>> iterator = resultMap.entrySet().iterator();
        List<String> resultList = new ArrayList<String>();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Entry<String, Long> entry = (Entry<String, Long>) iterator.next();
            resultList.add(entry.getKey() + "\t" + entry.getValue());
        }
        OutputStreamWriter osw = null;
        FileOutputStream fileos = null;
        BufferedWriter bw = null;
        try {
            fileos = new FileOutputStream(filepath, true);
            osw = new OutputStreamWriter(fileos, "UTF-8");
            bw = new BufferedWriter(osw);
            for (String s : resultList) {
                if (!s.equals("")) {
                    bw.append(s);
                    bw.newLine();
                }
            }
            bw.close();
            osw.close();
            fileos.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile(File file) {
        try {
            if (file.exists()) {
                file.delete();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
