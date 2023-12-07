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
    private boolean compressorEnabled = false;
    private boolean intakeEnabled = false;

    public Intake() {
        this.revph = new PneumaticHub();
        this.piston1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 6,9);
        this.piston2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 7,8);

        this.m_Intake = new TalonFX(IntakeConstants.motorID);
        configIntakeMotor();
    }

    public void configIntakeMotor() {
        this.m_Intake.configFactoryDefault();
        this.m_Intake.configAllSettings(CTREConfigs.config30A);
        this.m_Intake.setInverted(IntakeConstants.motorInvert);
        this.m_Intake.setNeutralMode(IntakeConstants.motorNeutralMode);
    }


    public void enableCompressor() {
        revph.enableCompressorAnalog(100, 120);
        compressorEnabled = true;
    }
    
    public void disableCompressor() {
        revph.disableCompressor();
        compressorEnabled = false;
    }

    public void toggleCompressor() {
        if (compressorEnabled) disableCompressor();
        else enableCompressor();
    }

    public void intakeBall(){
        m_Intake.set(ControlMode.PercentOutput, IntakeConstants.kIntakeSpeed);
    }

    public void stopMotor() {
        m_Intake.set(ControlMode.PercentOutput, 0);

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
        piston2.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void togglePistons(){
        piston1.toggle();
        piston2.toggle();
    }

    public void toggleIntake() {
        if (intakeEnabled) {
            disableIntake();
        } else {
            enableIntake();
        }
    }

    public void enableIntake() {
        pistonsExtend();
        intakeBall();
        intakeEnabled = true;
    }

    public void disableIntake() {
        pistonsRetract();
        stopMotor();
        intakeEnabled = false;
    }
}

