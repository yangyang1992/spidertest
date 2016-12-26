package com.ry.spidertest.application;

import com.ry.jspider.config.Const;

/**
 * Created by yangyang on 2016/12/25.
 */
public class Application {
    private static String url =
            "http://www.biquge3.com/xs/1169574/";
    static {
        Const.CONFIG_BASE =
                "F:\\code\\spidertest\\src\\main\\resources\\";
        Const.TASK_FILE = "spidertest.xml";
    }

    public static void main(String[] args) throws InterruptedException {
//        TaskCatcher taskCatcher = new TaskCatcher(url);
//        taskCatcher.catchTask();
        ArticleCatcher articleCatcher = new ArticleCatcher();
        articleCatcher.getArticleURL();
    }
}
