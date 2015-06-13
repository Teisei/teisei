package edu.ecnu.teisei.utils;

/**
 * 工具类，导入文件，成为数组或者矩阵
 * Created by dingcheng on 2014/10/29.
 */
public class ImportTool {

    static FileUtil myFiles = new FileUtil();

    /**
     * GET Data By line.
     */
    public static String[] getDataByLine(String inputpath, String r1, String encode) {
        String temp_src = myFiles.read(inputpath,encode);
        String[] temp_matrix = temp_src.split(r1);
        return temp_matrix;
    }

    public static String[][] getMatrix(String inputpath, String r1, String r2, String encode){

        String[][] temp_matrix;
        String temp_src = myFiles.read(inputpath,encode);

        String[] temp_list = temp_src.split(r1);
        temp_matrix = new String[temp_list.length][];
        for (int i = 0; i < temp_list.length; i++) {
            temp_matrix[i] = temp_list[i].split(r2);
        }
        return temp_matrix;
    }
    public static String[] getRow(String[][] m, int i){
        return m[i];
    }
    public static String[] getCol(String[][] m, int j){
        String[] res = new String[m.length];
        for(int u=0;u<m.length;u++){
            res[u] = m[u][j];
        }
        return res;
    }
}
