package gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import zaklad.*;

public class HlavneOkno extends JFrame{
	Liga liga;
	public HlavneOkno(int x, int y) {
		setLayout(null);
		setBounds(0, 0, x, y);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Tabulky");
		reset();
		
	}
	
	private void reset(){
		this.setVisible(false);
		this.setVisible(true);
		
	}
	
}
