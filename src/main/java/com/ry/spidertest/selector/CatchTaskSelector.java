package com.ry.spidertest.selector;

import com.ry.jspider.selector.annotation.Param;
import org.jsoup.select.Elements;

/**
 * Created by yangyang on 2016/12/25.
 */
public interface CatchTaskSelector {
    Elements catchTask(@Param("html")String html);
}
