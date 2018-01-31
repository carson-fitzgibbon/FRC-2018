package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;
import org.usfirst.frc.team4206.robot.RobotMap;
import org.usfirst.frc.team4206.robot.commands.PlayerDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This class controls the robot's drive train
 */
public class DriveTrain extends Subsystem {

	static WPI_TalonSRX leftMaster;
	static WPI_TalonSRX leftSlave;
	static WPI_TalonSRX rightMaster;
	static WPI_TalonSRX rightSlave;
	static VikeDrive vikeDrive;
	
	final double kF = 0.1097;
	final double kP = 0.113333;
	final double kI = 0;
	final double kD = 0;
	final int kTO = 100;
	
	public DriveTrain() {
		leftMaster = new WPI_TalonSRX(RobotMap.leftMaster);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTO);
		leftMaster.setSensorPhase(true);
		leftSlave = new WPI_TalonSRX(RobotMap.leftSlave);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMaster);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTO);
		rightMaster.setSensorPhase(true);
		rightSlave = new WPI_TalonSRX(RobotMap.rightSlave);
		
		leftMaster.configNominalOutputForward(0, kTO);
		leftMaster.configNominalOutputReverse(0, kTO);
		leftMaster.configPeakOutputForward(1, kTO);
		leftMaster.configPeakOutputReverse(-1, kTO);
		leftMaster.config_kF(0, kF, kTO);
		leftMaster.config_kP(0, kP, kTO);
		leftMaster.config_kI(0, kI, kTO);
		leftMaster.config_kD(0, kD, kTO);
		
		rightMaster.configNominalOutputForward(0, kTO);
		rightMaster.configNominalOutputReverse(0, kTO);
		rightMaster.configPeakOutputForward(1, kTO);
		rightMaster.configPeakOutputReverse(-1, kTO);
		rightMaster.config_kF(0, kF, kTO);
		rightMaster.config_kP(0, kP, kTO);
		rightMaster.config_kI(0, kI, kTO);
		rightMaster.config_kD(0, kD, kTO);
		
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		leftSlave.setNeutralMode(NeutralMode.Brake);
		rightSlave.setNeutralMode(NeutralMode.Brake);
		
		leftMaster.set(ControlMode.Velocity, 0);
		rightMaster.set(ControlMode.Velocity, 0);
		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);
		
		vikeDrive = new VikeDrive(leftMaster, rightMaster);
	}
	
	public void arcadeDrive(double power, double rotate) {
		vikeDrive.arcadeDrive(power, rotate);
	}
	
	public void velocityDrive(double power, double rotate) {
		vikeDrive.arcadeDriveCL(power, rotate);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new PlayerDrive());
    }

	public String requestDiagnostic() {
		System.out.println("\n----STARTING DRIVETRAIN DIAGNOSTIC----\n\n");
		String report1 = "\nLeft Master\n", report2 = "\nLeft Slave\n", report3 = "\nRight Master\n", report4 = "\nRight Slave\n";
		
		System.out.println("Checking firmware versions...");
		if (leftMaster.getFirmwareVersion() != 0x0303) report1 += "Firmware Out of Date!\n";
		if (leftSlave.getFirmwareVersion() != 0x0303) report2 += "Firmware Out of Date!\n";
		if (rightMaster.getFirmwareVersion() != 0x0303) report3 += "Firmware Out of Date!\n";
		if (rightSlave.getFirmwareVersion() != 0x0303) report4 += "Firmware Out of Date!\n";
		
		leftSlave.set(ControlMode.PercentOutput, 0);
		rightSlave.set(ControlMode.PercentOutput, 0);
		
		System.out.println("Testing left master motor...");
		leftMaster.set(ControlMode.PercentOutput, 0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.leftMasterPWR)==0) report1 += "Not Powered!\n";
		if (leftMaster.getSelectedSensorVelocity(0) == 0) report1 += "No Motion Detected!\n";
		leftMaster.set(ControlMode.PercentOutput, 0);
		
		System.out.println("Testing right master motor...");
		rightMaster.set(ControlMode.PercentOutput, 0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.rightMasterPWR)==0) report2 += "Not Powered!\n";
		if (rightMaster.getSelectedSensorVelocity(0) == 0) report2 += "No Motion Detected!\n";
		rightMaster.set(ControlMode.PercentOutput, 0);
		
		System.out.println("Testing left slave motor...");
		leftSlave.set(ControlMode.PercentOutput, 0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.leftSlavePWR)==0) report3 += "Not Powered!\n";
		if (leftMaster.getSelectedSensorVelocity(0) == 0) report3 += "No Motion Detected!\n";
		leftSlave.set(ControlMode.PercentOutput, 0);
		
		System.out.println("Testing right slave motor...");
		rightSlave.set(ControlMode.PercentOutput, 0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.rightSlavePWR)==0) report4 += "Not Powered!\n";
		if (rightMaster.getSelectedSensorVelocity(0) == 0) report4 += "No Motion Detected!\n";
		rightSlave.set(ControlMode.PercentOutput, 0);
		
		
		
		return report1+report2+report3+report4;
	}
}