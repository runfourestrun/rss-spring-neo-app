package com.fournier.statedeptrssfeed.rssfeed;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.licensemanager.model.LicenseStatus;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class SQSProxy {


    private static final String AWSREGION = "us-west-2";

    private final String accessKey;


    private final String secretKey;


    @Value("${queue.url}")
    private String queueUrl;

    private AWSCredentials awsCredentials;

    private AmazonSQS sqsClient;


    public SQSProxy(@Value("${access.key}") String accessKey,@Value("${secret.key}") String secretKey){
        this.accessKey = accessKey;
        this.secretKey = secretKey;

        this.awsCredentials = new BasicAWSCredentials(accessKey,secretKey);


        this.sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(AWSREGION)
                .build();
    }



    public void sqsPost(List<String> json){
        json.stream()
                .map(jsonObject -> createMessageAttributeValues(jsonObject))
                .forEach(jsonString -> sendMesssageStandardQueue(jsonString));


    }



    private MessageAttributeValue createMessageAttributeValues(String json){
        return new MessageAttributeValue()
                .withStringValue(json);
    }


    private void sendMesssageStandardQueue(MessageAttributeValue message){

        Map<String,MessageAttributeValue> messageAttributeValueMap = Map.of("Attribute", message);

        SendMessageRequest sendMessage = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody("test message")
                .withDelaySeconds(10)
                .withMessageAttributes(messageAttributeValueMap);



    }



}
