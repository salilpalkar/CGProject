package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class ProjectRainbow implements GLEventListener {

    public static void main(String[] args) throws IOException {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new ProjectRainbow());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }


    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        gl.glViewport(0, 0, 640, 480);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluOrtho2D(0,640,0,480);
    }
    

    private int x = 5;
    private int y = 300;
    int a,b,a1,b1,a2,b2;
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        drawCircle(gl);

        gl.glColor3f(1.0f, 1.0f, 1.0f );
        gl.glLineWidth(5.0f);
        if(x<=236){
            gl.glBegin(GL.GL_LINES);
                gl.glVertex2i(0, 300);
                gl.glVertex2i(x,y);
            gl.glEnd();
            x += 1;
            if(x==237)
            {
                a=x;b=y;
            }
            System.out.println(x);
        }
     //   if(x<=236)
           // x += 1;
        else{
            //gl.glLineWidth(1.0f);
            //white ray now touching the rain drop
            if(a<=415){
                
                gl.glBegin(GL.GL_LINES);
                gl.glVertex2i(0, 300);
                gl.glVertex2i(x,y);
                gl.glVertex2i(x,y);
                gl.glVertex2i(a, b);
                a+=2;b-=1;
               // System.out.println("a="+a+" b="+b);
                gl.glEnd();
                if(a==415)
                    a1=a;b1=b;
            }
            else{
                if(a1>=285){
                    gl.glBegin(GL.GL_LINES);
                    gl.glVertex2i(0, 300);
                    gl.glVertex2i(x,y);
                    gl.glVertex2i(x,y);
                    gl.glVertex2i(a, b);
                    gl.glVertex2i(a, b);
                    gl.glVertex2i(a1, b1);
                    a1-=2;b1-=1;
                    if(a1==283)
                    {
                        a2=a1;b2=b1;
                    }
                    System.out.println("a1="+a+" b1="+b);
                    gl.glEnd();
                }
                else{
                     gl.glBegin(GL.GL_LINES);
                    gl.glVertex2i(0, 300);
                    gl.glVertex2i(x,y);
                    gl.glVertex2i(x,y);
                    gl.glVertex2i(a, b);
                    gl.glVertex2i(a, b);
                    gl.glVertex2i(a1, b1);
                    gl.glVertex2i(a1, b1);
                    gl.glVertex2i(a2, b2);
                    a2-=1;b2-=1;
                    System.out.println("a1="+a1+" b1="+b1);
                    gl.glEnd();
                }
            }
            
                
            //Red ray
            
            //Orange ray
            
            //Yellow ray
            
            //Green ray
            
            //Blue ray
            
            //Indigo ray
            
            //Violet ray  
            
        }

       gl.glFlush();
        
    }
    
    private void drawCircle(GL gl){   
        gl.glPointSize(3.0f);
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        
            for(int i=0;i<360;i++){
            double theta = Math.toRadians(i);
            gl.glVertex2d(320+100*Math.sin(theta),240+100*Math.cos(theta));
        }
        
        gl.glEnd();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
}

