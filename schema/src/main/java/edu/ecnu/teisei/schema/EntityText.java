package edu.ecnu.teisei.schema;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingcheng on 2014/11/29.
 */
public class EntityText {
    public static String elemTag = "Record";

    public int id;
    public List<String> entities;

    public List<String> found_entities;

    public String entity__str;
    public String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getEntities() {
        return entities;
    }

    public void setEntities(List<String> entities) {
        this.entities = entities;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEntity__str() {
        return entity__str;
    }

    public void setEntity__str(String entity__str) {
        this.entity__str = entity__str;
    }

    public List<String> getFound_entities() {
        return found_entities;
    }

    public void setFound_entities(List<String> found_entities) {
        this.found_entities = found_entities;
    }

    /**
     * 构造一个对象
     * @param text
     * @param id
     */
    public EntityText(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public EntityText(int id, String entity, String text) {
        this.id = id;
        this.entity__str = entity;
        this.text = text;

        this.entities = new ArrayList<String>();
        String ents[] = entity__str.split("\\|");
        for (int i = 0; i < ents.length; i++) {
            this.entities.add(ents[i]);
        }
    }

    public EntityText(int id, List<String> entities, String text) {
        this.id = id;
        this.entities = entities;
        this.text = text;
        this.entity__str = "";
        int temp_index = 0;
        for (String en : entities) {
            if (temp_index != 0) {
                this.entity__str += "|";
            }
            this.entity__str += en;
            temp_index++;
        }
    }

    public EntityText(Element elem) {
        this.id = Integer.parseInt(elem.attribute("id").getText());
        this.text = elem.elementText("text");
        this.entity__str = elem.elementText("entities");

        this.entities = new ArrayList<String>();
        String entity_str[] = this.entity__str.split("\\|");
        for (int i = 0; i < entity_str.length; i++) {
            this.entities.add(entity_str[i]);
        }
    }

    public Element createElement() {
        Element root = DocumentHelper.createElement(elemTag);
        root.addAttribute("id", "" + getId());

        Element authorElem = root.addElement("text");
        authorElem.setText(this.text);

        Element timeElem = root.addElement("entities");
        timeElem.setText(this.entity__str);

        return root;

    }

    /**
     * 统计这一组测试数据的precision 和 recall
     *
     * @return
     */
    public String PrecisionAndRecall() {
        /**
         * 如果还没有找到实体。
         */
        if (this.found_entities == null) {
            return null;
        }

        String res = "";
        int right_found=0;
        /**
         * 遍历每个 entity
         */

        for (String term : entities) {
            for (String found_term : found_entities) {
                if (found_term.equals(term)) {
                    //是实体，并且被找到。
                    right_found++;
                    break;
                }
            }
        }
        return res+entities.size()+"_"+right_found;
    }

    public static void main(String args[]) {
        String str = "today";
        String entity_str[] = str.split("\\|");
        for (int i = 0; i < entity_str.length; i++) {
            System.out.println("|" + entity_str[i] + "|");
        }
    }
}
