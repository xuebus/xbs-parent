package com.xuebusi.fjf.mq.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ProxyKafkaConsumer<K, V>  implements IKafkaFactory<K, V> {
	
	
	/**
	 * @title: huaWeiKafkaConsumer 
	 * @describe: 华为的基础服务
	 * @createTime 2018年3月24日 下午4:32:54
	 */
	@SuppressWarnings("unused")
	private void huaWeiKafkaConsumer(){
	}
	
	private KafkaConsumer<String, String>  kafkaConsumer;
	
	/**
	 * @title: apacheKafkaConsumer 
	 * @describe: Apache的基础服务
	 * @createTime 2018年3月24日 下午4:32:54
	 */
	public KafkaConsumer<String, String> apacheKafkaConsumer(KafkaConsumer<String, String>  kafkaConsumer) {
		this.kafkaConsumer = kafkaConsumer;
		return this.kafkaConsumer;
	}

	@Override
	public KafkaConsumer<String,String> proxyMq() {
		return this.kafkaConsumer;
	}
	
	
	


	/**
	 * @title: proxyMq 
	 * @describe: Apache的基础服务
	 * @createTime 2018年3月24日 下午4:32:54
	 */
	
	

	
}