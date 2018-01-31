package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Class for a custom robotDrive with closed loop control
 */
public class VikeDrive extends DifferentialDrive {

	private WPI_TalonSRX _leftMotor;
	private WPI_TalonSRX _rightMotor;

	public VikeDrive(WPI_TalonSRX leftMotor, WPI_TalonSRX rightMotor) {
		super(rightMotor, rightMotor);
		_leftMotor = leftMotor;
	    _rightMotor = rightMotor;
	    addChild(_leftMotor);
	    addChild(_rightMotor);
	    setName("VikeDrive");
	}
	
	
	/**
	 * Drives the robot in a closed loop based on velocity.
	 * 
	 * @param y Target percentage of velocity forward or back
	 * @param x Target percentage of velocity in rotation
	 */
	public void arcadeDriveCL(double x, double y) {
		final double xTargetVel = limit(x) * Robot.shifter.getRPMMux() * 4096 / 600;
		final double yTargetVel = limit(y) * Robot.shifter.getRPMMux() * 4096 / 600;

		if (yTargetVel >= 0) {
		    if (xTargetVel >= 0) {
		    	_leftMotor.set(ControlMode.Velocity, yTargetVel);
		    	_rightMotor.set(ControlMode.Velocity, yTargetVel - xTargetVel);
		    } else {
		    	_leftMotor.set(ControlMode.Velocity, yTargetVel + xTargetVel);
		    	_rightMotor.set(ControlMode.Velocity, yTargetVel);
		    }
		} else {
		    if (xTargetVel >= 0) {
		    	_leftMotor.set(ControlMode.Velocity, yTargetVel);
		    	_rightMotor.set(ControlMode.Velocity, yTargetVel + xTargetVel);
		    } else {
		    	_leftMotor.set(ControlMode.Velocity, yTargetVel - xTargetVel);
		    	_rightMotor.set(ControlMode.Velocity, yTargetVel);
		    }
		}
		
	    m_safetyHelper.feed();
	}

	@Override
	public String getDescription() {
		return "VikeDrive";
	}
}

