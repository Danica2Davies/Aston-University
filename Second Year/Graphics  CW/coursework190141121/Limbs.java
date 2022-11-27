package coursework190141121;
import org.lwjgl.opengl.GL11;

/**
 * Inspiration taken from AnimatedPerson Arms & Legs with code from PersonParts as well
 * Combination of a number of classes in AnimatedPersonExample and also parts of my own code.
 */
public class Limbs
{
	//Basic Side (Left/Right) 
	private int side;
    
    //Basic Arms variables 
    private float ArmLength;
    private float ArmWidth;
    private float ArmDepth;
    private float ArmRadius;
    private float ArmAngleUD = 0.0f;
    private float ArmAngleLR = 0.0f;
    
  //Basic Arms variables 
    private float LegLength;
    private float LegWidth;
    private float LegDepth;
    private float LegRadius;
    private float LegAngleUD 	= 0.0f;
    private float LegAngleLR 	= 0.0f;
    
    //Says what side the person is on
    public static final int RIGHT_SIDE = 0;
    public static final int LEFT_SIDE  = 1;
    
    /**
     * Creates a Limb dependent on what 1 is called */
    public Limbs(String type, int side, float unitLength, float unitWidth, float unitDepth)
    {
    	if (type == "Arms") {
        this.side = side;
        ArmLength = 0.8f * unitLength;
        ArmWidth  = unitWidth / 5;
        ArmDepth  = unitDepth / 2;
        ArmRadius = (ArmDepth+ArmWidth)/4;
    }
    	if (type == "Legs") {
            this.side = side;
            
            LegLength = unitLength;
            LegWidth  = unitWidth / 3;
            LegDepth  = unitDepth / 2;
            
            LegRadius = (LegDepth+LegWidth)/4;
        }
    }
    
	
     /** Code taken directly from Example.AnimatedPerson.Person
      * Basic easy ability to draw limbs*/
    protected final void drawPart(float width, float length, float depth)
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(width,length,depth);
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
    
    /** Get and Set methods for Arm Angles and radius */ 
    public float getArmRadius()
    {   
    	return ArmRadius;
    }

    public float getArmAngleUD()
    {   
    	return ArmAngleUD;
    }
    
    public void setArmAngleUD(float ArmAngleUD)
    {   
    	this.ArmAngleUD = ArmAngleUD;
    }
    public float getArmAngleLR()
    {   
    	return ArmAngleLR;
    }

    public void setArmAngleLR(float ArmAngleLR)
    {   
    	this.ArmAngleLR = ArmAngleLR;
    }

    /** Basic draw Method to actually draw the arm */
    public void drawArms()
    {
        GL11.glPushMatrix();
        {
            GL11.glRotatef(ArmAngleUD, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(ArmAngleLR*(side==LEFT_SIDE?-1.0f:1.0f), 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(0.0f, (-ArmLength-ArmRadius)/2, 0.0f);
            drawArm();
        }
        GL11.glPopMatrix();
    }
    
    private void drawArm()
    {   
    	drawPart(ArmWidth, ArmLength, ArmDepth);
    }
    
    
    /** Get and Set methods for Leg Angles and radius */ 
    public float getLegRadius()
    {   
    	return LegRadius;
    }
    public float getLegAngleUD()
    {   return LegAngleUD;
    }
 
    public void setLegAngleUD(float LegAngleUD)
    {   
    	this.LegAngleUD = LegAngleUD;
    }

    public float getLegAngleLR()
    {   
    	return LegAngleLR;
    }
    
    public void setLegAngleLR(float LegAngleLR)
    {   
    	this.LegAngleLR = LegAngleLR;
    }
    
    /**Draws the actual Legs */ 
    public void drawLegs()
    {
        GL11.glPushMatrix();
        {
            GL11.glRotatef(LegAngleUD, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(LegAngleLR*(side==LEFT_SIDE?-1.0f:1.0f), 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(0.0f, (-LegLength-LegRadius)/2, 0.0f);
            drawLeg();
            GL11.glTranslatef(0.0f, (-LegLength-LegRadius)/2, 0.0f);
        }
        GL11.glPopMatrix();
    }
    
    private void drawLeg()
    {   
    	drawPart(LegWidth, LegLength, LegDepth);
    }

}
