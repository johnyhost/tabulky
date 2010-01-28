package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import zaklad.*;

public class HlavneOkno extends JFrame{
	Liga liga;
	File aktualnyAdresar = new File (".");
	String aktualnyNazovSuboru;
	public HlavneOkno(int x, int y) {
		setLayout(new BorderLayout());
		setBounds(0, 0, x, y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Tabulky");
		init();
		reset();
		
		
	}
	private void init(){
		this.setJMenuBar(vytvorHorneMenu());
		this.setContentPane(vytvorHlavnyPanel());
	}
	private JMenuBar vytvorHorneMenu(){
		JMenuBar horneMenu = new JMenuBar();
		//horneMenu.setBorder(BorderFactory.createLineBorder(Color.red));
		
		JMenu menuSubor = new JMenu("Subor");
		horneMenu.add(menuSubor);
		
		JMenuItem polozkaNovaLiga = new JMenuItem("Nova Liga");
		polozkaNovaLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liga = new Liga();
		    	}							
			}
		);
		menuSubor.add(polozkaNovaLiga);
		
		JMenuItem polozkaOtvorLigu = new JMenuItem("Otvor Ligu");
		polozkaOtvorLigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				try {
					fc = new JFileChooser(aktualnyAdresar.getCanonicalPath()+"\\Data");
					fc.addChoosableFileFilter(new XMLFilter());
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showOpenDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data();
			            liga = data.loadXML(file.getAbsolutePath());
			            aktualnyNazovSuboru=file.getName();
			        }
				} catch (IOException e1) {e1.printStackTrace();}
				
		        } 
		    								
			}
		);
		menuSubor.add(polozkaOtvorLigu);
		
		menuSubor.add(new JSeparator());
		
		JMenuItem polozkaUlozLigu = new JMenuItem("Uloz Ligu");
		polozkaUlozLigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(aktualnyNazovSuboru==null){
					JFileChooser fc;
					try {
						fc = new JFileChooser(aktualnyAdresar.getCanonicalPath()+"\\Data");
						fc.addChoosableFileFilter(new XMLFilter());
						fc.setAcceptAllFileFilterUsed(false);
						int returnVal = fc.showSaveDialog(null);

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file = fc.getSelectedFile();
				            Data data = new Data(liga);
				            if(file.getAbsolutePath().toLowerCase().contains(".xml")){
				            	data.saveXML(file.getAbsolutePath());
				            	aktualnyNazovSuboru=file.getName();
				            } else {
				            	data.saveXML(file.getAbsolutePath()+".xml");
				            	aktualnyNazovSuboru=file.getName()+".xml";
				            }
				           
				            
				        }
					} catch (IOException e1) {e1.printStackTrace();}
				} else {
				Data data = new Data(liga);
			    data.saveXML(aktualnyNazovSuboru);
				}
			}		    								
			}
		);
		menuSubor.add(polozkaUlozLigu);
		
		JMenuItem polozkaUlozLiguAko = new JMenuItem("Uloz Ligu ako");
		polozkaUlozLiguAko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				try {
					fc = new JFileChooser(aktualnyAdresar.getCanonicalPath()+"\\Data");
					fc.addChoosableFileFilter(new XMLFilter());
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showSaveDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data(liga);
			            if(file.getAbsolutePath().toLowerCase().contains(".xml")){
			            	data.saveXML(file.getAbsolutePath());
			            	aktualnyNazovSuboru=file.getName();
			            } else {
			            	data.saveXML(file.getAbsolutePath()+".xml");
			            	aktualnyNazovSuboru=file.getName()+".xml";
			            }
			           
			            
			        }
				} catch (IOException e1) {e1.printStackTrace();}
				
		        } 
		    								
			}
		);
		menuSubor.add(polozkaUlozLiguAko);
		
		menuSubor.add(new JSeparator());
		
		JMenu menuExport = new JMenu("Export");
		
		JMenuItem polozkaExportHtmlTeamy = new JMenuItem("Tabulka do HTML");
		polozkaExportHtmlTeamy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				try {
					fc = new JFileChooser(aktualnyAdresar.getCanonicalPath()+"\\Data");
					
					int returnVal = fc.showSaveDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data(liga);
			            if(file.getAbsolutePath().toLowerCase().contains(".htm")){   
			            	data.exportTeamyHTML(file.getAbsolutePath());
			            } else {
			            	data.exportTeamyHTML(file.getAbsolutePath()+".html");
			            }
			           
			            
			        }
				} catch (IOException e1) {e1.printStackTrace();}
				
		        } 
		    								
			}
		);
		menuExport.add(polozkaExportHtmlTeamy);
		
		JMenuItem polozkaExportHtmlHraci = new JMenuItem("Statistiky do HTML");
		polozkaExportHtmlHraci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				try {
					fc = new JFileChooser(aktualnyAdresar.getCanonicalPath()+"\\Data");
					int returnVal = fc.showSaveDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data(liga);
			            if(file.getAbsolutePath().toLowerCase().contains(".htm")){   
			            	data.exportHraciHTML(file.getAbsolutePath());
			            } else {
			            	data.exportHraciHTML(file.getAbsolutePath()+".html");
			            }
			           
			            
			        }
				} catch (IOException e1) {e1.printStackTrace();}
				
		        } 
		    								
			}
		);
		menuExport.add(polozkaExportHtmlHraci);
		
		JMenuItem polozkaExportHtmlZapasy = new JMenuItem("Zapasy do HTML");
		polozkaExportHtmlZapasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				try {
					fc = new JFileChooser(aktualnyAdresar.getCanonicalPath()+"\\Data");
					int returnVal = fc.showSaveDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data(liga);
			            if(file.getAbsolutePath().toLowerCase().contains(".htm")){   
			            	data.exportZapasyHTML(file.getAbsolutePath());
			            } else {
			            	data.exportZapasyHTML(file.getAbsolutePath()+".html");
			            }
			           
			            
			        }
				} catch (IOException e1) {e1.printStackTrace();}
				
		        } 
		    								
			}
		);
		menuExport.add(polozkaExportHtmlZapasy);
		
		JMenuItem polozkaExportSQL = new JMenuItem("Liga do SQL");
		polozkaExportSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				try {
					fc = new JFileChooser(aktualnyAdresar.getCanonicalPath()+"\\Data");
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = fc.showSaveDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data(liga);			      
			            
			            data.exportSQL(file.getAbsolutePath()+"\\"+aktualnyNazovSuboru.substring(0, aktualnyNazovSuboru.indexOf(".xml")));
			            	
			            
			        }
				} catch (IOException e1) {e1.printStackTrace();}
				
		        } 
		    								
			}
		);
		menuExport.add(polozkaExportSQL);
		
		menuSubor.add(menuExport);
		
		menuSubor.add(new JSeparator());
		
		JMenuItem polozkaKoniec = new JMenuItem("Koniec");
		polozkaKoniec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int odpoved;
	    		odpoved = JOptionPane.showConfirmDialog(null, "Chces ukoncit program?","Okno",0);
	    		if(odpoved==0)System.exit(0);
		    	}							
			}
		);
		menuSubor.add(polozkaKoniec);
		
		horneMenu.add(menuSubor);
		
		JMenu menuNastroje = new JMenu("Nastroje");
		
		JMenuItem polozkaSpravaHracovATeamov = new JMenuItem("Sprava hracov a teamov");
		polozkaSpravaHracovATeamov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int odpoved;
	    		odpoved = JOptionPane.showConfirmDialog(null, "Chces ukoncit program?","Okno",0);
	    		if(odpoved==0)System.exit(0);
		    	}							
			}
		);
		
		menuNastroje.add(polozkaSpravaHracovATeamov);
		
		horneMenu.add(menuNastroje);
		
		horneMenu.setVisible(true);
		return horneMenu;
	}
	private JPanel vytvorHlavnyPanel(){
		JPanel hlavnyPanel = new JPanel();
		//hlavnyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		hlavnyPanel.setBounds(this.getBounds());
		setPreferredSize(new Dimension(800, 600));
		
		return hlavnyPanel;
	}
	
	private void refresh(){
		
	}
	private void reset(){
		this.setVisible(false);
		this.pack();
		this.setVisible(true);		
	}
	
}
