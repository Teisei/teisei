package edu.ecnu.teisei.schema.news;

import edu.ecnu.teisei.schema.Article;
import edu.ecnu.teisei.utils.Sentenizer;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 *
 <?xml version="1.0" encoding="UTF-8"?>
 <businessnews>
 <news>
 <url>http://www.21cbh.com/2012/xhn_604/143165.html</url>
 <title>中国民营快�?�加快进入�?�航空时代�??</title>
 <info>新华�?                              2012-06-04 17:09:56            评论0�? 随时随地看新�?</info>
 <keyword>上市公司,股票,IPO,新股,基金,房产,汽车,财经频道,专业财经新闻�?</keyword>
 <text> 新华网上海６月４日电（记者叶锋）４日，一架波音７３７货机在杭州萧山国际机场腾空�?�起飞往深圳，圆通�?��?�的全货机运营正式起航�?�中国民营快递正加快进入“航空时代�?��?? 快�?�是集物流�?�资金流、信息流为一体的新型服务业�?�自２００６年邮政体制改革以来，我国的快递尤其是民营快�?�呈现出爆发式增长的态势，涌现出申�?��?�圆通�?�韵达�?�中通（“三通一达�?�）等一批领军企业，快�?�构建起覆盖全国的运营网络�?�２０１１年，全国快递企业业务量达到３６．７亿件，同比增长５７％，最高日处理量突破１８００万件，稳居世界第三�? 据介绍，总部位于上海的圆通�?��?�今后将用全货机主营华东、华南�?�华北之间的国内快�?�市场，实现长三角�?�珠三角、京津冀区域之间快件的次晨达与次日达服务�? “以前我们的快�?�主要是靠轮子在地面跑，现在插上翅膀起飞了�?��?�圆通�?��?�董事长喻渭蛟说。他举例说，通过全货机运营，从杭州到深圳的快递可以实现�?�今日收、明晨至”，比以�?缩短�?天时间�?? 随着贸易、经济的快�?�增长和电子商务的�?�井喷�?�，中国快�?�业获得了强劲的驱动力�?�在此过程中，快递服务市场细分明显，对时效要求更高的商务件份额越来越大�?�另�?方面，与ＵＰＳ�?�联邦快递等全球快�?�巨头相比，中国民营快�?�企业在运输工具、高端服务能力�?�国际化程度等方面的差距还十分明显�?? “拥有自己的货运飞机是不少民营快递企业多年追逐的梦想。�?�上海市邮政管理�?�?长李惠德说，目前上海已是国内快�?��?��?�部经济”集聚度�?高的城市，�?�全货机运营”将提升上海乃至全国快�?�业的运营水准�?? 目前，圆通�?��?�全网络已有５９个转运中心，５０００余个网点，员工８万余人，２０１１年，全网业务量同比增长７０％，运能同比增长４０％以上。喻渭蛟介绍，圆通计划在１０年内培育２０架规模的“全货机机队”，进一步优化航线和运力结构，并加大国际运力投放力度，加快国际化的推进�?�度�?</text>
 </news>
 </businessnews>
 */

/**
 * Created by dingcheng on 2014/11/10.
 */
public class Century21News implements Article {

    public final String rootTag = "businessnews";
    public final String elemTag = "news";

    public String url;  //url
    public String title;    //����
    public String info; //������Ϣ
    public String keyword; //�ؼ���
    public String[] keywords;    //�ؼ���
    public String text; //����

    public String getRootTag() {
        return rootTag;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getContent() {
        return getText();
    }

    /**
     * ����һƪ��������
     * @param url
     * @param title
     * @param info
     * @param keyword
     * @param text
     */
    public Century21News(String url, String title, String info, String keyword, String text) {
        this.url = url;
        this.title = title;
        this.info = info;
        this.keyword = keyword;
        this.text = text;

        keywords = Sentenizer.split3(keyword);
    }

    public Century21News(Element elem) {
        this.url = elem.elementText("url");
        this.title = elem.elementText("title");
        this.info = elem.elementText("info");
        this.keyword = elem.elementText("keyword");
        this.text = elem.elementText("text");

        keywords = Sentenizer.split3(keyword);
    }

    public Element createElement() {
        Element root = DocumentHelper.createElement(elemTag);

        Element urlElem = root.addElement("url");
        urlElem.setText(this.getUrl());

        Element titleElem = root.addElement("title");
        titleElem.setText(this.getTitle());

        Element infoElem = root.addElement("info");
        infoElem.setText(this.getInfo());

        Element keywordElem = root.addElement("keyword");
        keywordElem.setText(this.getKeyword());

        Element textElem = root.addElement("text");
        textElem.setText(this.getText());
        return root;
    }
}
