package edu.ecnu.teisei.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过正则表达式来去文本中的信息
 * @author dingcheng
 *
 */
public class MyRegex {
	// 。|；|，|：|“|”|（|）|、|？|《|》
	String puncs="[\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]";
	// 汉字
	String chars="[\u4e00-\u9fa5]";

	String regex1="(公司\\(.+\\)";

	String punc="(\u3002|\uff1b|\uff0c|\uff1a|\u201c|\u201d|\uff08|\uff09|\u3001|\uff1f|\u300a|\u300b)";
	String[] regexs={"公司\\((.*?)\\)","",""};
    List<String> regexsList = new ArrayList<String>();

    //正则表达式
    int regexNumber;
    Map<String, String> regexMap;   //正则表达式和附加信息，抽取的类型，抽取的位置

	List<String> Plist0;
	List<String> Plist1;
	Map<String, Integer> map0;
	Map<String, Integer> map1;

    FileUtil fileTool = new FileUtil();

    /**
     * 创建一个抽取类
     */
	public MyRegex(){
		init();
	}
	/**
	 * 采用默认设置，抽取公司名字
	 */
	public void init(){
		map0 = new HashMap<String, Integer>();
		map1 = new HashMap<String, Integer>();

        regexNumber = 0;
        regexMap = new HashMap<String, String>();

        map0.put("公司\\((.+?)\\)", 1);
		map1.put("^[\u4e00-\u9fa5]+(\"|“)(.*?)(”|\")", 2);
		map1.put("^([\u4e00-\u9fa5]+?)(，|,|$)",1);
	}
    //从文件读入规则
    public void loadRegexMap(String path, String encode) {
        String temp = fileTool.read(path, encode);
        String[] temp_list = temp.split("\r\n");
        for (int i = 0; i < temp_list.length; i++) {
            if(!temp_list[i].equals("")&&!temp_list[i].matches("#(.+?)")) {
                String[] r = temp_list[i].split("_");
                addOneRegex(r[0], "" + r[1] + ":" + r[2]);
            }
        }
    }
    //增加一个Regex和附加信息
    public void addOneRegex(String r, String e) {
        this.regexMap.put(r, e);
    }
    public void removeAllRegex() {
        this.regexMap = new HashMap<String, String>();
    }

    //返回所有结果
    public Map<String, String> findAll(String text) {
        Map<String, String> resMap = new HashMap<String, String>();
        for (Map.Entry<String, String> m1 : this.regexMap.entrySet()) {
            String regx = m1.getKey();
            String[] temp_e = m1.getValue().split(":");
            List<String> temp1 = find(text, regx, Integer.parseInt(temp_e[1]));
            for (String each : temp1) {
                resMap.put(each, temp_e[0]);
            }
        }
        return resMap;
    }

    public String mapToStr(Map<String, String> map) {
        String res = "";
        for (Map.Entry<String, String> m1 : map.entrySet()) {
            res += m1.getKey() + "_" + m1.getValue() + "||";
//            System.out.print(m1.getKey() + "_" + m1.getValue() + " || ");
        }
        return res;
    }


    /**
	 * 加入提取规则
	 * @param level
	 * @param e
	 * @param location
	 */
	public void addRegex(int level,String e,int location){
		if(level==0){
			map0.put(e, location);
		}else if(level==1){
			map1.put(e, location);
		}
	}


	/**
	 * 正则表达式
	 * @param src
	 * @param e
	 * @param a
	 * @return
	 */
	public static List<String> find(String src, String e,int a){
		List<String> list = new ArrayList<String>();
		Pattern pt = Pattern.compile(e);
        Matcher mt = pt.matcher(src);
        while(mt.find()) {
            String res = mt.group(a);
            list.add(res);
            //System.out.println(res);
        }
        return list;
	}

    /**
     * 一个二级查找
     * @param src
     * @return
     */
	public List<String> doProcess(String src){
		List<String> reslist = new ArrayList<String>();
		//遍历每一个外部规则
		for (Map.Entry<String, Integer> m : map0.entrySet()) {
			//得到临时粗结果
			List<String> temp = find(src, m.getKey(), m.getValue());
			//遍历每个粗结果
			for(String each: temp){
				for (Map.Entry<String, Integer> m1 : map1.entrySet()) {
					List<String> temp1 = find(each, m1.getKey(), m1.getValue());
					for(String e: temp1){
						reslist.add(e);
					}
				}
			}
		}
		return reslist;
	}

	/**
	 * 打印String list
	 * @param list
	 */
	public static void printList(List<String> list){
		System.out.println("*****print the list");
		for(String each: list){
			System.out.println(each);
		}
	}

	public static void main(String args[]){
		String src ="西风汽车集团股份有限公司(下称\"西风汽车\"，00489.HK).早报记者欧昌梅.拖延了九年之久的东风雷诺合资公司，终于修成正果。东风汽车集团股份有限公司(东风汽车，00489.HK)敲定了与法国雷诺汽车公司的合作计划。.12月5日，东风汽车公告称，已于12月2日获国家发改委批准，与雷诺通过联合重组三江雷诺汽车有限公司(下称“三江雷诺”)，组建合资公司。该合资公司计划年产15万辆多用途乘用车和配套发动机，投资总额约为77.56亿元。另外，双方有意在产品计划中引进新能源汽车。.公告显示，东风汽车和雷诺计划对现有的三江雷诺进行重组，经过工商变更程序，将其更名为东风雷诺汽车有限公司(下称“东风雷诺”)。东风雷诺注册资本约为47.06亿元，东风汽车和雷诺各持有50%权益。.与法国车企的连续合作，让东风汽车在法国政界颇受关注。.正在中国访问的法国总理埃罗昨日称，他刚获悉法国雷诺公司获批与东风汽车在武汉合作新的汽车制造项目，这对中法企业间合作是个好消息。埃罗说，目前已经有500多家中国企业在法国落户，对法投资近年来增长迅速。.这是埃罗去年5月出任法国总理后首次访问中国。除了北京，埃罗还将访问湖北武汉、广东台山等地。.而东风汽车的总部正位于武汉。.雷诺先“借”东风.事实上，东风汽车与雷诺的合作早有“伏笔”。.三江雷诺由中国航天三江集团与雷诺在1993年合资成立，注册资本为1.112亿美元，三江持有三江雷诺55%股权，雷诺持有三江雷诺45%股权。不过，三江雷诺发展并不理想。2012年11月6日，东风汽车与中国航天三江集团签署股权转让协议，三江向东风公司转让所持有三江雷诺全部55%的股权，代价为1元人民币。.国务院国资委已于今年5月批准了这一股权转让协议。.根据东风汽车和雷诺签订的合作协议，东风公司持有东风雷诺55%的股权，雷诺持有45%的股权，作为双方组建合资公司的过渡形态。双方将通过对东风雷诺的增资，最终形成对半持股的合资公司。双方的目标是将合资公司打造为具有全球竞争力的汽车制造商。.咨询机构罗兰贝格大中华区执行总监张君毅称，雷诺一直试图进入中国市场，但没有找到好的切入点，对雷诺来说，东风汽车是“更合适”的合作对象。.张君毅认为，东风汽车和日产有合作，雷诺和日产也有合作，东风汽车和雷诺再合作就是水到渠成的事。东风汽车可获得更多资源和品牌，进而加强自身业务。.有说法称，早在东风汽车和日产洽谈合作之初，就曾提出过“东风－日产－雷诺”的“金三角”大合作计划。但此后这一计划遭遇波折。.根据公告，东风与雷诺的合资公司未来可以根据市场形势，引入雷诺产品。东风汽车投资者关系部一位人士昨日对早报记者称，东风汽车董事会秘书胡信东，将出任东风雷诺的副总裁。.“初步接洽”标致雪铁龙.值得注意的是，东风汽车和雷诺此次合作的获批，是在东风汽车入股法国第一大汽车制造商标致雪铁龙悬而未定之时。.此前的10月，东风汽车资本运营部总经理卢锋曾对早报记者称，该公司收到来自标致雪铁龙有关注资的信息，东风汽车也意识到，标致雪铁龙“希望东风能成为投资人之一”。.上述东风汽车投资者关系部人士昨日称，东风和标致雪铁龙目前只是初步接洽，没有更进一步的消息可披露。.而标致雪铁龙的重整计划已开始启动。11月25日，标致雪铁龙宣布，法国雷诺汽车集团前首席运营长卡洛斯·塔瓦雷斯(CarlosTavares)将于2014年，接替菲利普·瓦兰(PhilippeVarin)出任标致雪铁龙的首席执行长。法国《巴黎人报》称，东风投资标致雪铁龙的条件是瓦兰离职。标致雪铁龙提前更换首席执行长，目的是为与东风结盟铺路。.一位业内人士此前在一场汽车投融资论坛上称，东风和标致雪铁龙的合作模式，或与北汽集团和全球最大的商用车制造商戴姆勒的合作模式相同。.今年11月，戴姆勒宣布完成收购北京汽车股份有限公司12%的股权。同时，北汽在双方合资制造企业-北京奔驰汽车有限公司(北京奔驰)的持股，将由50%增加到51%。戴姆勒在双方合资销售公司——北京梅赛德斯-奔驰销售服务有限公司的持股，将由50%增加到51%。.东风的“组合拳”.张君毅认为，东风和外资车企的合作一直比较成功，没有历史包袱，没有并购失败案例，所以才更积极地进行海外投资和海外拓展。东风不再“绑定”日产，而是多方投资，一方面是为了扩大业务，但最终目的是为提升自身竞争能力。.近两年，东风汽车海外投资动作不断。新华社的报道指出，自2012年以来，东风汽车已在合资合作方面打出了一套“组合拳”。东风汽车收购瑞典T工程技术公司70%股权，在瑞典落户首个海外研发基地。与德国史密斯集团合作，共同投资6亿元，设立东风史密斯半挂车工厂。与瑞典沃尔沃集团公司合资组建新的东风商用车有限公司，东风控股55%，使用“东风”商用车品牌。.东风汽车公司总经理朱福寿认为，中国汽车行业当前处于激烈竞争的市场环境，尤其是外资品牌与自主品牌同台竞争、战略相对峙的格局短期内不会改变。下一个十年，中国发展仍处于战略机遇期，而对汽车工业而言，也可以说是“最后一个黄金周期”，是发展壮大自主品牌、进入国际市场的关键时期。.张君毅提出，相比入股国外车企，组建合资公司更能发挥国内车企的作用。他指出，中方在与外资车企合作时，应走深度合作的路，进一步增强中方在合资公司中的贡献，在产品研发、落地和采购上，中方起到更切实的作用，不要在技术上一味跟随别人，应按照中国需要，全新开放。.";
        String regex="公司\\((.+?)\\)";
//        List<String> list = find(src, regex, 1);
//        for(String each: list){
//        	System.out.println("***** "+each+" *****");
//        	printList(find(each, "^[\u4e00-\u9fa5]+(\"|“)(.*?)(”|\")", 2));
//        	printList(find(each, "^([\u4e00-\u9fa5]+?)(，|,|$)", 1));
//        }

        src = "中国人民财产保险股份有限公司亳州市分公司机动车交通事故";
//        regex = "公司(.+?分公司)";
//        List<String> list2 = find(src, regex, 1);
//        for(String each: list2){
//            System.out.println("***** " + each + " *****");
//        }

    }
}
