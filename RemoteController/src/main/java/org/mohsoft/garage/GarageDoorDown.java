package org.mohsoft.garage;

import org.mohsoft.Command;

public class GarageDoorDown implements Command{

	GarageDoor door;
	
	
	public GarageDoorDown(GarageDoor door) {
		this.door = door;
	}
	@Override
	public String execute() {
		return door.down();
		
	}
	@Override
	public void undo() {
		door.up();
	}

}
