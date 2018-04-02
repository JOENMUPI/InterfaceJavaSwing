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

public class NEW extends JPanel{
	private ArrayList<JLabel> l;
	private JButton b;
	private ArrayList<JPanel> p;
	private JTextField tf;
	private JTextArea ta;
	private JScrollPane sp;
	private Conection conn;
	
	public void components(){
		ta = new JTextArea(5,15);
		sp = new JScrollPane(ta);
		
		b = new JButton("Record");
		tf = new JTextField(15);
		conn = new Conection();
		
		l = new ArrayList<JLabel>();
		p =  new ArrayList<JPanel>();
	}
	
	public void label(String s){
		for(int i=0;i<=2;i++){
			l.add(new JLabel());
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("New "+s);
		l.get(2).setText("Id:");
		l.get(1).setText("Desciption:");
	}
	
	public void text(){
		tf.setText("");
		ta.setText("");
		
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
		sp.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void panel(String s){
		label(s);
		text();
		button();
		
		for(int i=0;i<=8;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 5){ p.get(i).setLayout(new FlowLayout()); }
			if(i>5 && i<=7){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 8){ p.get(i).setLayout(new GridLayout(3,1)); } 
			
			if(i<=2){ p.get(i).add(l.get(i)); }
		}
		p.get(3).add(b);
		p.get(4).add(tf);
		p.get(5).add(sp);

		p.get(8).add(p.get(2));
		p.get(8).add(p.get(4));
		p.get(8).add(p.get(1));
		
		p.get(6).add(p.get(8),BorderLayout.NORTH);
		p.get(6).add(p.get(5),BorderLayout.CENTER);
		
		p.get(7).add(p.get(0),BorderLayout.NORTH);
		p.get(7).add(p.get(6),BorderLayout.CENTER);
		p.get(7).add(p.get(3),BorderLayout.SOUTH);
	}
	
	public void action(String s){
		this.b.addActionListener((e) -> {
			conn.execute("INSERT INTO "+s+"(ID_"+s+",DESCRIPTION) VALUES("+tf.getText()+",'"+ta.getText()+"')");
			conn.connClose(); conn.stmtClose(); text();
			JOptionPane.showMessageDialog(null,"New "+s+" saved");
		});
	}
	
	public NEW(String s){
		components();
		panel(s);
		action(s);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(7));
	}
}