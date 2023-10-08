package _01_Olympic_Rings;

import java.awt.Color;

import org.jointheleague.graphical.robot.Robot;

public class OlympicRings_Threaded {
	// Make A Program that uses Threads and robots to draw the Olympic rings. One
	// robot should draw one ring simultaneously with the other 4 robots.

	public static void main(String[] args) {

		Robot blue = new Robot(300, 200);
		Robot black = new Robot(550, 200);
		Robot red = new Robot(800, 200);
		Robot yellow = new Robot(425, 350);
		Robot green = new Robot(675, 350);
		

		
		
		
		Thread r1 = new Thread(()->circle(blue));
		Thread r2 = new Thread(()->circle(black));
		Thread r3 = new Thread(()->circle(red));
		Thread r4 = new Thread(()->circle(yellow));
		Thread r5 = new Thread(()->circle(green));
		
		blue.setPenColor(Color.blue);
		black.setPenColor(Color.BLACK);
		red.setPenColor(Color.red);
		yellow.setPenColor(Color.orange);
		green.setPenColor(Color.green);
		
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		r5.start();
		
	}

	static void circle(Robot robot) {
		robot.penDown();
		robot.setPenWidth(5);
		for(int i = 0; i<360;i++) {
			robot.move(2);
			robot.turn(1);
		}
	}
	
}
