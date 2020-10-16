package org.firstinspires.ftc.teamcode.Outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


import org.firstinspires.ftc.teamcode.Common.Assembly;

public abstract class Outtake extends Assembly {

    public HardwareMap hardwareMap;
    public Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry){

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }
    public abstract void start();
    public abstract void stop();
    public abstract void setSpeed(double speed);

}