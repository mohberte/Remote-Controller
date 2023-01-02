package org.mohsoft.heatpump;

import org.mohsoft.Command;

public class AmbientHeatPump implements Command{

	HeatPump heatPump;
	int prevMode;
	
	
	public AmbientHeatPump(HeatPump heatPump) {
	
		this.heatPump = heatPump;
	}


	@Override
	public String execute() {
		prevMode = heatPump.getMode();
		heatPump.ambientAir();
		return null;
	}


	@Override
	public void undo() {
		
		if(prevMode == HeatPump.HOT)
			heatPump.hotAir();
		else if(prevMode == HeatPump.AMBIENT)
			heatPump.ambientAir();
		else if(prevMode == HeatPump.COLD)
			heatPump.coldAir();
		else if(prevMode == HeatPump.OFF)
			heatPump.off();
	}

}
