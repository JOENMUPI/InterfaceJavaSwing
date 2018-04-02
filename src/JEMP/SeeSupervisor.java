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

public class SeeSupervisor extends JPanel {
	private ArrayList<JPanel> p;
	private JButton b;
	private ArrayList<JLabel> l;
	private ArrayList<JTextField> tf;
	private JScrollPane sp;
	private DefaultTableModel m;
	private Conection conn;
	private ResultSet rs;
	int j = 0;
	
	public void components(){
		conn = new Conection();
		
		b = new JButton("Search");
		
		m= new DefaultTableModel();
		sp = new JScrollPane(new JTable(m));
		
		tf = new ArrayList<JTextField>();
		l = new ArrayList<JLabel>();
		p = new ArrayList<JPanel>();
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void text(){	
		for(int i=0;i<=4;i++){
			tf.add(new JTextField(15));
			tf.get(i).setText("");
			
			tf.get(i).setForeground(Color.GRAY);
			tf.get(i).setBorder(new LineBorder(Color.GRAY));
		}
		
		sp.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void label(){
		for(int i=0;i<=6;i++){
			l.add(new JLabel());
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Search supervisor");
		l.get(1).setText("Name:");
		l.get(2).setText("Last Name:");
		l.get(3).setText("Id:");
		l.get(4).setText("Level(Id):");
		l.get(5).setText("Position(Id):");
		l.get(6).setText("Lists Supervisors");
	}
	
	public void table(String s){
		m.setNumRows(0);
		m.setRowCount(0);
		
		if(j == 0){
			m.addColumn("Id");
			m.addColumn("Name");
			m.addColumn("Last name");
			m.addColumn("Level");
			m.addColumn("Position");
			j++;
		}
		
		rs = conn.select(s);
		try{
			while(rs.next()){
				 Object[] o = new Object[5];
				 o[0] = rs.getInt("ID_SUPERVISOR");
	             o[1] = rs.getString("NAME");
	             o[2] = rs.getString("LAST_NAME");
	             o[3] = rs.getInt("LEVEL_ID");
	             o[4] = rs.getInt("POSITION_ID");
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
			if(i == 1){ s += " AND (ID_SUPERVISOR = "+tf.get(2).getText()+")"; }
			else{ s = "(ID_SUPERVISOR = "+tf.get(2).getText()+")"; i = 1; }
		}
		
		if(tf.get(3).getText().length() != 0){ 
			if(i == 1){ s += " AND (LEVEL_ID = "+tf.get(3).getText()+")"; }
			else{ s = "(LEVEL_ID = "+tf.get(3).getText()+")"; i = 1; }
		}
		
		if(tf.get(4).getText().length() != 0){ 
			if(i == 1){ s += " AND (POSITION_ID = "+tf.get(4).getText()+")"; }
			else{ s = "(POSITION_ID = "+tf.get(4).getText()+")"; i = 1; }
		}
		
		table("SELECT *FROM SUPERVISOR WHERE "+s);
		return i;
	}
	
	public void panel(){
		int j = 0;
		label();
		text();
		button();
		table("SELECT *FROM SUPERVISOR");
		
		for(int i=0;i<=16;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 13){ p.get(i).setLayout(new FlowLayout()); }
			if(i>13 && i<=15){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 16){ p.get(i).setLayout(new GridLayout(10,1)); } 
			
			if(i<=6){ p.get(i).add(l.get(i)); }
			if(i>7 && i<=12){ p.get(i).add(tf.get(j)); j++;}
		}
			
		p.get(7).add(b);
		p.get(13).add(sp);
		
		p.get(16).add(p.get(1));
		p.get(16).add(p.get(8));
		p.get(16).add(p.get(2));
		p.get(16).add(p.get(9));
		p.get(16).add(p.get(3));
		p.get(16).add(p.get(10));
		p.get(16).add(p.get(4));
		p.get(16).add(p.get(11));
		p.get(16).add(p.get(5));
		p.get(16).add(p.get(12));
		
		p.get(14).add(p.get(6),BorderLayout.NORTH);
		p.get(14).add(p.get(13),BorderLayout.CENTER);
		
		p.get(15).add(p.get(0),BorderLayout.NORTH);
		p.get(15).add(p.get(16),BorderLayout.CENTER);
		p.get(15).add(p.get(7),BorderLayout.SOUTH);
	}
	
	public void action(){ this.b.addActionListener((e) -> { 
		if(search() == 1){ search(); text(); }
		else{ JOptionPane.showMessageDialog(null,"No supervisor to search."); }
		}); 
	}
	
	public SeeSupervisor(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(15));
		this.add(p.get(14));
	}
}