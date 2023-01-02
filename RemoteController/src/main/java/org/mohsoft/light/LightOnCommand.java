package org.mohsoft.light;

import org.mohsoft.Command;

public class LightOnCommand implements Command{

	private Light light;

	public LightOnCommand(Light livingRoomLight) {
		this.light = livingRoomLight;
	}

	@Override
	public String execute() throws InterruptedException {
		
		
		return light.on();

	}

	@Override
	public String getName() {
		return " "+light.getName();
	}
	
	@Override
	public String toString() {
		return light.toString() ;
	}

	@Override
	public void undo() {
		
		light.off();
	}
	

}
