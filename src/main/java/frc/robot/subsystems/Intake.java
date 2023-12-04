package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CTREConfigs;
import frc.robot.Constants.IntakeConstants;



public class Intake extends SubsystemBase {
    
    // TODO: Set Can ID
    private TalonFX m_Intake;


    public final DoubleSolenoid piston1;
    public final DoubleSolenoid piston2;

    // Check ID?
    private PneumaticHub revph;

    public Intake() {
        this.revph = new PneumaticHub();
        this.piston1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2,3);
        this.piston2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2,3);

        this.m_Intake = new TalonFX(IntakeConstants.motorID);
        configIntakeMotor();
    }

    public void configIntakeMotor() {
        this.m_Intake.configFactoryDefault();
        this.m_Intake.configAllSettings(CTREConfigs.intakeTalonFXConfiguration);
        this.m_Intake.setInverted(IntakeConstants.motorInvert);
        this.m_Intake.setNeutralMode(IntakeConstants.motorNeutralMode);
    }


    public void enableCompressor() {
        revph.enableCompressorAnalog(100, 120);
    }
    
    public void disableCompressor() {
        revph.disableCompressor();
    }

    public void intakeBall(){
        m_Intake.set(ControlMode.PercentOutput, IntakeConstants.kIntakeSpeed);
    }

    public void outputBall(){
        m_Intake.set(ControlMode.PercentOutput, -IntakeConstants.kIntakeSpeed);
    }

    public void pistonsExtend(){
        piston1.set(DoubleSolenoid.Value.kForward);
        piston2.set(DoubleSolenoid.Value.kForward);
    }
    
    public void pistonsRetract(){
        piston1.set(DoubleSolenoid.Value.kReverse);
        piston1.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void togglePistons(){
        piston1.toggle();
        piston2.toggle();
    }
}

