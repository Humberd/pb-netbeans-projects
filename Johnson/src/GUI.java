
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class GUI extends JFrame{
	int macierzSasiedztwa[][];
	final int MAX = 999;
	//int[] tablica = {0,0,3,0,2,0,0,0,0,7,0,1,6,0,0,0};
	int[] tablica2 = {0,-1,0,0,2,0,0,0,0,2,0,-3,0,-2,0,2,0,0,1,0,0,0,-1,0,0,0,0,0,0,3,0,0,0,0,-2,0};
	GUI() throws IOException {
		String temp;
		do { 
			temp = JOptionPane.showInputDialog("Podaj liczbe wierzcholkow:");
		//temp ="6";
		}while (temp ==null);
		final int lW = Integer.parseInt(temp);
		macierzSasiedztwa = new int[lW+1][lW+1];
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Johnson");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension res = new Dimension (60+lW*60,120+lW*60);
		setBounds(dim.width/2 - res.width/2, dim.height/2 - res.height/2, res.width, res.height);
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(1,1));
		JPanel panel1 = new JPanel(new GridLayout(lW+2,1));
		cp.add(panel1);
		final JTextField[] text = new JTextField[lW*lW];
		for (int i=0; i<lW*lW;i++){
			text[i] = new JTextField("");
			text[i].setHorizontalAlignment(JTextField.CENTER);
			//text[i].setText(tablica2[i]+"");
			text[i].setText("0");
		}
		JLabel[] label = new JLabel[lW];
		for (int i =0;i<lW;i++){
			label[i] = new JLabel(i+"");
			label[i].setHorizontalAlignment(JLabel.CENTER);
		}
		JLabel[] label2 = new JLabel[lW];
		for (int i=0;i<lW;i++){
			label2[i] = new JLabel(i+"");
			label2[i].setHorizontalAlignment(JLabel.CENTER);
		}
		
		JPanel[] jp = new JPanel[lW+1];
		jp[0] = new JPanel(new GridLayout(1,lW+1));
		jp[0].add(new JLabel(""));
		for (int i=1; i<lW+1;i++){
			jp[0].add(label[i-1]);
		}
		panel1.add(jp[0]);
	
		for (int i=1; i<lW+1;i++){
			jp[i] = new JPanel(new GridLayout(1,lW+1));
			jp[i].add(label2[i-1]);
			for (int j=0; j<lW;j++){
				jp[i].add(text[j+lW*(i-1)]);
			}
			panel1.add(jp[i]);
		}
		
		JPanel zGuzikiem = new JPanel();
		JButton guzik = new JButton("Wcisnij");
		guzik.setHorizontalAlignment(JButton.CENTER);
		panel1.add(zGuzikiem);
		zGuzikiem.add(guzik);

		revalidate();
		
		guzik.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int m = 0;
				System.out.println("----------------Macierz Sasiedztwa-------------------");
				for (int i=1; i<=lW;i++){
					for (int j=1; j<=lW; j++){
						macierzSasiedztwa[i][j] = Integer.parseInt(text[m++].getText());
						System.out.print(macierzSasiedztwa[i][j]+"\t");
						if (i==j){
							macierzSasiedztwa[i][j] = 0;
							continue;
						}
						if (macierzSasiedztwa[i][j] == 0)
	                        macierzSasiedztwa[i][j] = MAX;
					}
					System.out.println();
				}
				Johnson johnsonsAlgorithm = new Johnson(lW);
	           johnsonsAlgorithm.johnsonsAlgorithms(macierzSasiedztwa);
				
	            if (johnsonsAlgorithm.wYNIK !=null) {
					m = 0;
					for (int i = 0; i < lW; i++) {
						for (int j = 0; j < lW; j++) {
							if (johnsonsAlgorithm.wYNIK[i+1][j+1]<500 && i!=j){
								text[m].setBackground(new Color(0xA3A0FF));
								text[m].setFont(new Font(null,Font.BOLD,18));
							}
							else {
								text[m].setBackground(new Color(0xFFFFFF));
								text[m].setFont(new Font(null,Font.BOLD,12));
							}
							text[m++].setText(johnsonsAlgorithm.wYNIK[i+1][j+1] + "");
						}
					}
				}
			}
		});



		
		
	}


}
