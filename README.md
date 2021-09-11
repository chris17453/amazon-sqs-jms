# Amazon SQS Java Messaging Library Example


### Story so far...

A friend, Matt,  was asking about JMS 2 SQS and heard about the AWS SQS Java lib. He wanted some help setting up a project. I started with eclipse, but there was so much black magic fuckery involved that I couldnt wizard the damned thing to run. 

I spooled up VS Code and opened a new folder. I slapped in some java extensions and had the test app up in about 45 minutes.

This is that App.

## Dependencies
- aws cli
- java
- whatever ide you want
- maven

## Setup your aws creds
Make sure your AWS creds are setup as the user running the app.
```
aws configure

```
- they end up in ->  ~/.aws/credentials


