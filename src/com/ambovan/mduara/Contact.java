package com.ambovan.mduara;

import java.util.ArrayList;

public class Contact {

	 public String id;
	    public String name;
	    public String number;
	    public String type;
	   
	   // public ArrayList<ContactPhone> numbers;

	    public Contact(String id, String name) {
	        this.id = id;
	        this.name = name;
	       
	       // this.numbers = new ArrayList<ContactPhone>();
	    }

	  
	    public void addNumber(String number, String type) {
	      //  numbers.add(new ContactPhone(number, type));
	    	this.number = number;
	    	this.type = type;
	    }
}
