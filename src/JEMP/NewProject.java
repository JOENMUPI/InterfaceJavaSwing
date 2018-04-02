package JEMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class NewProject extends JPanel{
	private ArrayList<JLabel> l;
	private JButton b;
	private ArrayList<JPanel> p;
	private JTextField tf,tf1;
	private JTextArea ta;
	private JScrollPane sp;
	private Conection conn;
	
	public void components(){
		sp = new JScrollPane(ta = new JTextArea(5,15));
		
		b = new JButton("Record");
		
		tf = new JTextField(15);
		tf1 = new JTextField(15);
		
		conn = new Conection();
		
		l = new ArrayList<JLabel>();
		p =  new ArrayList<JPanel>();
	}
	
	public void label(){
		for(int i=0;i<=3;i++){
			l.add(new JLabel());
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("New project");
		l.get(1).setText("Id:");
		l.get(2).setText("Tittle");
		l.get(3).setText("Activities");
	}
	
	public void text(){
		tf.setText("");
		tf1.setText("");
		ta.setText("");
		
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
		tf1.setForeground(Color.GRAY);
		tf1.setBorder(new LineBorder(Color.GRAY));
		
		sp.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void panel(){
		label();
		text();
		button();
		
		for(int i=0;i<=10;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 7){ p.get(i).setLayout(new FlowLayout()); }
			if(i>7 && i<=9){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 10){ p.get(i).setLayout(new GridLayout(5,1)); } 
			if(i <= 3){ p.get(i).add(l.get(i)); }
		}
		p.get(4).add(b);
		p.get(5).add(tf);
		p.get(6).add(tf1);
		p.get(7).add(sp);

		p.get(10).add(p.get(1));
		p.get(10).add(p.get(5));
		p.get(10).add(p.get(2));
		p.get(10).add(p.get(6));
		p.get(10).add(p.get(3));
		
		p.get(8).add(p.get(10),BorderLayout.NORTH);
		p.get(8).add(p.get(7),BorderLayout.CENTER);
		
		p.get(9).add(p.get(0),BorderLayout.NORTH);
		p.get(9).add(p.get(8),BorderLayout.CENTER);
		p.get(9).add(p.get(4),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.addActionListener((e) -> {
			conn.execute("INSERT INTO PROYECT(ID_PROYECT,TITTLE,ACTIVITIES) VALUES("+tf.getText()+",'"+tf1.getText()+"','"+ta.getText()+"')");
			conn.connClose(); conn.stmtClose(); text();
			JOptionPane.showMessageDialog(null,"New project saved.");
		});
	}
	
	public NewProject(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(9));
	}
}