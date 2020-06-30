package com.zzg.mybatis.generator.plugins;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * 类型转换
 */
public class MyJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {

    public MyJavaTypeResolverDefaultImpl() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
        super.typeMap.put(Types.SMALLINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}