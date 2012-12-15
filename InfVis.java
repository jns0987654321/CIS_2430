// InfVis.java
//
// This program downloads a file, counts the number of occurrences of 
// the words 'red, 'blue', and 'green', and makes and displays a colour 
// based on these values.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.beans.*;

public class InfVis extends JFrame implements ActionListener, PropertyChangeListener {

	private JMenu fileMenu;
	private JMenuItem loadItem;
	private JMenuBar bar;

	private JProgressBar progressBar;
	
	private Task task;

	private String text;
	private boolean ready;

	private int rs;
	private int gs;
	private int bs;
	private int tot;

	class Task extends SwingWorker<Void, Void> {

	@Override
	public Void doInBackground() {
            
		setProgress(0);
		loadText();
		setProgress(100);
            	return null;
        }

        @Override
        public void done() {
		progressBar.setIndeterminate(false);
            	Toolkit.getDefaultToolkit().beep();
            	setCursor(null); //turn off the wait cursor
        }
    }

	public InfVis() {
		super("Information Visualization");				//provides text for the title bar
		
		JPanel jp = new JPanel();

		//Add components here
		fileMenu = new JMenu("File");
		loadItem = new JMenuItem("Load Text");
		loadItem.addActionListener(this);
		fileMenu.add(loadItem);

		bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(false);

		jp.add(progressBar);
		setContentPane(jp);

		setSize(250,250);
		setVisible(true);

		// other initialization
		ready = false;
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
    	}

        public void actionPerformed(ActionEvent evt) {

		if(evt.getSource() == loadItem) {
			
			progressBar.setIndeterminate(true);
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		        task = new Task();
		        task.addPropertyChangeListener(this);
		        task.execute();
		}
	}

	private void loadText() {
		
		URL u;
		InputStream is = null;
		BufferedReader dis;
		String s;
		
		// reset text
		text = "";
		tot=rs=gs=bs=0;
		
		try {
			
			//u = new URL("http://www.gutenberg.org/files/2554/2554.txt");
			u = new URL("http://en.wikipedia.org/wiki/Special:Random");

			System.out.println("Downloading page...");
			
			is = u.openStream();
			
			dis = new BufferedReader(new InputStreamReader(is));
			
			while((s=dis.readLine()) != null) {
				text += s;
			}

			System.out.println("Done.");

			computeColour();

			ready=true;
			repaint();
		}
		catch(MalformedURLException mue) {
			
			System.out.println("Internet problem...");
		}
		catch(IOException ioe) {
			
			System.out.println("Internet problem...");
		}
		finally {
			
			try {
				is.close();
			}
			catch (IOException ioe) {
				// meh
			}
		}
	}

	private void computeColour() {

		System.out.println("Computing colour...");

		String[] words = text.split(" ");

		for(int i=0; i<words.length; i++) {

			if(words[i].equalsIgnoreCase("red")) {
				rs++;
				tot++;
			}
			else if(words[i].equalsIgnoreCase("green")) {
				gs++;
				tot++;
			}
			else if(words[i].equalsIgnoreCase("blue")) {
				bs++;
				tot++;
			}
		}
	}

	public void paint(Graphics g) {

		super.paint(g);

		if(ready) {

			int xs = getBounds().width;
			int ys = getBounds().height;

			float R = (float)rs / (float)tot;
			float G = (float)bs / (float)tot;
			float B = (float)gs / (float)tot;			

			Color c = new Color(R,G,B);

			g.setColor(c);
			g.fillOval(xs/4, xs/4+25, xs/2, xs/2);

			//g.setColor(Color.WHITE);
			//g.drawLine(0, 0, xs, ys);

		}
	}


	public static void main(String args[]) {
		InfVis iv = new InfVis();				
									
		iv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
