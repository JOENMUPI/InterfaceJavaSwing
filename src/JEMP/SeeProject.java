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

public class SeeProject extends JPanel{
	private ArrayList<JLabel> l;
	private JButton b;
	private ArrayList<JPanel> p;
	private JTextField tf,tf1;
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
		
		tf = new JTextField(15);
		tf1 = new JTextField(15);
		
		l = new ArrayList<JLabel>();
		p =  new ArrayList<JPanel>();
	}
	
	public void label(){
		for(int i=0;i<=4;i++){
			l.add(new JLabel());
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Search project");
		l.get(1).setText("Id:");
		l.get(2).setText("Tittle");
		l.get(3).setText("Activities");
		l.get(4).setText("Projects List");
	}
	
	public void text(){
		tf.setText("");
		tf1.setText("");
		
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
		tf1.setForeground(Color.GRAY);
		tf1.setBorder(new LineBorder(Color.GRAY));
		
		sp.setBorder(new LineBorder(Color.GRAY));
		sp1.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void table(String s){
		m.setNumRows(0);
		m.setRowCount(0);
		
		if(j == 0){
			m.addColumn("Id");
			m.addColumn("Tittle");
			m.addColumn("Activities");
			j++;
		}
		
		rs = conn.select(s);
		try{
			while(rs.next()){
				 Object[] o = new Object[3];
				 o[0] = rs.getInt("ID_PROYECT");
				 o[1] = rs.getString("TITTLE");
	             o[2] = rs.getString("ACTIVITIES");
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
		
		if(tf.getText().length() != 0){ 
			if(i == 1){ s += " AND (ID_PROYECT = "+tf.getText()+")"; }
			else{ s = "(ID_PROYECT = "+tf.getText()+")"; i = 1; }
			}
		
		if(tf1.getText().length() != 0){ 
			if(i == 1){ s += " AND (TITTLE = '"+tf1.getText()+"')"; }
			else{ s = "(TITTLE = '"+tf1.getText()+"')"; i = 1; }
		}
		
		if(ta.getText().length() != 0){
			if(i == 1){ s += " AND (ACTIVITIES = '"+ta.getText()+"')"; }
			else{ s = "(ACTIVITIES = '"+ta.getText()+"')"; i = 1; }
		}
		
		table("SELECT *FROM PROYECT WHERE "+s);
		return i;
	}
	
	public void panel(){
		label();
		text();
		button();
		table("SELECT *FROM PROYECT");
		
		for(int i=0;i<=13;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 9){ p.get(i).setLayout(new FlowLayout()); }
			if(i>9 && i<=12){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 13){ p.get(i).setLayout(new GridLayout(5,1)); }
			
			if(i<=4){ p.get(i).add(l.get(i));}
		}
	
		p.get(5).add(b);
		p.get(6).add(tf);
		p.get(7).add(tf1);
		p.get(8).add(sp);
		p.get(9).add(sp1);

		p.get(13).add(p.get(1));
		p.get(13).add(p.get(6));
		p.get(13).add(p.get(2));
		p.get(13).add(p.get(7));
		p.get(13).add(p.get(3));
		
		p.get(10).add(p.get(13),BorderLayout.NORTH);
		p.get(10).add(p.get(8),BorderLayout.CENTER);
		
		p.get(11).add(p.get(0),BorderLayout.NORTH);
		p.get(11).add(p.get(10),BorderLayout.CENTER);
		p.get(11).add(p.get(5),BorderLayout.SOUTH);
		
		p.get(12).add(p.get(4),BorderLayout.NORTH);
		p.get(12).add(p.get(9),BorderLayout.CENTER);
	}
	
	public void action(){ 
		this.b.addActionListener((e) -> { 
			if(search() == 1){ search(); text();}
			else{ JOptionPane.showMessageDialog(null,"No project to search."); }
		}); 
	}
	
	public SeeProject(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(11));
		this.add(p.get(12));
	}
}