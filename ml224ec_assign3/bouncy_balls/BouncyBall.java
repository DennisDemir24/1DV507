package ml224ec_assign3.bouncy_balls;

import java.util.Random;

import javafx.scene.shape.Circle;

public class BouncyBall extends Circle {
	
	public static final int MIN_RANDOM_VELOCITY = 2;
	public static final int MAX_RANDOM_VELOCITY = 10;
	
	double velocityX, velocityY;
	
	double boundaryWidth, boundaryHeight;
	
	public BouncyBall()
	{
		velocityX = randomVelocity();
		velocityY = randomVelocity();
	}
	
	// I don't like how this is written,
	// gotta make it readable on small screens.
	private double randomVelocity()
	{
		Random rand = new Random();
		double v = rand.nextInt(MAX_RANDOM_VELOCITY - MIN_RANDOM_VELOCITY + 1); 
		v +=  MIN_RANDOM_VELOCITY;
		v *= 1 - 2*rand.nextInt(2); // small hack to generate negative numbers
		return v;
	}
	
	// ISSUE: This is black magic
	public void update()
	{
		// fetch position data
		double r = this.getRadius();
		double posX = this.getCenterX() - r;
		double posY = this.getCenterY() - r;
		
		// predicted destinations
		double predictedDestX = posX + velocityX;
		double predictedDestY = posY + velocityY;
		
		// delta distance
		double diffX = velocityX;
		double diffY = velocityY;
		
		// Example: the first case is when the ball predicted to be ON or OUTSIDE the left boundary (negative x)
		// and if the velocity is negative for x
		if ( (predictedDestX <= 0 && velocityX < 0) || 						// min check
				(predictedDestX + r*2 >= boundaryWidth && velocityX > 0))	// max check
		{
			if (predictedDestX <= 0)
				diffX = -predictedDestX;									// min diff
			else
				diffX = boundaryWidth - (predictedDestX + r*2); 			// max diff
			
			// invert the velocity
			velocityX *= -1;
		}
		// same as above, except with the Y plane
		if ( (predictedDestY <= 0 && velocityY < 0) ||
				(predictedDestY + r*2 >= boundaryHeight && velocityY > 0))
		{
			if (predictedDestY <= 0)
				diffY = -predictedDestY;
			else
				diffY = boundaryHeight - (predictedDestY + r*2);
			
			// invert the velocity
			velocityY *= -1;
		}
			
		// If the ball hasn't reached to any boundary yet, the difference will remain being the velocity
		//
		// otherwise, to explain this with an example:
		// If the ball is at x = 290 (origio), and has the radius 5 (d:= 10),
		// the x velocity at 15, and the boundary (x_max) is 300.
		// The distance to travel on x will be 15 units.
		// Predictions say the ball will be touching the boundary at x = 295. 
		// When it happens, the ball will bounce (invert velocity).
		// The remaining distance to move the ball will be 10 units.
		// Since the velocity has been inverted at bounce.
		// The remainder distance has ALSO been inverted;
		// the delta will be -10.
		// But it will traverse back to the old position (x = 290), consuming another 5 units.
		// The delta is now -5;
		// Hence the destination will be 285.
		//
		// Although, I've been lying about how to calculate the delta (distance traveled)
		// at event of bounce.
		// It is actually ((<boundary>) - (<current position> + <velocity> + <radius>*2))
		// where radius is used only when calculating MAXIMUM (when going to right)
		double destX = posX + diffX;
		double destY = posY + diffY;
		
		// push the new position
		this.setCenterX(destX + r); 
		this.setCenterY(destY + r);
	}
	
	// No distance or position prediction for this one, PLEASE!
	// (Too much mathematics)
	//
	// This is written in a fashion where the balls' momentum/velocity will always be constant.
	public void handleCollision(BouncyBall other)
	{
		double distance = Math.sqrt(
				Math.pow(this.getCenterX() - other.getCenterX(), 2) +
				Math.pow(this.getCenterY() - other.getCenterY(), 2));
		
		if (distance <= this.getRadius() + other.getRadius()) 
			// the balls collide once the distance equals, or go under, the sum of balls radius
			// aka, they are intersecting - rubber balls, right?
		{
			double thisTotalVelocity = this.getTotalVelocity();
			double otherTotalVelocity = other.getTotalVelocity();
			
			double arg = Math.atan((this.velocityX + other.velocityX)/(this.velocityY + other.velocityY));
			double inverse = Math.PI + arg;
			
			this.velocityX = thisTotalVelocity * Math.cos(arg);
			this.velocityY = thisTotalVelocity * Math.sin(arg);
			other.velocityX = otherTotalVelocity * Math.cos(inverse);
			other.velocityY = otherTotalVelocity * Math.sin(inverse);
		}
	}
	
	public double getX()
	{
		return getCenterX() - getRadius();
	}
	
	public double getY()
	{
		return getCenterY() - getRadius();
	}
	
	public void setBoundaries(double width, double height)
	{
		boundaryWidth = width;
		boundaryHeight = height;
	}
	
	public void setVelocity(double x, double y)
	{
		velocityX = x;
		velocityY = y;
	}
	
	public double getTotalVelocity()
	{
		return Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2));
	}
}
