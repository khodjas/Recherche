package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
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
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import core.SearchSite;
import core.Spider;

class Interface extends JFrame {
	private SearchSite searchSite;
	private Spider spider;
	private static final String url = "http://google.com/";
	private static final File file = new File("sites.txt");

	private JLabel rechercheMot = new JLabel("mot");
	private JTextField champMot = new JTextField();
	private JButton rechercheMotButton = new JButton("rechercher");
	private JLabel informations = new JLabel();

	private JFrame frameResult = new JFrame("Résultats");

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
		String list="";
		for(String site:sites){
		 list+=("<a href= \""+site+"\">"+site+"</a>");
		 list+="<br />";
		}
		return list;
	}

	public void init() {

		// spider.search(url);
		searchSite = new SearchSite();
		frameResult.setSize(300, 300);
		setSize(600, 200);
		// champMot.setSize(30, 50);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel b2 = new JPanel();
		BorderLayout layout = new BorderLayout();

		b2.setLayout(layout);
		b2.add(rechercheMot, layout.WEST);
		b2.add(champMot, layout.CENTER);
		b2.add(rechercheMotButton, layout.EAST);
		b2.add(informations, layout.SOUTH);

		getContentPane().add(b2);

		setVisible(true);

	}

		

	private class SearchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (champMot.getText().equals("")) {
				System.out.println("aucun message entrer");
			} else {
				searchSite.Search(champMot.getText());

				
			}

			JPanel tmpPanel = new JPanel();

			final JEditorPane jep = new JEditorPane("text/html", listUrls());
			jep.setOpaque(false);
			jep.addHyperlinkListener(new HyperlinkListener() {
				public void hyperlinkUpdate(HyperlinkEvent hle) {
					if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
						if (hle.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							URL url = hle.getURL();
							link(url);
						}
					}
				}
			});
			jep.setEditable(false);

			tmpPanel.add(jep);

			frameResult.add(tmpPanel);
			frameResult.setVisible(true);
			
		}

	}

	public static void main(String[] args) {
		new Interface();
	}

}
