package edu.ecnu.teisei.nlp.ner;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现不同pos工具之间的tag转换
 * @author dingcheng
 *
 */
public class ConvertTag {
	
	static Map<String, String> a2fMap;
	static Map<String, String> ansjTag;
	static Map<String, String> fnlpTag;
	static Map<String, String> nerTag;//从ansj词性 到 实体
	
	//ansj未识别出的标点符号
	static String[] PUs={".","&",";","(",")"," ","-","■","[","]","",""};
	
	//从属连词
	static String[] CSs={"虽然","虽","无论","如果","尽管","一旦","即使","只要","不论","与其","不管","只有","若","假如"};
	//并列连词
	static String[] CCs={"及","和","与","或","同","跟","既","而","乃至","且"};
	
	//指示代词
	static String[] PNIs={"双方","对方","此","此前","其","这","这个","这些","这样","这里","相互","那","那个","那样","那些","那里","何","一切","之","个人","前者","后者","两者","二者","全部","有的","以下",""};
	//疑问代词
	static String[] PNQs={"谁","什么","什么样","何方","何物","何时","何处","哪","哪里","哪个","哪些","哪样","哪儿","怎样","怎么样"};
	//人称代词
	static String[] PNPs={"我","我们","你","你们","他","他们","她","她们","它","它们","自身","本人","自己","别人"};
	
	public ConvertTag(){
		loadAnsjTag();
		loadFnlpTag();
		loadNerTag();
		loadA2F();
	}
	public void loadFnlpTag(){
		fnlpTag=new HashMap<String, String>();
		fnlpTag.put("LOC", "地名");
		fnlpTag.put("NN", "名词");
		fnlpTag.put("CC", "并列连词");
		fnlpTag.put("VV", "动词");
		fnlpTag.put("NT", "时间短语");
		fnlpTag.put("PU", "标点");
		fnlpTag.put("PER", "人名");
		fnlpTag.put("LC", "方位词");
		fnlpTag.put("AS", "时态词");
		fnlpTag.put("ETC", "省略词");
		fnlpTag.put("DSP", "结构助词");
		fnlpTag.put("CD", "数词");
		fnlpTag.put("M", "量词");
		fnlpTag.put("JJ", "形容词");
		fnlpTag.put("AD", "副词");
		fnlpTag.put("P", "介词");
		fnlpTag.put("PNI", "指示代词");
		fnlpTag.put("VA", "形谓词");
		fnlpTag.put("DT", "限定词");
		fnlpTag.put("BEI", "被动词");
		fnlpTag.put("OD", "序数词");
		fnlpTag.put("CS", "从属连词");
		fnlpTag.put("PNP", "人称代词");
		fnlpTag.put("AV", "能愿动词");
		fnlpTag.put("DV", "趋向动词");
		fnlpTag.put("NR", "专有名");
		fnlpTag.put("ORG", "机构名");
		fnlpTag.put("BA", "把动词");
		fnlpTag.put("BDN", "品牌名");
		fnlpTag.put("SP", "语气词");
		fnlpTag.put("PNQ", "疑问代词");
		fnlpTag.put("EVT", "时间名");
		fnlpTag.put("MOD", "型号名");
		fnlpTag.put("IJ", "叹词");
		fnlpTag.put("X", "运算符");
		fnlpTag.put("ON", "拟声词");
		fnlpTag.put("URL", "网址");
		fnlpTag.put("EMO", "表情符");
		fnlpTag.put("IDIOM", "惯用词");
	}
	public void loadAnsjTag(){
		//名词
		ansjTag=new HashMap<String, String>();
		ansjTag.put("n", "名词");
		ansjTag.put("nba", "动植物");
		ansjTag.put("nbc", "生物科目");
		ansjTag.put("nf", "吃的食物");
		
		ansjTag.put("ng", "名词性语素");

		ansjTag.put("nhd", "病");
		ansjTag.put("nhm", "药物");
		
		ansjTag.put("nis", "机构单位组织（类型）");//有限公司，共产党，陆军，协会，办公室，。。。
		ansjTag.put("nit", "机构部分（类型）");//**科，**部，**院，**中心，**系，。。。
		
		ansjTag.put("nmc", "化学物品");
		
		ansjTag.put("nnd", "角色");
		ansjTag.put("nnt", "职业");
		
		ansjTag.put("nr", "人名");
		ansjTag.put("nr1", "汉语姓氏");
		ansjTag.put("nr2", "汉语名字");
		ansjTag.put("nrj", "日语名字");
		ansjTag.put("nrf", "音译人名");
		ansjTag.put("nrt", "音译人名");
		ansjTag.put("nrfg", "人名");

		ansjTag.put("na", "地名");//词典中只有“镇国寺”
		ansjTag.put("ns", "地名");
		ansjTag.put("nsf", "音译地名");
		
		ansjTag.put("nt", "机构团体名");
		ansjTag.put("ntc", "公司名");
		ansjTag.put("ntcb", "银行");
		ansjTag.put("ntcf", "工厂名");
		ansjTag.put("ntch", "酒店饭店名");
		ansjTag.put("nth", "医院名");
		ansjTag.put("nto", "政府部门");
		ansjTag.put("nts", "中学名");
		ansjTag.put("ntu", "大学名");
		
		ansjTag.put("nz", "其他专名");
		
		ansjTag.put("nl", "名词性惯用语");
		ansjTag.put("nw", "新词");
		

		//形容词
		ansjTag.put("a", "形容词");
		ansjTag.put("ad", "副形词");
		ansjTag.put("an", "名形词");
		ansjTag.put("ag", "形容词性语素");
		ansjTag.put("al", "形容词性惯用语");

		//区别词
		ansjTag.put("b", "区别词");
		ansjTag.put("bl", "区别词性惯用语");
		


		
		//连词
		ansjTag.put("c", "连词");
		ansjTag.put("cc", "并列连词");
		
		
		//副词
		ansjTag.put("d", "副词");
		ansjTag.put("dg", "副词");
		ansjTag.put("dl", "副词");
		
		//
		ansjTag.put("dl", "副词");
		

		//叹词
		ansjTag.put("e", "叹词");

		//方位词
		ansjTag.put("f", "方位词");
		
		//学术术语
		ansjTag.put("gb", "生物学术语");//
		ansjTag.put("gc", "化学术语");//
		ansjTag.put("gg", "地理学术语");//
		ansjTag.put("gi", "信息学术语");//
		ansjTag.put("gm", "数学术语");//
		ansjTag.put("gp", "物理学术语");//
		
		//前缀
		ansjTag.put("h", "前缀");
		
		//后缀
		ansjTag.put("k", "后缀");
		
		//
		ansjTag.put("i", "俗语");//言之凿凿，不诚实
		ansjTag.put("j", "专有词简称");//车改
		ansjTag.put("jn", "“东中西”");//一个词（东中西）
		ansjTag.put("l", "成语");//成语
		

		//拟声词
		ansjTag.put("o", "拟声词");
		

		//介词
		ansjTag.put("p", "介词");
		ansjTag.put("pba", "介词“把”");
		ansjTag.put("pbei", "介词“被”");
		


		//数词
		ansjTag.put("m", "数词");
		ansjTag.put("mq", "数量词");
		
		
		//量词
		ansjTag.put("q", "量词");
		ansjTag.put("qv", "动量词");
		ansjTag.put("qt", "时量词");
		

		//代词
		ansjTag.put("r", "代词");
		ansjTag.put("rG", "代词性语素");
		ansjTag.put("rr", "人称代词");
		ansjTag.put("ry", "疑问代词");
		ansjTag.put("rys", "处所疑问代词");
		ansjTag.put("ryt", "时间疑问代词");
		ansjTag.put("ryv", "谓词性疑问代词");
		ansjTag.put("rz", "指示代词");
		ansjTag.put("rzs", "处所指示代词");
		ansjTag.put("rzt", "时间指示代词");
		ansjTag.put("rzv", "谓词性指示代词");
		

		//处所词
		ansjTag.put("s", "处所词");
		
		
		//时间词
		ansjTag.put("t", "时间词");
		ansjTag.put("tg", "时间词性语素");
		

		//助词
		ansjTag.put("u", "助词");
		ansjTag.put("uzhe", "着");
		ansjTag.put("ule", "了 喽");
		ansjTag.put("uguo", "过");
		ansjTag.put("ude1", "的 底");
		ansjTag.put("ude2", "地");
		ansjTag.put("ude3", "得");
		ansjTag.put("usuo", "所");
		ansjTag.put("udeng", "等 等等 云云");
		ansjTag.put("uyy", "一样 一般 似得 般");
		ansjTag.put("udh", "语气词“的话”");
		ansjTag.put("uls", "来讲 来说 而言 说来");
		ansjTag.put("uzhi", "之");
		ansjTag.put("ulian", "连（“连小学生都会”）");
		
		ansjTag.put("uj", "结构助词“的”");
		ansjTag.put("ul", "时态词“了”");
		ansjTag.put("uv", "结构助词“地”");
		ansjTag.put("uz", "结构助词“着”");
		ansjTag.put("ug", "时态词“过”");
		
		
		//动词
		ansjTag.put("v", "动词");
		ansjTag.put("vd", "副动词");
		ansjTag.put("vn", "名动词");
		ansjTag.put("vshi", "动词“是”");
		ansjTag.put("vyou", "动词“有”");
		ansjTag.put("vf", "趋向动词");
		ansjTag.put("vx", "形式动词");
		ansjTag.put("vi", "不及物动词（内动词）");
		ansjTag.put("vl", "动词性惯用语");
		ansjTag.put("vg", "动词性语素");
		ansjTag.put("vq", "“去过”");


		//标点符号
		ansjTag.put("w", "标点符号");
		ansjTag.put("wkz", "左括号");
		ansjTag.put("wky", "右括号");
		ansjTag.put("wyz", "左引号");
		ansjTag.put("wyy", "右引号");
		ansjTag.put("wj", "句号");
		ansjTag.put("ww", "问好");
		ansjTag.put("wt", "叹号");
		ansjTag.put("wd", "逗号");
		ansjTag.put("wf", "分号");
		ansjTag.put("wn", "顿号");
		ansjTag.put("wm", "冒号");
		ansjTag.put("ws", "省略号");
		ansjTag.put("wp", "破折号");
		ansjTag.put("wb", "百分号千分号");
		ansjTag.put("wh", "单位符号");
		

		//字符串
		ansjTag.put("x", "字符串");
		ansjTag.put("xx", "非语素字");
		ansjTag.put("xu", "网址URL");
		
		//语气词
		ansjTag.put("y", "语气词");
		
		
		//状态词
		ansjTag.put("z", "状态词");

		//自己加入
		//其他，自己加的
		ansjTag.put("en", "英文单词");//patriziobertelli
		//其他，自己加的
		ansjTag.put("PU", "标点");//patriziobertelli改
	}
	/**
	 * 从ansj词性 到 实体
	 */
	public void loadNerTag(){
		nerTag=new HashMap<String, String>();
		nerTag.put("nr", "人名");
		nerTag.put("nr1", "人名");
		nerTag.put("nr2", "人名");
		nerTag.put("nrj", "人名");
		nerTag.put("nrf", "人名");
		nerTag.put("nrt", "人名");
		nerTag.put("nrfg", "人名");
		
		nerTag.put("na", "地名");
		nerTag.put("ns", "地名");
		nerTag.put("nsf", "地名");
		
		nerTag.put("nt", "机构团体");
		nerTag.put("ntc", "公司");
		nerTag.put("ntcb", "银行");
		nerTag.put("ntcf", "工厂");
		nerTag.put("ntch", "酒店饭店");
		nerTag.put("nth", "医院");
		nerTag.put("nto", "政府部门");
		nerTag.put("nts", "中学");
		nerTag.put("ntu", "大学");
		
		nerTag.put("nz", "专有名");
	}
	public String ansj2ner(String ansj){
		return nerTag.get(ansj);
	}
	/**
	 * 从ansj的词性转换到fudanNLP的词性
	 */
	public void loadA2F(){
		//名词
		a2fMap=new HashMap<String, String>();
		a2fMap.put("n", "NN");//一般性名词
		a2fMap.put("nba", "NN");//动植物
		a2fMap.put("nbc", "NN");//生物科目
		a2fMap.put("nf", "NN");//吃的食物
		
		a2fMap.put("ng", "NN");//名词性语素

		a2fMap.put("nhd", "NN");//病
		a2fMap.put("nhm", "NN");//药物
		
		a2fMap.put("nis", "NN");//机构单位组织（类型）：有限公司，共产党，陆军，协会，办公室，。。。
		a2fMap.put("nit", "NN");//机构部分（类型）：**科，**部，**院，**中心，**系，。。。
		
		a2fMap.put("nmc", "NN");//化学物品
		
		a2fMap.put("nnd", "NN");//角色
		a2fMap.put("nnt", "NN");//职业
		
		a2fMap.put("nr", "PER");//人名
		a2fMap.put("nr1", "PER");//汉语姓氏
		a2fMap.put("nr2", "PER");//汉语名字
		a2fMap.put("nrj", "PER");//日语名字
		a2fMap.put("nrf", "PER");//音译名字
		a2fMap.put("nrt", "PER");//音译人名
		a2fMap.put("nrfg", "PER");//人名

		a2fMap.put("na", "LOC");//词典中只有“镇国寺”
		a2fMap.put("ns", "LOC");//地名
		a2fMap.put("nsf", "LOC");//音译地名
		
		a2fMap.put("nt", "ORG");//机构团体名
		a2fMap.put("ntc", "ORG");//公司名
		a2fMap.put("ntcb", "ORG");//银行
		a2fMap.put("ntcf", "ORG");//工厂名
		a2fMap.put("ntch", "ORG");//酒店饭店名
		a2fMap.put("nth", "ORG");//医院名
		a2fMap.put("nto", "ORG");//政府部门
		a2fMap.put("nts", "ORG");//中学名
		a2fMap.put("ntu", "ORG");//大学名
		
		a2fMap.put("nz", "NR");//其他专有名
		
		a2fMap.put("nl", "NN");//名词性惯用语
		a2fMap.put("nw", "NN");//新词

		//形容词
		a2fMap.put("a", "JJ");
		a2fMap.put("ad", "AD");
		a2fMap.put("an", "JJ");
		a2fMap.put("ag", "JJ");
		a2fMap.put("al", "JJ");
		

		//区别词
		a2fMap.put("b", "DT");//复旦NLP中的限定词
		a2fMap.put("bl", "JJ");

		//连词
		a2fMap.put("c", "CC");
		a2fMap.put("cc", "CC");
		

		//副词
		a2fMap.put("d", "AD");
		ansjTag.put("dg", "AD");
		ansjTag.put("dl", "AD");

		//叹词
		a2fMap.put("e", "IJ");

		//方位词
		a2fMap.put("f", "LC");
		
		//学术术语
		a2fMap.put("gb", "NN");//
		a2fMap.put("gc", "NN");//
		a2fMap.put("gg", "NN");//
		a2fMap.put("gi", "NN");//
		a2fMap.put("gm", "NN");//特殊“最大”
		a2fMap.put("gp", "NN");//

		//前缀
		a2fMap.put("h", "NN");
		
		//后缀
		a2fMap.put("k", "NN");//后缀：后“者”
		
		//
		a2fMap.put("i", "AD");//言之凿凿，不诚实.
		a2fMap.put("j", "NR");//车改.
		a2fMap.put("jn", "NN");//车改
		
		//成语
		a2fMap.put("l", "AD");//说的好,财雄势大

		//拟声词
		a2fMap.put("o", "ON");

		//介词
		a2fMap.put("p", "P");
		a2fMap.put("pba", "BA");
		a2fMap.put("pbei", "BEI");

		//数词
		a2fMap.put("m", "CD");
		a2fMap.put("mq", "CD");

		//量词
		a2fMap.put("q", "M");
		a2fMap.put("qv", "M");//动量词
		a2fMap.put("qt", "M");//时量词

		//代词
		a2fMap.put("r", "代词");//都表为r，需要细分判断
		a2fMap.put("rg", "代词性语素");
		a2fMap.put("rr", "PNP");
		a2fMap.put("ry", "PNQ");
		a2fMap.put("rys", "PNQ");
		a2fMap.put("ryt", "PNQ");
		a2fMap.put("ryv", "PNQ");
		a2fMap.put("rz", "PNI");
		a2fMap.put("rzs", "PNI");
		a2fMap.put("rzt", "PNI");
		a2fMap.put("rzv", "PNI");

		//处所词
		a2fMap.put("s", "LC");
		
		//时间词
		a2fMap.put("t", "NT");
		a2fMap.put("tg", "NT");

		//助词
		a2fMap.put("u", "DSP");
		a2fMap.put("uzhe", "DSP");
		a2fMap.put("ule", "SP");
		a2fMap.put("uguo", "AS");//DSP:过
		a2fMap.put("ude1", "DSP");//DSP:的 底
		a2fMap.put("ude2", "DSP");//DSP:地
		a2fMap.put("ude3", "DSP");//DSP:得
		a2fMap.put("usuo", "DSP");//DSP:所
		a2fMap.put("udeng", "ETC");//省略词等 等等 云云
		a2fMap.put("uyy", "VA");//形谓词：一般 般 一样
		a2fMap.put("udh", "DSP");
		a2fMap.put("uls", "LC");//方位词：来讲 来说 而言 说来
		a2fMap.put("uzhi", "DSP");
		a2fMap.put("ulian", "AD");
		a2fMap.put("uj", "DSP");//结构助词“的”
		a2fMap.put("ul", "AS");//时态词“了”
		a2fMap.put("uv", "DSP");//结构助词“地”
		a2fMap.put("uz", "DSP");//结构助词“着”
		a2fMap.put("ug", "AS");//时态词“过”

		//动词
		a2fMap.put("v", "VV");//都识别为动词
		a2fMap.put("vd", "VV");
		a2fMap.put("vf", "DV");//趋向动词
		a2fMap.put("vn", "VV");
		a2fMap.put("vi", "VV");
		a2fMap.put("vl", "VV");
		a2fMap.put("vg", "VV");
		a2fMap.put("vq", "VV");
		a2fMap.put("vshi", "VV");
		a2fMap.put("vx", "VV");
		a2fMap.put("vyou", "VV");

		//标点符号
		a2fMap.put("w", "PU");
		a2fMap.put("wkz", "PU");
		a2fMap.put("wky", "PU");
		a2fMap.put("wyz", "PU");
		a2fMap.put("wyy", "PU");
		a2fMap.put("wj", "PU");
		a2fMap.put("ww", "PU");
		a2fMap.put("wt", "PU");
		a2fMap.put("wd", "PU");
		a2fMap.put("wf", "PU");
		a2fMap.put("wn", "PU");
		a2fMap.put("wm", "PU");
		a2fMap.put("ws", "PU");
		a2fMap.put("wp", "PU");
		a2fMap.put("wb", "PU");
		a2fMap.put("wh", "PU");

		//字符串
		a2fMap.put("x", "NN");
		a2fMap.put("xx", "NN");
		a2fMap.put("xu", "URL");

		//语气词
		a2fMap.put("y", "SP");

		//状态词
		a2fMap.put("z", "JJ");
		
		//其他，自己加的
		a2fMap.put("en", "NR");//patriziobertelli
		//其他，自己加的
		a2fMap.put("PU", "PU");//patriziobertelli
	}
	
	/**
	 * 获得fudanNLP的中文词性标签
	 * @param tag
	 * @return
	 */
	public String fnlpChinese(String tag){
		return fnlpTag.get(tag);
	}
	
	/**
	 * 获得ansj的中文词性标签
	 * @param tag
	 * @return
	 */
	public String ansjChinese(String tag){
		return ansjTag.get(tag);
	}
	
	/**从ansh到复旦之间的转换。ansj英文---fudanNLP英文
	 */
	public String ansj2fudan(String word,String tag){
		if(tag.equals("c")){//如果是连词
			//从属连词
			for(int i=0;i<CSs.length;i++){
				if(word.equals(CSs[i])){
					return "CS";
				}
			}
			//并列连词
			for(int i=0;i<CCs.length;i++){
				if(word.equals(CCs[i])){
					return "CC";
				}
			}
		}else if(tag.equals("r")){//如果是代词
			//指示代词
			for(int i=0;i<PNIs.length;i++){
				if(word.equals(PNIs[i])){
					return "PNI";
				}
			}
			//疑问代词
			for(int i=0;i<PNQs.length;i++){
				if(word.equals(PNQs[i])){
					return "PNQ";
				}
			}
			//人称代词
			for(int i=0;i<PNPs.length;i++){
				if(word.equals(PNPs[i])){
					return "PNP";
				}
			}
			return "PNI";
		}else if(tag.equals("p")){//如果是介词
			if(word.equals("把")){
				return "BA";
			}else if(word.equals("被")){
				return "BEI";
			}
			
		}else if(tag.equals("m")){//如果是数词
			if(word.subSequence(0, 1).equals("第")){
				return "OD";//序数词
			}
		}else if(tag.equals("gm")){//如果是gm
			if(word.equals("最大")){
				return "JJ";
			}else{
				return "NN";
			}
		}
		return a2fMap.get(tag);
	}
	/**
	 * 找为识别出来的符号
	 * @param pu
	 * @return
	 */
	public String recogPun(String pu){
		for(int i=0;i<PUs.length;i++){
			if(pu.equals(PUs[i])){
				return "PU";
			}
		}
		return "未知";
	}
}
