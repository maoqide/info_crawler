package com.mao.infocrawler.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by mao on 2016/3/10.
 */
public class CrawlerController {

    private static final Logger logger = LoggerFactory.getLogger(CrawlController.class);

    public static void main(String[] args) throws Exception {

        String crawlStorageFolder = "C:\\Users\\mao\\Desktop\\tmp";

        int numberOfCrawlers = 5;

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);

        config.setPolitenessDelay(1000);

        config.setMaxDepthOfCrawling(3);

        config.setMaxPagesToFetch(1000);

        config.setIncludeBinaryContentInCrawling(false);

        config.setResumableCrawling(false);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("http://tech.163.com/");

        controller.start(NeteaseCrawler.class, numberOfCrawlers);
    }
}
