package JEMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class EditElement extends JFrame{
	private JLabel l;
	private JButton b;
	private ArrayList<JPanel> p;
	private JTextField tf;
	private Conection conn;
	
	
	public void components(String s){
		conn = new Conection();
		
		l = new JLabel("Edit "+s);
		b = new JButton("Edit");
		p = new ArrayList<JPanel>();
		tf = new JTextField(15);
	}
	
	public void label(){
		l.setFont(new Font("Console",Font.BOLD,25));
		l.setForeground(Color.GRAY);
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void text(){	
		tf.setText("");
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void edit(String s,String s1,String s2){
		conn.select("UPDATE "+s+" SET "+s1+" = '"+tf.getText()+"' WHERE ID_"+s+" = "+s2);
		JOptionPane.showMessageDialog(null,s+"edited");
	}
	
	public void panel(){
		text();
		button();
		label();
		
		for(int i=0;i<=3;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 2){ p.get(i).setLayout(new FlowLayout()); }
			if(i == 3){ p.get(i).setLayout(new BorderLayout()); }
		}
		p.get(0).add(l);
		p.get(1).add(tf);
		p.get(2).add(b);
		
		p.get(3).add(p.get(0),BorderLayout.NORTH);
		p.get(3).add(p.get(1),BorderLayout.CENTER);
		p.get(3).add(p.get(2),BorderLayout.SOUTH);
	}
	
	public void action(String s,String s1,String s2){
		this.b.addActionListener((e) -> { edit(s,s1,s2); text(); this.dispose(); });
	}
	
	public void frame(){
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setTitle("S.C 0.1 By Solutions");
		this.pack();
		this.setVisible(true);
	}
	
	public EditElement(String s,String s1,String s2,String s3){
		components(s);
		panel();
		action(s1,s2,s3);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(3));
		
		frame();
	}
}