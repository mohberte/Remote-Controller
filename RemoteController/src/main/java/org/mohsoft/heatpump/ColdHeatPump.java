package org.mohsoft.heatpump;

import org.mohsoft.Command;

public class ColdHeatPump implements Command{
	int prevMode;
	HeatPump heatPump;
	
	
	public ColdHeatPump(HeatPump heatPump) {
		
		this.heatPump = heatPump;
	}


	@Override
	public String execute() throws InterruptedException {
		prevMode = heatPump.getMode();
		heatPump.coldAir();
		return "Cold air blowing";
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
