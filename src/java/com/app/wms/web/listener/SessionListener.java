/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author root
 */
public class SessionListener implements HttpSessionListener{

    public void sessionCreated(HttpSessionEvent se)
    {
        System.out.println("***** Session CREATED");
        
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
       System.out.println("***** Session INVALIDATED");
     }

}
