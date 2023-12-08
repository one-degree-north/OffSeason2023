package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CTREConfigs;

public class Indexer extends SubsystemBase {
  

  private CANSparkMax m_Indexer = new CANSparkMax(50, MotorType.kBrushless);
  private TalonFX m_Belt = new TalonFX(52); 
  private TalonFX m_Storage = new TalonFX(4);
  
  //move this to constants
  private final double indexerSpeed = 1;
  private final double beltSpeed = 0.3;
  private final double storageSpeed = 1;
  public Indexer() {
    configMotors();
  }

  public void configMotors() {
    this.m_Indexer.restoreFactoryDefaults();
    this.m_Indexer.setSmartCurrentLimit(40, 25);
    this.m_Indexer.setInverted(false);
    this.m_Indexer.setIdleMode(IdleMode.kCoast);

    this.m_Belt.configFactoryDefault();
    this.m_Belt.configAllSettings(CTREConfigs.config30A);
    this.m_Belt.setInverted(false);
    this.m_Belt.setNeutralMode(NeutralMode.Coast);

    this.m_Storage.configFactoryDefault();
    this.m_Storage.configAllSettings(CTREConfigs.config30A);
    this.m_Storage.setInverted(true);
    this.m_Storage.setNeutralMode(NeutralMode.Coast);
}
  public void defaultRun() {
    beltRun();
    indexRun();
    storageReverseRun();
  }

  public void shootRun() {
    beltRun();
    indexRun();
    storageRun();
  }

  public void stopAll() {
    beltStop();
    indexStop();
    storageStop();
  }

  public void beltRun(){
    m_Belt.set(ControlMode.PercentOutput, beltSpeed);
  }

  public void indexRun(){
    m_Indexer.set(indexerSpeed);
  }

  public void storageRun(){
    m_Storage.set(ControlMode.PercentOutput, storageSpeed);
  }

  public void beltStop(){
    m_Belt.set(ControlMode.PercentOutput, 0);
  }

  public void indexStop(){
    m_Indexer.set(0);
  }

  public void storageStop(){
    m_Storage.set(ControlMode.PercentOutput, 0);
  }

  public void storageReverseRun(){
    m_Storage.set(ControlMode.PercentOutput, -storageSpeed);
  }
}
