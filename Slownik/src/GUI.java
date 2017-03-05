import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GUI extends JFrame{
	
	GUI(String sciezka) throws IOException {
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Slownik");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension res = new Dimension (550,300);
		setBounds(dim.width/2 - res.width/2, dim.height/2 - res.height/2, res.width, res.height);
		choose(sciezka);
	}
	public void fromFile (AvlTree drzewo, String sciezka) throws IOException{
		if (sciezka !=""){
			BufferedReader read;
			try {
				read = new BufferedReader(new FileReader(sciezka));
				drzewo.resetRotations();
				while (read.ready()) {
					String[] temp = read.readLine().split("/");
					String[] tlumaczenia = new String[3];
					for (int i = 1; i < temp.length; i++) {
						if (temp[i] != null) {
							tlumaczenia[i - 1] = temp[i];
						}
					}
					drzewo.insert(temp[0], tlumaczenia);
				}
				System.out.println("Input File: [" + sciezka + "]");
				System.out.println("Rotations: "+ drzewo.getRotations());
			} catch (Exception e) {
				System.out.println("Z³a œcie¿ka");
			}
			System.out.println("----------------------------------------------------------------------------------------");
				//drzewo.getTree();
		}
	}
	public void toFile(AvlTree drzewo, String sciezka) throws FileNotFoundException{
		drzewo.getTreeToFile(sciezka);
		
	}
	private void choose(String sciezka) throws IOException{
		final AvlTree drzewo = new AvlTree();
		fromFile(drzewo,sciezka);

		Font font = new Font("TimesNewRoman",Font.BOLD,20);
		
		final JButton button1 = new JButton("Wstaw");
		button1.setFont(font);
		final JButton button2 = new JButton("Usun");
		button2.setFont(font);
		final JButton button3 = new JButton("Wyszukaj");
		button3.setFont(font);
		final JButton button4 = new JButton("Wczytaj z pliku");
		button4. setFont(font);
		final JButton button5 = new JButton("Zapisz do pliku");
		button5.setFont(font);
		final JButton button6 = new JButton("Pokaz drzewo");
		button6.setFont(font);
		
		
		final JTextField text1 = new JTextField();
		text1.setFont(font.deriveFont(NORMAL));
		text1.setHorizontalAlignment(JTextField.CENTER);
		text1.setBackground(new Color (0xCFCFCF));
		final JTextField text2 = new JTextField();
		text2.setFont(font.deriveFont(NORMAL));
		text2.setHorizontalAlignment(JTextField.CENTER);
		final JTextField text3 = new JTextField();
		text3.setFont(font.deriveFont(NORMAL));
		text3.setHorizontalAlignment(JTextField.CENTER);
		final JTextField[] tran = new JTextField[3];

		for (int i = 0; i<3; i++){
			tran[i] = new JTextField();
			tran[i].setFont(font.deriveFont(NORMAL));
			tran[i].setHorizontalAlignment(JTextField.CENTER);
			tran[i].setBackground(new Color(0xCFCFCF));
			new GhostText(tran[i], "Tlumaczenie", Color.GRAY);
		}
		
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(2,1));
		JPanel panel1 = new JPanel(new GridLayout(2,3));
		panel1.setSize(500, 150);
		cp.add(panel1);
			panel1.add(button1);
			panel1.add(button2);
			panel1.add(button3);
			panel1.add(text1);
			panel1.add(text2);
			panel1.add(text3); 
		JPanel panel2 = new JPanel(new GridLayout(2,1));
		cp.add(panel2);
		JPanel panel2a = new JPanel(new GridLayout(1,3));
		panel2a.add(button5);
		panel2a.add(button6);
		panel2a.add(button4);
		JPanel panel2b = new JPanel(new GridLayout(1,3));
		for (int i=0; i<3; i++)
			panel2b.add(tran[i]);
		panel2.add(panel2b);
		panel2.add(panel2a);
		revalidate();
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (!text1.getText().isEmpty() && isInteger(text1.getText())==false){
					for (int i=0; i<3; i++){
						if (tran[i].getText().equals("Tlumaczenie"))
							tran[i].setText("");
					}
					if (!tran[0].getText().isEmpty() || !tran[1].getText().isEmpty() || !tran[2].getText().isEmpty()) {
						String[] tab = new String [3];
						int k =0 ;
						for (int i =0; i<3; i++){
							if (!tran[i].getText().isEmpty()){
								tab[k]= tran[i].getText();
								tran[i].setText("");
								k++;
							}
						}
						drzewo.resetRotations();
						drzewo.resetComp();
						drzewo.insert(text1.getText(),tab);
						//drzewo.getTree();
						System.out.println("Insert: ["+text1.getText()+ "]");
						System.out.println("Rotations: "+ drzewo.getRotations());
						System.out.println("Comparison: "+ drzewo.getComp());
						System.out.println("----------------------------------------------------------------------------------------");
						text1.setText("");
					} else {
						JOptionPane.showMessageDialog(null,"Musi byc jakies tlumaczenie!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					for (int i=0; i<3; i++)
						new GhostText(tran[i], "Tlumaczenie", Color.GRAY);
				}
			}
		});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (!text2.getText().isEmpty() && isInteger(text2.getText())==false){
					drzewo.resetRotations();
					drzewo.resetComp();
					drzewo.remove(text2.getText());
					//drzewo.getTree();
					System.out.println("Remove: ["+text2.getText()+"]");
					System.out.println("Rotations: "+ drzewo.getRotations());
					System.out.println("Comparison: "+ drzewo.getComp());
					System.out.println("----------------------------------------------------------------------------------------");
					text2.setText("");
				}
			}
		});
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (!text3.getText().isEmpty() && isInteger(text3.getText())==false){
					//drzewo.getTree();
					drzewo.resetComp();
					drzewo.search(text3.getText());
					System.out.println("Search: ["+text3.getText()+"]");
					System.out.println("Comparison: "+ drzewo.getComp());
					System.out.println("----------------------------------------------------------------------------------------");
					text3.setText("");
				}
			}
		});
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String temp = JOptionPane.showInputDialog("Podaj sciezke pliku");
				if (temp!=null)
					try {
						fromFile(drzewo, temp);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

			}
		});
		button5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String temp = JOptionPane.showInputDialog("Podaj sciezke pliku");
				if (temp!=null)
					try {
						toFile(drzewo, temp);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
			}
		});
		button6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drzewo.getTree();
				System.out.println("----------------------------------------------------------------------------------------");
			}
		});
		
	}
	private boolean isInteger(String s){
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe)
		{
			return false;
		}
	}
	

}
