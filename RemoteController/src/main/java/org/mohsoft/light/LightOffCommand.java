package org.mohsoft.light;

import org.mohsoft.Command;

public class LightOffCommand implements Command {
	
	Light light;
	
	
	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public String getName() {
		return " "+light.getName();
	}
	
	@Override
	public String execute() {
		return light.off();
	}
	@Override
	public String toString() {
		return light.toString() ;
	}

	@Override
	public void undo() {
		light.on();
		
	}

}
