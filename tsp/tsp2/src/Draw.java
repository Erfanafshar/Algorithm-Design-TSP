import java.awt.*;
import java.awt.event.*;

public class Draw extends Frame{
    
    int[] x;
    int[] y;
    int num;
    Point [] pnt;
    
    public Draw(Point [] result,int number){
        super("Tsp");
        setSize(1200,700);
        setLocation(60,20);
        setVisible(true);
        
        num = number;
        pnt = result;
        
        x = new int[number + 1];
        y = new int[number + 1];
        
        for(int i = 0; i < number + 1; i++){
            x[i] = 3 * result[i].getX() + 600;
            y[i] = -3 * result[i].getY() + 350;
        }
        
        addWindowListener(new WindowAdapter()
       {public void windowClosing(WindowEvent e)
          {dispose(); System.exit(0);}
       }
    );
    }
    
    public void paint(Graphics g) {
         
         Graphics2D g2d = (Graphics2D)g;
         g2d.setColor(Color.black);
         g2d.drawLine(600, 0, 600, 600);
         g2d.drawLine(300, 350, 900, 350);

         g2d.setColor(Color.blue);
         g2d.drawPolyline(x, y, num + 1);
         
         
         g2d.setColor(Color.red);
         for (int i = 0; i < num; i++){
             g2d.drawString("(" + pnt[i].getX() + "," + pnt[i].getY() + ")",x[i],y[i]);
             g2d.drawString( (i + 1) + "" ,x[i] + 20,y[i] - 10);
         }
    }
}

