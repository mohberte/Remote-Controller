package org.mohsoft.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mohsoft.Command;
import org.mohsoft.Device;
import org.mohsoft.NoCommand;
import org.mohsoft.RemoteControl;
import org.mohsoft.configuration.ThymeLeafConfiguration;
import org.mohsoft.garage.GarageDoor;
import org.mohsoft.heatpump.HeatPump;
import org.mohsoft.light.Light;
import org.mohsoft.soundsystem.SoundSystem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class addDevicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public addDevicesServlet() {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
				ThymeLeafConfiguration.TEMPLATE_ENGINE_ATTR);
			IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
			.buildExchange(request, response);
			WebContext context = new WebContext(webExchange);
			
			// creation of the devices in their proper locations
			RemoteControl remoteController = new RemoteControl();
			//displaying the variables content for troubleshooting
			
			List<List<Command>> allCommands = new ArrayList<List<Command>>();
			List<String> devicesSelected = new ArrayList<>();
			
				
				String[] devicesSelectedString = request.getParameterValues("devices");
			// we set up the remote which the appropriate functions
			for(int i=0; i< devicesSelectedString.length;i++)
			{
				List<Command> rowCommands = new ArrayList<>();
			
				Device device = null;
				
				try {
					device = (Device) Device.fromString(devicesSelectedString[i]);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				devicesSelected.add(device.getName());
				if (device.getClassName().equals("Light"))
				{
					
					if(device.getName().equals("Living Room light"))
					{
						rowCommands.add((Command) request.getSession().getAttribute("livingRoomLightOn"));
						rowCommands.add((Command) request.getSession().getAttribute("livingRoomLightOff"));

					}
					else if(device.getName().equals("Kitchen light"))
					{
						rowCommands.add((Command) request.getSession().getAttribute("kitchenLightOn"));
						rowCommands.add((Command) request.getSession().getAttribute("kitchenLightOff"));
					}
				}
				else if (device.getClassName().equals("GarageDoor"))
				{
					rowCommands.add((Command) request.getSession().getAttribute("doorUp"));
					rowCommands.add( (Command) request.getSession().getAttribute("doorDown"));
				}
				else if (device.getClassName().equals("HeatPump"))
				{
					rowCommands.add((Command) request.getSession().getAttribute("hotAir"));
					rowCommands.add( (Command) request.getSession().getAttribute("coldAir"));

				}
				else if (device.getClassName().equals("SoundSystem"))
				{
					rowCommands.add((Command)request.getSession().getAttribute("volumeUp"));
					rowCommands.add((Command)request.getSession().getAttribute("soundSystemOff"));
				}
				else
					;
				allCommands.add(rowCommands);
		
			}
			
		
			//if theres empty spots on the controller
			//we fill the remaining spots
			if(request.getParameterValues("devices").length<3)
			{
				for(int i=0;i<3-devicesSelectedString.length;i++)
			{
				List<Command> rowCommands = new ArrayList<>();
				Command noCommand = new NoCommand();
				devicesSelected.add("no device");	
				rowCommands.add(noCommand);
				rowCommands.add(noCommand);
				allCommands.add(rowCommands);
			}
			
			}
			
			// Configuration of the Macro button 
			String[] commandsSelected = request.getParameterValues("macroSelection");
			
			StringBuilder macro = new StringBuilder();
			
			List<Command> commands = (List<Command>) request.getSession().getAttribute("Commands");
			
			for(int i=0; i<commandsSelected.length;i++)
			{
				
				for(int j=0; j<commands.size();j++)
				{	
					if(commandsSelected[i].equals(commands.get(j).getCommandClass()))
						try {
							macro.append(commands.get(j).execute() + ", ");
						} catch (InterruptedException e) {
				
							e.printStackTrace();
						}
				}
			}
			macro = macro.deleteCharAt(macro.length() - 2); //we remove the comma
		
			// creation of the devices in their proper locations
			
			Light livingRoomLight = new Light("Living Room light");
			Light kitchenLight = new Light("kitchen Room light");
			new Light("All lights");
			
			HeatPump centralTempRegulator = new HeatPump("Central heating system");
			
			
			GarageDoor garageDoor = new GarageDoor("Front Garage Door");
			
			SoundSystem soundsystem = new SoundSystem("Sound system");
				List <Device> devices = new ArrayList<>();
			
			devices.add(livingRoomLight);
			devices.add(kitchenLight);
			devices.add(garageDoor);
			devices.add(centralTempRegulator);
			devices.add(soundsystem);

			context.setVariable("Devices", devices);
			context.setVariable("DevicesSelected", devicesSelected);
			request.getSession().setAttribute("RemoteController", remoteController);
			request.getSession().setAttribute("Macro", macro);
			request.getSession().setAttribute("CommandList", allCommands);
			context.setVariable("CommandList", allCommands);
			templateEngine.process("index", context, response.getWriter());		
	}

}
