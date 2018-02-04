package org.usfirst.frc.team4206.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * This class is the subsystem controlling the Limelight vision processor and camera
 */
@SuppressWarnings("deprecation")
public class LimelightVision extends Subsystem {

	NetworkTable table;
	double targetOffsetAngle_Horizontal;
	double targetOffsetAngle_Vertical;
	double targetArea;
	double targetSkew;
	double targetValidity;
	
    public LimelightVision() {
    }

    public double getTargetOffsetX() {
    	NetworkTable table = NetworkTable.getTable("limelight");
    	return table.getNumber("tx", 0);
    }
    
    public double getTargetOffsetY() {
    	NetworkTable table = NetworkTable.getTable("limelight");
    	return table.getNumber("ty", 0);
    }
    
    public double getTargetArea() {
    	NetworkTable table = NetworkTable.getTable("limelight");
    	return table.getNumber("ta", 0);
    }
    
    public double hasValidTarget() {
    	NetworkTable table = NetworkTable.getTable("limelight");
    	return table.getNumber("tv", 0);
    }
    
    /**
     * Sets the camera mode of the Limelight.
     * 
     * @param mode : true is vision, false is driver cam
     */
    public void setMode(boolean mode) {
    	NetworkTable table = NetworkTable.getTable("limelight");
    	table.putNumber("camMode", mode ? 0 : 1);
    }
    
    /**
     * Sets the LED mode of the Limelight.
     * 
     * @param mode : 0 is on, 1 is off, 2 is blinking
     */
    public void setLEDMode(int mode) {
    	NetworkTable table = NetworkTable.getTable("limelight");
    	table.putNumber("ledMode", mode);
    }
    
    public void initDefaultCommand() {
    }

	public String requestDiagnostic() {
		System.out.println("\n----STARTING LIMELIGHT DIAGNOSTIC----\n\n");
		System.out.println("Limelight will blink LEDs for 3 seconds, use the web interface to confirm functionality.\n");
		setLEDMode(2);
		Timer.delay(3);
		setLEDMode(0);
		
		return "";
	}
}

