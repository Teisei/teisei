package edu.ecnu.teisei.schema.domain;

import edu.ecnu.teisei.utils.StringUtil;

/**
 * Created by Teisei on 2015/5/22.
 */
public class NewsSentRelation implements Comparable<NewsSentRelation> {
    String url;
    String title;
    String time;
    String sentence;
    String entities;
    String relations;

    public NewsSentRelation(String text) {
        String record[] = text.split(StringUtil._read_split_);
        this.url = record[0];
        this.title = record[1];
        this.time = record[2];
        this.sentence = record[3];
        this.entities = record[4];
        this.relations = record[5];
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getSentence() {
        return sentence;
    }

    public String getEntities() {
        return entities;
    }

    public String getRelations() {
        return relations;
    }

    public void setEntities(String entities) {
        this.entities = entities;
    }

    public void setRelations(String relations) {
        this.relations = relations;
    }



    public NewsSentRelation(String url, String title,String time, String sentence, String entities, String relations) {
        this.url = url;
        this.title = title;
        this.time = time;
        this.sentence = sentence;
        this.entities = entities;
        this.relations = relations;
    }

    @Override
    public String toString() {
        return  url + '\t' +
                title + '\t' +
                time + '\t' +
                sentence + '\t' +
                entities + '\t' +
                relations;
    }

    public int compareTo(NewsSentRelation o) {
        return this.url.compareTo(o.url);
    }
}
