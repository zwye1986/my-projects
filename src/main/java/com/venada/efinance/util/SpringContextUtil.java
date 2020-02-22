/*
 * 文件名：SpringContextUtil.java
 * 版权： 
 * 描述：  
 * 修改人： OPP
 * 修改时间：2010-12-9
 * 修改内容：新增 
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.venada.efinance.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * 〈Spring工具类〉
 * 〈功能详细描述〉
 * @author    yma
 * @version   V1.00 2009-10-15[版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     TP V1.0R001 [产品/模块版本] 
 */
public class SpringContextUtil implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     *〈获取对象〉
     * @param name 对象名称
     * @return Object bean的实例
     */
    public static Object getBean(String name)
        throws BeansException
    {
        return applicationContext.getBean(name);
    }
    
    /**
     *〈获取对象〉
     * @param name 对象名称
     * @return Object bean的实例
     */
    public static Object getBean(Object obj)
        throws BeansException
    {
        return applicationContext.getBean(obj.getClass());
    }

    /**
     *〈获取对象实例〉
     * @param name 注册Bean的名称
     * @param requiedType 返回对象类型
     * @return
     * @throws BeansException Object
     */
    public static Object getBean(String name, Class<?> requiedType)
        throws BeansException
    {
        return applicationContext.getBean(name, requiedType);
    }
}
