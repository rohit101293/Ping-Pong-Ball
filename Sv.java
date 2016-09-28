import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*
<applet code = "Sv" width=400 height=400>
</applet>
*/
public class Sv extends Applet implements ActionListener,Runnable
{
String msg="SAVE THE BALL...";
String msg1="Click start to begin";
Thread t;
public void init()
{
Button b1=new Button("Start");
Button b2=new Button("Stop");
setLayout(null);
setBackground(Color.blue);
b1.setBounds(145,250,100,30);
b2.setBounds(145,300,100,30);
add(b1);
add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
t=new Thread(this);
t.start();
}
public void run()
{
while(true)
{
try
{
repaint();
Thread.sleep(250);
}
catch(InterruptedException e)
{
}
}
}
public void paint(Graphics g)
{
Color c=new Color(200,10,10);
g.setColor(c);
Font f=new Font("TimesNewRoman",Font.BOLD,26);
g.setFont(f);
g.drawString(msg+" ",105,50);
msg=msg.substring(1)+msg.charAt(0);
Color m=new Color(10);
g.setColor(m);
Font j=new Font("TimesNewRoman",Font.BOLD,18);
g.setFont(j);
g.drawString(msg1,120,120);
}
public void actionPerformed(ActionEvent e)
{
}
}