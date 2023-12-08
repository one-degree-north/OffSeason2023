// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CTREConfigs;

public class Turret extends SubsystemBase { 
  /** Creates a new Turret. */
  TalonFX rotationMotor = new TalonFX(14);
  TalonFX flywheelMotor = new TalonFX(26);
  TalonFX flywheelSlave = new TalonFX(31);
  private final double flywheelSpeed = 1;
  private final double turretSpeed = 0.1;

  public Turret() {
    configMotors();

  }

  public void configMotors() {

    this.rotationMotor.configFactoryDefault();
    this.rotationMotor.configAllSettings(CTREConfigs.config30A);
    this.rotationMotor.setInverted(false);
    this.rotationMotor.setNeutralMode(NeutralMode.Coast);

    this.flywheelMotor.configFactoryDefault();
    this.flywheelMotor.configAllSettings(CTREConfigs.config30A);
    this.flywheelMotor.setInverted(true);
    this.flywheelMotor.setNeutralMode(NeutralMode.Coast);

    this.flywheelSlave.configAllSettings(CTREConfigs.config30A);
    this.flywheelSlave.configFactoryDefault();
    this.flywheelSlave.setInverted(false);
    this.flywheelSlave.setNeutralMode(NeutralMode.Coast);

    flywheelSlave.follow(flywheelMotor);
  }

  public void runFlywheel(){
    flywheelMotor.set(ControlMode.PercentOutput, flywheelSpeed);
  }
  public void stopFlywheel(){
    flywheelMotor.set(ControlMode.PercentOutput, 0);
  }

  // CW should be "forward" (makes more sense intuitively)
  public void runTurretCW(double input){
    rotationMotor.set(ControlMode.PercentOutput, input*turretSpeed);
  }

  public void runTurretCCW(double input){
    rotationMotor.set(ControlMode.PercentOutput, -input*turretSpeed);
  }
  public void stopTurret(){
    rotationMotor.set(ControlMode.PercentOutput, 0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
