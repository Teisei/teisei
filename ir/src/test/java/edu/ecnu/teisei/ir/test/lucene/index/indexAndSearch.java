package edu.ecnu.teisei.ir.test.lucene.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by dingcheng on 2014/12/22.
 */
public class indexAndSearch {
    public static void main(String args[]) {
        String path = "";
        String output = "";
    }

    /**
     * 创建 index
     */
    @Test
    public void testCreateIndex() throws IOException {

        String indexPath = "./ir/output/index_"+this.getClass().getName().toString();

        Date start = new Date();

        Directory dir = FSDirectory.open(new File(indexPath));

        /**
         * 解析文本的分析器
         */
        Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter writer = new IndexWriter(dir, iwc);

        String[] strs = new String[]{
                "终端的保修期为一年。",
                "凡在保修期内非人为损坏，均可免费保修。",
                "人为损坏的终端将视情况收取维修费用。",
                "今天我们将派人去你家修理损坏的终端，并收取一定的费用。"
        };

        for (int i = 0; i < strs.length; i++) {
            System.out.print("process: " + strs[i]+"  ");
            Document doc = new Document();

            Field field = new TextField("content", strs[i], Field.Store.YES);
            doc.add(field);
            if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                System.out.println("create index");
                writer.addDocument(doc);
            } else {
                writer.updateDocument(new Term("content", strs[i]), doc);
                System.out.println("update index");
            }
        }

        writer.close();

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime() + " total milliseconds");
    }

    /**
     * 通过 index 来搜索
     */
    @Test
    public void testSearch() throws IOException, ParseException {
        String indexPath = "./ir/output/index_"+this.getClass().getName().toString();

        System.out.println("Index directory '" + indexPath);
        Date start = new Date();

        Directory dir = FSDirectory.open(new File(indexPath));
        Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(dir);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser(Version.LUCENE_46, "content", analyzer);
        Query query = parser.parse("终端 费用^4");
        ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;

        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("content"));
            System.out.println(hits[i].score);
        }
        ireader.close();
        dir.close();

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime() + " total milliseconds");
    }
}
