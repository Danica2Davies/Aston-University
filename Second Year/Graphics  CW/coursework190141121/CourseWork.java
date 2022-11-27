/* 190141121CourseWork.java
 * A simple scene consisting of a 3D runner based game snapshot
 * 
 * Student Number: 190141121
 * Student Name: Danica Davies
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(10,1,10) T(0,-2.15,-10)] Ground plane
 *  |
 *  +-- [T(0,personY,-10)] Person
 *  |   |
 *  |   +-- [] Torso
 *  |       |
 *  |       +-- [T(0, 0.1+(torsoHeight+headHeight)/2, 0)] Head]
 *  |       |
 *  |       +-- [T((-torsoWidth/2)-shoulderRadius, (torsoHeight-shoulderRadius)/2, 0)] Left arm
 *  |       |   |
 *  |       |   +-- [] Limbs
 *  |       |       |
 *  |       |       +-- [ T(0, (-rArmLength-shoulderRadius)/2, 0) Rz() R()
 *  |       |             Rz(shoulderAngleLR*(side==PersonPart.Side.LEFT?-1.0f:1.0f))
 *  |       |             Rx(shoulderAngleUD) ] Arm         
 *  |       |
 *  |       +-- [T(( torsoWidth/2)+shoulderRadius, (torsoHeight-shoulderRadius)/2, 0)] Right arm & Left Arm
 *  |       |   
 *  |       |   
 *  |       |
 *  |       +-- [T(0, (-torsoHeight - waistHeight)/2, 0)] Waist
 *  |           |
 *  |           +-- [T((-waistWidth)/2 + LegRadius, (-waistHeight)/2 - LegRadius, 0)] Left leg
 *  |           |   |
 *  |           |   +-- [] Limbs
 *  |           |       |
 *  |           |       +-- [T(0, (-LegLength-LegRadius)/2, 0)
 *  |           |            Rz(LegAngleLR*(side==PersonPart.Side.LEFT?-1.0f:1.0f))
 *  |           |            Rx(LegAngleUD) ] Leg
 *  |           |
 *  |           +-- [T(( waistWidth)/2 - LegRadius, (-waistHeight)/2 - LegRadius, 0)] Right leg & Left Leg
 *	|
 *	+--[T(5.0f, 7.0f, personZ), R[90.0f*-RotateMoon/1000.0f),0.0f,1.0f,0.0f]] DrawSphere (The Moon)
 *	|	
 * 	+-- [] UnitCity
 * 		 |
 *  	 +-- []BuildWall
 * 		 | 	 |
 * 		 | 	 +-- [S(3.2,15.9,5.5),T(-3.0f,-1.15f,Z)] BuildRightWall
 * 		 | 	 |
 * 		 |   +-- [S(3.2,15.9,5.5),T(2.5f,-1.15f,Z)] BuildLeftWall
 * 		 | 	 
 *  	 +-- []Build Left
 *    	 | 	 |
 *  	 | 	 +-- [S(3.2,15.9,5.5),T(-3.0f,-1.15f,Z)] 

 *    	 |
 *  	 +-- []Build Middle
 *    	 | 	 |
 * 		 |   +-- [S(2.2f,15.9f,5.5f),T(0.5f,-1.15f,Z)] BuildSecondObstacle
 *     	 |
 *  	 +-- []Build Right
 *    	  	 |
 *  		 +-- [S(3.2f,15.9f,5.5f),T((2.5f,-1.15f,Z)] BuildRightObstacle
*/
package coursework190141121;
import java.lang.Math;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;

import org.lwjgl.input.Keyboard;

import GraphicsLab.*;

/**
 * Some Code was Taken from Example: AnimatedPerson.
 * Elements of this code can be seen within: 
 * The Human Movement Animation with inspired (and Based upon) the walking animation
 * in the example AnimationPlayer code created by Anthony Jones and Dan Cornford
 * 
 * Important Notice:
 * UnitCube class was taken directly from the AnimatedPerson class.
 * Limbs, Person and UnitCube was inspired by or has code taken from Example.AnimatedPerson
 * Lighting was inspired by the AnimatedPerson class but has been expanded upon.
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis
 * <li>Press the S key to move in the middle
 * <li>Press the A key to move in the left lane
 * <li>Press the D key to move in the Right lane.
 * </ul>
 * </ul>
 * 
 * @author Danica Davies
 */
public class CourseWork extends GraphicsLab
{
    /** Spawning the Player */
    private Person person = new Person(3.0f,0.6f,0.5f);
    
    /** animationTimer, indicates how long the animation has been running so far. */
    private float animationTimer = 0.0f;
    
    private float Colour1 = 1.0f;
    private float Colour2 = 1.0f;
    private float Colour3 = 1.0f;
    private float personY = 0.0f;
    private float personX = 0.0f;
    private float personZ = -10.0f;
    private float CameraZ = 4.0f;
    private float Plane = -10.0f;
    
    /** creates Animation id which indicates that no animation or the Running animation is to be used */
    private static final int NO_ANIMATION = 0;
    private static final int RUNNING_ANIMATION = 1;
    private int animation;
    
    private final int planeList = 1;
    private Texture GroundTextures;
    private float RotateMoon = 0.0f;
   
    public static void main(String args[])
    {   
    	new CourseWork().run(WINDOWED,"CourseWork",0.3f);
    }

    protected void initScene() throws Exception
    {
    
    	GroundTextures = loadTexture("coursework190141121/textures/free brick tiles.bmp");
    	//Ground Textures taken from https://www.textures.com/download/floorstreets0100/39445
    
    	GL11.glNewList(planeList,GL11.GL_COMPILE);
    		{   
    			drawUnitPlane();
    		}
    	GL11.glEndList();
        
        // Lighting inspired by Example.Animtion but changed to allow more lighting options as the player gets further along the level.
        float globalAmbient[]   = {Colour1,  Colour2,  Colour3, 1.0f}; 
        // set the global ambient lighting
    	GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
        // Sets the Ambient, Diffuse and Positioning.
    	float diffuse0[]  = { 0.8f,  0.8f, 0.8f, 1.0f};
    	float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
    	float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f}; 
        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light, Lighting Calculations and normaises it all.
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_NORMALIZE);
    }
    
    protected void checkSceneInput()
    {
    	if (personZ < 100) { //Red
    		Colour1 = 2.0f;
    		Colour2 = 1.0f;
    		Colour3 = 1.0f;
    		
    		float globalAmbient[]   = {Colour1,  Colour2,  Colour3, 1.0f}; //1.0f,  1.1f,  1.0f, 1.0f
    	    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
            
    	    GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_NORMALIZE);	
    	}
    	
    	if (personZ > 200 && personZ < 300) { //Orange
    		Colour1 = 2.0f;
    		Colour2 = 1.65f;
    		Colour3 = 1.0f;
    		
    		float globalAmbient[]   = {Colour1,  Colour2,  Colour3, 1.0f}; //1.0f,  1.1f,  1.0f, 1.0f
    	    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
            
    	    GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_NORMALIZE);	
    	}
    	
    	if (personZ > 300 && personZ < 400) { //Yellow
    		Colour1 = 2.0f;
    		Colour2 = 1.9f;
    		Colour3 = 1.0f;
    		
    		float globalAmbient[]   = {Colour1,  Colour2,  Colour3, 1.0f}; //1.0f,  1.1f,  1.0f, 1.0f
    	    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
            
    	    GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_NORMALIZE);	
    	}
    	
    	if (personZ > 400 && personZ < 500) { //Green
    		Colour1 = 1.0f;
    		Colour2 = 2.0f;
    		Colour3 = 1.0f;
    		
    		float globalAmbient[]   = {Colour1,  Colour2,  Colour3, 1.0f}; //1.0f,  1.1f,  1.0f, 1.0f
    	    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
            
    	    GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_NORMALIZE);	
    	}
    	
    	if (personZ > 500 && personZ < 600) { //Aqua
    		Colour1 = 1.0f;
    		Colour2 = 1.5f;
    		Colour3 = 1.5f;
    		
    		float globalAmbient[]   = {Colour1,  Colour2,  Colour3, 1.0f}; //1.0f,  1.1f,  1.0f, 1.0f
    	    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
            
    	    GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_NORMALIZE);	
    	}
    	

    	if (personZ > 600 && personZ < 700) {
    		Colour1 = 1.0f;
    		Colour2 = 1.3f;
    		Colour3 = 1.5f;
    		
    		float globalAmbient[]   = {Colour1,  Colour2,  Colour3, 1.0f}; //1.0f,  1.1f,  1.0f, 1.0f
    	    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
            
    	    GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_NORMALIZE);    		
    	}
 
        personZ = personZ + 0.04f;
        CameraZ = CameraZ + 0.04f;
        Plane = Plane - 0.01f;
        animation = RUNNING_ANIMATION;

        if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {        	
        	if (personX > -1.4) 
        		{
        		personX = personX - 0.9f;
        		}
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
        	personX = 0.15f;
        }
        	
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
             if (personX < 1.4) 
             	{
            	 	personX = personX + 0.9f;
             	}
        }

        else if(Keyboard.isKeyDown(Keyboard.KEY_O))
        	{   
        		 resetAnimation();
        		 animation = NO_ANIMATION;
        		 Restart();
        	}
    	}
    
    protected void updateScene()
    {
    	 RotateMoon += + 1.0f * getAnimationScale();
    	 
    	if(animation == RUNNING_ANIMATION)
    	 	{   
    		 	updateRunningAnimation();
    	 	}
     
       	if (personZ > 100 && personZ < 200) 
       		{
       			Colour1 = 2.0f;
       		}
    }

    void resetAnimation()
    {
        // reset all variables that take part in the animations
        // to their initial values... which should all be zero
        final float zero = 0.0f;
        animationTimer = zero;
        personY = zero;
        
        
        person.getRightArm().setArmAngleUD(zero);
        person.getRightArm().setArmAngleLR(zero);
        
        person.getLeftArm().setArmAngleUD(zero);
        person.getLeftArm().setArmAngleLR(zero);
        
        person.getRightLeg().setLegAngleUD(zero);
        person.getRightLeg().setLegAngleLR(zero);

        person.getLeftLeg().setLegAngleUD(zero);
        person.getLeftLeg().setLegAngleLR(zero);
    }
    
    void updateRunningAnimation()
    {
        //Animation Inspired by Example.AnimatedPerson but more specialised to suit a
    	//Shorter player without Joints with different animation values and Animation timer.
    	animationTimer += getAnimationScale();

        double angle = Math.sin(animationTimer / 65) * 80;

        person.getRightArm().setArmAngleUD((float)angle+5);
        person.getLeftArm().setArmAngleUD(-(float)angle+5);
        
        person.getRightLeg().setLegAngleUD(-(float)angle);
        person.getLeftLeg().setLegAngleUD((float)angle);
        
        personY = (float)Math.abs(Math.sin(animationTimer / 45)) * 0.2f;
    }
  
    
    protected void renderScene()
    {
		 GL11.glPushMatrix();
	        {
	            GL11.glTranslatef(0.0f, -0.1f, -8f);
	            GL11.glScalef(1f, 1.3f, 0.5f);
	            new UnitCube().draw();
	 }
	        GL11.glPopMatrix();
    	GL11.glPushMatrix();
        	{ 
              GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
              // change the geometry colour to white so that the texture
              // is bright and details can be seen clearly
              Colour.WHITE.submit();
              
              // enable texturing and bind an appropriate texture
              GL11.glEnable(GL11.GL_TEXTURE_2D);
              GL11.glBindTexture(GL11.GL_TEXTURE_2D,GroundTextures.getTextureID());
              
              // position, scale and draw the ground plane using its display list
              GL11.glTranslatef(0.0f,-1.05f,-12.0f); 
              GL11.glScaled(20.0f, 1.0f, 2000.0f);
              GL11.glCallList(planeList);

              // disable textures
              GL11.glDisable(GL11.GL_TEXTURE_2D);
              GL11.glPopAttrib();
          }
          GL11.glPopMatrix();
          
          
          GL11.glPushMatrix();
          {
              // rotate the moon sometimes spotted behind the player
              GL11.glRotatef((90.0f*-RotateMoon/1000.0f),0.0f,1.0f,0.0f);
              // Make the moon follow the player allowing for it to be spotted multiple times
              GL11.glTranslatef(5.0f,7.0f,personZ);
              drawSphere(Colour.BLUE, 0.3f);
          }
          GL11.glPopMatrix();
        
          new UnitCity().draw();
       
          GL11.glPushMatrix();
        	{
        		// position, rotate and draw the person
        		GL11.glTranslatef(personX,personY,personZ); //-10.0f
        		GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
        		person.draw();
        	}

        	GL11.glPushMatrix();
        	{
          if (personZ > 1000) 
        		{
        				Restart();
        		}
        	}
          GL11.glPopMatrix();
        
          GL11.glPushMatrix();
          {
        	  // Collison Based code.
        	  //Left Side
          if (personZ < 42.0 && personZ > 40.0 && personX > -1.6 && personX < 0.09 )
          	{
        	  	Restart();
          	}
          if (personZ < 127.0 && personZ > 125.0 && personX > -1.6 && personX < 0.09) 
          	{
        	  	Restart();
          	}
          if (personZ < 250.0 && personZ > 248.0 && personX > -1.6 && personX < 0.09 ) 
          	{
        	  Restart();
          	}
          if (personZ < 456.0 && personZ > 454.0 && personX > -1.6 && personX < 0.09 ) 
          	{
        	  Restart();
          	}

          //Middle
          if (personZ < 102.0 && personZ > 100.0 && personX > 0.09 && personX < 1.2 ) 
          	{
        	  Restart();
          	}
          if (personZ < 154.0 && personZ > 152.0 && personX > 0.09 && personX < 1.2 ) 
			{
        	  Restart();
			}
          if (personZ < 265.0 && personZ > 263.0 && personX > 0.09 && personX < 1.2 ) 
          	{
        	  Restart();
          	}
          if (personZ < 385.0 && personZ > 383.0 && personX > 0.09 && personX < 1.2 ) 
          	{
        	  Restart();
          	}

          //Right Side
          if (personZ <336 && personZ > 334.0 && personX > 1.2 && personX < 3) 
          	{
        	  Restart();
          	}
          if (personZ < 524 && personZ > 522.0 && personX > 1.2 && personX < 3) 
          	{
        	  Restart();
          	}
          if (personZ < 71 && personZ > 69.0 && personX > 1.2 && personX < 3) 
			{
        	  Restart();
			}
          if (personZ < 696 && personZ > 694.0 && personX > 1.2 && personX < 3) 
          {
        	  Restart();
          }
    }
    GL11.glPopMatrix();
    }
    
    protected void Restart() 
    {
        GL11.glPushMatrix();
        {
        	personZ = 0;
        	personY = 1;
        	CameraZ = 4;
            GL11.glTranslatef(personX,personY,personZ); 
        	person.draw();
        } 
        GL11.glPopMatrix();
    }
    
    protected void setSceneCamera()
    {
        // Sets the Camera up to begin just ahead of the player
    	// And to allow for it to keep moving at the same pace as the Player
        super.setSceneCamera();
        CameraZ = personZ + 18;
        GLU.gluLookAt(0.0f,5.0f,CameraZ,0.0f,0.0f,0.0f, 0.0f,1.0f,0.0f);
   }
     
    /** draws a coloured sphere*/
    private void drawSphere(Colour colour, float size)
    {
        colour.submit();
        new Sphere().draw(size, 20, 20);
    }
    
    private void drawUnitPlane()
    {
        Vertex v1 = new Vertex(-0.5f, 0.0f,-0.5f); // left,  back
        Vertex v2 = new Vertex( 0.5f, 0.0f,-0.5f); // right, back
        Vertex v3 = new Vertex( 0.5f, 0.0f, 0.5f); // right, front
        Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left,  front
        
        // draws the plane
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            
            GL11.glTexCoord2f(1.0f,0.0f);
            v3.submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v2.submit();
            
            GL11.glTexCoord2f(0.0f,1.0f);
            v1.submit();
        }
        	GL11.glEnd();        
        // Should allow the viewer to view the Plane on an axis.
        if(isViewingAxis())
        {
            GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                v4.submit();
                v3.submit();
                v2.submit();
                v1.submit();
            }
            GL11.glEnd();
            GL11.glPopAttrib();
        }
    }
    
    protected void cleanupScene()
    {
    }
  
}
