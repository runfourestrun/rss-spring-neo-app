package com.fournier.statedeptrssfeed.rssfeed;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepublishFeed {
    private final Logger LOG = LoggerFactory.getLogger(RepublishFeed.class);

    private final RssFeedUrlListProperties rssFeedUrlListProperties;

    @Autowired
    public RepublishFeed(RssFeedUrlListProperties rssFeedUrlListProperties){
        this.rssFeedUrlListProperties = rssFeedUrlListProperties;
    }


    }
