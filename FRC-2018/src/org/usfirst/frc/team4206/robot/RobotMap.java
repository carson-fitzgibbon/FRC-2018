/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// We use 1 as the starting CAN ID because 0 is the default and new devices
	// will conflict with an existing '0' device
	public static final int leftMaster = 1;
	public static final int leftSlave = 4;
	public static final int rightMaster = 3;
	public static final int rightSlave = 2;
	
	public static final int leftMasterPWR = 0;
	public static final int leftSlavePWR = 1;
	public static final int rightMasterPWR = 2;
	public static final int rightSlavePWR = 3;
	
	public static final int solenoidForward = 0;
	public static final int solenoidReverse = 1;
	public static final int compressor = 6;
	
}
