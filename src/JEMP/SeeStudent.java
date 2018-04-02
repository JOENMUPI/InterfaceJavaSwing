package JEMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class SeeStudent extends JPanel{
	private ArrayList<JPanel> p;
	private JButton b;
	private ArrayList<JLabel> l;
	private ArrayList<JTextField> tf;
	private JScrollPane sp;
	private DefaultTableModel m;
	private ResultSet rs;
	private Conection conn;
	int j = 0;
	
	public void components(){
		conn = new Conection();
		
		b = new JButton("Search");
		
		sp = new JScrollPane(new JTable(m = new DefaultTableModel()));
		
		tf = new ArrayList<JTextField>();
		l = new ArrayList<JLabel>();
		p = new ArrayList<JPanel>();
		
	}
	public void label(){
		for(int i=0;i<=5;i++){
			l.add(new JLabel());
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Search student");
		l.get(1).setText("Name:");
		l.get(2).setText("Last Name:");
		l.get(3).setText("Id:");
		l.get(4).setText("Career(Id):");
		l.get(5).setText("Student list");
	}
	
	public void text(){	
		for(int i=0;i<=3;i++){
			tf.add(new JTextField(15));
			tf.get(i).setText("");
			
			tf.get(i).setForeground(Color.GRAY);
			tf.get(i).setBorder(new LineBorder(Color.GRAY));
		}
		
		sp.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void table(String s){
		m.setNumRows(0);
		m.setRowCount(0);
		
		if(j == 0){
			m.addColumn("Id");
			m.addColumn("Name");
			m.addColumn("Last name");
			m.addColumn("Career");
			j++;
		}
		
		rs = conn.select(s);
		try{
			while(rs.next()){
				 Object[] o = new Object[4];
				 o[0] = rs.getInt("ID_STUDENT");
	             o[1] = rs.getString("NAME");
	             o[2] = rs.getString("LAST_NAME");
	             o[3] = rs.getInt("CAREER_ID");
	             m.addRow(o);
			}
		}
		catch(Exception e){ System.out.println("Error al llenar la tabla"+e); }
		conn.stmtClose();
		conn.connClose();
	}
	
	public int search(){
		int i = 0;
		String s = null;
		
		if(tf.get(0).getText().length() != 0){ 
			if(i == 1){ s += " AND (NAME = '"+tf.get(0).getText()+"')"; }
			else{ s = "(NAME = '"+tf.get(0).getText()+"')"; i = 1; }
			}
		
		if(tf.get(1).getText().length() != 0){ 
			if(i == 1){ s += " AND (LAST_NAME = '"+tf.get(1).getText()+"')"; }
			else{ s = "(LAST_NAME = '"+tf.get(1).getText()+"')"; i = 1; }
		}
		
		if(tf.get(2).getText().length() != 0){
			if(i == 1){ s += " AND (ID_STUDENT = "+tf.get(2).getText()+")"; }
			else{ s = "(ID_STUDENT = "+tf.get(2).getText()+")"; i = 1; }
		}
		
		if(tf.get(3).getText().length() != 0){ 
			if(i == 1){ s += " AND (CAREER_ID = "+tf.get(3).getText()+")"; }
			else{ s = "(CAREER_ID = "+tf.get(3).getText()+")"; i = 1; }
		}
		table("SELECT *FROM STUDENT WHERE "+s);
		return i;
	}
	
	public void panel(){
		int j = 0;
		label();
		text();
		button();
		table("SELECT *FROM STUDENT");
		
		for(int i=0;i<=14;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 11){ p.get(i).setLayout(new FlowLayout()); }
			if(i>11 && i<=13){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 14){ p.get(i).setLayout(new GridLayout(8,1)); } 
			
			if(i<=5){ p.get(i).add(l.get(i)); }
			if(i>6 && i<=10){ p.get(i).add(tf.get(j)); j++;}
		}
		
		p.get(6).add(b);
		p.get(11).add(sp);
		
		p.get(14).add(p.get(1));
		p.get(14).add(p.get(7));
		p.get(14).add(p.get(2));
		p.get(14).add(p.get(8));
		p.get(14).add(p.get(3));
		p.get(14).add(p.get(9));
		p.get(14).add(p.get(4));
		p.get(14).add(p.get(10));
		
		p.get(12).add(p.get(5),BorderLayout.NORTH);
		p.get(12).add(p.get(11),BorderLayout.WEST);
		
		p.get(13).add(p.get(0),BorderLayout.NORTH);
		p.get(13).add(p.get(14),BorderLayout.WEST);
		p.get(13).add(p.get(6),BorderLayout.SOUTH);
	}
	
	public void action(){ 
		this.b.addActionListener((e) -> { 
			if(search() == 1){ search(); text();}
			else{ JOptionPane.showMessageDialog(null,"No student to search."); }
		}); 
	}

	public SeeStudent(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(13));
		this.add(p.get(12));
	}
}