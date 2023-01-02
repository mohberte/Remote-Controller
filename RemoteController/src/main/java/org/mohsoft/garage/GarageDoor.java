package org.mohsoft.garage;

import org.mohsoft.Device;

public class GarageDoor extends Device{
	
	private static final long serialVersionUID = 3471533640195356841L;
	public GarageDoor(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GarageDoor [name=" + name + "]";
	}

	public String up() {	
		return "Door up";
	}
	public String down() {
		return "Door down";
	}
}
