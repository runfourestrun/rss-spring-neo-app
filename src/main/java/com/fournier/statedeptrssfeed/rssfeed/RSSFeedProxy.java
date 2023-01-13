package com.fournier.statedeptrssfeed.rssfeed;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fournier.statedeptrssfeed.entity.Article;
import com.fournier.statedeptrssfeed.util.TriFunction;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/***
 * I think this would technically be considered a service?
 */
@Component
public class RSSFeedProxy {
    private final Logger LOG = LoggerFactory.getLogger(RSSFeedProxy.class);

    @Value("${rss.feed.urls[0]}")
    private String rssFeedUrl;



    //todo: look into article factory
    //private final TriFunction<String, String, String, Article> articleFactory = Article::new;

    private SyndFeed syndFeed;
    private AmazonSQS amazonSQS;


    public RSSFeedProxy() {
        this.syndFeed = new SyndFeedImpl();

    }





    //TODO: 1. Behavior parameterize.
    //TODO: 2. Remove ugly lambda
    //TODO: 3. I had to remove the List<Author> attribute from Article model, to include that I need to write a custom deserliazer.
    public List<Article> consumeRSSFeed() throws IOException {
        URL url = new URL(rssFeedUrl);
        List<SyndEntry> rssEntries = getRssEntries(url);
        List<SyndEntry> filteredRssEntries = rssEntryFilter(rssEntries);

        List<Article> articles = filteredRssEntries.stream()
                .map(syndEntry -> {
                    var link = syndEntry.getLink();
                    var title = syndEntry.getTitle();
                    var date = syndEntry.getPublishedDate();
                    var description = syndEntry.getDescription().getValue();
                    Article article = new Article(link,title,date,description);
                    return article;


                })
                .collect(Collectors.toList());



        return articles;





    }


    /***
     * This is the correct way to serialize a pojo/bean => json!
     * @param articles
     * @return
     */
    private List<String> marshall(List<Article> articles){
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> json = articles.stream()
                .map(article -> {
                    try {
                        return objectMapper.writeValueAsString(article);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                }).collect(Collectors.toList());

        return json;

    }




    private List<SyndEntry> getRssEntries(URL url) {
        List<SyndEntry> syndEntries = new ArrayList<>();
        try (XmlReader reader = new XmlReader(url)) {
            SyndFeed feed = new SyndFeedInput().build(reader);
            syndEntries = feed.getEntries();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return syndEntries;
    }


    //todo: behavior parameterize. Pull out the lambda logic in the filter.
    private List<SyndEntry> rssEntryFilter(List<SyndEntry> entryList) {
        List<SyndEntry> filteredEntryList = entryList.stream()
                .filter(syndEntry -> {
                    Instant now = Instant.now();
                    Instant yesterday = now.minus(1, ChronoUnit.DAYS);
                    Date yeserdayDate = Date.from(yesterday);
                    return syndEntry.getPublishedDate().after(yeserdayDate);
                })
                .collect(Collectors.toList());

        filteredEntryList.forEach(syndEntry -> System.out.println(syndEntry.toString()));


        return filteredEntryList;
    }













}





