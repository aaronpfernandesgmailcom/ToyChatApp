# Toy pub/sub app
A toy app that uses Spring Data Redis as an intermediary between a publisher and a subscriber.

## Publisher
Located in the publisher module, it connects to the configured redis server and sends a configured amount of messages to a configured topic before stopping.

## Subscriber
Located in the subscriber module, it connects to the configured redis server and awaits messages from one or more publishers on a configured topic.

## Docker files
Basic docker files included for both publisher and subscriber.
