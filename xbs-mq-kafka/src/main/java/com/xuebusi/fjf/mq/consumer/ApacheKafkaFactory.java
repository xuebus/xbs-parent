package com.xuebusi.fjf.mq.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class ApacheKafkaFactory<K, V> extends KafkaConsumer<K, V> {

	public ApacheKafkaFactory(Properties properties) {
		super(properties);
	}



}
