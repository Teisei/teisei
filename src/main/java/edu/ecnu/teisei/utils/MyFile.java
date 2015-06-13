package edu.ecnu.teisei.utils;

import java.io.*;

/**
 * A tool for file reading and writing.
 * Created by dingcheng on 2015/1/7.
 */
public class MyFile {

    private File _file_;

    /**
     * Objects for read.
     */
    protected FileInputStream fis = null;
    protected InputStreamReader isr = null;
    protected BufferedReader br = null;

    /**
     * Objects for write.
     */
    OutputStreamWriter osw = null;
    FileOutputStream fileos = null;
    BufferedWriter bw = null;

    protected String path = null;
    protected String encode = null;

    /**
     * the status of this file operating object.
     */
    protected boolean isRead = false;
    protected boolean isWrite = false;
    public boolean isOpen = false;

    /**
     * constance
     */
    private String _newLineChar_ = "\r\n";


    public MyFile(String path, String encode, char s) throws FileNotFoundException, UnsupportedEncodingException {
        this.path = path;
        this.encode = encode;
        if (s == 'w') {
            openForWrite(path, encode);
        }else if (s == 'r') {
            open(path, encode);
        }
    }

    public MyFile(String path, String encode) {
        this.path = path;
        this.encode = encode;
    }

    /**
     * Open the file for reading.
     *
     * @param path the path of the file ready to open
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    private void open(String path, String encode) throws UnsupportedEncodingException, FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        fis = new FileInputStream(path);
        isr = new InputStreamReader(fis, encode);
        br = new BufferedReader(isr);
        isOpen = isRead = true;
    }

    /**
     * Open the file for writing.
     * @param path the path of the file ready to open.
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private void openForWrite(String path, String encode) throws FileNotFoundException, UnsupportedEncodingException {
        _file_ = new File(path);
        fileos = new FileOutputStream(path, true);
        osw = new OutputStreamWriter(fileos, encode);
        bw = new BufferedWriter(osw);
        isOpen = isWrite = true;
    }

    /**
     * Stop using the file, reading or writing.
     */
    public void close() {
        try {
            if(br!=null)
                br.close();
            if(isr!=null)
                isr.close();
            if(fis!=null)
                fis.close();

            if(bw!=null)
                bw.close();
            if(osw!=null)
                osw.close();
            if(fileos!=null)
                fileos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isOpen = isWrite = isRead = false;
    }

    public boolean isExist() {
        _file_ = new File(path);
        return _file_.exists();
    }

    public boolean ifNotExistCreate() {
        _file_ = new File(path);
        if (!_file_.exists()) {
            try {
                String dir = _file_.getParent();
                File fdir = new File(dir);
                fdir.mkdirs();
                _file_.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * 读取一行，如果末尾，返回null
     * @throws IOException
     */
    public String readLine() throws IOException {
        if (isWrite) {
            System.out.println("The file is being written!!!");
            throw new IOException();
        }
        if (!isRead) {
            isRead = true;
            open(path, encode);
        }
        return br.readLine();
    }

    public String read() throws IOException {
        if (isWrite) {
            System.out.println("The file is being written!!!");
            throw new IOException();
        }
        if (!isRead) {
            isRead = true;
            open(path, encode);
        }
        String line;
        String temp = null;
        StringBuffer buff = new StringBuffer();// 字符串缓存，存读入进来的字符串的
        try {
            while ((line = br.readLine()) != null) {
                // line = line.substring(line.indexOf("\t") + 1);
                // System.out.println(line);
                buff.append(line);
                buff.append("\r\n");
            }
            temp = buff.toString();
            buff.delete(0, buff.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Write something to the file.
     * @param str the text need to be written.
     * @throws IOException
     */
    public boolean writeLine(String str) throws IOException {
        return write(str + _newLineChar_);
    }
    public boolean write(String str) throws IOException {
        if (isRead) {
            System.out.println("The file is being reading!!!");
            throw new IOException();
        }
        if (!isWrite) {
            isWrite = true;
            openForWrite(path, encode);
        }
        try {
            bw.append(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void writeFlush() {
        try {
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeClose() {
        try {
            if(bw!=null)
                bw.close();
            if(osw!=null)
                osw.close();
            if(fileos!=null)
                fileos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isOpen = isWrite = false;
    }

}
