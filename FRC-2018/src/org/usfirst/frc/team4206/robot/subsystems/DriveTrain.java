package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;
import org.usfirst.frc.team4206.robot.commands.PlayerDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class controls the robot's drive train
 */
public class DriveTrain extends Subsystem {

	static WPI_TalonSRX leftMaster;
	static WPI_TalonSRX leftSlave;
	static WPI_TalonSRX rightMaster;
	static WPI_TalonSRX rightSlave;
	static VikeDrive vikeDrive;
	
	final double kF = 0.1097; //0.1097
	final double kP = 0.113333; //0.113333
	final double kI = 0;
	final double kD = 0;
	
	public DriveTrain() {
		leftMaster = new WPI_TalonSRX(Robot.map.leftMaster);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Robot.map.kTO);
		leftMaster.setSensorPhase(true);
		leftSlave = new WPI_TalonSRX(Robot.map.leftSlave);
		rightMaster = new WPI_TalonSRX(Robot.map.rightMaster);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Robot.map.kTO);
		rightMaster.setSensorPhase(true);
		rightSlave = new WPI_TalonSRX(Robot.map.rightSlave);
		
		leftMaster.configNominalOutputForward(0, Robot.map.kTO);
		leftMaster.configNominalOutputReverse(0, Robot.map.kTO);
		leftMaster.configPeakOutputForward(1, Robot.map.kTO);
		leftMaster.configPeakOutputReverse(-1, Robot.map.kTO);
		leftMaster.config_kF(0, kF, Robot.map.kTO);
		leftMaster.config_kP(0, kP, Robot.map.kTO);
		leftMaster.config_kI(0, kI, Robot.map.kTO);
		leftMaster.config_kD(0, kD, Robot.map.kTO);
		leftMaster.configClosedloopRamp(0.15, Robot.map.kTO);
		
		rightMaster.configNominalOutputForward(0, Robot.map.kTO);
		rightMaster.configNominalOutputReverse(0, Robot.map.kTO);
		rightMaster.configPeakOutputForward(1, Robot.map.kTO);
		rightMaster.configPeakOutputReverse(-1, Robot.map.kTO);
		rightMaster.config_kF(0, kF, Robot.map.kTO);
		rightMaster.config_kP(0, kP, Robot.map.kTO);
		rightMaster.config_kI(0, kI, Robot.map.kTO);
		rightMaster.config_kD(0, kD, Robot.map.kTO);
		rightMaster.configClosedloopRamp(0.15, Robot.map.kTO);
		
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		leftSlave.setNeutralMode(NeutralMode.Brake);
		rightSlave.setNeutralMode(NeutralMode.Brake);
		
		leftMaster.set(ControlMode.Velocity, 0);
		rightMaster.set(ControlMode.Velocity, 0);
		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);
		
		vikeDrive = new VikeDrive(leftMaster, rightMaster);
		
		leftMaster.setSelectedSensorPosition(0, 0, Robot.map.kTO);
		rightMaster.setSelectedSensorPosition(0, 0, Robot.map.kTO);
	}
	
	public void arcadeDrive(double power, double rotate) {
		vikeDrive.arcadeDrive(power, rotate);
	}
	
	public void velocityDrive(double power, double rotate) {
		vikeDrive.arcadeDriveCL(-power, rotate);
	}
	
	public boolean positionDrive(double positionL, double positionR) {
		leftMaster.set(ControlMode.Position, -positionL);
		rightMaster.set(ControlMode.Position, positionR);
		
		return (Math.abs((leftMaster.getClosedLoopError(0) + rightMaster.getClosedLoopError(0)) / 2) <= 200);
	}

	public boolean setAngle(double angle) {
		arcadeDrive(0, (angle - Robot.navx.getGyro())/90);
		return (angle - Robot.navx.getGyro() <= 5);
	}
	
	public double getVelocity() {
		return (leftMaster.getSelectedSensorVelocity(0) + rightMaster.getSelectedSensorVelocity(0)) / 2;
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new PlayerDrive());
    }
}