package edu.ecnu.teisei.ir.lucene.index;

import edu.ecnu.teisei.ir.config.Configer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IndexDAO {


    static IndexDAO instance = null;
    String indexPath = Configer.INDEX_PATH_DEFAULT;
    Directory dir = null;

    /**
     * 文本分析
     */
    SmartChineseAnalyzer analyzer = null;

    QueryParser queryParser = null;

    public static IndexDAO getInstance() {
        if (instance == null) {
            instance = new IndexDAO(Configer.INDEX_PATH_DEFAULT);
        }
        return instance;
    }

    public IndexDAO(String indexpath) {
        this.indexPath = indexpath;
        initial();
    }

    public SmartChineseAnalyzer getAnalyzer() {
        return analyzer;
    }

    public IndexDAO() {
        initial();
    }

    private void initial() {
        System.out.println("IndexDAO.initial():......");
        try {
            dir = FSDirectory
                    .open(new File(this.indexPath));
            analyzer = new SmartChineseAnalyzer(Version.LUCENE_46, true);
            queryParser = new QueryParser(Version.LUCENE_46, "title", new SmartChineseAnalyzer(Version.LUCENE_46, true));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * add the docs into index in batch!
     *
     * @param docs
     */
    public void add(List<Document> docs) {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            for (int i = 0; i < docs.size(); i++) {
                indexWriter.addDocument(docs.get(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                indexWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * add the doc to index
     *
     * @param doc
     */
    public void add(Document doc) {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            indexWriter.addDocument(doc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                indexWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param term
     */
    public void delete(Term term) {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            indexWriter.deleteDocuments(term);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                indexWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 优化，合并索引文件
     */
    public void optimize() {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            indexWriter.forceMerge(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                indexWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文档跟新之后优化
     *
     * @param term
     * @param doc
     */
    public void update(Term term, Document doc) {
        IndexWriter indexWriter = null;
        try {
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            indexWriter.updateDocument(term, doc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                indexWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <pre>
     * totalPage = recordCount / pageSize;
     * if (recordCount % pageSize &gt; 0)
     * 	totalPage++;
     * </pre>
     *
     * @param queryString
     * @param firstResult
     * @param maxResults
     * @return
     */
//	public QueryResult search(String queryString, int firstResult, int maxResults) {
//		try {
    //
            /*
             *
			 * PhraseQuery
			PhraseQuery phraseQuery = new PhraseQuery();
			String[] phrase=new MMAnalyzer().segment(queryString, "\t").split("\t");
			for(int i=0;i<phrase.length;i++){
				phraseQuery.add(new Term("title", phrase[i]));
			}
			phraseQuery.setSlop(10);
			return search(phraseQuery, firstResult, maxResults);
			*/
			
			/*
			 * 
			 * TermQuery
			Term term=new Term("title",queryString);
			TermQuery query= new TermQuery(term);
			return search(query, firstResult, maxResults);
			*/

//			Query query = queryParser.parse(queryString);
//			return search(query, firstResult, maxResults);
			/*
			 * 
			 * WildcardQuery
			String[] phrase=new MMAnalyzer().segment(queryString, "\t").split("\t");
			for(int i=0;i<phrase.length;i++){
				//phraseQuery.add(new Term("title", phrase[i]));
				queryString+=phrase[i]+"*";
			}
			Term term = new Term("title", queryString);
			Query query = new WildcardQuery(term);

			return search(query, firstResult, maxResults);
						*/
//			
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

    /**
     * search with filter
     *
     * @param queryString
     * @param firstResult
     * @param maxResults
     * @return
     */
    public QueryResult search(String[] queryString, int firstResult, int maxResults) {
        IndexSearcher indexSearcher = null;
        try {

            PhraseQuery query = new PhraseQuery();
            query.setSlop(3);

            for (int i = 0; i < queryString.length; i++) {
                query.add(new Term("title", queryString[i]));
            }

            System.out.println("dir: " + dir);
            indexSearcher = new IndexSearcher(DirectoryReader.open(dir));
            Filter filter = null;
            Sort sort = new Sort();
            sort.setSort(new SortField("time", SortField.Type.LONG, false));

            TopDocs topDocs = indexSearcher.search(query, filter, 10000, sort);
            int recordCount = topDocs.totalHits;
            Map<Integer, Document> recordList = new HashMap<Integer, Document>();

            int end = Math.min(firstResult + maxResults, topDocs.totalHits);
            for (int i = firstResult; i < end; i++) {
                ScoreDoc scoreDoc = topDocs.scoreDocs[i];

                int docSn = scoreDoc.doc; //
                Document doc = indexSearcher.doc(docSn);
                recordList.put(i, doc);
            }
            return new QueryResult(recordCount, recordList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
