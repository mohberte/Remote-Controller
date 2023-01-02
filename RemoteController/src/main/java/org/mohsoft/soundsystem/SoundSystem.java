package org.mohsoft.soundsystem;

import org.mohsoft.Device;

public class SoundSystem extends Device {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9193485057829742846L;
	public SoundSystem(String name) {
		this.name = name;
	}
	public String IncreaseVolume() {	
		return "Volume up";
	}
	public String DecreaseVolume() {	
		return "Volume down";
	}
	public String SoundSystemOff() {
		return "Turning off";
	}

}
