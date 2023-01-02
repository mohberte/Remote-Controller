package org.mohsoft;

public class RemoteControl {
	
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand;
	
	
	public RemoteControl() {
		onCommands = new Command[3];
		offCommands = new Command[3];
		
		NoCommand noCommand = new NoCommand();
		for (int i = 0; i<3; i++)
		{
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
		
		undoCommand = noCommand;
	}
	
	public void setCommand(int slot, Command onCommand, Command offCommand)
	{
		onCommands[slot] = onCommand;
		offCommands[slot]= offCommand;
	}
	
	public void onButtonWasPushed(int slot) throws InterruptedException
	{
		onCommands[slot].execute();
		undoCommand = onCommands[slot];
	}

	public void offButtonWasPushed(int slot) throws InterruptedException
	{
		offCommands[slot].execute();
		undoCommand = offCommands[slot];
	}
	
	public void undoButtonWasPushed()
	{
	
		undoCommand.undo();
	}

	public Command[] getOnCommands() {
		
		return onCommands;
	}
	public Command[] getOffCommands() {
		
		return offCommands;
	}
	

}
