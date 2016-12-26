package com.ry.spidertest.filter;

import com.alibaba.fastjson.JSONArray;
import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import com.ry.jspider.selector.annotation.Selector;
import com.ry.spidertest.selector.CatchTaskSelector;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/25.
 */
public class CatchTaskFilter extends TaskFilter {
    public CatchTaskFilter(String name) {
        super(name);
    }

    @Selector
    private CatchTaskSelector catchTaskSelector;

    @Override
    public void beforeChain(Task task, Result result) {
        String html = (String) task.getAttributes().get("html");
        Elements elements = catchTaskSelector.catchTask(html);

        JSONArray jsonArray = new JSONArray();

        for (Element element : elements) {
            jsonArray.add(element.attr("href"));
        }
        task.getAttributes().put("json", jsonArray);
    }

    @Override
    public void afterChain(Task task, Result result) {
        JSONArray jsonArray = (JSONArray) task.getAttributes().get("json");
        String json = jsonArray.toJSONString();
        result.setResultString(json);
    }
}
