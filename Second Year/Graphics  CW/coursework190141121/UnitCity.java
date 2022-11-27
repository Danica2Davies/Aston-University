package coursework190141121;

import org.lwjgl.opengl.GL11;

/**
 * Creation of the relevant obstacles & walls for the game
 * @author Danica Davies
 */
public class UnitCity
{
    /**
     *Triggers the drawing off all the needed Obstacles & walls
     */
    public void draw()
    {
    	//Draw Main Walls
    	drawWalls();
    	
    	//Draw Left Obstacles
    	drawLeftObstacles(454.0f);
    	drawLeftObstacles(125.0f);
    	drawLeftObstacles(40.0f);
    	drawLeftObstacles(248.0f);
    	
    	//Draw Right Obstacles
    	drawRightObstacles(334.0f);
    	drawRightObstacles(522.0f);
    	drawRightObstacles(69.0f);
    	drawRightObstacles(694.0f);
    	
    	//Draw Middle Obstacles
    	drawMiddleObstacles(100.0f);
    	drawMiddleObstacles(152.0f);
    	drawMiddleObstacles(263.0f);
    	drawMiddleObstacles(383.0f);
    }

    //Draws the left obstacles at where float Z is
    protected void drawLeftObstacles(float Z) 
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(-3.0f,-1.15f,Z);
        GL11.glScalef(3.2f,15.9f,5.5f);
        new UnitCube().draw();
        GL11.glPopMatrix();
    }
    
    //Draws the Right obstacles at where float Z is
    protected void drawRightObstacles(float Z) 
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(2.5f,-1.15f,Z);
        GL11.glScalef(3.2f,15.9f,5.5f);
        new UnitCube().draw();
        GL11.glPopMatrix();	
    }
    
    //Draws the Middle obstacles at where float Z is
    protected void drawMiddleObstacles(float Z) 
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5f,-1.15f,Z);
        GL11.glScalef(2.2f,15.9f,5.5f);
        new UnitCube().draw();
        GL11.glPopMatrix();
    }
    
    //Draws the left & Right Walls
    protected void drawWalls() 
    {	
        GL11.glPushMatrix();
        	GL11.glTranslatef(-5.0f,-1.15f,-4.0f);
        	GL11.glScalef(3.2f,15.9f,2000.0f);
        	new UnitCube().draw();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        	GL11.glTranslatef(4.0f,-1.15f,-4.0f);
        	GL11.glScalef(3.2f,15.9f,2000.5f);
        	new UnitCube().draw();
        GL11.glPopMatrix();
     }
}
