package com.fournier.statedeptrssfeed.controller;

import com.amazonaws.services.kendra.model.SalesforceKnowledgeArticleState;
import com.fournier.statedeptrssfeed.entity.Article;
import com.fournier.statedeptrssfeed.rssfeed.RSSFeedProxy;
import com.fournier.statedeptrssfeed.rssfeed.SQSProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
public class RssController {


    private RSSFeedProxy rssFeedProxy;
    private SQSProxy sqsProxy;

    public RssController(RSSFeedProxy rssFeedProxy,SQSProxy sqsProxy){
        this.rssFeedProxy = rssFeedProxy;
        this.sqsProxy = sqsProxy;
    }


    @GetMapping("/poll")

    public ResponseEntity<?> pollRSSFeed() throws IOException {


        List<Article> articles = rssFeedProxy.consumeRSSFeed();


        if(articles.isEmpty()){
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .build();
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }


    }





}
