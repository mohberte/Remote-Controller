package org.mohsoft.soundsystem;

import org.mohsoft.Command;

public class IncreaseVolume implements Command {

	SoundSystem soundSystem;
	
	public IncreaseVolume(SoundSystem soundSystem) {
		this.soundSystem= soundSystem;
	}

	@Override
	public String execute() throws InterruptedException {
		return soundSystem.IncreaseVolume();
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
