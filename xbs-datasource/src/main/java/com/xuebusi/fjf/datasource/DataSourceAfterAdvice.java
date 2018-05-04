package com.xuebusi.fjf.datasource;

import com.xuebusi.fjf.annotation.MarkMasterDataSource;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @Description: 动态切换数据源DataSourceAfterAdvice通知类
 *
 */
public class DataSourceAfterAdvice implements AfterReturningAdvice, ThrowsAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
//		if(methodIsAnnotation(target)){
//			System.out.println("######DataSourceAfterAdvice -- afterReturning");
//			TradingDataSourceHolder.clearThreadDataSource();
//		}
		TradingDataSourceHolder.clearThreadDataSource();
	}

	public void afterThrowing(Exception ex) {
		TradingDataSourceHolder.clearThreadDataSource();
	}
	
	/**
	 * @Title: 验证方法是否
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @throws
	 */
	public boolean  methodIsAnnotation(Object target){
		MarkMasterDataSource tds = null;
		tds = target.getClass().getAnnotation(MarkMasterDataSource.class);
		return tds != null ? true : false;
	}

}
