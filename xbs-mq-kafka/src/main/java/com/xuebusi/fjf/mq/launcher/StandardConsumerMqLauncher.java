package com.xuebusi.fjf.mq.launcher;


import com.xuebusi.fjf.mq.consumer.ConsumerManagerPool;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class StandardConsumerMqLauncher {
	private static final Logger logger = LoggerFactory.getLogger(StandardConsumerMqLauncher.class);
    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
    	logger.info("启动");   	
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-*.xml");
    	ConsumerManagerPool bean = context.getBean(ConsumerManagerPool.class);
		bean.start();
    	logger.info("启动完成");
    	synchronized (StandardConsumerMqLauncher.class) {
			while (true) {
				try {
					StandardConsumerMqLauncher.class.wait();
				} catch (InterruptedException e) {
					logger.error("后台服务异常终止:" + e.getMessage(), e);
				}
			}
		}
    }
}
