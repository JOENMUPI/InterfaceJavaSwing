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

public class SeeStudentMatter extends JPanel{
	private ArrayList<JPanel> p;
	private ArrayList<JButton> b;
	private ArrayList<JLabel> l;
	private ArrayList<JTextField> tf;
	private JScrollPane sp;
	private DefaultTableModel m;
	private Conection conn;
	private ResultSet rs;
	int j = 0;

	public void components(){
		conn = new Conection();
		
		m = new DefaultTableModel();
		sp = new JScrollPane(new JTable(m));
		
		b = new ArrayList<JButton>();
		tf = new ArrayList<JTextField>();
		p = new ArrayList<JPanel>();
		l = new ArrayList<JLabel>();
	}
	
	public void label(){
		for(int i=0;i<=8;i++){
			l.add(new JLabel());
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("See student/matter");
		l.get(1).setText("Student(Id):");
		l.get(2).setText("Matter(Id):");
		l.get(3).setText("Period(Id):");
		l.get(4).setText("Project(Id):");
		l.get(5).setText("Intitution(Id):");
		l.get(6).setText("Note(Optional):");
		l.get(8).setText("List student/matter");
	}
	
	public void text(){	
		for(int i=0;i<=4;i++){
			tf.add(new JTextField(15));
			tf.get(i).setText("");
			
			tf.get(i).setForeground(Color.GRAY);
			tf.get(i).setBorder(new LineBorder(Color.GRAY));
		}
		sp.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){
		for(int i=0;i<=3;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
			b.get(i).setText("Search");
			
			if(i>0 && i<=5){ b.get(i).setBorder(new LineBorder(Color.GRAY)); }
		}
		b.get(1).setText("API");
		b.get(2).setText("APL");
		b.get(3).setText("PASS");
	}
	
	public void table(String s){
		m.setNumRows(0);
		m.setRowCount(0);
		if(j == 0){
			m.addColumn("Student");
			m.addColumn("Matter");
			m.addColumn("Period");
			m.addColumn("Note");
			m.addColumn("Proyect");
			m.addColumn("Intitution");
			j++;
		}
		
		rs = conn.select(s);
		try{
			while(rs.next()){
				 Object[] o = new Object[6];
				 o[0] = rs.getInt("ID_STUDENT");
				 o[1] = rs.getInt("ID_MATTER");
				 o[2] = rs.getInt("ID_PERIOD");
				 o[3] = rs.getString("NOTE");
				 o[4] = rs.getInt("PROYECT");
				 o[5] = rs.getInt("INTITUTION");
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
			if(i == 1){ s += " AND (ID_STUDENT = "+tf.get(0).getText()+")"; }
			else{ s = "(ID_STUDENT = '"+tf.get(0).getText()+"')"; i = 1; }
			}
		
		if(tf.get(1).getText().length() != 0){ 
			if(i == 1){ s += " AND (ID_MATTER = "+tf.get(1).getText()+")"; }
			else{ s = "(ID_MATTER = '"+tf.get(1).getText()+"')"; i = 1; }
		}
		
		if(tf.get(2).getText().length() != 0){
			if(i == 1){ s += " AND (ID_PERIOD = "+tf.get(2).getText()+")"; }
			else{ s = "(ID_PERIOD = "+tf.get(2).getText()+")"; i = 1; }
		}
		
		if(tf.get(3).getText().length() != 0){ 
			if(i == 1){ s += " AND (PROYECT = "+tf.get(3).getText()+")"; }
			else{ s = "(PROYECT = "+tf.get(3).getText()+")"; i = 1; }
		}
		if(tf.get(4).getText().length() != 0){ 
			if(i == 1){ s += " AND (INTITUTION = "+tf.get(4).getText()+")"; }
			else{ s = "(INTITUTION = "+tf.get(4).getText()+")"; i = 1; }
		}
		if(l.get(7).getText().length() != 0){ 
			if(i == 1){ s += " AND (NOTE = '"+l.get(7).getText()+"')"; }
			else{ s = "(NOTE = '"+l.get(7).getText()+"')"; i = 1; }
		}
		table("SELECT *FROM STUDENT_MATTER WHERE "+s);
		return i;
	}
	
	public void panel(){
		int j = 0,k = 0;
		label();
		text();
		button();
		table("SELECT *FROM STUDENT_MATTER");
		
		for(int i=0;i<=21;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 18){ p.get(i).setLayout(new FlowLayout()); }
			if(i>18 && i<=20){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 21){ p.get(i).setLayout(new GridLayout(12,1)); }
			
			if(i<=8){p.get(i).add(l.get(i)); }
			if(i>8 && i<=13){p.get(i).add(tf.get(j)); j++; }
			if(i>13 && i<=17){p.get(i).add(b.get(k)); k++; }
		}
		p.get(18).add(sp);
		
		p.get(6).add(p.get(7));
		p.get(15).add(p.get(16));
		p.get(15).add(p.get(17));
		
		p.get(21).add(p.get(1));
		p.get(21).add(p.get(9));
		p.get(21).add(p.get(2));
		p.get(21).add(p.get(10));
		p.get(21).add(p.get(3));
		p.get(21).add(p.get(11));
		p.get(21).add(p.get(4));
		p.get(21).add(p.get(12));
		p.get(21).add(p.get(5));
		p.get(21).add(p.get(13));
		p.get(21).add(p.get(6));
		p.get(21).add(p.get(15));
	
		p.get(19).add(p.get(8),BorderLayout.NORTH);
		p.get(19).add(p.get(18),BorderLayout.CENTER);
		
		p.get(20).add(p.get(0),BorderLayout.NORTH);
		p.get(20).add(p.get(21),BorderLayout.CENTER);
		p.get(20).add(p.get(14),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.get(0).addActionListener((e) -> { 
			if(search() == 1){ search(); text(); label(); }
			else{ JOptionPane.showMessageDialog(null,"No student/Matter to search."); }
		}); 
		this.b.get(1).addActionListener((e) -> { l.get(7).setText("API"); });
		this.b.get(2).addActionListener((e) -> { l.get(7).setText("APL"); });
		this.b.get(3).addActionListener((e) -> { l.get(7).setText("PASS"); });
	}
	
	public SeeStudentMatter(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(20));
		this.add(p.get(19));
	}
}