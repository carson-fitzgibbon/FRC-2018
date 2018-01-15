package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * This subsystem controls the robot's shifting mechanism
 */
public class Shifter extends Subsystem {

	static DoubleSolenoid shiftSolenoid;
	static boolean loGear;
	
	public Shifter() {
		shiftSolenoid = new DoubleSolenoid(RobotMap.solenoidForward, RobotMap.solenoidReverse);
		loGear = true;
	}
	
	public void shift() {
		if (loGear)shiftSolenoid.set(DoubleSolenoid.Value.kForward); 
		else shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
		
		shiftSolenoid.set(DoubleSolenoid.Value.kOff);
		loGear = !loGear;
	}
	
    public void initDefaultCommand() {
    	
    }
}

