package com.ry.spidertest.application;

import com.ry.jspider.core.builder.TaskXMLBuilder;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskWorker;
import com.ry.jspider.log.Log;
import com.ry.spidertest.mapper.TaskURLMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 2016/12/26.
 */
public class ArticleCatcher {
    private static Log log = Log.getLogger(ArticleCatcher.class);
    private List<String> urls;
    private TaskWorker worker;

    private SqlSession sqlSession;
    private TaskURLMapper taskURLMapper;

    public ArticleCatcher() {
        worker = new TaskWorker("catchArticle");
        worker.setHandler(new ArticleCatcherHandler());
        worker.init();

        Thread thread = new Thread(worker);
        thread.start();

        try{
            Reader reader = Resources.getResourceAsReader("Configuration.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
            taskURLMapper = sqlSession.getMapper(TaskURLMapper.class);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<String> getArticleURL() throws InterruptedException {
        List<Map<String, String>> articleList = taskURLMapper.getArticleURL(1);
        sqlSession.commit();

        for (Map<String, String> map : articleList) {
            String articleURL = map.get("url");
            Task task = TaskXMLBuilder.build(articleURL, "catchArticle");
            worker.submitTask(task);
            Thread.sleep(1000L);
        }
        sqlSession.close();
        return null;
    }
}
