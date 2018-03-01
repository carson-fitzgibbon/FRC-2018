package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * This subsystem controls the robot's shifting mechanism
 */
public class Shifter extends Subsystem {

	static DoubleSolenoid shiftSolenoid;
	static Compressor compressor;
	static boolean loGear;
	
	public Shifter() {
		//shiftSolenoid = new DoubleSolenoid(RobotMap.solenoidForward, RobotMap.solenoidReverse);
		//compressor = new Compressor(RobotMap.compressor);
		loGear = true;
	}
	
	public void shift() {
		//if (loGear)shiftSolenoid.set(DoubleSolenoid.Value.kForward); 
		//else shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
		Timer.delay(0.005);
		//shiftSolenoid.set(DoubleSolenoid.Value.kOff);
		loGear = !loGear;
	}
	
	public int getRPMMux() {
		if (loGear) return 300;
		return 1000;
	}
	
    public void initDefaultCommand() {
    }

	public String requestDiagnostic() {
		// TODO Auto-generated method stub
		return null;
	}
}

