package edu.ecnu.teisei.schema.news;

/**
 * Created by dingcheng on 2014/11/3.
 *
 */

import edu.ecnu.teisei.schema.Article;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 <businessnews>
 <news>
 <url>http://money.163.com/12/0329/08/7TOH1IDF00253B0H.html</url>
 <title>今年116家公司公布定向增发预案</title>
 <time>2012-03-29 08:03:35　</time>
 <plate>网易财经频道</plate>
 <source>证券日报-资本证券网</source>
 <text>公布定向增发预案的个股中，有80只个股今年实现上涨，占比68.97%昨日上证指数下跌2.65%，深证成指下跌3.15%，但是公布定向增发预案的个股昨日却逆市涨停，例如广州药业涨停，ST宝龙涨停，ST宝丽来涨停。通过定向增发引入战略投资者或者收购兼并，对上市公司的发展是重大利好。因为定向增发常常伴随着重组，增发完成之后，公司的股权结构往往会发生较大变化，甚至发生控股权变更的情况，将会使得上市公司的经营情况大大改善，而其股价在二级市场自然也会获得更高的重新估值。而从在二级市场上表现看，定向增发对对上市公司股价影响显著。据《证券日报》市场研究中心数据统计显示，今年两市共有116家上市公司公布定向增发预案，其中有80只个股今年实现上涨，占比68.97%。本期，挑选3只昨日涨停的定向增发预案公布的个股进行剖析，希望能给投资者选股提供一定参考。广州药业资产重组促昨涨停统计显示，广州药业昨日公告定向增发预案，受利好刺激昨日广州药业涨停。据悉，公司拟向广药集团发行股份作为支付对价，收购广药集团拥有或有权处置的房屋建筑物、商标、保联拓展100%股权、百特医疗12.50%股权。本次拟购买资产的预估值为4.82亿元，拟作价4.38亿元。本次发行股份价格为12.2元/股，拟发行股份约0.36亿股。(异议股东收购请求权的价格，即A股人民币12.2元/股，H股港币5.54元/股)(本次重大资产重组能否最终成功实施存在不确定性)此外，公司拟以新增股份换股方式吸收合并白云山，交易完成后，白云山将注销法人资格，其全部资产、负债、权益、业务和人员将并入公司。白云山与公司的换股比例为1:0.95.ST宝龙购买吉隆矿业100%股权统计显示，ST宝龙昨日涨停，拟拟8.68元/股向吉隆矿业全体股东(8位自然人)发行1.84亿股购买吉隆矿业100%股权。公司2012年3月20日公告，公司拟1元将全部资产及负债(评估值-842.18万元)出售给威远集团，同时拟8.68元/股向吉隆矿业全体股东(8位自然人)发行1.84亿股购买吉隆矿业100%股权(评估值15.94亿元，增值率1085.37%)，前述两项交易互为生效条件。吉隆矿业及其全资子公司华泰矿业的矿产资源主要为黄金，共包含有3项采矿权和5项探矿权，现已经国土资源部评审备案的资源储量21.211吨。本次交易完成后，公司主营业务将变更为黄金采选及销售。此外，公司与赵美光等签订的《利润补偿协议》，赵美光及其一致行动人赵桂香、赵桂媛向公司保证并承诺在本次交易实施完毕当年度起三年内(2012年、2013年、2014年)，拟购买资产(吉隆矿业100%股权)实现净利润不低于2.32亿元、2.41亿元、2.41亿元。每年计算一次股份补偿数，由公司以1元的价格进行回购，赵美光及其一致行动人赵桂香、赵桂媛按照其各自在本次交易前持有购买资产权益比例分别计算各自应承担的补偿股份。ST宝利来购大股东酒店资产统计显示，ST宝利来昨日涨停，公司拟向大股东宝利来实业发行不超过7814.76万股购宝利来投资100%股权，交易完成后，高端酒店的经营管理将成为公司的主营业务。据悉，公司拟向大股东宝利来实业发行不超过7814.76万股购宝利来投资100%股权。发行价格6.91元/股。宝利来投资认购股份锁定期为36个月内。截至2011年12月31日，交易标的合并会计报表账面净值约为4.42亿元，预估值约为5.85亿元，评估增值率约为32.35%，最终作价不高于5.4亿元。本次交易前，宝利来实业持有公司23.15%的股权，为公司控股股东，交易完成后宝利来实业将持有公司不超过62.71%的股权。本次交易标的的核心资产是宝利来国际大酒店公路，距离深圳宝安国际机场约5公里路程，距离规划中的国家级“深圳前海深港现代服务业合作区”约19公里。酒店目前拥有客房502间，是深圳宝安区目前最新建的挂牌五星级酒店。该酒店2010年度和2011年度的客房入住率分别约为58.84%和67.33%，平均房价分别约为585元/天和626元/.</text>
 </news>
 ......
 </businessnews>
 */
public class NetEaseNews implements Article {

    public final String rootTag = "businessnews";
    public final String elemTag = "news";

    public String url;  //爬取url
    public String title;    //新闻标题
    public String time; //新闻发布时间
    public String plate;   //新闻版块
    public String source;   //来源网站名
    public String text; //新闻正文

    public String getRootTag() {
        return rootTag;
    }

    public String getTitle() {
        return title;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getContent() {
        return getText();
    }

    public NetEaseNews(){

    }



    /**
     * 创建一个新闻
     * @param title
     * @param plate
     * @param time
     * @param url
     * @param text
     * @param source
     */
    public NetEaseNews(String url, String title, String time, String plate, String source, String text) {
        this.url = url;
        this.title = title;
        this.time = time;
        this.plate = plate;
        this.source = source;
        this.text = text;
    }
    public NetEaseNews(Element elem) {
        this.title = elem.elementText("title");
        this.plate = elem.elementText("plate");
        this.time = elem.elementText("time");
        this.url = elem.elementText("url");
        this.source = elem.elementText("source");
        this.text = elem.elementText("text");
    }

    /**
     * 以这篇新闻创建一个xml的节点
     * @return
     */
    public Element createElement(){
        Element root = DocumentHelper.createElement(elemTag);

        Element titleElem = root.addElement("title");
        titleElem.setText(this.getTitle());
        Element plateElem = root.addElement("plate");
        plateElem.setText(this.getPlate());
        Element timeElem = root.addElement("time");
        timeElem.setText(this.getTime());
        Element urlElem = root.addElement("url");
        urlElem.setText(this.getUrl());
        Element sourceElem = root.addElement("source");
        sourceElem.setText(this.getSource());
        Element textElem = root.addElement("text");
        textElem.setText(this.getText());

        return root;
    }
}
