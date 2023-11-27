package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Intake extends SubsystemBase {
    
    private TalonFX m_Intake = new TalonFX(1);

    public static final DoubleSolenoid pistons = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2,3);

    private final double kIntakeSpeed = 0.7;

    public void intakeBall(){
        m_Intake.set(ControlMode.PercentOutput, kIntakeSpeed);
    }

    public void outputBall(){
        m_Intake.set(ControlMode.PercentOutput, -kIntakeSpeed);
    }

    public void pistonsExtend(){
        pistons.set(DoubleSolenoid.Value.kForward);
    }
    
    public void pistonsRetract(){
        pistons.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void togglePistons(){
        pistons.toggle();
    }
}

