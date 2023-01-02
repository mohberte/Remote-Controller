package org.mohsoft;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public abstract class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360459858840202850L;
	protected String name;

	public String getName() {
		return name;
	}
	
	public String serial() throws IOException
	{
		return toString(this);
	}
	
	//returns the class name of the object
	public String getClassName()
	{
		String className = this.getClass().getName();
		int lastDotIndex = className.lastIndexOf('.');
		String simpleClassName = className.substring(lastDotIndex + 1);
		
		return simpleClassName;
	}
	
	/*Transforms the serial number to a string*/
    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
    }
    
    /*Transforms the string into the original serial number*/
	   public static Object fromString( String s ) throws IOException ,
	                                                       ClassNotFoundException {
	        byte [] data = Base64.getDecoder().decode( s );
	        ObjectInputStream ois = new ObjectInputStream( 
	                                        new ByteArrayInputStream(  data ) );
	        Object o  = ois.readObject();
	        ois.close();
	        return o;
	   }
	
}
