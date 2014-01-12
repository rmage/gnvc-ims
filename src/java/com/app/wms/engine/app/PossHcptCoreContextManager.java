/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.app;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author programmer
 */
public class PossHcptCoreContextManager implements ApplicationContextAware {
    

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println(getClass().getName() + ": Loading Poss HCPT ApplicationContext");
        applicationContext = context;
    }

    /**
     * @return the Spring applicationContext Object
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * This is about the same as context.getBean("beanName"), except it has its
     * own static handle to the Spring context, so calling this method statically
     * will give access to the beans by name in the Spring application context.
     *
     * @param beanName the name of the bean to get.
     * @return an Object reference to the named bean.
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

}
