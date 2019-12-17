package com.alan.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts(
        {@Signature(type = Executor.class,method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class,method = "update",
                        args = {MappedStatement.class, Object.class})
        })
public class ExamplePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement ms = (MappedStatement)invocation.getArgs()[0];
        Object args = invocation.getArgs()[1];
        SqlCommandType sqlCommandType = ms.getSqlCommandType();

        System.out.println("调用"+sqlCommandType.name());
        System.out.println("执行sql: "+ms.getSqlSource().getBoundSql(args).getSql());
        String str = args==null?"null":args.toString();
        System.out.println("参数："+str);
        return invocation.proceed();
    }
}
