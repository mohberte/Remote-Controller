package org.mohsoft;

public class NoCommand implements Command {

	@Override
	public String execute() {
		return "No Command Registered here";
	}

	@Override
	public void undo() {

	}

}
