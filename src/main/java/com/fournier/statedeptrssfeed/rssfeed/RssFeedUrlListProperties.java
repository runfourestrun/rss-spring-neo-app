package com.fournier.statedeptrssfeed.rssfeed;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "rss.feed")
public class RssFeedUrlListProperties {


    public RssFeedUrlListProperties(){};

    private List<String> feedUrls;

    public List<String> getFeedUrls() {
        return feedUrls;
    }

    public void setFeedUrls(List<String> feedUrls) {
        this.feedUrls = feedUrls;
    }
}
