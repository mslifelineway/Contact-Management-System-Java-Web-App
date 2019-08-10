/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oyedost.contactapp.exceptions;

/**
 *
 * @author janeman
 */
public class UserBlockedException extends Exception{
    
    /**
     * it creates user blocked exception without error
     */
    public UserBlockedException() {
       
    }
     /**
     * it creates user blocked exception with error
     */
    public UserBlockedException(String errDesc) {
       super(errDesc);
    }
    
}
