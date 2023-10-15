package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;
import java.util.List;

@TeleOp (name="GG Wheel Test", group = "Testing")
public class GearGrinderWheelTest extends LinearOpMode {

    // Defining All the Motors for Wheels!!
    private DcMotor frontLeft, frontRight;
    private DcMotor backLeft, backRight;

    // Defining All the Motors for the Arm!!
    private DcMotor armBaseRotate;

    // Defining a Variable
    private double power;
    private double halfPower;
    private boolean wheelsRunning;

    @Override
    public void runOpMode() throws InterruptedException {
        ArrayList<DcMotor> motors = new ArrayList<>();
        // Wheel Motor Initialization!
        frontLeft = hardwareMap.get(DcMotor.class, "motor0");
        motors.add(frontLeft);
        frontRight = hardwareMap.get(DcMotor.class, "motor1");
        motors.add(frontRight);
        backLeft = hardwareMap.get(DcMotor.class, "motor2");
        motors.add(backLeft);
        backRight = hardwareMap.get(DcMotor.class, "motor3");
        motors.add(backRight);
        // Arm Motors Initialization!
        armBaseRotate = hardwareMap.get(DcMotor.class, "motorE-0");
        // Variable Setting
        power = 5;
        // TODO - Make it so that there is a way that when a button is held, it goes at half power
        wheelsRunning = false;


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
                wheelsRunning = true;
            }
            // If Left-Stick is Neutral
            if (gamepad1.left_stick_y == 0 && gamepad1.left_stick_x == 0) {
                wheelsRunning = false;
            }
            // If Left-Stick is Pushed Backward
            if (gamepad1.left_stick_y < 0) {
                // AlL Left is Reverse, all Right is Forward (diff sides)
                frontRight.setDirection(DcMotor.Direction.FORWARD);
                frontLeft.setDirection(DcMotor.Direction.REVERSE);
                backRight.setDirection(DcMotor.Direction.FORWARD);
                backLeft.setDirection(DcMotor.Direction.REVERSE);
                wheelsRunning = true;
            }
            // If Left-Stick is Pushed Left
            if (gamepad1.left_stick_x < 0) {
                setMotorArrayDirection(motors, true);
                wheelsRunning = true;
            }
            // If Left-Stick is Pushed Right
            if (gamepad1.left_stick_x > 0) {
                setMotorArrayDirection(motors, false);
                wheelsRunning = true;
            }
            // If Right-Stick is Pushed Left
            if (gamepad1.right_stick_x < 0) {
                armBaseRotate.setDirection(DcMotor.Direction.FORWARD);
                armBaseRotate.setPower(2);
            }
            // If Right-Stick is Pushed Right
            if (gamepad1.right_stick_x > 0) {
                armBaseRotate.setDirection(DcMotor.Direction.REVERSE);
                armBaseRotate.setPower(2);
            }
            // If Right-Stick X is Neutral
            if (gamepad1.right_stick_x == 0) {
                armBaseRotate.setPower(0);
            }
            // Check if Wheels Should Run
            if (wheelsRunning) {
                setMotorArrayPower(motors, power);
            }
            else {
                setMotorArrayPower(motors, 0);
            }

        }
    }
    // Sets all power of all the motors in the Array to
    public void setMotorArrayPower(ArrayList<DcMotor> myMotors, double myPower){
        // Through the power of arrays we make this teeny!
        for (DcMotor motor : myMotors){
            motor.setPower(myPower);
        }
    }
    public void setMotorArrayDirection(ArrayList<DcMotor> myMotors, boolean myDirection ) { //If Direction is true we turn right, else we turn left
        for (DcMotor motor : myMotors){
            if (myDirection) {
                motor.setDirection(DcMotor.Direction.FORWARD);
            }
            else {
                motor.setDirection(DcMotor.Direction.REVERSE);
            }
        }
    }
}
// I want the Commit to Work so I changed the code