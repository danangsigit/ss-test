package com.java.test.ss.config;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.DateType;

public class CommonMysqlDBDialect  extends MySQLDialect {
    public CommonMysqlDBDialect() {
        super();
        registerFunction("date_add_interval", new SQLFunctionTemplate( DateType.INSTANCE, "date_add(?1, INTERVAL ?2 ?3)" ));
        registerFunction("date_format", new SQLFunctionTemplate( DateType.INSTANCE, "DATE_FORMAT(?1, ?2)" ));
    }
}
