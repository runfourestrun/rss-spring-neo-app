package com.fournier.statedeptrssfeed.controller;

import com.fournier.statedeptrssfeed.rssfeed.RSSFeedProxy;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
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
        var list = rssFeedProxy.consumeRSSFeed();
        var example = list.stream()
                .filter(syndEntry -> {
                    boolean b = syndEntry.getAuthor().length() > 10;
                    return b;
                })
                .findFirst();


        if(example.isPresent()){
           return  ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(example.get());
        }

        else {

           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }



    }





}
