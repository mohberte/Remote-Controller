package org.mohsoft.light;

import org.mohsoft.Device;

public class Light extends Device{


	private static final long serialVersionUID = -3659480574388326873L;
	public Light(String name) {
		this.name = name;
	}

	public String off() {	
		return "Lights Off";
	}
	public String on() {
		return "Lights On";
	}	
}
