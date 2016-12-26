package com.ry.spidertest.mapper;

import com.ry.spidertest.entity.TaskURL;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 2016/12/25.
 */
public interface TaskURLMapper {
    int add(TaskURL taskURL);
    List<Map<String, String>> getArticleURL(int bookId);
}
