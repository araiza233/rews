/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.publicidad.logic;

/**
 *
 * @author Jorge
 */
public interface SecurityInterceptorLogic {
    public boolean getUrlAccess(String user, String urlToBeAccess);
}
