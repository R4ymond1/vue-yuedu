package com.gedc.test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupParserTest {

    public static void main(String[] args) {

        // 确定目标地址 URL 统一资源定位符
        String url = "https://book.douban.com/";

        // 2 解析 html ： https：//jsoup.org
        try {
            //
            Document doc = Jsoup.connect(url).get();

            // System.out.println(doc.title());
            // System.out.println(doc.html());

            // 从 Doc 的树形结构中查找 img 标签
            // .class 选择器
            Elements els = doc.select(".cover img");
            System.out.println(els.size());

            // 创建一个线程池
            // .class 选择器
            ExecutorService pool = Executors.newCachedThreadPool();
            pool = Executors.newFixedThreadPool(9);
            // pool = Executors.newSingleThreadExecutor();

            for (Element e : els) {
                // <img src="" width="" height="" />
                String src = e.attr("src");
                System.out.println(src);

                // 下载每张图片
                pool.execute(new DownloadTask(src));
            }
            // 释放资源
            pool.shutdown();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}