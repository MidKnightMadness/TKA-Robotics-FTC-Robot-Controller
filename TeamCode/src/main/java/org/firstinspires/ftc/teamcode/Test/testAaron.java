package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class testAaron extends OpMode {
    @Override
    public void init() {
        telemetry.addLine("Hello World! 2: Electric Boogaloo.");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addLine("hi");
        telemetry.update();
    }
}
