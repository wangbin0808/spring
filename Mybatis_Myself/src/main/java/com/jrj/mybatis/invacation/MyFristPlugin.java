package com.jrj.mybatis.invacation;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
/**
 * 
 * @author bin.wang
 * 自定义的插件
 *
 */

@Intercepts({
	@Signature(type=StatementHandler.class,method="parameterize",
			args=java.sql.Statement.class)
})
public class MyFristPlugin implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		Object target = invocation.getTarget();
		System.out.println("当前拦截到的对象："+target);
		Object[] args = invocation.getArgs();
		System.out.println("当前拦截对象的参数："+args);
		
		Object proceed = invocation.proceed();
		
		return proceed;
	}

	@Override
	public Object plugin(Object target) {
		//对拦截的对象进行包装，
		//我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
		System.out.println("plugin--插件对目标对象进行封装");
		target=Plugin.wrap(target, this);
		//wrap在插件一包装产生代理对象，把代理对象返回回来
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
		
		System.out.println("setProperties---插件配置的信息："+properties);
	}

}
