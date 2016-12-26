package com.ry.spidertest.filter;

import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.log.Log;
import com.ry.jspider.selector.annotation.Selector;
import com.ry.spidertest.selector.GetArticleSelector;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/25.
 */
public class GetArticleFilter extends TaskFilter {
    private static Log log = Log.getLogger(GetArticleFilter.class);

    @Selector
    private GetArticleSelector getArticleSelector;

    public GetArticleFilter(String name) {
        super(name);
    }

    @Override
    public void beforeChain(Task task, Result result) {
        String html = (String) task.getAttributes().get("html");
//        log.info(html);
        Elements elements = getArticleSelector.getArticle(html);
//        log.info(elements.html());
        task.getAttributes().put("elements", elements);
    }
}
