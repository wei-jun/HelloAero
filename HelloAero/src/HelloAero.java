/*2018-05-19*/

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

 
public class HelloAero {
	static Icon logo;
	private static final String PROPERTY_PATH = "property_path";
	private static String zipFile;
	private static String dir;

	public HelloAero() throws ParserConfigurationException, SAXException, IOException, 
		ClassNotFoundException, InstantiationException, IllegalAccessException, 
		UnsupportedLookAndFeelException
	{
		System.out.println("Hello World, Hello AeroSoft!");
//		ImageIcon icon = createImageIcon("/images/aerosoftsys.gif", "company icon");
//		JFrame frame new LogoFrame(icon);
		
//		Font font = new Font("Courier", Font.BOLD,14);
		Font font = new Font("Arial", 0, 16);
		
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
		
		fileMenu.add(properyMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		JPanel panel = new JPanel();
		JLabel welcomeLabel = new JLabel("Welcome to AeroSoftsys!");
		welcomeLabel.setFont(font);
		
		JLabel zipFileLabel = new JLabel("file to unzip:");
		JTextField zipFileTextField = new JTextField();
		JButton zipFileButton = new JButton("browse");
		
		JLabel dirLabel = new JLabel("dir to zip:");
		JTextField dirTextField = new JTextField();
		JButton dirButton = new JButton("browse");
		
		JLabel destLabel = new JLabel("dest folder:");
		JTextField destTextField = new JTextField();
		JButton destButton = new JButton("browse");
		
		JButton zipButton = new JButton("zip");
		JButton unzipButton = new JButton("unzip");
		
		JLabel delLabel = new JLabel("delete all files and folders in the following folder:");
		JTextField delTextField = new JTextField();
		JButton delButton = new JButton("browse");		
		JButton deleteButton = new JButton("delete");
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(welcomeLabel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(zipFileLabel)								
								.addComponent(zipFileTextField)								
								.addComponent(zipFileButton))
						.addGroup(layout.createSequentialGroup()
								.addComponent(dirLabel)								
								.addComponent(dirTextField)								
								.addComponent(dirButton))
						.addGroup(layout.createSequentialGroup()
								.addComponent(destLabel)								
								.addComponent(destTextField)								
								.addComponent(destButton))
						.addGroup(layout.createSequentialGroup()
							.addComponent(zipButton)
							.addGap(40)
							.addComponent(unzipButton))
						.addComponent(delLabel)
						.addGroup(layout.createSequentialGroup()										
								.addComponent(delTextField)								
								.addComponent(delButton))
						.addComponent(deleteButton)
				)					
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(welcomeLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(zipFileLabel)
						.addComponent(zipFileTextField)
						.addComponent(zipFileButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(dirLabel)								
						.addComponent(dirTextField)								
						.addComponent(dirButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(destLabel)								
						.addComponent(destTextField)								
						.addComponent(destButton))
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(zipButton)
						.addComponent(unzipButton))
				.addGap(15)
				.addComponent(delLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(delTextField)								
						.addComponent(delButton))
				.addComponent(deleteButton)
		);
		
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
		
		zipFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				zipFile = FileManager.chooseFile();
				zipFileTextField.setText(zipFile);
//				zipFileTextField.setEnabled(false);
			}			
		});
		
		
		dirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dir = FileManager.chooseDir();
				dirTextField.setText(dir);
//				dirTextField.setEnabled(false);
			}			
		});
		
		destButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dir = FileManager.chooseDir();
				destTextField.setText(dir);
//				destTextField.setEnabled(false);
			}			
		});
		
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dir = FileManager.chooseFileOrDir();
				delTextField.setText(dir);
//				destTextField.setEnabled(false);
			}			
		});
		
		zipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dir = dirTextField.getText();
				String destDir = destTextField.getText();
				try {
					ZipUtils.zipFiles(dir, destDir);
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}			
		});
		
		unzipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String zipFile = zipFileTextField.getText();
				String destDir = destTextField.getText();
				ZipUtils.unzipFile(zipFile, destDir);	
			}			
		});
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String delDir = delTextField.getText();
				FileManager.deleteFiles(delDir);
				delTextField.setText("");
			}			
		});
		
		
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
