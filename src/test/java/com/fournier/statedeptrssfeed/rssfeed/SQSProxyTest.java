package com.fournier.statedeptrssfeed.rssfeed;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class SQSProxyTest {

    @Value("${access.key[0]}")
    String key;

    @Value("${rss.feed.urls[0]}")
    String url;


}