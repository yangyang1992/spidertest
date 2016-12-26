package com.ry.spidertest.application;

import com.ry.jspider.core.task.TaskHandlerAdaptor;
import com.ry.spidertest.mongodb.MongoConnector;

/**
 * Created by yangyang on 2016/12/26.
 */
public class ArticleCatcherHandler extends TaskHandlerAdaptor {
    private MongoConnector mongoConnector = new MongoConnector("book", "bookCollection");

    @Override
    public void messageReceived(String result) {
        System.out.println(result);
        mongoConnector.insertDocument(result);
    }
}
