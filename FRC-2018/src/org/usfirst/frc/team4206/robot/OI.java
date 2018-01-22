/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot;

import org.usfirst.frc.team4206.robot.commands.ShiftGear;
import org.usfirst.frc.team4206.robot.commands.VisionDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//set up button map
	public static Joystick driver = new Joystick(0);
	public static Joystick autonomousSelect = new Joystick(1);
	
	static Button A = new JoystickButton(driver, 1);
	static Button B = new JoystickButton(driver, 2);
	static Button X = new JoystickButton(driver, 3);
	static Button Y = new JoystickButton(driver, 4);
	static Button LB = new JoystickButton(driver, 5);
	static Button RB = new JoystickButton(driver, 6);
	static Button Start = new JoystickButton(driver, 7);
	static Button Select = new JoystickButton(driver, 8);
	
	static Button autoForward = new JoystickButton(autonomousSelect, 0);
	
	public static int leftX = 1;
	public static int rightX = 4;
	public static int leftY = 2;
	public static int rightY = 5;
	public static int leftTrigger = 3;
	public static int rightTrigger = 6;


	public OI() {
		RB.whenPressed(new ShiftGear());
		A.whenPressed(new VisionDrive());
		A.cancelWhenPressed(new VisionDrive());
	}	
}