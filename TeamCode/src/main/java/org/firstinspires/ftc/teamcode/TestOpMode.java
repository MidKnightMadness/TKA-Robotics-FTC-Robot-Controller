package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
/*
Player 1
left stick (hold) = shifting
right stick (hold) = rotating
dpad up (hold) = go to shipping hub position

Player 2
y (toggle) = catapult to upper
b (toggle) = catapult to middle
a (toggle) = catapult to lower
x (toggle) = catapult flap
left bumper (toggle) = team shipping element lift motor
left trigger (toggle) = team shipping element lift servo
right bumper (toggle) = surgical tubing
dpad down (toggle) = rotate catapult head left
dpad left (hold) = spin carousel left
dpad right (hold) = spin carousel right
*/

@TeleOp
public class TestOpMode extends OpMode {
    SampleDrive drive;
    Catapult catapult;
    Carousel carousel;
    Intake intake;
    Lift lift;

    private DistanceSensor sensorDistanceL; //left front sensor
    private ModernRoboticsI2cRangeSensor sensorRangeM; //middle front range sensor
    private DistanceSensor sensorDistanceR; //right front sensor

    private boolean lastPressedCatapultUpper = false;
    private boolean lastPressedCatapultMiddle = false;
    private boolean lastPressedCatapultLower = false;
    private boolean catapultUpperToggle = false;
    private boolean catapultMiddleToggle = false;
    private boolean catapultLowerToggle = false;
    private boolean lastPressedFlap = false;
    private boolean flapToggle = false;
    private boolean lastPressedCatapultHeadLeft = false;
    private boolean catapultHeadLeftToggle = false;

    private boolean lastPressedSurgical = false;
    private boolean surgicalToggle = false;
    private boolean lastPressedIntakeHolder = false;
    private boolean intakeHolderToggle = false;
    private boolean lastPressedLiftMotor = false;
    private boolean liftMotorToggle = false;
    private boolean lastPressedLiftServo = false;
    private boolean liftServoToggle = false;

    @Override
    public void init() {
        drive = new SampleDrive(hardwareMap);
        catapult = new Catapult(hardwareMap);
        carousel = new Carousel(hardwareMap);
        intake = new Intake(hardwareMap);
        lift = new Lift(hardwareMap);
        sensorDistanceL = hardwareMap.get(DistanceSensor.class, "sensor_distance_left");
        sensorRangeM = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range_middle");
        sensorDistanceR = hardwareMap.get(DistanceSensor.class, "sensor_distance_right");

        catapult.headReturn();
    }

    @Override
    public void loop() {
        //drive to shipping hub position
        if ((sensorRangeM.getDistance(DistanceUnit.INCH) <= 8.5 || sensorRangeM.getDistance(DistanceUnit.INCH) >= 9.5) &&
             gamepad1.dpad_up && sensorRangeM.getDistance(DistanceUnit.INCH) < 100) {
            drive.drive(0, (sensorRangeM.getDistance(DistanceUnit.INCH) - 9) / 10, 0);
        } else {
            drive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            drive.telemetry(telemetry);
        }

        //catapult
        if (gamepad2.y && !lastPressedCatapultUpper) {
            catapultUpperToggle = !catapultUpperToggle;
            catapultMiddleToggle = false;
            catapultLowerToggle = false;
        } else if (gamepad2.b && !lastPressedCatapultMiddle) {
            catapultMiddleToggle = !catapultMiddleToggle;
            catapultUpperToggle = false;
            catapultLowerToggle = false;
        } else if (gamepad2.a && !lastPressedCatapultLower) {
            catapultLowerToggle = !catapultLowerToggle;
            catapultUpperToggle = false;
            catapultMiddleToggle = false;
        }
        if (catapultUpperToggle) {
            catapult.upper();
        } else if (catapultMiddleToggle) {
            catapult.middle();
        } else if (catapultLowerToggle) {
            catapult.lower();
        } else {
            catapult.returnPosition();
        }
        lastPressedCatapultUpper = gamepad2.y;
        lastPressedCatapultMiddle = gamepad2.b;
        lastPressedCatapultLower = gamepad2.a;

        //turn flap
        if(gamepad2.x && !lastPressedFlap) {
            flapToggle = !flapToggle;
        }
        if(flapToggle) {
            catapult.flapOn();
        }
        else {
            catapult.flapOff();
        }
        lastPressedFlap = gamepad2.x;

        //catapult head
        if (gamepad2.dpad_down && !lastPressedCatapultHeadLeft) {
            catapultHeadLeftToggle = !catapultHeadLeftToggle;
        }
        if (catapultHeadLeftToggle) {
            catapult.headLeft();
        } else {
            catapult.headReturn();
        }
        lastPressedCatapultHeadLeft = gamepad2.dpad_down;

        //team shipping element lift
        /*if (gamepad2.left_bumper && !lastPressedLiftMotor) {
            liftMotorToggle = !liftMotorToggle;
        }
        if (liftMotorToggle) {
            lift.lift();
        } else {
            lift.lower();
        }
        lastPressedLiftMotor = gamepad2.left_bumper;

        if (gamepad2.left_trigger > 0 && !lastPressedLiftServo) {
            liftServoToggle = !liftServoToggle;
        }
        if (liftMotorToggle) {
            lift.open();
        } else {
            lift.close();
        }
        lastPressedLiftServo = (gamepad2.left_trigger > 0);*/

        //surgical tubing
        if (gamepad2.right_bumper && !lastPressedSurgical) {
            surgicalToggle = !surgicalToggle;
        }
        if (surgicalToggle) {
            intake.surgicalTubingOn();
        } else {
            intake.surgicalTubingOff();
        }
        lastPressedSurgical = gamepad1.right_bumper;

        //intake holder
        if(gamepad2.right_trigger > 0 && !lastPressedIntakeHolder) {
            intakeHolderToggle = !intakeHolderToggle;
        }
        if(intakeHolderToggle) {
            intake.dropIntake();
        } else {
            intake.returnIntakeHolder();
        }
        lastPressedIntakeHolder = (gamepad2.right_trigger > 0);

        //spinning carousel
        if(gamepad2.dpad_left) {
            carousel.spinRed();
        } else if(gamepad2.dpad_right) {
            carousel.spinBlue();
        } else {
            carousel.spinOff();
        }
    }
}
