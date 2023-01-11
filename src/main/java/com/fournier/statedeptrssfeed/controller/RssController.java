package com.fournier.statedeptrssfeed.controller;

import com.fournier.statedeptrssfeed.rssfeed.RSSFeedProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class RssController {


    private RSSFeedProxy rssFeedProxy;

    public RssController(RSSFeedProxy rssFeedProxy){
        this.rssFeedProxy = rssFeedProxy;
    }


    @GetMapping("/poll")
    public ResponseEntity<?> pollRSSFeed() throws IOException {


        var example = rssFeedProxy.consumeRSSFeed();

        if(example.isEmpty()){
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .build();
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }


    }





}
