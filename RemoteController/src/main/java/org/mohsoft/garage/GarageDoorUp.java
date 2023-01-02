package org.mohsoft.garage;

import org.mohsoft.Command;

public class GarageDoorUp implements Command {

	GarageDoor door;
	
	
	public GarageDoorUp(GarageDoor door) {
		this.door = door;
	}


	@Override
	public String execute()  {
		return door.up();
		
	}


	@Override
	public void undo() {
		door.down();
		
	}

}
