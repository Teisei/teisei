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
 <title>����116�ҹ�˾������������Ԥ��</title>
 <time>2012-03-29 08:03:35��</time>
 <plate>���ײƾ�Ƶ��</plate>
 <source>֤ȯ�ձ�-�ʱ�֤ȯ��</source>
 <text>������������Ԥ���ĸ����У���80ֻ���ɽ���ʵ�����ǣ�ռ��68.97%������ָ֤���µ�2.65%����֤��ָ�µ�3.15%�����ǹ�����������Ԥ���ĸ�������ȴ������ͣ���������ҩҵ��ͣ��ST������ͣ��ST��������ͣ��ͨ��������������ս��Ͷ���߻����չ��沢�������й�˾�ķ�չ���ش����á���Ϊ���������������������飬�������֮�󣬹�˾�Ĺ�Ȩ�ṹ�����ᷢ���ϴ�仯�����������ع�Ȩ��������������ʹ�����й�˾�ľ�Ӫ��������ƣ�����ɼ��ڶ����г���ȻҲ���ø��ߵ����¹�ֵ�������ڶ����г��ϱ��ֿ������������Զ����й�˾�ɼ�Ӱ���������ݡ�֤ȯ�ձ����г��о���������ͳ����ʾ���������й���116�����й�˾������������Ԥ����������80ֻ���ɽ���ʵ�����ǣ�ռ��68.97%�����ڣ���ѡ3ֻ������ͣ�Ķ�������Ԥ�������ĸ��ɽ���������ϣ���ܸ�Ͷ����ѡ���ṩһ���ο�������ҩҵ�ʲ����������ͣͳ����ʾ������ҩҵ���չ��涨������Ԥ���������ô̼����չ���ҩҵ��ͣ����Ϥ����˾�����ҩ���ŷ��йɷ���Ϊ֧���Լۣ��չ���ҩ����ӵ�л���Ȩ���õķ��ݽ�����̱ꡢ������չ100%��Ȩ������ҽ��12.50%��Ȩ�������⹺���ʲ���Ԥ��ֵΪ4.82��Ԫ��������4.38��Ԫ�����η��йɷݼ۸�Ϊ12.2Ԫ/�ɣ��ⷢ�йɷ�Լ0.36�ڹɡ�(����ɶ��չ�����Ȩ�ļ۸񣬼�A�������12.2Ԫ/�ɣ�H�ɸ۱�5.54Ԫ/��)(�����ش��ʲ������ܷ����ճɹ�ʵʩ���ڲ�ȷ����)���⣬��˾���������ɷݻ��ɷ�ʽ���պϲ�����ɽ��������ɺ󣬰���ɽ��ע�������ʸ���ȫ���ʲ�����ծ��Ȩ�桢ҵ�����Ա�����빫˾������ɽ�빫˾�Ļ��ɱ���Ϊ1:0.95.ST��������¡��ҵ100%��Ȩͳ����ʾ��ST����������ͣ������8.68Ԫ/����¡��ҵȫ��ɶ�(8λ��Ȼ��)����1.84�ڹɹ���¡��ҵ100%��Ȩ����˾2012��3��20�չ��棬��˾��1Ԫ��ȫ���ʲ�����ծ(����ֵ-842.18��Ԫ)���۸���Զ���ţ�ͬʱ��8.68Ԫ/����¡��ҵȫ��ɶ�(8λ��Ȼ��)����1.84�ڹɹ���¡��ҵ100%��Ȩ(����ֵ15.94��Ԫ����ֵ��1085.37%)��ǰ������׻�Ϊ��Ч��������¡��ҵ����ȫ���ӹ�˾��̩��ҵ�Ŀ����Դ��ҪΪ�ƽ𣬹�������3��ɿ�Ȩ��5��̽��Ȩ�����Ѿ�������Դ�����󱸰�����Դ����21.211�֡����ν�����ɺ󣬹�˾��Ӫҵ�񽫱��Ϊ�ƽ��ѡ�����ۡ����⣬��˾���������ǩ���ġ����󲹳�Э�顷�������⼰��һ���ж����Թ��㡢�Թ�����˾��֤����ŵ�ڱ��ν���ʵʩ��ϵ������������(2012�ꡢ2013�ꡢ2014��)���⹺���ʲ�(��¡��ҵ100%��Ȩ)ʵ�־����󲻵���2.32��Ԫ��2.41��Ԫ��2.41��Ԫ��ÿ�����һ�ιɷݲ��������ɹ�˾��1Ԫ�ļ۸���лع��������⼰��һ���ж����Թ��㡢�Թ��°���������ڱ��ν���ǰ���й����ʲ�Ȩ������ֱ�������Ӧ�е��Ĳ����ɷݡ�ST����������ɶ��Ƶ��ʲ�ͳ����ʾ��ST������������ͣ����˾�����ɶ�������ʵҵ���в�����7814.76��ɹ�������Ͷ��100%��Ȩ��������ɺ󣬸߶˾Ƶ�ľ�Ӫ������Ϊ��˾����Ӫҵ�񡣾�Ϥ����˾�����ɶ�������ʵҵ���в�����7814.76��ɹ�������Ͷ��100%��Ȩ�����м۸�6.91Ԫ/�ɡ�������Ͷ���Ϲ��ɷ�������Ϊ36�����ڡ�����2011��12��31�գ����ױ�ĺϲ���Ʊ������澻ֵԼΪ4.42��Ԫ��Ԥ��ֵԼΪ5.85��Ԫ��������ֵ��ԼΪ32.35%���������۲�����5.4��Ԫ�����ν���ǰ��������ʵҵ���й�˾23.15%�Ĺ�Ȩ��Ϊ��˾�عɹɶ���������ɺ�����ʵҵ�����й�˾������62.71%�Ĺ�Ȩ�����ν��ױ�ĵĺ����ʲ��Ǳ��������ʴ�Ƶ깫·���������ڱ������ʻ���Լ5����·�̣�����滮�еĹ��Ҽ�������ǰ������ִ�����ҵ��������Լ19����Ƶ�Ŀǰӵ�пͷ�502�䣬�����ڱ�����Ŀǰ���½��Ĺ������Ǽ��Ƶꡣ�þƵ�2010��Ⱥ�2011��ȵĿͷ���ס�ʷֱ�ԼΪ58.84%��67.33%��ƽ�����۷ֱ�ԼΪ585Ԫ/���626Ԫ/.</text>
 </news>
 ......
 </businessnews>
 */
public class NetEaseNews implements Article {

    public final String rootTag = "businessnews";
    public final String elemTag = "news";

    public String url;  //��ȡurl
    public String title;    //���ű���
    public String time; //���ŷ���ʱ��
    public String plate;   //���Ű��
    public String source;   //��Դ��վ��
    public String text; //��������

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
     * ����һ������
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
     * ����ƪ���Ŵ���һ��xml�Ľڵ�
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
