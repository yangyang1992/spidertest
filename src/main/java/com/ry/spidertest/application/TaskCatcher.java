package com.ry.spidertest.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ry.jspider.core.builder.TaskXMLBuilder;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskWorker;
import com.ry.jspider.log.Log;

/**
 * Created by yangyang on 2016/12/25.
 */
public class TaskCatcher {
    private static Log log = Log.getLogger(TaskCatcher.class);
    private String url;
    private TaskWorker worker;

    public TaskCatcher(String url) {
        this.url = url;
    }

    public void catchTask() {
        worker = new TaskWorker("catchTask");
        worker.setHandler(new TaskCatcherHandler(this));
        worker.init();
        Task task = TaskXMLBuilder.build(url, "catchTask");

        Thread thread = new Thread(worker);
        thread.start();
        worker.submitTask(task);
    }

    public void stop() {
        worker.destroy();
    }
}
