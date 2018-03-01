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
	private final boolean isPractice = true;
	public final int kTO = 100;
	
	public int leftMaster;
	public int leftSlave;
	public int rightMaster;
	public int rightSlave;
	public int elevatorMaster;
	public int elevatorSlave;
	public int shooterLeft;
	public int shooterRight;
	public int grabber;
	public int rearIntakeLeft;
	public int rearIntakeRight;
	
	public int leftMasterPWR;
	public int leftSlavePWR;
	public int rightMasterPWR;
	public int rightSlavePWR;
	public int elevatorMasterPWR;
	public int elevatorSlavePWR;
	public int shooterLeftPWR;
	public int shooterRightPWR;
	public int grabberPWR;
	public int rearIntakeLeftPWR;
	public int rearIntakeRightPWR;
	
	public int solenoidForward;
	public int solenoidReverse;
	public int compressor;
	// We use 1 as the starting CAN ID because 0 is the default and new devices
	// will conflict with an existing '0' device
	public RobotMap() {
		if (isPractice) {
			leftMaster = 1;
			leftSlave = 4;
			rightMaster = 3;
			rightSlave = 2;
			elevatorMaster = 5;
			elevatorSlave = 11;
			shooterLeft = 6;
			shooterRight = 7;
			grabber = 8;
			rearIntakeLeft = 9;
			rearIntakeRight = 10;
			
			leftMasterPWR = 0;
			leftSlavePWR = 1;
			rightMasterPWR = 2;
			rightSlavePWR = 3;
			elevatorMasterPWR = 12;
			shooterLeftPWR = 13;
			shooterRightPWR = 14;
			grabberPWR = 4;
			rearIntakeLeftPWR = 5;
			rearIntakeRightPWR = 6;
			
			solenoidForward = 0;
			solenoidReverse = 1;
			compressor = 0;
		} else {
			leftMaster = 1;
			leftSlave = 4;
			rightMaster = 3;
			rightSlave = 2;
			elevatorMaster = 5;
			elevatorSlave = 11;
			shooterLeft = 6;
			shooterRight = 7;
			grabber = 8;
			rearIntakeLeft = 9;
			rearIntakeRight = 10;
			
			leftMasterPWR = 0;
			leftSlavePWR = 1;
			rightMasterPWR = 2;
			rightSlavePWR = 3;
			elevatorMasterPWR = 12;
			shooterLeftPWR = 13;
			shooterRightPWR = 14;
			grabberPWR = 4;
			rearIntakeLeftPWR = 5;
			rearIntakeRightPWR = 6;
			
			solenoidForward = 0;
			solenoidReverse = 1;
			compressor = 0;
		}
	}
}
