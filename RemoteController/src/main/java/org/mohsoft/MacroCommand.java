package org.mohsoft;

public class MacroCommand implements Command {

	Command[] commands;
	
	public MacroCommand(Command[] commands) {
		this.commands = commands;
	}

	@Override
	public String execute() throws InterruptedException {
		for(int i=0; i< commands.length;i++)
		{
			commands[i].execute();
		}
		return null;
		
	}

	@Override
	public void undo() {
		
		for(int i=0; i< commands.length;i++)
		{
			commands[i].undo();
		}
	}
	

}
