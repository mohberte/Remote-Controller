package org.mohsoft.soundsystem;

import org.mohsoft.Command;

public class SoundSystemOff implements Command {

	SoundSystem soundSystem;
	
	public SoundSystemOff() {
		
	}

	public SoundSystemOff(SoundSystem soundSystemOff) {
		this.soundSystem = soundSystemOff;
	}

	
	@Override
	public String execute() throws InterruptedException {
		return soundSystem.SoundSystemOff();
	}

	@Override
	public String getName() {
		return " "+soundSystem.getName();
	}
	@Override
	public String toString() {
		return soundSystem.toString() ;
	}

	@Override
	public void undo() {
		
	}

}
