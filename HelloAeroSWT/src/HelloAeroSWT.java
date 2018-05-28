/* 2018-05-19 */


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class HelloAeroSWT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Hello World!, Hello AeroSoft");		
		shell.setLayout(new FillLayout());
		
		Label label = new Label(shell, SWT.BORDER);
        label.setText("This is a label:");
        
        Text text = new Text(shell, SWT.NONE);
        text.setText("This is the text in the text widget");
		
		Button button =  new Button(shell, SWT.PUSH);
		button.setText("push button");
		//register listener for the selection event
		button.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		        System.out.println("Called!");
		    }
		});
		
		shell.open();		
		// run the event loop as long as the window is open		
		while (!shell.isDisposed()) {
			// read the next OS event queue and transfer it to a SWT event
			if (!display.readAndDispatch()) {
				// if there are currently no other OS event to process
			    // sleep until the next OS event is available
				display.sleep();
			}			
		}
		// disposes all associated windows and their components
		display.dispose();
	}

}
