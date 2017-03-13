package com.mao.infocrawler.crawler;

import com.csvreader.CsvWriter;
import com.mao.infocrawler.utils.DateUtil;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/**
 * Created by mao on 2017/3/13.
 */
public class CtripCrawler extends WebCrawler {

    public static final String[] CTRIP_CSV_HEADER = {"title", "price", "score", "person_num"};
    public static final String CSV_FILE_PATH = "C:\\test\\ctrip.csv";

    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    static {
        createCsv(CTRIP_CSV_HEADER);
    }
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of image extensions.
        if (FILTERS.matcher(href).matches()) {
            return false;
        }

        // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
        return href.startsWith("http://vacations.ctrip.com/");
    }

    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();

        if (!url.endsWith("html") && !url.endsWith("shtml")) {
            return;
        }

        CsvWriter wr;

        try {
            Document doc = Jsoup.connect(url).get();
            //Element epContentLeft = doc.select("#epContentLeft").first();

            Element h1 = doc.select(".product_scroll_wrap > h1").first();

            Element price_wrap = doc.select("#js_main_price_wrap").first();
            Element price = price_wrap.select(".total_price").first();
            Element sco = doc.select(".comment_wrap > .score").first();
            Element p_num = price_wrap.select(".comment_wrap > span").first();

            String title = h1.text().trim();
            //String content = endText.html();
            String pri = price.text().trim();
            String score = sco.text().trim();
            String person_num = p_num.text().trim();

            logger.debug("Docid: {}", docid);
            logger.info("Title: {}", title);
            logger.info("PRICE: {}", pri);
            logger.info("score: {}", score);
            logger.info("p_num: {}", person_num);
            String record = title + "###" + pri + "###" + score + "###" + person_num;
            writeCsv(record);



        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.debug("=============");
    }



    public static void createCsv(String[] headers) {
        CsvWriter wr = new CsvWriter(CSV_FILE_PATH, ',', Charset.forName("GBK"));
        try {
            wr.writeRecord(headers);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            wr.close();
        }
        return;
    }
    public void writeCsv(String record) {

        CsvWriter wr = new CsvWriter(CSV_FILE_PATH, ',', Charset.forName("GBK"));
        //System.out.println("*******************************"+record);
        String[] row = record.split("###");
        try {
            wr.writeRecord(row);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            wr.close();
        }
        return;

    }

    public static void main(String[] args) {

    }
}
