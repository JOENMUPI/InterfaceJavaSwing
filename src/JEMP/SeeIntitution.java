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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class SeeIntitution extends JPanel{
	private ArrayList<JLabel> l;
	private JButton b;
	private ArrayList<JPanel> p;
	private ArrayList<JTextField> tf;
	private JScrollPane sp,sp1;
	private DefaultTableModel m;
	private Conection conn;
	private ResultSet rs;
	private JTextArea ta;
	int j = 0;
	
	public void components(){
		conn = new Conection();
	
		sp1 = new JScrollPane(new JTable(m = new DefaultTableModel()));
		sp = new JScrollPane(ta = new JTextArea(5,15));
		
		b = new JButton("Search");
		
		tf = new ArrayList<JTextField>();
		l = new ArrayList<JLabel>();
		p = new ArrayList<JPanel>();
	}

	public void text(){	
		for(int i=0;i<=3;i++){
			tf.add(new JTextField(15));
			tf.get(i).setText("");
			
			tf.get(i).setForeground(Color.GRAY);
			tf.get(i).setBorder(new LineBorder(Color.GRAY));
		}

		sp.setBorder(new LineBorder(Color.GRAY));
		sp1.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){ b.setBackground(Color.GRAY);}
	
	public void label(){
		for(int i=0;i<=6;i++){
			l.add(new JLabel());
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Search Intitution");
		l.get(1).setText("Name:");
		l.get(2).setText("Phone:");
		l.get(3).setText("Id:");
		l.get(4).setText("Direction:");
		l.get(5).setText("Supervisor(Id):");
		l.get(6).setText("Intitution List");
	}
	
	public void table(String s){
		m.setNumRows(0);
		m.setRowCount(0);
		
		if(j == 0){
			m.addColumn("Id");
			m.addColumn("Name");
			m.addColumn("Direction");
			m.addColumn("Phone");
			m.addColumn("Supervisor");
			j++;
		}
		
		rs = conn.select(s);
		try{
			while(rs.next()){
				 Object[] o = new Object[5];
				 o[0] = rs.getInt("ID_INTITUTION");
	             o[1] = rs.getString("NAME");
	             o[2] = rs.getString("DIRECCTION");
	             o[3] = rs.getString("PHONE");
	             o[4] = rs.getInt("SUPERVISOR_ID");
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
			if(i == 1){ s += " AND (PHONE = '"+tf.get(1).getText()+"')"; }
			else{ s = "(PHONE = '"+tf.get(1).getText()+"')"; i = 1; }
		}
		
		if(tf.get(2).getText().length() != 0){
			if(i == 1){ s += " AND (ID_INTITUTION = "+tf.get(2).getText()+")"; }
			else{ s = "(ID_INTITUTION = "+tf.get(2).getText()+")"; i = 1; }
		}
		
		if(ta.getText().length() != 0){ 
			if(i == 1){ s += " AND (DIRECCTION = '"+ta.getText()+"')"; }
			else{ s = "(DIRECCTION = '"+tf.get(3).getText()+"')"; i = 1; }
		}
		if(tf.get(4).getText().length() != 0){ 
			if(i == 1){ s += " AND (SUPERVISOR_ID = "+tf.get(3).getText()+")"; }
			else{ s = "(SUPERVISOR_ID = "+tf.get(3).getText()+")"; i = 1; }
		}
		
		table("SELECT *FROM INTITUTION WHERE "+s);
		return i;
	}
	
	public void panel(){
		int j = 0;
		label();
		text();
		button();
		table("SELECT *FROM INTITUTION");
		
		for(int i=0;i<=18;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 13){ p.get(i).setLayout(new FlowLayout()); }
			if(i>13 && i<=17){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 18){ p.get(i).setLayout(new GridLayout(7,1)); } 
			
			if(i<=6){ p.get(i).add(l.get(i)); }
			if(i>9 && i<=13){ p.get(i).add(tf.get(j)); j++;}
		}
		
		p.get(7).add(sp);
		p.get(8).add(sp1);
		p.get(9).add(b);
		
		p.get(18).add(p.get(1));
		p.get(18).add(p.get(10));
		p.get(18).add(p.get(2));
		p.get(18).add(p.get(11));
		p.get(18).add(p.get(3));
		p.get(18).add(p.get(12));
		p.get(18).add(p.get(4));
		
		
		p.get(14).add(p.get(5),BorderLayout.NORTH);
		p.get(14).add(p.get(13),BorderLayout.CENTER);
		
		p.get(15).add(p.get(18),BorderLayout.NORTH);
		p.get(15).add(p.get(7),BorderLayout.CENTER);
		p.get(15).add(p.get(14),BorderLayout.SOUTH);
		
		p.get(16).add(p.get(0),BorderLayout.NORTH);
		p.get(16).add(p.get(15),BorderLayout.CENTER);
		p.get(16).add(p.get(9),BorderLayout.SOUTH);
		
		p.get(17).add(p.get(6),BorderLayout.NORTH);
		p.get(17).add(p.get(8),BorderLayout.CENTER);
	}
	
	public void action(){ 
		this.b.addActionListener((e) -> { 
			if(search() == 1){ search(); text();}
			else{ JOptionPane.showMessageDialog(null,"No intitution to search."); }
		}); 
	}
	
	public SeeIntitution(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(16));
		this.add(p.get(17));
	}
}