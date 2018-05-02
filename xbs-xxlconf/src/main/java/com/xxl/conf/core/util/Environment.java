package com.xxl.conf.core.util;

import java.util.Properties;

/**
 * 环境基类
 * @author xuxueli 2015-8-28 10:37:43
 */
public class Environment {

	/**
	 * conf data path in zk
     */
	public static final String CONF_CACHE_DATA_PATH = "/xxl-conf";

	/**
	 * zk config file
     */
	private static final String ZK_ADDRESS_FILE = "config.properties";

	/**
	 * zk address
     */
	public static final String ZK_ADDRESS,PROJECT_GROUP_NAME;		// zk地址：格式	ip1:port,ip2:port,ip3:port
	static {
//		Properties prop = PropertiesUtil.loadFileProperties(ZK_ADDRESS_FILE);
		Properties prop = PropertiesUtil.loadProperties(ZK_ADDRESS_FILE);
		ZK_ADDRESS = PropertiesUtil.getString(prop, "conf.zkServer");
        PROJECT_GROUP_NAME = PropertiesUtil.getString(prop, "conf.groupName");
	}
}