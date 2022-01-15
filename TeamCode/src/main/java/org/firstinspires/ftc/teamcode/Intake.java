package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private DcMotor surgicalTubingMotor; //intake
    private Servo flapServo; //intake flap
    //private TouchSensor intakeTouch; //touch sensor inside of intake

    public Intake(HardwareMap hardwareMap){
        surgicalTubingMotor = hardwareMap.get(DcMotor.class, "surgical_tubing");
        flapServo = hardwareMap.get(Servo.class, "intake_flap");
        //intakeTouch = hardwareMap.get(TouchSensor.class, "touchSensor");
    }

    public void surgicalTubingOn() {
        surgicalTubingMotor.setPower(1);
    }

    public void surgicalTubingOff() {
        surgicalTubingMotor.setPower(0);
    }

    public void intakeFlapOn() {
        flapServo.setPosition(100);
    }

    public void intakeFlapOff() {
        flapServo.setPosition(0);
    }
}
