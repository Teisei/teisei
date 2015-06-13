package edu.ecnu.teisei.ir.common;

import org.apache.commons.lang.StringUtils;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SenSegmentation {
  /**
   * 将文本内容切分为句子
   * 
   * @param content
   * @return 切分好的句子列表List<String>
   */
  public static List<String> segSentence(String content) {

      List<String> list = new ArrayList<String>();
      String temp = content;

      temp = StringUtils.deleteWhitespace(temp);
      temp = StringUtils
              .trim(temp)
              .replaceAll(
                      "(@)?(href=\")?(http://)?[A-Za-z]+(\\.\\w+)+(/[&\\n=?\\+\\%/\\.\\w]+)?",
                      "");
      temp = StringUtils.trim(temp).replaceAll(
              "[`^&*()+=|{}\\[\\]<>/（）——|__【】‘”“’]", "");
      temp = temp
              .replaceAll(
                      "(\\d{0,4})-?(\\d{2})-(\\d{1,2}) ?\\d{2}:\\d{2}:?\\d{0,2}",
                      "").replaceAll("★|●|\\*|\\/|'+", "")
              .replaceAll("~", "。").replaceAll("～", "。").replaceAll("！", "。")
              .replaceAll("!", "。").replaceAll("？", "。").replaceAll("﹖", "。")
              .replaceAll(";", "。").replaceAll("；", "。")
              .replaceAll("。+", "。").replaceAll("\\.\\.", "。")
              .replaceAll("……", "。");
      // 根据中文标点符号切分
      BreakIterator boundary = BreakIterator
              .getSentenceInstance(Locale.CHINESE);
      // 设置要处理的文本
      boundary.setText(temp);
      int start = boundary.first(); // 设置开始位置
      for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
              .next()) {
          // 输出子串
          // System.out.println(temp.substring(start, end)+":"+i);

          String str = temp.substring(start, end);
          if (isSentence(str) == true) {
              list.add(str);
          }
      }
      return list;
  }

  /**
   * 判断切分字符串是否为句子
   * 
   * @param str
   * @return boolean
   */
  private static boolean isSentence(String str) {
      boolean isSen = false;
      if (isChinese(str.charAt(0)) == true) {
          isSen = true;
      }// 第一个字符为中文则判定该句为中文
      else {
          if (isChinese(str) == true) {
              isSen = true;
          }// 句子中含中文则判定该句为中文
      }
      return isSen;
  }

  /**
   * 判断输入字符是否为中文
   * 
   * @param c
   * @return boolean
   */
  private static boolean isChinese(char c) {
      Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
      if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
              || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
              || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
              || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
      // || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
      // || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
      ) {
          return true;
      }
      return false;
  }

  /**
   * 判断输入字符串是否为中文
   * 
   * @param strName
   * @return boolean
   */
  private static boolean isChinese(String strName) {
      char[] ch = strName.toCharArray();
      for (int i = 0; i < ch.length; i++) {
          char c = ch[i];
          if (isChinese(c)) {
              return true;
          }
      }
      return false;
  }

  /**
   * 利用通用编码区间来判断是否为中文,编码区间为[0x4e00-0x9fbb][\u4E00-\u9FA5]"
   * 
   * @param chineseStr
   * @return boolean
   */
  @SuppressWarnings("unused")
  private static boolean isChineseCharacter(String chineseStr) {
      char[] charArray = chineseStr.toCharArray();
      for (int i = 0; i < charArray.length; i++) {
          if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
              return true;
          }
      }
      return false;
  }
}
