package coursework190141121;
import org.lwjgl.opengl.GL11;

import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * This class was inspired and used code from Person.java in Example.AnimatedPerson.Person
 * most of this class was taken directly from that code to be used within the project
 * however edits have been made with new commenting, formatting, removal of some code
 * and changes to names and drawHead.
 * 
 * @authors of Example.AnimatedPerson.Person code: Anthony Jones and Dan Cornford
 * Code changes produced by: Danica Davies
 */
public class Person
{
    /** The person's height, Width & Depth */
    private float height;
    private float width;
    private float depth;
    
    /** The person's unit height, HeadHeight, TorsoHeight and Waist Height*/
    private float unitHeight;
    private float headHeight;
    private float torsoHeight;
    private float waistHeight;

    /** The person's unit width, head width, torsoWidth and Waist width */
    private float unitWidth;
    private float headWidth;
    private float torsoWidth;
    private float waistWidth;

    /** The person's unit depth, head depth, torso depth and Waist Depth*/
    private float unitDepth;
    private float headDepth;
    private float torsoDepth;
    private float waistDepth;

    /** The person's Limbs */
    private Limbs leftArm;
    private Limbs rightArm;
    private Limbs leftLeg;
    private Limbs rightLeg;

    /**
     * Constructs a person of a given height, using default values for the width and depth
     */
    public Person(float height)
    {   init(height,height*0.6f,height*0.5f);
    }
    /**
     * Constructs a person of a given height, width and depth
     * @param height the person's height
     * @param width the person's width
     * @param depth the person's depth
     */
    public Person(float height, float width, float depth)
    {   init(height,width,depth);
    }
    // method used to set the person's attributes
    private void init(float height, float width, float depth)
    {
        this.height = height;
        this.width  = width;
        this.depth  = depth;
        
        unitHeight  = height / 5;
        headHeight  = 1f * unitHeight;
        torsoHeight = 1f * unitHeight;
        waistHeight = 0.3f * unitHeight;

        unitWidth   = width;
        headWidth   = unitWidth;
        torsoWidth  = 0.9f * unitWidth;
        waistWidth  = 0.85f * unitWidth;

        unitDepth   = depth;
        headDepth   = unitDepth;
        torsoDepth  = unitDepth;
        waistDepth  = unitDepth;
        
        leftArm = new Limbs("Arms",Limbs.LEFT_SIDE, unitHeight,unitWidth,unitDepth);
        rightArm    = new Limbs("Arms",Limbs.RIGHT_SIDE,unitHeight,unitWidth,unitDepth);
        
        leftLeg     = new Limbs("Legs",Limbs.LEFT_SIDE, unitHeight,unitWidth,unitDepth);
        rightLeg    = new Limbs("Legs",Limbs.RIGHT_SIDE,unitHeight,unitWidth,unitDepth);
    }
    
    public float getHeight()
    {   return height;
    }

    public float getWidth()
    {   return width;
    }

    public float getDepth()
    {   return depth;
    }

    /** Return the persons Limbs */
    public Limbs getLeftArm()
    {   return leftArm;
    }
    public Limbs getRightArm()
    {   return rightArm;
    }
    public Limbs getLeftLeg()
    {   return leftLeg;
    }
    public Limbs getRightLeg()
    {   return rightLeg;
    }

    public void draw()
    {
        // draw the torso
        drawTorso();
        
        // draw the head
        GL11.glPushMatrix();
        	{
        		GL11.glTranslatef(0.0f, 0.1f + (torsoHeight + headHeight)/2, 0.0f);
        		drawHead();
        	}
        GL11.glPopMatrix();
        
        // draw the left arm
        GL11.glPushMatrix();
        	{
        		GL11.glTranslatef((-torsoWidth/2)-leftArm.getArmRadius(), (torsoHeight-leftArm.getArmRadius())/2, 0.0f);
        		leftArm.drawArms();
        	}
        GL11.glPopMatrix();

        // draw the right arm
        GL11.glPushMatrix();
        	{
            GL11.glTranslatef((torsoWidth/2)+rightArm.getArmRadius(), (torsoHeight-rightArm.getArmRadius())/2, 0.0f);
            rightArm.drawArms();
        	}
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        	{
        		GL11.glTranslatef(0.0f, (-torsoHeight - waistHeight)/2, 0.0f);
        		drawWaist();
            
            // draw the left leg
        GL11.glPushMatrix();
            {
                GL11.glTranslatef((-waistWidth)/2 + leftLeg.getLegRadius(), (-waistHeight)/2 - leftLeg.getLegRadius(), 0.0f);
                leftLeg.drawLegs();
            }
            GL11.glPopMatrix();
            
            // draw the right leg
            GL11.glPushMatrix();
            {
                GL11.glTranslatef((waistWidth)/2 - leftLeg.getLegRadius(), (-waistHeight)/2 - leftLeg.getLegRadius(), 0.0f);
                rightLeg.drawLegs();
            }
            GL11.glPopMatrix();

        }
        GL11.glPopMatrix();
    }

    private void drawHead()
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(headWidth,headHeight,headDepth);
            // the vertices For the Unique headshape
            Vertex v1 = new Vertex(-0.6f, -0.5f,  0.5f);
            Vertex v2 = new Vertex(-0.7f,  0.6f,  0.5f);
            Vertex v3 = new Vertex( 0.7f,  0.6f,  0.5f);
            Vertex v4 = new Vertex( 0.6f, -0.5f,  0.5f);
            Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
            Vertex v6 = new Vertex(-0.7f,  0.6f, -0.5f);
            Vertex v7 = new Vertex( 0.7f,  0.6f, -0.5f);
            Vertex v8 = new Vertex( 0.5f, -0.5f, -0.5f);

            // draw the near face:
            GL11.glBegin(GL11.GL_POLYGON);
            {
                new Normal(v3.toVector(),v2.toVector(),v1.toVector(),v4.toVector()).submit();
                v3.submit();
                v2.submit();
                v1.submit();
                v4.submit();
            }
            GL11.glEnd();

            // draw the left face:
            GL11.glBegin(GL11.GL_POLYGON);
            {
                new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
                v2.submit();
                v6.submit();
                v5.submit();
                v1.submit();
            }
            GL11.glEnd();

            // draw the right face:
            GL11.glBegin(GL11.GL_POLYGON);
            {
                new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
                
                v7.submit();
                v3.submit();
                v4.submit();
                v8.submit();
            }
            GL11.glEnd();

            // draw the top face:
            GL11.glBegin(GL11.GL_POLYGON);
            {
                new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
                
                v7.submit();
                v6.submit();
                v2.submit();
                v3.submit();
            }
            GL11.glEnd();

            // draw the bottom face:
            GL11.glBegin(GL11.GL_POLYGON);
            {
                new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
                
                v4.submit();
                v1.submit();
                v5.submit();
                v8.submit();
            }
            GL11.glEnd();

            // draw the far face:
            GL11.glBegin(GL11.GL_POLYGON);
            {
                new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
                
                v6.submit();
                v7.submit();
                v8.submit();
                v5.submit();
            }
            GL11.glEnd();
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
    
    private void drawTorso()
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(torsoWidth,torsoHeight,torsoDepth);
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
    private void drawWaist()
    {
        GL11.glPushMatrix();
        {
            GL11.glScalef(waistWidth,waistHeight,waistDepth);
            new UnitCube().draw();
        }
        GL11.glPopMatrix();
    }
}
