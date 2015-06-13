package edu.ecnu.teisei.schema.domain;

import edu.ecnu.teisei.utils.StringUtil;

/**
 * Created by Teisei on 2015/5/22.
 */
public class NewsSentRelation implements Comparable<NewsSentRelation> {
    String url;
    String title;
    String sentence;
    String entities;
    String relations;

    public NewsSentRelation(String text) {
        String record[] = text.split(StringUtil._read_split_);
        this.url = record[0];
        this.title = record[1];
        this.sentence = record[2];
        this.entities = record[3];
        this.relations = record[4];
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

    public NewsSentRelation(String url, String title, String sentence, String entities, String relations) {
        this.url = url;
        this.title = title;
        this.sentence = sentence;
        this.entities = entities;
        this.relations = relations;
    }

    public int compareTo(NewsSentRelation o) {
        return this.url.compareTo(o.url);
    }
}
