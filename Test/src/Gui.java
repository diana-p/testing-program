import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
//����� �������� ������������ ����������
public class Gui extends JFrame{
	private static final long serialVersionUID = 1L;
	
	int k, ball;
	JPanel[] cards = new JPanel[23];
	JPanel card = new JPanel(new CardLayout());;
	JButton start = new JButton("������ ������������");
	JButton end = new JButton("��������� ������������");
	JButton next = new JButton("�������� ������");
	int i = 2;
	long startt = System.currentTimeMillis();
	String fam, name;
	JLabel lfam = new JLabel("�������:");
	JLabel lname = new JLabel("���:");
	JTextField forfam = new JTextField(25);
	JTextField forname = new JTextField(25);
	long timeConsumedMillis;

	
	Gui(){
		
		JFrame jfrm = new JFrame("����: ���� � ����� � �����");
		jfrm.setSize (650, 300);
		jfrm.setLocationRelativeTo(null);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel(); 
		jfrm.add(p); 
		p.setLayout(new  BoxLayout(p, BoxLayout.PAGE_AXIS));
		JLabel lab1 = new JLabel("�������, ����� ������ ������������.");
		JLabel lab2 = new JLabel("����� ������ ���� ������� ������� � ���.");
		JLabel lab3 = new JLabel("�� ���� �������� ������ ���� ���������� �����!");
		
		for (int i=0; i<23; i++) {
			cards[i] = new JPanel();
			cards[i].setLayout((new  BoxLayout(cards[i], BoxLayout.PAGE_AXIS)));
			String m = "Card" + i;
			card.add(cards[i], m);
		}
		
		CardLayout cardLayout = (CardLayout) card.getLayout();
		cardLayout.show(card, "Card0");
		
		forfam.setMaximumSize(new Dimension(Integer.MAX_VALUE, forfam.getMinimumSize().height));
		forname.setMaximumSize(new Dimension(Integer.MAX_VALUE, forname.getMinimumSize().height));
		
		cards[0].add(lab2);
		cards[0].add(Box.createRigidArea(new Dimension(0,5)));
		cards[0].add(lfam);
		cards[0].add(Box.createRigidArea(new Dimension(0,5)));
		cards[0].add(forfam);
		cards[0].add(Box.createRigidArea(new Dimension(0,5)));
		cards[0].add(lname);
		cards[0].add(Box.createRigidArea(new Dimension(0,5)));
		cards[0].add(forname);
		cards[0].add(Box.createRigidArea(new Dimension(0,15)));
		
		cards[0].add(lab1);
		cards[0].add(Box.createRigidArea(new Dimension(0,5)));
		cards[0].add(lab3);
		cards[0].add(Box.createRigidArea(new Dimension(0,10)));
		cards[0].add(start);
		start.setActionCommand("start");
		ActionListener actionListener1 = new TestActionListener1();
		start.addActionListener(actionListener1);
		
		try {
			//������ �������� �� ����� � ������� XML
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse("test.xml");
			
			NodeList nodeList = doc.getElementsByTagName("list");
			
			for (int i = 0; i<20; i++) {
				Node qw = nodeList.item(i);
				Element element = (Element) qw;
				
				String answer = element.getAttribute("id");
				int a = Integer.parseInt(answer);
				
				JLabel lab = new JLabel(element.getElementsByTagName("qw").item(0).getTextContent());
				JRadioButton radio1 = new JRadioButton(element.getElementsByTagName("ans").item(0).getTextContent());
				if (a == 1) {
					radio1.setActionCommand("true");
				} 
			
				JRadioButton radio2 = new JRadioButton(element.getElementsByTagName("ans").item(1).getTextContent());	
				if (a == 2) 
					radio2.setActionCommand("true");
				
				JRadioButton radio3 = new JRadioButton(element.getElementsByTagName("ans").item(2).getTextContent());
				if (a == 3) 
					radio3.setActionCommand("true");
				
				JRadioButton radio4 = new JRadioButton(element.getElementsByTagName("ans").item(3).getTextContent());
				if (a == 4) 
					radio4.setActionCommand("true");
				
				cards[i+1].add(lab);
				cards[i+1].add(Box.createRigidArea(new Dimension(0,10)));
				ButtonGroup group = new ButtonGroup();
				radio1.setSelected(true);
			    group.add(radio1);
			    group.add(radio2);
			    group.add(radio3);
			    group.add(radio4);
			    
			    cards[i+1].add(radio1);
			    cards[i+1].add(Box.createRigidArea(new Dimension(0,5)));
			    cards[i+1].add(radio2);
			    cards[i+1].add(Box.createRigidArea(new Dimension(0,5)));
			    cards[i+1].add(radio3);
			    cards[i+1].add(Box.createRigidArea(new Dimension(0,5)));
			    cards[i+1].add(radio4);
			    cards[i+1].add(Box.createRigidArea(new Dimension(0,5)));
			    
			    JButton next = new JButton("C������� ������");
			    JButton end = new JButton("��������� ������������");
			    
			    if (i+1 == 20) {
			    	end.setActionCommand("end");
					end.addActionListener(actionListener1);
					cards[i+1].add(end);
			    	
			    }
			    else {
			    	ActionListener actionListener = new TestActionListener(radio1, radio2, radio3,radio4);
					next.setActionCommand("next");
					next.addActionListener(actionListener);
					cards[i+1].add(next);
					cards[i+1].add(Box.createRigidArea(new Dimension(0,10)));
					JLabel labend = new JLabel("�������, ���� �� ������ ���������� ������������:");
					
					cards[i+1].add(labend);
					cards[i+1].add(Box.createRigidArea(new Dimension(0,5)));
					end.setActionCommand("end");
					end.addActionListener(actionListener1);
					cards[i+1].add(end);
			    	
			    }
				}
			}
			catch(ParserConfigurationException e) {
				e.printStackTrace();
			}
			catch(SAXException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		
		end.setActionCommand("end");
		end.addActionListener(actionListener1);
		cards[21].add(end);
		
		p.add(card);
		jfrm.setVisible(true);
		
	}
	
//���������� ������� ������ ����������� ������
public class TestActionListener implements ActionListener {
	
	private JRadioButton r1;
	private JRadioButton r2;
	private JRadioButton r3;
	private JRadioButton r4;
	
	TestActionListener(JRadioButton radio1, JRadioButton radio2, JRadioButton radio3, JRadioButton radio4){
		r1 = radio1;
		r2 = radio2;
		r3 = radio3;
		r4 = radio4;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cardLayout = (CardLayout) card.getLayout();
		
		if (e.getActionCommand() == "next") {
			String mes = "Card" + i;
			cardLayout.show(card, mes);
			i++;
			
			if (r1.isSelected() == true && r1.getActionCommand() == "true") {
				ball++;
			}
			if (r2.isSelected() == true && r2.getActionCommand() == "true") {
				ball++;
			}
			if (r3.isSelected() == true && r3.getActionCommand() == "true") {
				ball++;
			}
			if (r4.isSelected() == true && r4.getActionCommand() == "true") {
				ball++;
			}
			write(ball, null,null);
				
		}
	}

}

//���������� ������� ����� ��������
public class TestActionListener1 implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cardLayout = (CardLayout) card.getLayout();
		fam = forfam.getText();
		name = forname.getText();
		write(ball, fam, name);
		
		
		if (e.getActionCommand() == "start") {
			if ((forfam.getText().equals("")) | (forname.getText().equals(""))) 
				JOptionPane.showMessageDialog(null, "��������� ��� �������!", "Output", JOptionPane.PLAIN_MESSAGE);
			else 
				cardLayout.show(card, "Card1");
			
		}
		if (e.getActionCommand() == "end") {
			long finish = System.currentTimeMillis();
			timeConsumedMillis = (finish - startt)/60000;
	
			try(FileWriter writer = new FileWriter("out.txt", true))
			{
				if (timeConsumedMillis < 1) {
					timeConsumedMillis = (finish - startt)/1000;
					writer.append(" ����������� �����: " + timeConsumedMillis + " ���");
				}
				else writer.append(" ����������� �����: " + timeConsumedMillis + " ���");
					
			}
			catch(IOException ex){
				System.out.println(ex.getMessage());
			} 
			
			result();
			
		}
		
	}
}

//������ ����������� � ����
public void write(int ball, String fam, String name) {

	Date date = new Date();
	SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd ");	
	
	int score;
	
	if (ball > 18)
		score = 5;
	else if (ball > 10)
		score = 4;
	else if (ball > 6)
		score = 3;
	else score = 2;
	
	
	try(FileWriter writer = new FileWriter("out.txt", false))
    {
       
        writer.append("����: " + formatForDateNow.format(date) +" "+fam + " " + name+ " ���������� ������: " + ball +" ������: " + score);
        
        writer.flush();
    }
    catch(IOException ex){
         
        System.out.println(ex.getMessage());
    }	
	
}
//����� ����������� �� �����
public void result() {

	JLabel res = new JLabel();
	CardLayout cardLayout = (CardLayout) card.getLayout();
	
	try(FileInputStream fin=new FileInputStream("out.txt"); 
			FileOutputStream fout = new FileOutputStream("result.txt", true)){
		
        BufferedReader readLines = new BufferedReader(new FileReader("result.txt"));
        int lines = 0;
        while (readLines.readLine() != null) {
            lines++;
            System.out.println(lines);
        }
        lines++;
        readLines.close();
		 
		 BufferedReader br = new BufferedReader(new FileReader("out.txt"));
		 String st = br.readLine();
		 br.close();
		 res.setText(st);
		 
		 BufferedWriter bw = new BufferedWriter(new FileWriter("result.txt", true));
		 bw.write(String.valueOf(lines) + ". " + st + System.getProperty("line.separator"));
		 bw.close();
		 
		 cards[22].add(res);
		 cardLayout.show(card, "Card22");
	}
	catch(IOException e) {
		System.out.println("Error");
	}
}
}


