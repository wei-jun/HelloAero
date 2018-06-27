/*2018-06-10 */

import java.awt.Graphics;
import javax.swing.JApplet;

public class WelcomeApplet extends JApplet{
	//draw text on applet's background
	public void paint(Graphics g) {
		//call supper class version of metod paint
		super.paint(g);
		//draw a String at x-coordinate 25 and y-coordinate 25
		g.drawString("Welcom to Java Programming!", 25, 25);
	}
}
