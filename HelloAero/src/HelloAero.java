/*2018-05-19*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


 
public class HelloAero {
	static Icon logo;
	private static final String PROPERTY_PATH = "property_path";

	public HelloAero() throws ParserConfigurationException, SAXException, IOException, 
		ClassNotFoundException, InstantiationException, IllegalAccessException, 
		UnsupportedLookAndFeelException
	{
		System.out.println("Hello World, Hello AeroSoft!");
//		ImageIcon icon = createImageIcon("/images/aerosoftsys.gif", "company icon");
//		JFrame frame new LogoFrame(icon);
		
		JFrame frame = new JFrame("AeroSoftsys");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		JFrame.setDefaultLookAndFeelDecorated(true);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");		
		Preferences prefs = Preferences.userNodeForPackage(HelloAero.class);
//		prefs.put(PROPERTY_PATH, "LOCATION");
		String path = prefs.get(PROPERTY_PATH, "LOCATION");
		
		// read property.xml if exists
		if (!path.equals("LOCATION")) {
			File file = new File(path);
			if (file.isFile()) {
				FileManager.readPropertyFile(path);
			}			
		}
		System.out.println("propertyFilePath = " + path);	

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(" File ");
		JMenuItem properyMenuItem = new JMenuItem("Open Property File");
		properyMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String path = FileManager.chooseFile();
				System.out.println("file path : " + path);					
				//read and display property.xml
				File file = new File(path);
				if (file.isFile()) {						
					try {
						FileManager.readPropertyFile(path);
						FileManager.editPropertyFile(path);
						prefs.put(PROPERTY_PATH, path);
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					} catch (SAXException e) {							
						e.printStackTrace();
					} catch (IOException e) {							
						e.printStackTrace();
					}						
				}
			}
		});
		
		fileMenu.add(properyMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		JPanel panel = new JPanel();
		JLabel welcomeLabel = new JLabel("Welcome to AeroSoftsys!");
		panel.add(welcomeLabel);		
		frame.add(panel);			
		
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, 
		IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, 
		UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		new HelloAero();
	}
}
