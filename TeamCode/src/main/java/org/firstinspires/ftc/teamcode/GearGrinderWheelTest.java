package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;
import java.util.List;

@TeleOp (name="GG Wheel Test", group = "Testing")
public class GearGrinderWheelTest extends LinearOpMode {

    // Defining All the Motors!!
    private DcMotor frontLeft, frontRight;
    private DcMotor backLeft, backRight;

    // Defining a Variable
    private double power;
    private double halfPower;
    private boolean motorsRunning;

    @Override
    public void runOpMode() throws InterruptedException {
        ArrayList<DcMotor> motors = new ArrayList<>();
        // Motor Initialization!
        frontLeft = hardwareMap.get(DcMotor.class, "motor0");
        motors.add(frontLeft);
        frontRight = hardwareMap.get(DcMotor.class, "motor1");
        motors.add(frontRight);
        backLeft = hardwareMap.get(DcMotor.class, "motor2");
        motors.add(backLeft);
        backRight = hardwareMap.get(DcMotor.class, "motor3");
        motors.add(backRight);
        // Variable Setting
        power = 5;
        // TODO - Make it so that there is a way that when a button is held, it goes at half power
        motorsRunning = false;


        // Waiting for Start Button to be Pushed
        waitForStart();
        // After Start Button is Pushed

        while (opModeIsActive()) {
            // If Left-Stick is Pushed Forward
            if (gamepad1.left_stick_y > 0) {
                // AlL Left is Forward, all Right is Reverse (diff sides)
                frontLeft.setDirection(DcMotor.Direction.FORWARD);
                frontRight.setDirection(DcMotor.Direction.REVERSE);
                backLeft.setDirection(DcMotor.Direction.FORWARD);
                backRight.setDirection(DcMotor.Direction.REVERSE);
                motorsRunning = true;
            }
            // If Left-Stick is Neutral
            if (gamepad1.left_stick_y == 0) {
                motorsRunning = false;
            }
            // If Left-Stick is Pushed Backward
            if (gamepad1.left_stick_y < 0) {
                // AlL Left is Reverse, all Right is Forward (diff sides)
                frontRight.setDirection(DcMotor.Direction.FORWARD);
                frontLeft.setDirection(DcMotor.Direction.REVERSE);
                backRight.setDirection(DcMotor.Direction.FORWARD);
                backLeft.setDirection(DcMotor.Direction.REVERSE);
                motorsRunning = true;
            }
            // If Left-Stick is Pushed Left
            if (gamepad1.left_stick_x < 0) {
                setAllMotorDirection(motors, true);
                motorsRunning = true;
            }
            // If Left-Stick is Pushed Right
            if (gamepad1.left_stick_x > 0) {
                setAllMotorDirection(motors, false);
                motorsRunning = true;
            }
            // Check if Motors Should Run
            if (motorsRunning) {
                setAllMotorPower(motors, power);
            }
            else {
                setAllMotorPower(motors, 0);
            }

        }
    }
    // Sets all power of all the motors to power
    public void setAllMotorPower(ArrayList<DcMotor> motors, double power){
        // Through the power of arrays we make this teeny!
        for (DcMotor motor : motors){
            motor.setPower(power);
        }
    }
    public void setAllMotorDirection(ArrayList<DcMotor> motors, boolean direction ) { //If Direction is true we turn right, else we turn left
        for (DcMotor motor : motors){
            if (direction) {
                motor.setDirection(DcMotor.Direction.FORWARD);
            }
            else {
                motor.setDirection(DcMotor.Direction.REVERSE);
            }
        }
    }
}
