package com.Blog.Luence;

import com.Blog.Entity.Blog;
import com.Blog.Util.DateUtil;
import com.Blog.Util.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.helper.DataUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 * 用于添加索引
 * luence索引
 */
@Component
public class BlogIndex {
    private Directory directory;

    public IndexWriter getwrite() {
        IndexWriter indexWriter = null;
        try {
            directory = FSDirectory.open(Paths.get("D://Luence"));
            SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(smartChineseAnalyzer);
            indexWriter = new IndexWriter(directory, indexWriterConfig);
            return indexWriter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public void addIndex(Blog blog) {
        IndexWriter indexWriter = getwrite();
        Document document = new Document();
        document.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        document.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        document.add(new StringField("releasedate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        document.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
        try {
            indexWriter.addDocument(document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public void update(Blog blog)
   {
       IndexWriter indexWriter = getwrite();
       Document document = new Document();
       document.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
       document.add(new TextField("title", blog.getTitle(), Field.Store.YES));
       document.add(new StringField("releasedate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
       document.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
       try {
           indexWriter.updateDocument(new Term("id", String.valueOf(blog.getId())),document);
           indexWriter.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public List<Blog> searchBlog(String search) {
        IndexReader indexReader = null;
        IndexSearcher indexSearcher = null;
        List<Blog> list = null;
        try {
            directory = FSDirectory.open(Paths.get("D://Luence"));
            indexReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
            //查询标题
            QueryParser queryParser = new QueryParser("title", smartChineseAnalyzer);
            Query query = queryParser.parse(search);
            //查询内容
            QueryParser queryParser1 = new QueryParser("content", smartChineseAnalyzer);
            Query query1 = queryParser1.parse(search);

            builder.add(query, BooleanClause.Occur.SHOULD);
            builder.add(query1, BooleanClause.Occur.SHOULD);

            TopDocs topDocs = indexSearcher.search(builder.build(), 100);
            QueryScorer queryScorer = new QueryScorer(query);
            Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color = 'red'>", "</font></b>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, queryScorer);
            highlighter.setTextFragmenter(fragmenter);

            list = new LinkedList<>();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                Blog blog = new Blog();
                blog.setId(Integer.parseInt(document.get("id")));
                blog.setReleasedateString(document.get("releasedate"));
                String title = document.get("title");
                String content = StringEscapeUtils.escapeHtml(document.get("content"));

                if (title != null) {
                    TokenStream tokenStream = smartChineseAnalyzer.tokenStream("title", new StringReader(title));
                    String highTitle = highlighter.getBestFragment(tokenStream, title);
                    if (StringUtil.isEmpty(highTitle)) {
                        blog.setTitle(title);
                    } else {
                        blog.setTitle(highTitle);
                    }
                }
                if (content != null) {
                    TokenStream tokenStream = smartChineseAnalyzer.tokenStream("content", new StringReader(content));
                    String highcontent = highlighter.getBestFragment(tokenStream, content);
                    if (StringUtil.isEmpty(highcontent)) {
                        if (content.length() <= 200) {
                            blog.setContext(content);
                        } else {
                            blog.setContext(content.substring(0, 200));
                        }
                    } else {
                        blog.setContext(highcontent.substring(0, 200));
                    }
                }
                list.add(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public void delete(String id)
    {
        IndexWriter indexWriter = getwrite();
        try {
            indexWriter.deleteDocuments(new Term("id",id));
            indexWriter.forceMergeDeletes();
            indexWriter.commit();
            indexWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
