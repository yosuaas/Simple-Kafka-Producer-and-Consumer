# Spring Boot with Kafka Producer Example

Simple kafka producer / consumer using Spring Boot.


## Installation


### Start Zookeeper
##### Linux
- `bin/zookeeper-server-start.sh config/zookeeper.properties`
##### Windows
- `bin\windows\zookeeper-server-start.bat config\zookeeper.properties`


### Start Kafka Server
##### Linux
- `bin/kafka-server-start.sh config/server.properties`
##### Windows
- `bin\windows\kafka-server-start.bat config\server.properties`


### Create Kafka Topic
##### Linux
- `bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Book`
##### Windows
- `bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Book`

#### Run Application
1. Go to the parent module folder.
2. Run command `mvn clean package`. It will generate ./output file
3. Run producer service : 
- ##### Linux
    `output/simpleproducer/run.sh`
- ##### Windows
    `output/simpleproducer/run.bat`
4. Run consumer service :
- ##### Linux
    `output/simpleconsumer/run.sh`
- ##### Windows
    `output/simpleconsumer/run.bat`


#### Logs
- `output/simpleconsumer/logs/simpleconsumer.log`
- `output/simpleconsumer/logs/simpleproducer.log`


## Publish message
* **URL**
    `http://localhost:5000/produce/book`
* **Method:**

    `POST`
* **Body**
```json
        {
            "author": <String>,
            "title" : <String>,
            "pages" : <Integer>
        }
```