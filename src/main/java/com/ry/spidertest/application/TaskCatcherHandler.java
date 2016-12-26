package com.ry.spidertest.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ry.jspider.core.task.TaskHandlerAdaptor;
import com.ry.jspider.log.Log;
import com.ry.spidertest.entity.TaskURL;
import com.ry.spidertest.mapper.TaskURLMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by yangyang on 2016/12/25.
 */
public class TaskCatcherHandler extends TaskHandlerAdaptor {
    private static Log log = Log.getLogger(TaskCatcherHandler.class);

    private TaskCatcher taskCatcher;
    private TaskURLMapper taskURLMapper;
    private SqlSession sqlSession;

    public TaskCatcherHandler(TaskCatcher taskCatcher) {
        this.taskCatcher = taskCatcher;
        try{
            Reader reader = Resources.getResourceAsReader("Configuration.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
            taskURLMapper = sqlSession.getMapper(TaskURLMapper.class);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void messageReceived(String result) {
        log.info("json->{}", result);
        JSONArray jsonArray = JSON.parseArray(result);

        for (Object object : jsonArray) {
            String url = (String) object;
            TaskURL taskURL = new TaskURL();
            taskURL.setBookId(1);
            taskURL.setUrl(url);
            taskURLMapper.add(taskURL);
        }
        sqlSession.commit();
        sqlSession.close();
        taskCatcher.stop();
    }
}
