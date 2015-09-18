package edu.ecnu.teisei.schema.news;

/**
 * Created by dingcheng on 2014/11/3.
 *
 */

import edu.ecnu.teisei.schema.Article;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 <NewsList>
 <New>
 <Title>百度齐家共享数据库 铺路统一招商</Title>
 <Author>搜狐IT</Author>
 <Time>2011-01-01 09:07:00</Time>
 <Url>http://it.sohu.com/20110101/n278629722.shtml</Url>
 <Text>核心提示：百度与齐家共享后台庞大的数据库，并将搜索引擎与“有啊”平台流量导入齐家网，而齐家则能为自身与百度“有啊”进行统一招商。在引入百度的资金前，齐家网与腾讯、阿里巴巴均有接触。“我们与百度谈了两个通宵后就决定了这笔投资。”对齐家网CEO邓华金来说，“百度对网络用户的习惯和行为更有经验，无论是用户体验的提升，还是在做一个聚流量的网站上，它都能帮到我们。”根据双方的计划，百度可以与齐家共享后台庞大的数据库，并将搜索引擎与“有啊”平台上的流量导入齐家网，而齐家则能为自身与百度“有啊”进行统一招商。据介绍，对百度来说，“人们对电子商务的需求正从单纯的物品交易延伸到生活服务，而齐家网是国内目前服务类电子商务中的龙头。”对此，邓华金的解读是“他们（百度）未必一定要投电子商务，而是齐家网涉及的是服务类的电子商务，这是大家之前忽略的一块，成长空间很大。”拿到投资后，齐家网的目标是加大物流系统的投入，在“未来三年，我们会花2亿元进行物流仓储系统的建设，以实现非标准定制服务的异地扩张。”邓华金称。2005年成立的齐家网，甫一出生就扮演着“信息中介”的角色。它的一端聚合了超过一万家的建材家居供应商，另一端则是150万名具有装修需求的用户，公司要做的便是匹配供需，通过在线平台达成建材、装修、家具等领域的交易。正是靠着“店中店”的轻资产模式，齐家网在五年内迅速完成了原始积累。根据艾瑞咨询的数据，公司2009年占全国家居建材网上销售33%的市场份额，在2010年，公司的份额则有望达到37%。齐家之所以介入物流环节，是考虑到“用户对供应链的要求越来越高”，如果线下服务仍由供应商分别提供，用户体验就很可能受到损害。这其中，由异地配送引发的服务缺口最为明显。例如一个济南的用户在网上订购了上海的橱柜，物流公司将商品从上海的仓库运往济南仓库后，便把货卸下，通知顾客上门来取。大部分情况下，顾客会再找一家本地的物流公司，将橱柜从仓库送到家中。即便将橱柜送到了目的地，供应商又会面临上门安装的问题。事实上，大部分建材家居品牌都缺乏全国的服务网络。“谁来配送，谁来安装”的问题似乎难以解决，这不仅导致了用户的抱怨，也阻碍了齐家网上绝大部分供应商的异地扩张之路。“显然，异地用户想要的是送货上门的服务。”邓华金称，“但很可惜，国内尚未建立起大件商品异地物流的整体解决方案。”此外，“家居建材讲究的是定时配送，往往是今天下订单，十几天后送到。如果早到，家中很可能还在装修，没有地方安放；如果晚到，则可能延误工期。”在这种情况下，邓华金发现此前的老系统完全失了效，毕竟，建材与3C等品类分属完全不同的业态，整个配送流程都发生了颠覆。在勾勒了异地扩张的蓝图后，齐家的下一步便是在全国范围内跑马圈地。目前拥有28个分站的齐家网，计划到2013年底将业务拓展至国内50个城市；到2015年底，扩展到全国100个城市。而邓华金同样清楚，“非标定制服务”涉及到本地化的运营，需要一个个城市精耕细作，不可能一蹴而就。“资金、人才、系统、流程与异地扩张的准备，我全都拟好了，2011年是我们发力的时候。”邓华金称。不可否认的是，在实现“非标定制服务”的全国扩张中，齐家仍需要反复试错。</Text>
 </New>
 </NewsList>
 */
public class BigDataNews implements Article {

    public final String rootTag = "NewsList";
    public final String elemTag = "News";

    public String title;    //新闻标题
    public String author;   //作者，制作单位
    public String time; //新闻发布时间
    public String url;  //爬取url
    public String text; //新闻正文

    public String getRootTag() {
        return rootTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public BigDataNews(){

    }

    public String getContent() {
        return getText();
    }

    /**
     * 创建一个新闻
     * @param title
     * @param author
     * @param time
     * @param url
     * @param text
     */
    public BigDataNews(String title, String author, String time, String url, String text) {
        this.title = title;
        this.author = author;
        this.time = time;
        this.url = url;
        this.text = text;
    }
    public BigDataNews(Element elem) {
        this.title = elem.elementText("Title");
        this.author = elem.elementText("Author");
        this.time = elem.elementText("Time");
        this.url = elem.elementText("Url");
        this.text = elem.elementText("Text");
    }

    /**
     * 以这篇新闻创建一个xml的节点
     * @return
     */
    public Element createElement(){
        Element root = DocumentHelper.createElement(elemTag);

        Element titleElem = root.addElement("Title");
        titleElem.setText(this.getTitle());
        Element authorElem = root.addElement("Author");
        authorElem.setText(this.getAuthor());
        Element timeElem = root.addElement("Time");
        timeElem.setText(this.getTime());
        Element urlElem = root.addElement("Url");
        urlElem.setText(this.getUrl());
        Element textElem = root.addElement("Text");
        textElem.setText(this.getText());

        return root;
    }
}
