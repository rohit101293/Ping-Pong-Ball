import java.applet.*;
import java.awt.event.*;
import java.awt.*;
/*
<applet code =pongMain width =500 height=320>
</applet>
*/
public class pongMain extends Applet implements MouseMotionListener,Runnable
{
     Ball ball;
     PaddleLeft pLeft;
     PaddleRight pRight;
     Font newFont = new Font("sansserif", Font.BOLD, 20);
	 Font newFont1 = new Font("TimesNewRoman",Font.BOLD,15);
     Graphics bufferGraphics;
     Image offscreen;
     final int WIDTH = 500, HEIGHT = 320;
     long currentTime;
	 Thread t=null;
     public void init() 
     {
	      t=new Thread(this);
          setSize(500, 320);
          ball = new Ball();
          pLeft = new PaddleLeft();
          pRight = new PaddleRight(ball.getY() - 35);
          addMouseMotionListener(this);
          setBackground(Color.green);
          offscreen = createImage(WIDTH,HEIGHT);
          bufferGraphics = offscreen.getGraphics();
		  t.start();
     }
     public void run()
	 {
         currentTime = System.currentTimeMillis();
		 try
		 {
         while(pRight.getScore() < 10)
		 {
		 ball.move();
         pRight.setPos(ball.getY() - 35);
         checkCollision();
         repaint();
		 Thread.sleep(15);
         }
		 }
		 catch(InterruptedException e)
		 {
		 }
         currentTime = System.currentTimeMillis() - currentTime;
         repaint();
     }
     public void stop()
	 {
     }
      public void paint(Graphics g) 
     {
          bufferGraphics.clearRect(0,0,WIDTH,HEIGHT);
          bufferGraphics.setColor(Color.blue);
          bufferGraphics.fillRect(pLeft.XPOS,pLeft.getPos(),10,70);
          bufferGraphics.fillRect(pRight.XPOS, pRight.getPos(), 10, 70);
		  bufferGraphics.setColor(Color.pink);
		  bufferGraphics.fillRect(0, 300 , 500, 20);
		  bufferGraphics.setColor(Color.black);
          bufferGraphics.setFont(newFont1);
		  bufferGraphics.drawString("See how many seconds you can last!Use the mouse to play!!", 20, 315);
          bufferGraphics.setColor(Color.white);
          bufferGraphics.setFont(newFont);
          bufferGraphics.drawString("Futile", 150, 15);
          bufferGraphics.drawString(""+ pRight.getScore(),300,15);
          bufferGraphics.fillRect(240,0,20,300);
          if(pRight.getScore() == 10)
		  {
              bufferGraphics.drawString("You Lasted: " + (currentTime/ 1000) + "sec.", 40, 150);
          }
          bufferGraphics.setColor(Color.red);
          bufferGraphics.fillRect(ball.getX(),ball.getY(),10,10);
          g.drawImage(offscreen,0,0,this);
     }
     public void update(Graphics g)
     {
          paint(g);
     }
     public void mouseMoved(MouseEvent evt) 
     {
          pLeft.setPos(evt.getY()- 35);
     }
     public void mouseDragged(MouseEvent evt) 
     {
     } 
     public void checkCollision()
	 {
        if(ball.getY() == 0 || ball.getY() == 290)
		{
            ball.dy = (ball.dy * -1);
        }
        if((ball.getX() == 40) && hitPaddle())
		{
            ball.dx = (ball.dx * -1);
        }
        if(ball.getX() == 460)
		{
            ball.dx = (ball.dx * -1);
        }
        if(ball.getX() == 0)
		{
            pRight.setScore(pRight.getScore() + 1);
            ball.reset();
        }
     }
     public boolean hitPaddle()
	 {
         boolean didHit = false;
         if((pLeft.getPos() - 10) <= ball.getY() && (pLeft.getPos() + 70) > ball.getY())
		 {
             didHit = true;
         }
         return didHit;
     }
 }

