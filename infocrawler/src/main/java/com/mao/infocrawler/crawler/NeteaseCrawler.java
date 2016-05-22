package com.mao.infocrawler.crawler;

import com.alibaba.fastjson.JSON;
import com.mao.infocrawler.model.entity.Item;
import com.mao.infocrawler.utils.RedisUtil;
import com.mao.infocrawler.utils.DateUtil;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by mao on 2016/2/13.
 */
public class NeteaseCrawler extends WebCrawler {

    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    private RedisUtil redisUtil = new RedisUtil();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of image extensions.
        if (FILTERS.matcher(href).matches()) {
            return false;
        }

        // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
        return href.startsWith("http://tech.163.com/");
    }

    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();

        if (!url.endsWith("html")) {
            return;
        }

        try {
            Document doc = Jsoup.connect(url).get();
            //Element epContentLeft = doc.select("#epContentLeft").first();

            Element h1title = doc.select("#h1title").first();
            Element h1 = doc.select("#epContentLeft > h1").first();
            Element endText = doc.select("#endText").first();
            String title = "";
            if (h1title != null) {
                title = h1title.text().trim();
            } else {
                title = h1.text().trim();
            }
            //String content = endText.html();
            String content = endText.text().trim();

            logger.debug("Docid: {}", docid);
            logger.info("Title: {}", title);
            logger.info("URL: {}", url);
            logger.info("content: {}", content);

            Item item = createItem(title, content, url);
            String jsonItem = JSON.toJSONString(item);

            if (redisUtil.setStringVal(DateUtil.getTimeStamp(), jsonItem)) {
                logger.info("Docid: {} add to redis successfully", docid);
            } else {
                logger.info("Docid: {} add to redis failed", docid);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        Header[] responseHeaders = page.getFetchResponseHeaders();
        if (responseHeaders != null) {
            logger.debug("Response headers:");
            for (Header header : responseHeaders) {
                logger.debug("\t{}: {}", header.getName(), header.getValue());
            }
        }

        logger.debug("=============");
    }

    private Item createItem(String title, String content, String url) {
        Item item = new Item();
        item.setTitle(title);
        item.setContent(content);
        item.setUrl(url);
        item.setResource("netease");
        item.setTime(DateUtil.getYMDHTime());
        return item;
    }

    public static void main(String[] args) {
    }

}
    