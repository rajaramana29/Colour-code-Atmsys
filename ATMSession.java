package com.util;

import java.util.HashMap;
import java.util.Map;

//Java program implementing Singleton class
//with getInstance() method
public class ATMSession
{
 // static variable single_instance of type Singleton
 private static ATMSession single_instance = null;

 // variable of type String
 public String s;
 public Map stateItems = new HashMap();

 // private constructor restricted to this class itself
 private ATMSession()
 {
     s = "Hello I am a string part of Singleton class";
 }

 // static method to create instance of Singleton class
 public static ATMSession getInstance()
 {
     if (single_instance == null)
         single_instance = new ATMSession();

     return single_instance;
 }
 
 public Object getItem(String key)
 {
     return stateItems.get(key);
 }

 public void setItem(String key, Object item)
 {
     stateItems.put(key, item);
 }

 public void removeItem(String item)
 {
     stateItems.remove(item);

 }

 public void cleanUp()
 {
     stateItems.clear();
 }
}
