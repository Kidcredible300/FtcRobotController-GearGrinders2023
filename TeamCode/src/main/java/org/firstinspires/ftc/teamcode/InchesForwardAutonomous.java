package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

@Autonomous (name = "Going X Inches Forward", group = "Gear Grinders Testing")
public class InchesForwardAutonomous extends LinearOpMode {

    // Some Encoder stuff. The first value is a preset value, and the 2nd is the amount of inches we want to go.
    private final int ENCODER_360 = 1440;
    private final int INCH_GOAL = 6;

    //Setting diameter, radius, etc.
    //Diameter of Wheel is 4 inches, making radius 2 inches, and circumference 4PI inches.
    private final int DIAMETER = 4;
    private final int RADIUS = DIAMETER/2;
    private final double CIRCUMFERENCE = RADIUS*2*Math.PI;

    // Defining All the Motors!!
    private DcMotor frontLeft, frontRight;
    private DcMotor backLeft, backRight;
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

        // Resetting motor encoder position to 0
        resetEncoders(motors);

        waitForStart();

        while(opModeIsActive()) {
            moveForward(INCH_GOAL, CIRCUMFERENCE, ENCODER_360, motors);
        }
    }

    public void resetEncoders(ArrayList<DcMotor> motors) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void moveForward(int inchGoal, double circumference, int encoderConstant, ArrayList<DcMotor> motors) {
        // Doing some math to figure out what encoder value to rotate to. The place where we made this math is on our documentation
        double x;
        double y;
        x = inchGoal/circumference;
        y = encoderConstant*x;
        for (DcMotor motor : motors) {
            motor.setTargetPosition((int)y);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        setAllMotorPower(motors, 5);
    }
    public void setAllMotorPower(ArrayList<DcMotor> motors, double power){
        // Through the power of arrays we make this teeny!
        for (DcMotor motor : motors){
            motor.setPower(power);
        }
    }
}   
