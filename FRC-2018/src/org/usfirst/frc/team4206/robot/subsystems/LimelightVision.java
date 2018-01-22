package org.usfirst.frc.team4206.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * This class is the subsystem controlling the Limelight vision processor and camera
 */
public class LimelightVision extends Subsystem {

	NetworkTable table;
	double targetOffsetAngle_Horizontal;
	double targetOffsetAngle_Vertical;
	double targetArea;
	double targetSkew;
	double targetValidity;
	
    public LimelightVision() {
    	NetworkTable table = NetworkTable.getTable("limelight");
    	targetOffsetAngle_Horizontal = table.getNumber("tx", 0);
    	targetOffsetAngle_Vertical = table.getNumber("ty", 0);
     	targetArea = table.getNumber("ta", 0);
    	targetSkew = table.getNumber("ts", 0);
    	targetValidity = table.getNumber("tv", 0);
    }

    public double getTargetOffsetX() {
    	return targetOffsetAngle_Horizontal;
    }
    
    public double getTargetOffsetY() {
    	return targetOffsetAngle_Vertical;
    }
    
    public double getTargetArea() {
    	return targetArea;
    }
    
    public double hasValidTarget() {
    	return targetValidity;
    }
    
    /**
     * Sets the camera mode of the Limelight.
     * 
     * @param mode : true is vision, false is driver cam
     */
    public void setMode(boolean mode) {
    	table.putNumber("camMode", mode ? 0 : 1);
    }
    
    /**
     * Sets the LED mode of the Limelight.
     * 
     * @param mode : 0 is on, 1 is off, 2 is blinking
     */
    public void setLEDMode(int mode) {
    	table.putNumber("ledMode", mode);
    }
    
    public void initDefaultCommand() {
    }
}

