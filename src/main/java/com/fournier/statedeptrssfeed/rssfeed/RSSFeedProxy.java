package com.fournier.statedeptrssfeed.rssfeed;


import com.fournier.statedeptrssfeed.entity.ArticleEntity;
import com.fournier.statedeptrssfeed.util.GeneralUtil;
import com.fournier.statedeptrssfeed.util.TriFunction;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/***
 * I think this would technically be considered a service?
 */
@Component
public class RSSFeedProxy {
    private final Logger LOG = LoggerFactory.getLogger(RSSFeedProxy.class);

    @Value("${rss.feed.urls[0])")
    private String rssFeedUrl;

    private final TriFunction<String,String,String, ArticleEntity> articleFactory = ArticleEntity::new;

    private SyndFeed syndFeed;



    public RSSFeedProxy() {
        this.syndFeed = new SyndFeedImpl();
    }



    public List<SyndEntry> consumeRSSFeed() throws IOException {

        URL url = new URL(rssFeedUrl);

        List<SyndEntry> rssEntries = getRssEntries(url);

        rssEntries.forEach(feed -> System.out.println(feed.toString()));

        return rssEntries;

        }






    private List<SyndEntry> getRssEntries(URL url){
        List<SyndEntry> syndEntries = new ArrayList<>();
        try(XmlReader reader = new XmlReader(url)){
            SyndFeed feed = new SyndFeedInput().build(reader);
            syndEntries = feed.getEntries();
            syndEntries.forEach(entry -> System.out.println(entry.toString()));
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }

        return syndEntries;
    }







}





