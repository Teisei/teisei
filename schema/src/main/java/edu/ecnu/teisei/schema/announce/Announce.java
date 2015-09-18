package edu.ecnu.teisei.schema.announce;

import edu.ecnu.teisei.schema.Article;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Created by dingcheng on 2014/11/3.
 */
public class Announce implements Article {
    public int id;  //编号
    public String title;    //标题
    public String party;    //当事人
    public String[] parties;    //当事人s
    public String date; //日期
    public String affiliation;  //执法单位
    public String content;  //公告内容


    public Announce() {

    }

    /**
     * 构造一个公告
     * @param party
     * @param date
     * @param affiliation
     * @param content
     */
    public Announce(String party, String date, String affiliation, String content, int id) {
        this.setId(id);
        this.party = party;
        this.parties = party.split("、");
        this.date = date;
        this.affiliation = affiliation;
        this.content = content;
    }
    public Announce(Element elem){
        //如果有id的话，设置一下id
        String temp_id = elem.attributeValue("id");
        if (!temp_id.equals("") && temp_id != null) {
            this.setId(Integer.parseInt(temp_id));
        }

        this.party = elem.elementText("party");
        this.parties = party.split("、");
        this.date = elem.elementText("date");
        this.affiliation = elem.elementText("affiliation");
        this.content = elem.elementText("content");
    }

    public String getRootTag() {
        return null;
    }

    public void setId(int id) { this.id = id; }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public String getParty() {
        return party;
    }
    public void setParty(String party) {
        this.party = party;
    }

    public String[] getParties() {
        return parties;
    }
    public void setParties(String[] parties) {
        this.parties = parties;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getAffiliation() {
        return affiliation;
    }
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Element createElement() {
        Element root = DocumentHelper.createElement("Announce");

        Element partyElem = root.addElement("party");
        partyElem.setText(this.getParty());
        Element dateElem = root.addElement("date");
        dateElem.setText(this.getDate());
        Element affiliationElem = root.addElement("affiliation");
        affiliationElem.setText(this.getAffiliation());
        Element contentElem = root.addElement("content");
        contentElem.setText(this.getContent());

        return root;
    }
}
