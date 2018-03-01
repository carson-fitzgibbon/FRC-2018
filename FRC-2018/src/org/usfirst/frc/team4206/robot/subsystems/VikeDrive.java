package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
	public void arcadeDriveCL(double y, double x) {
		final double maxVal = 4096 * 900 / 600;
	    y = applyDeadband(y, 0.02);
	    y = limit(y);
	    
	    x = applyDeadband(x, 0.02);
	    x = limit(x);

	    // Square the inputs (while preserving the sign) to increase fine control
	    // while permitting full power.
	    double leftMotorOutput;
	    double rightMotorOutput;

	    double maxInput = Math.copySign(Math.max(Math.abs(y), Math.abs(x)), y);
	    if (y >= 0.0) {
	      // First quadrant, else second quadrant
	      if (x >= 0.0) {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = y - x;
	      } else {
	        leftMotorOutput = y + x;
	        rightMotorOutput = maxInput;
	      }
	    } else {
	      // Third quadrant, else fourth quadrant
	      if (x >= 0.0) {
	        leftMotorOutput = y + x;
	        rightMotorOutput = maxInput;
	      } else {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = y - x;
	      }
	    }

	    _leftMotor.set(ControlMode.Velocity, limit(leftMotorOutput) * maxVal);
	    _rightMotor.set(ControlMode.Velocity, -limit(rightMotorOutput) * maxVal);
	    	    
	    m_safetyHelper.feed();
	}

	@Override
	public String getDescription() {
		return "VikeDrive";
	}
}

