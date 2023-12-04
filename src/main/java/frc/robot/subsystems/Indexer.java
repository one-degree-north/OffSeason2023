package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {

  private TalonFX m_Indexer = new TalonFX(1);

  private final double kBeltSpeed = 1;

  public void index(){
    m_Indexer.set(ControlMode.PercentOutput, kBeltSpeed);
  }

  public void stopIndex(){
    m_Indexer.set(ControlMode.PercentOutput, 0);
  }

  public void outIndex(){
    m_Indexer.set(ControlMode.PercentOutput, -kBeltSpeed);  
  }
}
