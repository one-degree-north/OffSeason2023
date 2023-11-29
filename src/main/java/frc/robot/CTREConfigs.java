// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;


/** Add your docs here. */
public final class CTREConfigs {
    public static TalonFXConfiguration intakeTalonFXConfiguration;
    public CTREConfigs() {
        intakeTalonFXConfiguration = new TalonFXConfiguration();

        SupplyCurrentLimitConfiguration intakeSupplyLimit = new SupplyCurrentLimitConfiguration(true, 25, 40, 0.1);

        intakeTalonFXConfiguration.supplyCurrLimit = intakeSupplyLimit;

    }
    
}
