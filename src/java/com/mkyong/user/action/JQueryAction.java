package com.mkyong.user.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.hibernate3.HibernateTemplate;


    public class JQueryAction extends ActionSupport{
    private String name;
    private String greeting;
    private List<String> countryList;
    private Map<String, String> countryMap;
    HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
    public String sayHi(){

        setGreeting("HI " + getName());
        System.out.println("greetings value: "+greeting);   
        countryList=new ArrayList();
        countryList.add("US");
        countryList.add("UK");
        countryList.add("Russia");
 
        countryMap= new HashMap();
        countryMap.put("US","US");
        countryMap.put("UK","UK");
        countryMap.put("Russia","Russia");
        countryMap.put("Russia","Russia");
        countryMap.put("Uva","Uva");
        countryMap.put("Union","Union");
        countryMap.put("USA","USA");
        countryMap.put("Ursula","Ursula");
        countryMap.put("Uriel","Uriel");
        countryMap.put("Usted","Usted");
        countryMap.put("Unidos","Unidos");
        countryMap.put("Zacatecas","Zacatecas");
        countryMap.put("Aguascalientes","Aguascalientes");
        countryMap.put("Zared","Zared");
        countryMap.put("Leticia","Leticia");
        countryMap.put("Yesenia","Yesenia");
        return ActionSupport.SUCCESS;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the greeting
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * @param greeting the greeting to set
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    /**
     * @return the countryList
     */
    public List<String> getCountryList() {
        return countryList;
    }

    /**
     * @param countryList the countryList to set
     */
    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    /**
     * @return the countryMap
     */
    public Map<String,String> getCountryMap() {
        return countryMap;
    }

    /**
     * @param countryMap the countryMap to set
     */
    public void setCountryMap(Map<String,String> countryMap) {
        this.countryMap = countryMap;
    }
    
}