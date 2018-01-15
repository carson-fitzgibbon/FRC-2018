package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;
import org.usfirst.frc.team4206.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the pneumatic control module's compressor
 */
public class Pneumatics extends Subsystem {

	static Compressor compressor;

    public Pneumatics() {
    	compressor = new Compressor(RobotMap.compressor);
    }
    
    public void start() {
    	compressor.setClosedLoopControl(false);
    	if (compressor.getPressureSwitchValue()) compressor.start();
    }
    
    public void stop() {
    	compressor.stop();
    }
    
    public boolean getSwitchValue() {
    	return compressor.getPressureSwitchValue();
    }
    
    public void setClosedLoopControl(boolean value) {
    	compressor.setClosedLoopControl(value);
    }
    
    public void initDefaultCommand() {
    }
}

