package com.ry.spidertest.filter;

import com.ry.jspider.core.task.Result;
import com.ry.jspider.core.task.Task;
import com.ry.jspider.core.task.TaskFilter;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangyang on 2016/12/26.
 */
public class CatchTextFilter extends TaskFilter {
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
//    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符

    public CatchTextFilter(String name) {
        super(name);
    }

    @Override
    public void beforeChain(Task task, Result result) {
        Elements elements = (Elements) task.getAttributes().get("elements");
        String str = elements.html();
        String data = "{\'articleURL\':\'" + task.getTaskURL()
                + "\',\'article\':\'"+ delHtmlTag(str) + "\'}";
        result.setResultString(data);
    }

    public static String delHtmlTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

//        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
//        Matcher m_space = p_space.matcher(htmlStr);
//        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }

    public static void main(String[] args) {
        String str = "<div style='text-align:center;'> 整治“四风”   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会。</span><br/></div>";
        str = str.replace("&nbsp;", "");
        System.out.println(delHtmlTag(str));
    }

}
