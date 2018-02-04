package org.usfirst.frc.team4206.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4206.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This subsystem is for the robot's shooter-feeder mechanism
 */
public class ShooterFeeder extends Subsystem {

	AnalogPotentiometer angleSensor;
	DigitalInput cubeSwitch;
	DigitalInput grabberSqueezed;
	DigitalInput grabberStretched;
	
	WPI_TalonSRX elevator;
	WPI_TalonSRX shooterLeft;
	WPI_TalonSRX shooterRight;
	WPI_TalonSRX grabber;
	WPI_TalonSRX rearIntakeLeft;
	WPI_TalonSRX rearIntakeRight;

	final double kP_elevator = 1 / 90;
	final double degreePerVolt = 360 / 0.5;
	private double angleSetPoint =  0;
	
	final double grabberOpen = 0;
	final double grabberClose = 0;
	
	public ShooterFeeder() {
		angleSensor = new AnalogPotentiometer(0);
		cubeSwitch = new DigitalInput(0);
		
		elevator = new WPI_TalonSRX(RobotMap.elevator);
			elevator.setNeutralMode(NeutralMode.Brake);
		shooterLeft = new WPI_TalonSRX(RobotMap.shooterLeft);
			shooterLeft.setNeutralMode(NeutralMode.Coast);
			shooterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTO);
		shooterRight = new WPI_TalonSRX(RobotMap.shooterRight);
			shooterRight.setNeutralMode(NeutralMode.Coast);
			shooterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTO);
			shooterRight.follow(shooterLeft); // Let's give this a try
			shooterRight.setInverted(true);
		grabber = new WPI_TalonSRX(RobotMap.grabber);
			grabber.setNeutralMode(NeutralMode.Brake);
			grabber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTO);
		rearIntakeLeft = new WPI_TalonSRX(RobotMap.rearIntakeLeft);
			rearIntakeLeft.setNeutralMode(NeutralMode.Brake);
		rearIntakeRight = new WPI_TalonSRX(RobotMap.rearIntakeRight);
			rearIntakeRight.setNeutralMode(NeutralMode.Brake);
			rearIntakeRight.follow(rearIntakeLeft);
			rearIntakeRight.setInverted(true);
	}
	
    public void setAngle(double angle) {
    	angleSetPoint = angle - (degreePerVolt * angleSensor.get());
    	elevator.set(ControlMode.PercentOutput, kP_elevator * (angle - (degreePerVolt * angleSensor.get())));
    }
    
    public boolean angleOnTarget() {
    	return getAngle() < angleSetPoint + 1 & getAngle() > angleSetPoint - 1;
    }
    
    private double getAngle() {
    	return degreePerVolt * angleSensor.get();
    }
    
    public void closeGrabber() {
    	grabber.set(ControlMode.Position, grabberClose);
    }
    
    public void openGrabber() {
    	grabber.set(ControlMode.Position, grabberOpen);
    }
    
    public void setAngularVelocity(double omega) {
    	double encOmega = omega * 4096 / 600;
    	shooterLeft.set(ControlMode.Velocity, encOmega);
    	//shooterRight.set(ControlMode.Velocity, encOmega);
    }

    public void setIntake(double power) {
    	rearIntakeLeft.set(ControlMode.PercentOutput, power);
    }
    
    public boolean hasCube() {
    	return cubeSwitch.get();
    }
    
    public boolean grabberTripped() {
    	return grabberSqueezed.get() | grabberStretched.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

