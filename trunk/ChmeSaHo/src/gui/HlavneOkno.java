package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import porovnavanie.HracByStats;

import zaklad.*;

public class HlavneOkno extends JFrame{
	Liga liga;
	File aktualnyAdresar = new File (".");
	String aktualnyNazovSuboru;
	int obsahHlavnejCasti;
	public HlavneOkno(int x, int y) {
		setLayout(new BorderLayout());
		setBounds(0, 0, x, y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Tabulky");
		obsahHlavnejCasti=0;
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
				liga = new Liga();
				reset();
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
					int returnVal = fc.showSaveDialog(null);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Data data = new Data(liga);			      
			            
			            if(file.getAbsolutePath().toLowerCase().contains(".sql")){   
			            	data.exportSQL(file.getAbsolutePath());
			            } else {
			            	data.exportSQL(file.getAbsolutePath()+".sql");
			            }
			            
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
				obsahHlavnejCasti=1;		
				reset();
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
		JPanel nadpis = new JPanel();
		nadpis.add(new JLabel("TOP 10 Hraci"));
		panel.add(nadpis);
		panel.setLayout(new GridLayout(12,1));
		List<Hrac> hraci = liga.getZoznamNebrankarov();
		Collections.sort(hraci,Collections.reverseOrder(new HracByStats(liga))); 
		panel.add(vytvorPanelNadpisTop10Hraci());
		for(int i=0;i<Math.min(10, hraci.size());i++){
			
			panel.add(vytvorPanelHraca(hraci.get(i)));
			
		}
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	private JPanel vytvorPanelTop5Brankari(){
		JPanel panel = new JPanel();
		JPanel nadpis = new JPanel();
		nadpis.add(new JLabel("TOP 5 Brankari"));
		panel.add(nadpis);
		panel.setLayout(new GridLayout(7,1));
		List<Hrac> hraci = liga.getZoznamBrankarov();
		Collections.sort(hraci,Collections.reverseOrder(new HracByStats(liga))); 
		panel.add(vytvorPanelNadpisTop5Brankari());
		for(int i=0;i<Math.min(10, hraci.size());i++){
			
			panel.add(vytvorPanelBrankara(hraci.get(i)));
			
		}
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	private JPanel vytvorLavyPanel(){
		JPanel panel = new JPanel();
		if(obsahHlavnejCasti==0){
			
		}
		if(obsahHlavnejCasti==1){
			panel=vytvorPanelSpravaHAT();
		}		
		return panel;		
	}
	private JPanel vytvorPanelSpravaHAT(){
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
		JPanel obsah = vytvorPanelObsahHAT();
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
	private JPanel vytvorPanelObsahHAT(){
		int cisloRiadku = 0;
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
			c.gridy = i+cisloRiadku;
			panel.add(meno, c);
			
			JPanel nic = new JPanel();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 1;
			c.gridy = i+cisloRiadku;
			panel.add(nic, c);
			
			JButton uprav = new JButton("Uprav team");
			final int idTeamu=i;		
			uprav.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String response = JOptionPane.showInputDialog(null,
							  "Zadajte meno hraca",
							  "Meno hraca",
							  JOptionPane.QUESTION_MESSAGE);
					liga.getZoznamTeamov().get(idTeamu).setNazov(response);
					reset();
				}
			});
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 2;
			c.gridy = i+cisloRiadku;
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
			c.gridy = i+cisloRiadku;
			panel.add(vymaz, c);
			for(int j=0;j<liga.getZoznamTeamov().get(i).getZoznamHracov().size();j++){
				
				meno=new JLabel("   "+liga.getZoznamTeamov().get(i).getZoznamHracov().get(j).getMeno());			
				c.fill = GridBagConstraints.HORIZONTAL;
				c.anchor = GridBagConstraints.NORTH;
				c.weightx = 0.7;
				c.gridx = 0;
				c.gridy = i+cisloRiadku+j+1;
				panel.add(meno, c);
				
				nic = new JPanel();
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.1;
				c.gridx = 1;
				c.gridy = i+cisloRiadku+j+1;
				panel.add(nic, c);
				final int idTeamu2=i;
				final int idHraca=j;
				uprav = new JButton("Uprav hraca");
				uprav.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String response = JOptionPane.showInputDialog(null,
								  "Zadajte meno hraca",
								  "Meno hraca",
								  JOptionPane.QUESTION_MESSAGE); if(response!=null && response!="") if(response!=null && response!="")
						liga.getZoznamTeamov().get(idTeamu2).getZoznamHracov().get(idHraca).setMeno(response);
						reset();
					}
				});
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.1;
				c.gridx = 2;
				c.gridy = i+cisloRiadku+j+1;
				panel.add(uprav, c);

				vymaz = new JButton("Vymaz hraca");
				vymaz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int odpoved;
			    		odpoved = JOptionPane.showConfirmDialog(null, "Chces vymazat hraca "+liga.getZoznamTeamov().get(idTeamu).getZoznamHracov().get(idHraca).getMeno()+"?","Okno",0);
			    		if(odpoved==0)liga.getZoznamTeamov().get(idTeamu).getZoznamHracov().remove(idHraca);
						
						reset();
					}
				});
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 0.1;
				c.gridx = 3;
				c.gridy = i+cisloRiadku+j+1;
				panel.add(vymaz, c);
			}
			
			cisloRiadku+=liga.getZoznamTeamov().get(i).getZoznamHracov().size()+1;
			meno=new JLabel();			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.NORTH;
			c.weightx = 0.7;
			c.gridx = 0;
			c.gridy = i+cisloRiadku;
			panel.add(meno, c);
			final int idTeamu2=i;
			final int pocetHracov = liga.getZoznamHracov().size();
			JButton pridaj = new JButton("Pridaj hraca");
			pridaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String response = JOptionPane.showInputDialog(null,
							  "Zadajte meno hraca",
							  "Meno hraca",
							  JOptionPane.QUESTION_MESSAGE); if(response!=null && response!="")
					liga.getZoznamTeamov().get(idTeamu2).getZoznamHracov().add(new Hrac(pocetHracov+1,response));
					reset();
				}
			});
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 1;
			c.gridy = i+cisloRiadku;
			panel.add(pridaj, c);
			
			nic = new JPanel();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 2;
			c.gridy = i+cisloRiadku;
			panel.add(nic, c);

			nic = new JPanel();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.1;
			c.gridx = 3;
			c.gridy = i+cisloRiadku;
			panel.add(nic, c);
			cisloRiadku++;
		}
		cisloRiadku+=liga.getZoznamTeamov().size();
		JLabel meno=new JLabel();			
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 0.7;
		c.gridx = 0;
		c.gridy = cisloRiadku;
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
		c.gridy = cisloRiadku;
		panel.add(pridaj, c);
		
		JPanel nic = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 2;
		c.gridy = cisloRiadku;
		panel.add(nic, c);

		nic = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridx = 3;
		c.gridy = cisloRiadku;
		panel.add(nic, c);
		cisloRiadku++;
		
		nic = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = liga.getZoznamTeamov().size()+cisloRiadku;
		panel.add(nic, c);
		return panel;
	}
	private JPanel vytvorPanelHraca(Hrac hrac){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		JPanel lavaCast = new JPanel();
		lavaCast.add(new JLabel(hrac.getMeno()));
		panel.add(lavaCast);		
		
		JPanel pravaCast = new JPanel();
		pravaCast.setLayout(new FlowLayout());
		pravaCast.add(new JLabel(hrac.getGoly()+"  "));
		pravaCast.add(new JLabel(hrac.getAsist()+"  "));
		pravaCast.add(new JLabel(hrac.getOdohratychZapasov()+"  "));
		pravaCast.add(new JLabel(hrac.getTrestMin()+"  "));
		panel.add(pravaCast);		
		return panel;		
	}
	private JPanel vytvorPanelNadpisTop10Hraci(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		JPanel lavaCast = new JPanel();
		lavaCast.add(new JLabel("Meno"));
		panel.add(lavaCast);		
		
		JPanel pravaCast = new JPanel();
		pravaCast.setLayout(new FlowLayout());
		pravaCast.add(new JLabel("G  "));
		pravaCast.add(new JLabel("A  "));
		pravaCast.add(new JLabel("OZ "));
		pravaCast.add(new JLabel("TM "));
		panel.add(pravaCast);		
		return panel;		
	}
	private JPanel vytvorPanelNadpisTop5Brankari(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		JPanel lavaCast = new JPanel();
		lavaCast.add(new JLabel("Meno"));
		panel.add(lavaCast);		
		
		JPanel pravaCast = new JPanel();
		pravaCast.setLayout(new FlowLayout());
		pravaCast.add(new JLabel("IG  "));
		pravaCast.add(new JLabel("OM  "));
		pravaCast.add(new JLabel("P "));
		panel.add(pravaCast);		
		return panel;		
	}
	private JPanel vytvorPanelBrankara(Hrac hrac){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		JPanel lavaCast = new JPanel();
		lavaCast.add(new JLabel(hrac.getMeno()));
		panel.add(lavaCast);		
		
		JPanel pravaCast = new JPanel();
		pravaCast.setLayout(new FlowLayout());
		pravaCast.add(new JLabel(hrac.getInkasGoly()+"  "));
		pravaCast.add(new JLabel(hrac.getOdchytMin()+"  "));
		pravaCast.add(new JLabel(hrac.getPriemer(liga.getDlzkaZapasu())+"  "));
		panel.add(pravaCast);		
		return panel;		
	}
	private void reset(){
		//this.setVisible(false);
		this.setJMenuBar(vytvorHorneMenu());
		this.setContentPane(vytvorHlavnyPanel());
		this.pack();
		this.setVisible(true);		
	}
	
}
