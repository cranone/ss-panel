package com.dep.sspanel.filter;

import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * FastJson过滤Hibernate代理对象
 * @author Sherlock
 *
 */
public class HibernatePropertyFilter implements PropertyFilter {

    @Override
    public boolean apply(Object object, String name, Object value) {
        if (value instanceof HibernateProxy) {// hibernate代理对象
            LazyInitializer initializer = ((HibernateProxy) value).getHibernateLazyInitializer();
            if (initializer.isUninitialized()) {
                return false;
            }
        } else if (value instanceof PersistentCollection) {// 实体关联集合一对多等
            PersistentCollection collection = (PersistentCollection) value;
            if (!collection.wasInitialized()) {
                return false;
            }
            Object val = collection.getValue();
            if (val == null) {
                return false;
            }
        }
        return true;
    }

}
