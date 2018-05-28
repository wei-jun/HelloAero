package com.example.swt.widgets;

/*
 * 2018-05-27
 * http://www.vogella.com/tutorials/SWT/article.html#available-widgets-in-the-swt-library
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/* 2018-05-27 */

public class FirstSWTApplication {
	
	public FirstSWTApplication() {
		Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Hello World!");

        // the layout manager handle the layout
        // of the widgets in the container
        shell.setLayout(new FillLayout());

        //TODO add some widgets to the Shell
        Label label = new Label(shell, SWT.BORDER);
        label.setText("This is a label:");
        label.setToolTipText("This is the text in the text widget");
        
        Text text = new Text(shell, SWT.NONE);
        text.setText("This is the text in the text widget");
        text.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        text.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
        
        Button button =  new Button(shell, SWT.PUSH);
        button.setText("push button");
        
        Label label2 = new Label(shell, SWT.NONE);
        Font font = new Font(label2.getDisplay(), new FontData("Mono", 10, SWT.ITALIC));
        label2.setFont(font);
        label2.setText("label2's text");

        //register listener for the selection event
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Called!");
            }
        });
        
        //set widgets size to their preferred size
        text.pack();
        label.pack();
        shell.setSize(800, 500);
        
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
	}

	public static void main(String[] args) {
		new FirstSWTApplication();
	}
}
