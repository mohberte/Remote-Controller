package org.mohsoft;

public interface Command {

	default String getName()
	{return "";}
	
	public String execute() throws InterruptedException;

	public void undo();

	default String getCommandClass()
	{
		String className = this.getClass().getName();
		int lastDotIndex = className.lastIndexOf('.');
		String simpleClassName = className.substring(lastDotIndex + 1);
		return simpleClassName + this.getName();
	}
	
}
