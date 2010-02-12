package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import porovnavanie.BrankarByStats;
import porovnavanie.HracByStats;
import porovnavanie.TeamByStats;

import zaklad.*;

// trieda obsahujuca vsetky prvky GUI

public class HlavneOkno extends JFrame{
	Liga liga;
	String aktualnyAdresar;
	String aktualnyNazovSuboru;
	int obsahHlavnejCasti;
	int idUpravovanehoTeamu;
	int idUpravovanehoZapasu;
	int cisloKola;
	public HlavneOkno(int x, int y) {
		setLayout(new BorderLayout());
		setBounds(0, 0, x, y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Tabulky");
		obsahHlavnejCasti=0;
		try {
			aktualnyAdresar=new File (".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
		reset();
		
		
	}
	private void init(){
		this.setPreferredSize(new Dimension(800, 600));
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
				String response = JOptionPane.showInputDialog(null,
						  "Zadajte nazov ligy",
						  "Nazov ligy",
						  JOptionPane.QUESTION_MESSAGE); 
				if(response!=null && response.equals("")==false){
					liga = new Liga();
					liga.setNazovLigy(response);
					reset();
				}
		    }							
		}
		);
		menuSubor.add(polozkaNovaLiga);
		
		JMenuItem polozkaOtvorLigu = new JMenuItem("Otvor Ligu");
		polozkaOtvorLigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				try {
					fc = new JFileChooser(aktualnyAdresar);
					fc.addChoosableFileFilter(new XMLFilter());
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showOpenDialog(null);
					
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data();
			            liga = data.loadXML(file.getAbsolutePath());
			            aktualnyAdresar = file.getCanonicalPath().replaceFirst(file.getName(), "");
			            aktualnyNazovSuboru=file.getName();
			            reset();
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
					fc = new JFileChooser(aktualnyAdresar);
					fc.addChoosableFileFilter(new XMLFilter());
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showSaveDialog(null);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
					    File file = fc.getSelectedFile();
					    Data data = new Data(liga);
					    try {
							aktualnyAdresar = file.getCanonicalPath().replaceFirst(file.getName(), "");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					    if(file.getAbsolutePath().toLowerCase().contains(".xml")){
					    	data.saveXML(file.getAbsolutePath());
					    	aktualnyNazovSuboru=file.getName();
					    } else {
					    	data.saveXML(file.getAbsolutePath()+".xml");
					    	aktualnyNazovSuboru=file.getName()+".xml";
					    }
					   
					    
					}
				} else {
				Data data = new Data(liga);
			    data.saveXML(aktualnyAdresar+"\\"+aktualnyNazovSuboru);
				}
			}		    								
			}
		);
		menuSubor.add(polozkaUlozLigu);
		
		JMenuItem polozkaUlozLiguAko = new JMenuItem("Uloz Ligu ako");
		polozkaUlozLiguAko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				fc = new JFileChooser(aktualnyAdresar);
				fc.addChoosableFileFilter(new XMLFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showSaveDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    File file = fc.getSelectedFile();
				    Data data = new Data(liga);
				    try {
						aktualnyAdresar = file.getCanonicalPath().replaceFirst(file.getName(), "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    if(file.getAbsolutePath().toLowerCase().contains(".xml")){
				    	data.saveXML(file.getAbsolutePath());
				    	aktualnyNazovSuboru=file.getName();
				    } else {
				    	data.saveXML(file.getAbsolutePath()+".xml");
				    	aktualnyNazovSuboru=file.getName()+".xml";
				    }
				   
				    
				}
				
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
				fc = new JFileChooser(aktualnyAdresar);
				
				int returnVal = fc.showSaveDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    File file = fc.getSelectedFile();
				    Data data = new Data(liga);
				    try {
						aktualnyAdresar = file.getCanonicalPath().replaceFirst(file.getName(), "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    if(file.getAbsolutePath().toLowerCase().contains(".htm")){   
				    	data.exportTeamyHTML(file.getAbsolutePath());
				    } else {
				    	data.exportTeamyHTML(file.getAbsolutePath()+".html");
				    }
				   
				    
				}
				
		        } 
		    								
			}
		);
		menuExport.add(polozkaExportHtmlTeamy);
		
		JMenuItem polozkaExportHtmlHraci = new JMenuItem("Statistiky do HTML");
		polozkaExportHtmlHraci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				fc = new JFileChooser(aktualnyAdresar);
				int returnVal = fc.showSaveDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    File file = fc.getSelectedFile();
				    Data data = new Data(liga);
				    try {
						aktualnyAdresar = file.getCanonicalPath().replaceFirst(file.getName(), "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    if(file.getAbsolutePath().toLowerCase().contains(".htm")){   
				    	data.exportHraciHTML(file.getAbsolutePath());
				    } else {
				    	data.exportHraciHTML(file.getAbsolutePath()+".html");
				    }
				   
				    
				}
				
		        } 
		    								
			}
		);
		menuExport.add(polozkaExportHtmlHraci);
		
		JMenuItem polozkaExportHtmlZapasy = new JMenuItem("Zapasy do HTML");
		polozkaExportHtmlZapasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				fc = new JFileChooser(aktualnyAdresar);
				int returnVal = fc.showSaveDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    File file = fc.getSelectedFile();
				    Data data = new Data(liga);
				    try {
						aktualnyAdresar = file.getCanonicalPath().replaceFirst(file.getName(), "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    if(file.getAbsolutePath().toLowerCase().contains(".htm")){   
				    	data.exportZapasyHTML(file.getAbsolutePath());
				    } else {
				    	data.exportZapasyHTML(file.getAbsolutePath()+".html");
				    }
				   
				    
				}
				
		        } 
		    								
			}
		);
		menuExport.add(polozkaExportHtmlZapasy);
		
		JMenuItem polozkaExportSQL = new JMenuItem("Liga do SQL");
		polozkaExportSQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc;
				fc = new JFileChooser(aktualnyAdresar);
				int returnVal = fc.showSaveDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    File file = fc.getSelectedFile();
				    Data data = new Data(liga);			      
				    try {
						aktualnyAdresar = file.getCanonicalPath().replaceFirst(file.getName(), "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    if(file.getAbsolutePath().toLowerCase().contains(".sql")){   
				    	data.exportSQL(file.getAbsolutePath());
				    } else {
				    	data.exportSQL(file.getAbsolutePath()+".sql");
				    }
				    
				}
				
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
		
		JMenuItem polozkaZobrazPrehladTeamov = new JMenuItem("Zobraz prehlad teamov");
		polozkaZobrazPrehladTeamov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obsahHlavnejCasti=0;		
				reset();
		    }							
		}
		);
		menuNastroje.add(polozkaZobrazPrehladTeamov);
		
		JMenuItem polozkaSpravaHracovATeamov = new JMenuItem("Sprava hracov a teamov");
		polozkaSpravaHracovATeamov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obsahHlavnejCasti=1;		
				reset();
			}
		}
		);
		
		menuNastroje.add(polozkaSpravaHracovATeamov);
		
		JMenuItem polozkaSpravaZapasov = new JMenuItem("Sprava zapasov");
		polozkaSpravaZapasov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obsahHlavnejCasti=2;		
				reset();
			}
		}
		);
		
		menuNastroje.add(polozkaSpravaZapasov);
		
		JMenuItem polozkaGenerovatZapasy = new JMenuItem("Generuj zapasy");
		polozkaGenerovatZapasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String response = JOptionPane.showInputDialog(null,
						  "Zadajte pocet odviet",
						  "Pocet odviet",
						  JOptionPane.QUESTION_MESSAGE); 
				if(response!=null && response.equals("")==false){
					liga.generateZapasy(liga.getZoznamTeamov().size(), Integer.parseInt(response));
					reset();
				}
		    }							
		}
		);
		if(liga!=null)if(liga.getZoznamZapasov().size()>0) polozkaGenerovatZapasy.setEnabled(false);
		menuNastroje.add(polozkaGenerovatZapasy);
		
		horneMenu.add(menuNastroje);
		
		horneMenu.setVisible(true);
		return horneMenu;
	}
	private JPanel vytvorHlavnyPanel(){
		JPanel hlavnyPanel = new JPanel();
		hlavnyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		hlavnyPanel.setBounds(this.getBounds());
		hlavnyPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		if(liga!=null){
			// ak je liga nacitana, zobraz zvysok gui
			JPanel hornyPanel = new JPanel();
			hornyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			JLabel nazovLigy = new JLabel(liga.getNazovLigy());
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.PAGE_START;
			c.gridwidth = 2;
			c.weightx = 0.0;
			c.gridx = 0;
			c.gridy = 0;			
			hornyPanel.add(nazovLigy);
			hlavnyPanel.add(hornyPanel,c);
			
			JPanel lavyPanel = vytvorLavyPanel();
			//lavyPanel.setBorder(BorderFactory.createLineBorder(Color.red));
			c.fill = GridBagConstraints.BOTH;	
			c.weighty = 1.0;
			c.anchor = GridBagConstraints.PAGE_END;
			c.gridwidth = 1;
			c.weightx = 0.75;
			c.gridx = 0;
			c.gridy = 1;	
			JScrollPane scrollpane = new JScrollPane(lavyPanel);
			hlavnyPanel.add(scrollpane,c);
			
			JPanel pravyPanel = vytvorPravyPanel();
			//pravyPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
			c.weighty = 1.0;
			c.gridwidth = 1;
			c.weightx = 0.0;
			c.gridx = 1;
			c.gridy = 1;	
			hlavnyPanel.add(pravyPanel,c);
			
		}
		
		return hlavnyPanel;
	}
	private JPanel vytvorPravyPanel(){
		// JPanel obsahujuci zoznam TOP10 hracov a TOP5 brankarov
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;	
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 1;
		c.weighty = 0.66;
		c.gridx = 0;
		c.gridy = 0;	
		panel.add(vytvorPanelTop10Hraci(),c);
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 1;
		c.weighty = 0.33;
		c.gridx = 0;
		c.gridy = 1;	
		panel.add(vytvorPanelTop5Brankari(),c);
		return panel;		
	}
	private JPanel vytvorPanelTop10Hraci(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel nadpis = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 1;
		c.gridwidth = 7;
		c.gridx = 0;
		c.gridy = 0;
		nadpis.add(new JLabel("TOP 10 Hraci"));
		panel.add(nadpis,c);
		c.insets = new Insets(5,5,2,2);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("#"),c);
		c.weightx = 0.2;
		c.gridx = 1;		
		panel.add(new JLabel("Meno"),c);
		c.weightx = 0.2;
		c.gridx = 2;		
		panel.add(new JLabel("Team"),c);
		c.weightx = 0.1;
		c.gridx = 3;
		panel.add(new JLabel("G"),c);
		c.gridx = 4;
		panel.add(new JLabel("A"),c);
		c.gridx = 5;
		panel.add(new JLabel("OZ"),c);
		c.gridx = 6;
		panel.add(new JLabel("TM"),c);
		
		List<Hrac> hraci = liga.getZoznamHracov();
		Collections.sort(hraci,Collections.reverseOrder(new HracByStats(liga))); 
		
		for(int i=0;i<Math.min(10, hraci.size());i++){
			Hrac hrac = hraci.get(i);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.NORTH;
			c.weightx = 0.1;
			c.gridx = 0;
			c.gridy = i+2;
			panel.add(new JLabel(i+1+"."),c);
			c.weightx = 0.2;
			c.gridx = 1;		
			panel.add(new JLabel(hraci.get(i).getMeno()),c);
			c.weightx = 0.2;
			c.gridx = 2;		
			panel.add(new JLabel(liga.getTeamHraca(hraci.get(i).getIdHraca()).getNazov()),c);
			c.weightx = 0.1;
			c.gridx = 3;
			panel.add(new JLabel(hrac.getGoly()+""),c);
			c.gridx = 4;
			panel.add(new JLabel(hrac.getAsist()+""),c);
			c.gridx = 5;
			panel.add(new JLabel(hrac.getOdohratychZapasov()+""),c);
			c.gridx = 6;
			panel.add(new JLabel(hrac.getTrestMin()+""),c);
		}
		JPanel nic = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 5;
		c.gridx = 1;
		c.gridy = 13;
		panel.add(nic, c);
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	private JPanel vytvorPanelTop5Brankari(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel nadpis = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 1;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 0;
		nadpis.add(new JLabel("TOP 5 Brankari"));
		panel.add(nadpis,c);
		c.insets = new Insets(5,5,2,2);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(new JLabel("#"),c);
		c.weightx = 0.25;
		c.gridx = 1;
		panel.add(new JLabel("Meno"),c);
		c.weightx = 0.25;
		c.gridx = 2;
		panel.add(new JLabel("Team"),c);
		c.weightx = 0.1;
		c.gridx = 3;
		panel.add(new JLabel("IG"),c);
		c.gridx = 4;
		panel.add(new JLabel("OM"),c);
		c.gridx = 5;
		panel.add(new JLabel("P"),c);
		
		List<Hrac> hraci = liga.getZoznamBrankarov();
		Collections.sort(hraci,new BrankarByStats(liga)); 
		
		for(int i=0;i<Math.min(5, hraci.size());i++){
			Hrac hrac = hraci.get(i);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.NORTH;
			c.weightx = 0.1;
			c.gridx = 0;
			c.gridy = i+2;
			panel.add(new JLabel(i+1+"."),c);
			c.weightx = 0.25;
			c.gridx = 1;
			panel.add(new JLabel(hraci.get(i).getMeno()),c);
			c.weightx = 0.25;
			c.gridx = 2;
			panel.add(new JLabel(liga.getTeamHraca(hraci.get(i).getIdHraca()).getNazov()),c);
			c.weightx = 0.1;
			c.gridx = 3;
			panel.add(new JLabel(hrac.getInkasGoly()+""),c);
			c.gridx = 4;
			panel.add(new JLabel(hrac.getOdchytMin()+""),c);
			c.gridx = 5;
			panel.add(new JLabel(hrac.getPriemer(liga.getDlzkaZapasu())+""),c);
		}
		JPanel nic = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 8;
		panel.add(nic, c);
		return panel;
	}
	private JPanel vytvorLavyPanel(){
		// hlavna plocha aplikacie, ktora sa meni podla zvolenej polozky v menu Nastroje
		JPanel panel = new JPanel();
		// hlavna obrazovka, ta co sa zobrazi po spusteni
		if(obsahHlavnejCasti==0){
			panel=vytvorPanelPrehladTeamov();
		}
		// obrazovka so zoznamom teamov a tlacidlami na ich upravu, pridavanie
		if(obsahHlavnejCasti==1){
			panel=vytvorPanelSpravaTeamov();
		}	
		// obrazovka so spravou hracov teamu
		if(obsahHlavnejCasti==11){
			panel=vytvorPanelSpravaHracov();
		}
		// obrazovka so spravou zapasov
		if(obsahHlavnejCasti==2){
			panel=vytvorPanelSpravaZapasov();
		}
		// obrazovka umoznujuca upravy konkreneho zapasu
		if(obsahHlavnejCasti==22){
			panel=vytvorPanelUpravaZapasu();
		}
		return panel;		
	}		
	private JPanel vytvorPanelPrehladTeamov() {
		// metoda na vytvorenie JPanelu s prehladom vsetkych teamov ligy zoradenych podla bodov
		// tato vytvori len nadpis v hornej casti a panel urceny pre samotnu tabulku v dolnej casti
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel nadpis = new JPanel();
		nadpis.add(new JLabel("Prehlad Teamov"));
		panel.add(nadpis);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;	
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 1;
		c.weighty = 0;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;	
		panel.add(nadpis,c);
		JPanel obsah = vytvorPanelObsahPrehladTeamov();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		//obsah.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(obsah,c);
		return panel;	
	}
	private JPanel vytvorPanelObsahPrehladTeamov() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,2,2);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.gridy = 0;
		c.weightx = 0.05;
		panel.add(new JLabel("Poradie"),c);
		c.weightx = 0.5;
		c.gridx = 1;		
		panel.add(new JLabel("Nazov"),c);
		c.weightx = 0.1;
		c.gridx = 2;
		panel.add(new JLabel("Vyhry"),c);
		c.gridx = 3;
		panel.add(new JLabel("Prehry"),c);
		c.gridx = 4;
		panel.add(new JLabel("Remizy"),c);
		c.gridx = 5;
		panel.add(new JLabel("Skore"),c);
		c.gridx = 6;
		panel.add(new JLabel("Body"),c);
		List<Team> zoradenyZoznamTeamov = liga.getZoznamTeamov();
		Collections.sort(zoradenyZoznamTeamov,Collections.reverseOrder(new TeamByStats(liga)));
		for(int i=0;i<zoradenyZoznamTeamov.size();i++){
			c.gridx = 0;
			c.gridy = i+1;
			c.weightx = 0.05;
			panel.add(new JLabel(i+1+"."),c);
			c.gridx = 1;
			c.weightx = 0.5;
			panel.add(new JLabel(zoradenyZoznamTeamov.get(i).getNazov()),c);
			c.weightx = 0.1;
			c.gridx = 2;
			panel.add(new JLabel(""+zoradenyZoznamTeamov.get(i).getPocetVyhier(liga.getZoznamZapasovTeamu(zoradenyZoznamTeamov.get(i).getIdTeamu()))),c);
			c.gridx = 3;
			panel.add(new JLabel(""+zoradenyZoznamTeamov.get(i).getPocetPrehier(liga.getZoznamZapasovTeamu(zoradenyZoznamTeamov.get(i).getIdTeamu()))),c);
			c.gridx = 4;
			panel.add(new JLabel(""+zoradenyZoznamTeamov.get(i).getPocetRemiz(liga.getZoznamZapasovTeamu(zoradenyZoznamTeamov.get(i).getIdTeamu()))),c);
			c.gridx = 5;
			panel.add(new JLabel(zoradenyZoznamTeamov.get(i).getStrelGoly()+":"+zoradenyZoznamTeamov.get(i).getInkasGoly()),c);
			c.gridx = 6;
			panel.add(new JLabel(""+zoradenyZoznamTeamov.get(i).getBody(liga.getZoznamZapasovTeamu(zoradenyZoznamTeamov.get(i).getIdTeamu()),liga.getBodyZaVyhru())),c);
		}
		
		
		
		JPanel nic = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = liga.getZoznamTeamov().size()+2;
		panel.add(nic, c);
		
		return panel;
	}
	private JPanel vytvorPanelSpravaTeamov(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel nadpis = new JPanel();
		nadpis.add(new JLabel("Sprava hracov a teamov"));
		panel.add(nadpis);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;	
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 1;
		c.weighty = 0;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;	
		panel.add(nadpis,c);
		JPanel obsah = vytvorPanelObsahSpravaTeamov();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		//obsah.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(obsah,c);
		return panel;		
	}
	private JPanel vytvorPanelSpravaHracov(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel nadpis = new JPanel();
		nadpis.add(new JLabel("Sprava hracov teamu "+liga.getZoznamTeamov().get(idUpravovanehoTeamu).getNazov()));
		panel.add(nadpis);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;	
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 1;
		c.weighty = 0;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;	
		panel.add(nadpis,c);
		JPanel obsah = vytvorPanelObsahSpravaHracov();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		//obsah.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(obsah,c);
		return panel;		
	}
	private JPanel vytvorPanelObsahSpravaTeamov(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,2,2);
		for(int i=0;i<liga.getZoznamTeamov().size();i++){
			
			JLabel meno=new JLabel(liga.getZoznamTeamov().get(i).getNazov());			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.NORTH;
			c.weightx = 0.7;
			c.gridx = 0;
			c.gridy = i;
			panel.add(meno, c);
			
			JButton spravuj = new JButton("Sprava hracov");
			final int idTeamu=i;		
			spravuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					idUpravovanehoTeamu=idTeamu;
					obsahHlavnejCasti=11;
					reset();
				}
			});
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 1;
			c.gridy = i;
			panel.add(spravuj, c);
			
			JButton uprav = new JButton("Uprav team");		
			uprav.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String response = JOptionPane.showInputDialog(null,
							  "Zadajte meno teamu",
							  "Meno hraca",
							  JOptionPane.QUESTION_MESSAGE); if(response!=null && response!="")
					liga.getZoznamTeamov().get(idTeamu).setNazov(response);
					reset();
				}
			});
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 2;
			c.gridy = i;
			panel.add(uprav, c);
	
			JButton vymaz = new JButton("Vymaz team");
			vymaz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int odpoved;
		    		odpoved = JOptionPane.showConfirmDialog(null, "Chces vymazat team "+liga.getZoznamTeamov().get(idTeamu).getNazov()+"?","Okno",0);
		    		if(odpoved==0)liga.getZoznamTeamov().remove(idTeamu);
					
					reset();
				}
			});
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 3;
			c.gridy = i;
			panel.add(vymaz, c);
			
		}
		
		JLabel meno=new JLabel();			
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 0.7;
		c.gridx = 0;
		c.gridy = liga.getZoznamTeamov().size();
		panel.add(meno, c);
		final int idTeamu=liga.getZoznamTeamov().size()+1;
		JButton pridaj = new JButton("Pridaj team");
		pridaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String response = JOptionPane.showInputDialog(null,
						  "Zadajte nazov teamu",
						  "Nazov teamu",
						  JOptionPane.QUESTION_MESSAGE); if(response!=null && response!="")
				liga.getZoznamTeamov().add(new Team(response,idTeamu));
				reset();
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 1;
		c.gridy = liga.getZoznamTeamov().size();
		panel.add(pridaj, c);
		
		JPanel nic = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 3;
		c.gridy = liga.getZoznamTeamov().size();
		panel.add(nic, c); 
		
		
		nic = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = liga.getZoznamTeamov().size()+1;
		panel.add(nic, c);
		return panel;
	}
	private JPanel vytvorPanelObsahSpravaHracov(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,2,2);	
		for(int j=0;j<liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().size();j++){
				
				JLabel meno=new JLabel("   "+liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().get(j).getMeno());			
				c.fill = GridBagConstraints.HORIZONTAL;
				c.anchor = GridBagConstraints.NORTH;
				c.weightx = 0.7;
				c.gridx = 0;
				c.gridy = j;
				panel.add(meno, c);				
				
				final int idHraca=j;
				JButton uprav = new JButton("Uprav hraca");
				uprav.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String response = JOptionPane.showInputDialog(null,
								  "Zadajte meno hraca",
								  "Meno hraca",
								  JOptionPane.QUESTION_MESSAGE); if(response!=null && response!="") if(response!=null && response!="")
						liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().get(idHraca).setMeno(response);
						reset();
					}
				});
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.1;
				c.gridx = 2;
				c.gridy = j;
				panel.add(uprav, c);

				JButton vymaz = new JButton("Vymaz hraca");
				vymaz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int odpoved;
			    		odpoved = JOptionPane.showConfirmDialog(null, "Chces vymazat hraca "+liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().get(idHraca).getMeno()+"?","Okno",0);
			    		if(odpoved==0)liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().remove(idHraca);
						
						reset();
					}
				});
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.1;
				c.gridx = 3;
				c.gridy = j;
				panel.add(vymaz, c);
			}
			
			JButton pridaj = new JButton("Pridaj hraca");
			pridaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String response = JOptionPane.showInputDialog(null,
							  "Zadajte meno hraca",
							  "Meno hraca",
							  JOptionPane.QUESTION_MESSAGE); if(response!=null && response!="")
					liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().add(new Hrac(liga.getVolneIdHraca(),response));
					reset();
				}
			});
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 1;
			c.gridy = liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().size();
			panel.add(pridaj, c);	
			
			JPanel nic = new JPanel();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 3;
			c.gridy = liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().size();
			panel.add(nic, c); 
			
			JButton spat = new JButton("Zoznam teamov");
			spat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					obsahHlavnejCasti=1;
					reset();
				}
			});
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 3;
			c.gridy = liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().size()+1;
			panel.add(spat, c);
			
			nic = new JPanel();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1.0;
			c.weighty = 1.0;
			c.gridwidth = 4;
			c.gridx = 1;
			c.gridy = liga.getZoznamTeamov().get(idUpravovanehoTeamu).getZoznamHracov().size()+2;
			panel.add(nic, c);
			
		
		return panel;
	}
	private JPanel vytvorPanelSpravaZapasov() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel nadpis = new JPanel();
		nadpis.add(new JLabel("Sprava zapasov"));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;	
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 1;
		c.weighty = 0;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;	
		panel.add(nadpis,c);
		JPanel obsah = vytvorPanelObsahSpravaZapasov();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		//obsah.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(obsah,c);
		return panel;
	}
	private JPanel vytvorPanelObsahSpravaZapasov() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JLabel cislo = new JLabel(cisloKola+1+". kolo");
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,2,2);	
		c.gridx = 0;
		c.gridy = 0;
		panel.add(cislo,c);
		JButton back = new JButton("Predchadzajuce kolo");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pT=liga.getZoznamTeamov().size();
				if(cisloKola==0) cisloKola=faktorial(pT)/(faktorial(2)*faktorial(pT-2))/(pT/2)-1; else
				cisloKola--;
				reset();
			}
		});
		c.gridx = 1;
		panel.add(back,c);
		JButton next = new JButton("Nasledujuce kolo");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pT=liga.getZoznamTeamov().size();
				if(cisloKola==faktorial(pT)/(faktorial(2)*faktorial(pT-2))/(pT/2)-1) cisloKola=0; else
				cisloKola++;
				reset();
			}
		});
		c.gridx = 2;
		panel.add(next,c);
		for(int i=0;i<liga.getZoznamTeamov().size()/2;i++){
			Zapas zapas = liga.getZoznamZapasov().get(cisloKola*(liga.getZoznamTeamov().size()/2)+i);
			JLabel nazov = new JLabel(liga.getTeam(zapas.getIdTeamu1()).getNazov()+" vs "+liga.getTeam(zapas.getIdTeamu2()).getNazov());
			c.gridx = 0;
			c.gridy=i+1;
			panel.add(nazov,c);
			JButton upravit = new JButton("Upravit zapas");
			final int id=zapas.getIdZapasu();
			upravit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					obsahHlavnejCasti=22;
					idUpravovanehoZapasu=id;
					reset();
				}
			});
			c.gridx=1;
			panel.add(upravit,c);
			if(zapas.getVysledok()==-1){
				JLabel info = new JLabel("Zapas este neprebehol");
				c.gridx=2;
				panel.add(info,c);
			} else {
				JLabel info = new JLabel(liga.getTeam(zapas.getIdTeamu1()).getSkore(zapas.getIdZapasu())+":"+liga.getTeam(zapas.getIdTeamu2()).getSkore(zapas.getIdZapasu()));
				c.gridx=2;
				panel.add(info,c);
			} 
			
		}
		
		JPanel nic = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = liga.getZoznamTeamov().size()/2+2;
		panel.add(nic, c);
		
		return panel;
	}
	private JPanel vytvorPanelUpravaZapasu() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel nadpis = new JPanel();
		nadpis.add(new JLabel("Uprava zapasu"));
		panel.add(nadpis);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;	
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 1;
		c.weighty = 0;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;	
		panel.add(nadpis,c);
		JPanel obsah = vytvorPanelObsahUpravaZapasu();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		//obsah.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(obsah,c);
		return panel;
	}
	private JPanel vytvorPanelObsahUpravaZapasu() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JLabel nadpis = new JLabel(liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu1()).getNazov()+ " vs "+liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu2()).getNazov());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,2,2);	
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth=3;
		panel.add(nadpis,c);
		
		
		final JPanel lavyTeam = new JPanel();
		lavyTeam.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5,5,2,2);	
		d.fill = GridBagConstraints.HORIZONTAL;
		d.anchor = GridBagConstraints.NORTH;
		d.weightx = 0.4;
		d.gridx = 0;
		d.gridy = 0;
		lavyTeam.add(new JLabel("Meno"),d);
		d.weightx = 0.1;
		d.gridx = 1;
		lavyTeam.add(new JLabel("Hral"),d);
		d.gridx = 2;
		lavyTeam.add(new JLabel("G"),d);
		d.gridx = 3;
		lavyTeam.add(new JLabel("A"),d);
		d.gridx = 4;
		lavyTeam.add(new JLabel("TM"),d);
		d.gridx = 5;
		lavyTeam.add(new JLabel("IG"),d);
		d.gridx = 6;
		lavyTeam.add(new JLabel("OM"),d);
		for (int i = 0; i < liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu1()).getZoznamHracov().size(); i++) {
			d.gridy=i+1;
			d.gridx=0;
			lavyTeam.add(new JLabel(liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu1()).getZoznamHracov().get(i).getMeno()),d);
			d.gridx=1;
			Hrac hrac = liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu1()).getZoznamHracov().get(i);
			JCheckBox hral = new JCheckBox();
			if(hrac.existujeZapas(idUpravovanehoZapasu)) hral.setSelected(hrac.getHralZapas(idUpravovanehoZapasu));
			lavyTeam.add(hral,d);
			
			for(int j = 2;j<7;j++){
				d.gridx=j;
				JTextField policko = new JTextField("0");
				if(hrac.existujeZapas(idUpravovanehoZapasu)){
					if(j==2)policko.setText(""+hrac.getGoly(idUpravovanehoZapasu));
					if(j==3)policko.setText(""+hrac.getAsist(idUpravovanehoZapasu));
					if(j==4)policko.setText(""+hrac.getTrestMin(idUpravovanehoZapasu));
					if(j==5)policko.setText(""+hrac.getInkasGoly(idUpravovanehoZapasu));
					if(j==6)policko.setText(""+hrac.getOdchytMin(idUpravovanehoZapasu));
				}
				
				policko.setColumns(2);
				lavyTeam.add(policko,d);
			}
			
			
		}
		c.gridwidth=1;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(lavyTeam,c);
		
		final JPanel pravyTeam = new JPanel();
		pravyTeam.setLayout(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		e.insets = new Insets(5,5,2,2);	
		e.fill = GridBagConstraints.HORIZONTAL;
		e.anchor = GridBagConstraints.NORTH;
		e.weightx = 0.4;
		e.gridx = 0;
		e.gridy = 0;
		pravyTeam.add(new JLabel("Meno"),e);
		e.weightx = 0.1;
		e.gridx = 1;
		pravyTeam.add(new JLabel("Hral"),e);
		e.gridx = 2;
		pravyTeam.add(new JLabel("G"),e);
		e.gridx = 3;
		pravyTeam.add(new JLabel("A"),e);
		e.gridx = 4;
		pravyTeam.add(new JLabel("TM"),e);
		e.gridx = 5;
		pravyTeam.add(new JLabel("IG"),e);
		e.gridx = 6;
		pravyTeam.add(new JLabel("OM"),e);
		for (int i = 0; i < liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu2()).getZoznamHracov().size(); i++) {
			e.gridy=i+1;
			e.gridx=0;
			pravyTeam.add(new JLabel(liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu2()).getZoznamHracov().get(i).getMeno()),e);
			e.gridx=1;
			Hrac hrac = liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu2()).getZoznamHracov().get(i);
			JCheckBox hral = new JCheckBox();
			if(hrac.existujeZapas(idUpravovanehoZapasu)) hral.setSelected(hrac.getHralZapas(idUpravovanehoZapasu));
			pravyTeam.add(hral,e);
			
			for(int j = 2;j<7;j++){
				e.gridx=j;
				JTextField policko = new JTextField("0");
				if(hrac.existujeZapas(idUpravovanehoZapasu)){
					if(j==2)policko.setText(""+hrac.getGoly(idUpravovanehoZapasu));
					if(j==3)policko.setText(""+hrac.getAsist(idUpravovanehoZapasu));
					if(j==4)policko.setText(""+hrac.getTrestMin(idUpravovanehoZapasu));
					if(j==5)policko.setText(""+hrac.getInkasGoly(idUpravovanehoZapasu));
					if(j==6)policko.setText(""+hrac.getOdchytMin(idUpravovanehoZapasu));
				}
				
				policko.setColumns(2);
				pravyTeam.add(policko,e);
			}
			
			
		}
		c.gridwidth=1;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(pravyTeam,c);
		
		JPanel tlacidla = new JPanel();
		
		JButton ulozit = new JButton("Ulozit");
		ulozit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// kontrola ci su zadane data cisla
				for(int i=7;i<lavyTeam.getComponentCount();i++){
					if(i%7>1 ){ //ak component nie je ani Meno ani Hral, teda JLabel a JCheckbox
						 JTextField pole =  (JTextField) lavyTeam.getComponent(i);
						 for(int j=0;j<pole.getText().length();j++){
							 if(Character.isDigit(pole.getText().charAt(j))==false){
								 JOptionPane.showMessageDialog(null, "Zadane hodnoty nie su platne, data neboli ulozene");
								 return;
							 }
						 }
					}
				}
				for(int i=7;i<pravyTeam.getComponentCount();i++){
					if(i%7>1){
						 JTextField pole =  (JTextField) pravyTeam.getComponent(i);
						 if(pole.getText().length()==0){
							 pole.setText(""+0);
						 }
						 for(int j=0;j<pole.getText().length();j++){
							 if(Character.isDigit(pole.getText().charAt(j))==false){
								 JOptionPane.showMessageDialog(null, "Zadane hodnoty nie su platne, data neboli ulozene");
								 return;
							 }
						 }
					}
				}
				// overili sme ze vsetky data su kladne cisla (nie je v nich ani -)
				boolean h=false;
				int golySpolu=0;
				int g=0;
				int a=0;
				int tm=0;
				int ig=0;
				int om=0;
				
				for(int i=7;i<lavyTeam.getComponentCount();i++){
					if(i%7==1){
						 JCheckBox hral =  (JCheckBox) lavyTeam.getComponent(i);
						 h=hral.isSelected();
					}
					if(i%7==2){
						 JTextField pole =  (JTextField) lavyTeam.getComponent(i);
						 g=Integer.parseInt(pole.getText());
						 golySpolu-=g;
					}
					if(i%7==3){
						 JTextField pole =  (JTextField) lavyTeam.getComponent(i);
						 a=Integer.parseInt(pole.getText());
					}
					if(i%7==4){
						 JTextField pole =  (JTextField) lavyTeam.getComponent(i);
						 tm=Integer.parseInt(pole.getText());
					}
					if(i%7==5){
						 JTextField pole =  (JTextField) lavyTeam.getComponent(i);
						 ig=Integer.parseInt(pole.getText());
					}
					if(i%7==6){
						 JTextField pole =  (JTextField) lavyTeam.getComponent(i);
						 om=Integer.parseInt(pole.getText());
						 if(h==true){
							 liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu1()).getZoznamHracov().get(i/7-1).addZapas(idUpravovanehoZapasu, g, a, tm, om, ig);
						 } else {
							 liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu1()).getZoznamHracov().get(i/7-1).nehralZapas(idUpravovanehoZapasu);
						 }
						 
						
					}
				}
				
				for(int i=7;i<pravyTeam.getComponentCount();i++){
					if(i%7==1){
						 JCheckBox hral =  (JCheckBox) pravyTeam.getComponent(i);
						 h=hral.isSelected();
					}
					if(i%7==2){
						 JTextField pole =  (JTextField) pravyTeam.getComponent(i);
						 g=Integer.parseInt(pole.getText());
						 golySpolu+=g;
					}
					if(i%7==3){
						 JTextField pole =  (JTextField) pravyTeam.getComponent(i);
						 a=Integer.parseInt(pole.getText());
					}
					if(i%7==4){
						 JTextField pole =  (JTextField) pravyTeam.getComponent(i);
						 tm=Integer.parseInt(pole.getText());
					}
					if(i%7==5){
						 JTextField pole =  (JTextField) pravyTeam.getComponent(i);
						 ig=Integer.parseInt(pole.getText());
					}
					if(i%7==6){
						 JTextField pole =  (JTextField) pravyTeam.getComponent(i);
						 om=Integer.parseInt(pole.getText());
						 if(h==true){
							 liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu2()).getZoznamHracov().get(i/7-1).addZapas(idUpravovanehoZapasu, g, a, tm, om, ig);
						 } else {
							 liga.getTeam(liga.getZapas(idUpravovanehoZapasu).getIdTeamu2()).getZoznamHracov().get(i/7-1).nehralZapas(idUpravovanehoZapasu);
						 }
						 
						
					}
				}
				if(golySpolu<0){ //prvy team dal viac golov
					liga.getZapas(idUpravovanehoZapasu).setVysledok(1);
				} else if(golySpolu>0){ //druhy team dal viac golov
					liga.getZapas(idUpravovanehoZapasu).setVysledok(2);
				} else liga.getZapas(idUpravovanehoZapasu).setVysledok(0);
				
				
				JOptionPane.showMessageDialog(null, "Data boli uspesne ulozene");
				reset();
			}
		});
		tlacidla.add(ulozit);
		
		JButton spat = new JButton("Spat");
		spat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obsahHlavnejCasti=2;
				reset();
			}
		});
		tlacidla.add(spat);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth=3;
		panel.add(tlacidla,c);		
				
		JPanel nic = new JPanel();		
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(nic, c);
		
		return panel;
	}
	private int faktorial(int n){
		int vysledok = 1;
		for(int i = n;i>0;i--){
			vysledok*=i;
		}
		return vysledok;
	}
	private void reset(){
		//this.setVisible(false);
		this.setJMenuBar(vytvorHorneMenu());
		this.setContentPane(vytvorHlavnyPanel());
		this.pack();
		this.setVisible(true);		
	}
	
}
