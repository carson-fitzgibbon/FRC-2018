package org.usfirst.frc.team4206.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PDP extends Subsystem {

	static PowerDistributionPanel _PDP;
	
	public PDP() {
		//_PDP = new PowerDistributionPanel(0);
	}
	
	public double portCurrent(int port) {
		//return _PDP.getCurrent(port);
		return 0;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

