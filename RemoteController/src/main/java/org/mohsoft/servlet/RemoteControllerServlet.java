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
import org.mohsoft.garage.GarageDoorDown;
import org.mohsoft.garage.GarageDoorUp;
import org.mohsoft.heatpump.AmbientHeatPump;
import org.mohsoft.heatpump.ColdHeatPump;
import org.mohsoft.heatpump.HeatPump;
import org.mohsoft.heatpump.HotHeatPump;
import org.mohsoft.light.Light;
import org.mohsoft.light.LightOffCommand;
import org.mohsoft.light.LightOnCommand;
import org.mohsoft.soundsystem.IncreaseVolume;
import org.mohsoft.soundsystem.SoundSystem;
import org.mohsoft.soundsystem.SoundSystemOff;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RemoteControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RemoteControllerServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
				ThymeLeafConfiguration.TEMPLATE_ENGINE_ATTR);
			IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
			.buildExchange(request, response);
			WebContext context = new WebContext(webExchange);
			
			
			// creation of the devices in their proper locations
			RemoteControl remoteController = new RemoteControl();
			
			Light livingRoomLight = new Light("Living Room light");
			Light kitchenLight = new Light("Kitchen light");
			
			HeatPump centralTempRegulator = new HeatPump("Central heating system");
			
			GarageDoor garageDoor = new GarageDoor("Front Garage Door");
			
			SoundSystem soundSystem = new SoundSystem("Sound system");
			
			List <Device> devices = new ArrayList<>();
			
			devices.add(livingRoomLight);
			devices.add(kitchenLight);
			devices.add(garageDoor);
			devices.add(centralTempRegulator);
			devices.add(soundSystem);
			
		
			context.setVariable("Devices", devices);
			
			// Creation of the different commands for a specific device
			//lights
			LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
			LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
			
			LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
			LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

			//HeatPump
			ColdHeatPump coldAir = new ColdHeatPump(centralTempRegulator);
			HotHeatPump hotAir = new HotHeatPump(centralTempRegulator);
			
			//Garage Door
			GarageDoorUp doorUp = new GarageDoorUp(garageDoor);
			GarageDoorDown doorDown = new GarageDoorDown(garageDoor);
			
			//Sound system
			IncreaseVolume volumeUp = new IncreaseVolume(soundSystem);
			SoundSystemOff soundSystemOff = new SoundSystemOff(soundSystem);

			List <Command> commands = new ArrayList<>();
			
			
			commands.add(livingRoomLightOn);
			commands.add(livingRoomLightOff);
			commands.add(kitchenLightOn);
			commands.add(kitchenLightOff);
			commands.add(doorUp);
			commands.add(doorDown);
			commands.add(coldAir);
			commands.add(hotAir);
			commands.add(volumeUp);
			commands.add(soundSystemOff);
			
			//initialization of the null object
			List<List<Command>> group = new ArrayList<List<Command>>();
			
			
			List<Command> coms = new ArrayList<>();
			Command noCom = new NoCommand();
			
			coms.add(noCom);
			coms.add(noCom);
			for(int i=0;i<3;i++)
			{group.add(coms);}
			
			List<String> devicesSelected = new ArrayList<>();
			devicesSelected.add("No device");
			devicesSelected.add("No device");
			devicesSelected.add("No device");
			
			String macro = "Empty";
			
			
			//setting the context and session attributes
			request.getSession().setAttribute("CommandList", group);
			context.setVariable("CommandList", group);
			
			request.getSession().setAttribute("Devices", devices);
			context.setVariable("Commands", commands);
			request.getSession().setAttribute("Commands", commands);
			request.getSession().setAttribute("livingRoomLightOn", livingRoomLightOn);
			request.getSession().setAttribute("livingRoomLightOff", livingRoomLightOff);

			request.getSession().setAttribute("kitchenLightOn", kitchenLightOn);
			request.getSession().setAttribute("kitchenLightOff", kitchenLightOff);
			
			request.getSession().setAttribute("doorUp", doorUp);
			request.getSession().setAttribute("doorDown", doorDown);
			
			request.getSession().setAttribute("hotAir", hotAir);
			request.getSession().setAttribute("coldAir", coldAir);
			
			request.getSession().setAttribute("volumeUp", volumeUp);
			request.getSession().setAttribute("soundSystemOff", soundSystemOff);
			
			remoteController.setCommand(0, livingRoomLightOn, livingRoomLightOff);
			remoteController.setCommand(1, doorUp, doorDown);
			remoteController.setCommand(2, hotAir, coldAir);
			
			request.getSession().setAttribute("RemoteController", remoteController);
			
			
			request.getSession().setAttribute("Macro", macro);
			
			context.setVariable("DevicesSelected", devicesSelected);
			
				
			templateEngine.process("index", context, response.getWriter());
		
			}

}
