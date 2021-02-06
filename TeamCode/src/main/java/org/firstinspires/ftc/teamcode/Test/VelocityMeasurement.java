package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Common.Config;

@TeleOp
public class VelocityMeasurement extends OpMode {
    DcMotorEx motor;
    @Override
    public void init() {
        motor = (DcMotorEx) hardwareMap.dcMotor.get(Config.OUTTAKEMOTOR);
    }

    @Override
    public void loop() {
        telemetry.addLine(String.valueOf(motor.getVelocity()));
        telemetry.update();
    }
}