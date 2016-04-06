package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.log4j.Logger;

import core.SearchSite;
import core.Spider;
import log.LoggerUtility;

class Interface extends JFrame {
	private SearchSite searchSite;
	private Spider spider;
	private static final File file = new File("sites.txt");

	private JLabel rechercheMot = new JLabel("requête");
	private JTextField champMot = new JTextField();
	private JButton rechercheMotButton = new JButton("rechercher");
	private JLabel informations = new JLabel();

	private JFrame frameResult = new JFrame("Résultats");
	
	private static Logger logger=LoggerUtility.getLogger(SearchSite.class);

	public Interface() {
		super("Bingo!");

		spider = new Spider();
		rechercheMotButton.addActionListener(new SearchAction());
		init();

	}
	
	public void link(URL url) {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.OPEN)) {
				try {
					try {
						Desktop.getDesktop().browse(url.toURI());
					} catch (URISyntaxException e1) {
						System.err.println(e1.getMessage());
					}
				} catch (IOException ioe) {
					// Traitement de l'exception
				}
			} else {
				// La fonction n'est pas supportee par votre systeme
				// d'exploitation
			}
		} else {
			// Desktop pas supporte par votre systeme d'exploitation
		}
	}
	
	public String listUrls(){
		ArrayList<String> sites = searchSite.getResult();
		String list="le nombre de page résultant est "+sites.size()+"<br />";
		logger.info("nombre de page:"+sites.size());
		for(String site:sites){
			
		 list+=("<a href= \""+site+"\">"+site+"</a>");
		 list+="<br /><br />";
		}
		return list;
	}

	public void init() {

//		spider.recursiveSearch();
		searchSite = new SearchSite();
		frameResult.setSize(600, 500);
		this.setSize(500, 100);
		champMot.setSize(30, 50);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Font font = new Font("Arial",Font.BOLD,20);
		rechercheMot.setFont(font);
		rechercheMotButton.setFont(font);
		informations.setFont(font);
		
		
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();

		
		panel.setLayout(layout);
		panel.add(rechercheMot, BorderLayout.WEST);
		panel.add(champMot, BorderLayout.CENTER);
		panel.add(rechercheMotButton, BorderLayout.EAST);
		panel.add(informations, BorderLayout.SOUTH);

		getContentPane().add(panel);
		
		setResizable(false);
		setVisible(true);

	}

		

	private class SearchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (champMot.getText().equals("")) {
				informations.setText("aucune requête entrer");
			} else {
				try{
				searchSite.search(champMot.getText());
				

			JPanel tmpPanel = new JPanel();

			 JEditorPane jep = new JEditorPane("text/html", listUrls());
			jep.setOpaque(false);
			jep.addHyperlinkListener(new HyperlinkListener() {
				public void hyperlinkUpdate(HyperlinkEvent hyperLink) {
					if (HyperlinkEvent.EventType.ACTIVATED.equals(hyperLink.getEventType())) {
						if (hyperLink.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							URL url = hyperLink.getURL();
							link(url);
						}
					}
				}
			});
			jep.setEditable(false);

			tmpPanel.add(jep);
			JScrollPane editorScrollPane = new JScrollPane(tmpPanel);
			editorScrollPane.setVerticalScrollBarPolicy(
			                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			editorScrollPane.setPreferredSize(new Dimension(100, 100));
			editorScrollPane.setMinimumSize(new Dimension(10, 10));

			frameResult.add(tmpPanel);
			frameResult.setVisible(true);
				}
				catch(NullPointerException npe){
					informations.setText("aucun résultat");
				}
				
			}	
		}

	}

	public static void main(String[] args) {
		new Interface();
	}

}
