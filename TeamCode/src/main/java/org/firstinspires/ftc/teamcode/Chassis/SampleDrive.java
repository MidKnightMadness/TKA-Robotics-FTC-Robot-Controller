package org.firstinspires.ftc.teamcode.Chassis;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.Assembly;
import org.firstinspires.ftc.teamcode.Common.Config;

public class SampleDrive extends Drive{

    //declare wheel motors
    private DcMotorEx motorFL;
    private DcMotorEx motorFR;
    private DcMotorEx motorBL;
    private DcMotorEx motorBR;
    private final double maxVel = 2500;
    private double xPosition;
    private double yPosition;

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        super.init(hardwareMap, telemetry);
        motorFL = (DcMotorEx)hardwareMap.dcMotor.get(Config.DRIVEFL);
        motorFR = (DcMotorEx)hardwareMap.dcMotor.get(Config.DRIVEFR);
        motorBL = (DcMotorEx)hardwareMap.dcMotor.get(Config.DRIVEBL);
        motorBR = (DcMotorEx)hardwareMap.dcMotor.get(Config.DRIVEBR);
    }

    @Override
    public void drive(double forwards, double sideways, double turn) {
        motorFL.setVelocity((-forwards + sideways + turn) * maxVel);
        motorFR.setVelocity((forwards + sideways - turn) * maxVel);
        motorBL.setVelocity((-forwards - sideways + turn) * maxVel);
        motorBR.setVelocity((forwards - sideways - turn) * maxVel);
    }

    @Override
    public void moveToPosition(double x, double y) {
        //get x and y position (i dunno how)
        xPosition = 1;
        yPosition = -1;

        while(xPosition < x) {
            drive(0, 1, 0);
        }
        while(xPosition > x) {
            drive(0, -1,0);
        }
        while(yPosition < y) {
            drive(1,0,0);
        }
        while(yPosition > y) {
            drive(-1,0,0);
        }
    }
}
