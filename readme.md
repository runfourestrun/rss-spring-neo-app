## WSJ Article Analyzer

This project consists of the following technologies:


* AWS Comprehend
* Neo4j
* AWS SQS Queues (toDo: Replace with Kafka topics) 
* Spring Boot / Spring Data Neo4j 


![image](https://user-images.githubusercontent.com/90913666/232853787-6b20d539-f97d-49d8-8c64-db4a53f1b40e.png)



* The project creates a Spring Boot service that polls WSJ RSS Feeds, extracts free form text / natural language and publishes that too an SQS queue. From there the text is parsed by AWS comprehend and mapped to Neo4j using another Spring service. 
