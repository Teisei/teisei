package edu.ecnu.teisei.nlp.pos;


import edu.ecnu.teisei.nlp.seg.SegInterface;
import edu.ecnu.teisei.utils.ImportTool;
import edu.ecnu.teisei.utils.Sentenizer;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PosAnsj implements PosInterface,SegInterface {
	/**
	 * 设置分词词典
	 */
	@Override
	public void setDict(String dicpath) throws IOException {

        String words[] = ImportTool.getDataByLine(dicpath, "\r\n", "utf-8");
        String a = null;
        String b = null;
        for (int i = 0; i < words.length; i++) {
            //增加新词
            UserDefineLibrary.insertWord(words[i].split("\t")[0], words[i].split("\t")[1], 1000);
        }
    }

    /**
	 * 删除单词word
	 */
	@Override
	public void removeWord(String word) {
		//删除词
		UserDefineLibrary.removeWord(word);
	}
	/**
	 * 在词典中添加一个词
	 */
	@Override
	public void addWord(String word,String pos) {
		//增加新词
		UserDefineLibrary.insertWord(word, pos, 1000);
	}
	/**
	 * 在词典中添加一些词
	 */
	@Override
	public void addWords(List<String> words, String pos) {
		//增加新词
		for(String word: words){
			UserDefineLibrary.insertWord(word, pos, 1000);
		}
	}

    /**
     * 对字符串进行词性标注，结果以 List<Term> 的形式返回。
     * @param str
     * @return
     */
    @Override
    public List<Term> tagOneSeq2List(String str) {
        return NlpAnalysis.parse(str);
    }

    /**
	 * 对字符串进行词性标注，结果以..w/pos w/pos..的形式返回。
	 */
	@Override
	public String tagOneSeq(String str) {
        // TODO Auto-generated method stub
        String res = "";
        List<Term> terms = tagOneSeq2List(str);
        for (Term tt : terms) {
            res+=tt.getName()+"/"+tt.getNatureStr()+" ";
        }
        return res;
    }


    @Override
    public List<Term> tag2List(String str) {
        List<Term> res = NlpAnalysis.parse(str);
        return res;
    }

    @Override
    public String[] tag2Array(String str) {
        List<Term> terms = tag2List(str);
        String res[] = new String[terms.size()];
        int temp_i = 0;
        for (Term t : terms) {
            res[temp_i] = t.getName();
            temp_i++;
        }
        return res;
    }

    /**
	 * 将输入的句子分词，词性标注，然后返回.
	 */
	@Override
	public String[][] tag(String str) {
		List<Term> res = NlpAnalysis.parse(str);
		String wordandtag[][] = new String[2][res.size()];
		int i=0;
		for(Term word: res){
			wordandtag[0][i]=word.getName();
			wordandtag[1][i]=word.getNatureStr();
			i++;
		}
		return wordandtag;
	}


    /**
     * 过滤掉某些词性的词
     * @param tl
     * @param f
     * @return
     */
    public List<Term> tagFilter2List(List<Term> tl, String[] f) {
        List<Term> res = new ArrayList<Term>();
        for (Term tt : tl) {
            res.add(tt);
        }
        for (Term word : tl) {
            String temp_pos = word.getNatureStr();
            //如果词性出现在过滤list中，则不返回
            for (int v = 0; v < f.length; v++) {
                //如果符合筛选的词
                if (temp_pos.equals(f[v])) {
                    res.remove(word);
                }
            }
        }
        return res;
    }
    public List<Term> tagFilter2List(String str, String[] f) {
        List<Term> res = NlpAnalysis.parse(str);
        return tagFilter2List(res, f);
    }




    /**
     * 筛选出某些词性的词
     * @param tl
     * @param f
     * @return
     */
    public List<Term> tagSelect2List(List<Term> tl, String[] f) {
        List<Term> res = new ArrayList<Term>();
        for (Term word : tl) {
            String temp_pos = word.getNatureStr();
            //如果词性出现在过滤list中，则不返回
            for (int v = 0; v < f.length; v++) {
                //如果符合筛选的词
                if (temp_pos.equals(f[v])) {
                    res.add(word);
                }
            }
        }
        return res;
    }
    public List<Term> tagSelect2List(String str, String[] f) {
        List<Term> res = NlpAnalysis.parse(str);
        return tagSelect2List(res, f);
    }

    /**
     * 把分好词的、标好词性的 二维数组，筛选出需要词性的单词。
     * @param terms
     * @param f
     * @return
     */
    public String[][] selectPOS(String[][] terms, String[] f) {
        List<String> word = new ArrayList<String>();
        List<String> pos = new ArrayList<String>();
        int length = 0;
        for (int i = 0; i < terms[0].length; i++) {
            boolean flag = false;
            for (int j = 0; j < f.length; j++) {
                if (terms[1][i].equals(f[j])) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                word.add(terms[0][i]);
                pos.add(terms[1][i]);
                length++;
            }
        }
        String res[][] = new String[2][length];
        for (int i = 0; i < length; i++) {
            res[0][i] = word.get(i);
            res[1][i] = pos.get(i);
        }
        return res;
    }

    /**
     * 过滤掉不需要的词性
     * @param terms
     * @param f
     * @return
     */
    public String[][] filtePOS(String[][] terms, String[] f) {
        List<String> word = new ArrayList<String>();
        List<String> pos = new ArrayList<String>();
        int length = 0;
        for (int i = 0; i < terms[0].length; i++) {
            boolean flag = true;    //为删除
            for (int j = 0; j < f.length; j++) {
                if (terms[1][i].equals(f[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                word.add(terms[0][i]);
                pos.add(terms[1][i]);
                length++;
            }
        }
        String res[][] = new String[2][length];
        for (int i = 0; i < length; i++) {
            res[0][i] = word.get(i);
            res[1][i] = pos.get(i);
        }
        return res;
    }




    /**
     * 对str先进行断句，再进行词性标注，结果以 List<Term>[] 返回。
     * @param str
     * @return
     */
    @Override
    public List tagSents2List(String str) {
        String[] sents = Sentenizer.split(str);
        List res = new ArrayList();
        for(int i=0;i<sents.length;i++){
            res.add(tag2List(sents[i]));
        }
        return res;
    }
    @Override
    public List tagSents2ListSelect(String str, String[] f) {
        String[] sents = Sentenizer.split(str);
        List res = new ArrayList();
        for(int i=0;i<sents.length;i++){
            res.add(tagSelect2List(sents[i], f));
        }
        return res;
    }




    /**
	 * 对str先进行断句，再进行词性标注，结果以String tags[][][]返回。
	 */
	@Override
	public String[][][] tagSents(String str) {
		String[] sents = Sentenizer.split(str);
		String[][][] res = new String[sents.length][][];
		for(int i=0;i<sents.length;i++){
			res[i]=tag(sents[i]);
		}
		return res;
	}
	/**
	 * 对str进行词性标注，结果以String 返回(词按行，每行w \t pos)。
	 */
	@Override
	public String tag2String(String str) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 将已经分好词的数组传入，进行词性标注，返回词性数组。
	 */
	@Override
	public String[] tagSeged2Array(String[] str) {


		// TODO Auto-generated method stub
		return null;
	}

    /**
     * 将词和词性数组传入，返回字符串，
     */
    @Override
    public String array2str(String words[][]) {
        String res = "";
        for (int i = 0; i < words[0].length; i++) {
            res += words[0][i] + split_wordTag + words[1][i]+split_words;
        }
        return res.trim();
    }

    @Override
    public String[][] str2array(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String terms[] = str.split(split_words);
        String words[][] = new String[2][terms.length];
        for (int uu = 0; uu < terms.length; uu++) {
//            System.out.println("|" + terms[uu] + "|");

            words[0][uu] = terms[uu].split(split_wordTag)[0];
            words[1][uu] = terms[uu].split(split_wordTag)[1];
        }
        return words;
    }


    /**
     * 获得分词结果
     * @param src
     * @return
     */
    @Override
    public List<String> Seg2List(String src) {
        List<Term> terms = tag2List(src);
        List<String> res = new ArrayList<String>();
        for (Term t : terms) {
            res.add(t.getName());
        }
        return null;
    }

    /**
     *
     * @param src
     * @return
     */
    @Override
    public String[] Seg2Array(String src) {
        List<Term> terms = tag2List(src);
        String res[] = new String[terms.size()];
        int temp_i = 0;
        for (Term t : terms) {
            res[temp_i++] = t.getName();
        }
        return res;
    }

    @Override
    public String[][] Seg2DoubleArray(String src) {
        String[] sents = Sentenizer.split(src);
        String[][] res = new String[sents.length][];
        for(int i=0;i<sents.length;i++) {
            res[i] = Seg2Array(sents[i]);
        }
        return res;
    }
}
