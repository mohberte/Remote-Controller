package org.mohsoft.heatpump;

import org.mohsoft.Device;

public class HeatPump extends Device {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6788754255490182679L;
	public static final int HOT = 3;
	public static final int AMBIENT = 2;
	public static final int COLD = 1;
	public static final int OFF = 0;
	
	
	int mode;
	
	public HeatPump(String name) {
		this.name = name;
		mode = OFF;
	}
	
	public void coldAir() {	
		mode = COLD;
	}
	public void hotAir() {
		mode = HOT ;
	}
	
	public void ambientAir() {
		mode = AMBIENT;
	}
	
	public int getMode()
	{
		return mode;
	}

	public void off() {
		System.out.println("Turning the pump off");
	}

	
}
