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

public class SEE extends JPanel{
	private ArrayList<JLabel> l;
	private JButton b;
	private ArrayList<JPanel> p;
	private JTextField tf;
	private JScrollPane sp,sp1;
	private DefaultTableModel m;
	private Conection conn;
	private ResultSet rs;
	private JTextArea ta;
	int j =0;
	
	public void components(){
		conn = new Conection();
		
		sp1 = new JScrollPane(new JTable(m = new DefaultTableModel()));
		sp = new JScrollPane(ta = new JTextArea(5,15));
		
		b = new JButton("Search");
		
		tf = new JTextField(15);
		
		l = new ArrayList<JLabel>();
		p =  new ArrayList<JPanel>();
	}
	
	public void label(String s){
		for(int i=0;i<=3;i++){
			l.add(new JLabel());
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Search "+s);
		l.get(1).setText("Id:");
		l.get(2).setText("Description:");
		l.get(3).setText(s+" list");

	}
	
	public void text(){
		tf.setText("");
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
		sp.setBorder(new LineBorder(Color.GRAY));
		sp1.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void table(String s,String s1){
		m.setNumRows(0);
		m.setRowCount(0);
		
		if(j == 0){
			m.addColumn("Id");
			m.addColumn("Description");
			j++;
		}
		
		rs = conn.select(s1+s);
		try{
			while(rs.next()){
				 Object[] o = new Object[2];
				 o[0] = rs.getInt("ID_"+s);
	             o[1] = rs.getString("DESCRIPTION");
	             m.addRow(o);
			}
		}
		catch(Exception e){ System.out.println("Error al llenar la tabla"+e); }
		conn.stmtClose();
		conn.connClose();
	}
	
	public int search(String s1){
		int i = 0;
		String s = null;
		
		if(tf.getText().length() != 0){ 
			if(i == 1){ s += " AND (ID_"+s1+" = "+tf.getText()+")"; }
			else{ s = "(ID_"+s1+" = "+tf.getText()+")"; i = 1; }
			}
		
		if(ta.getText().length() != 0){ 
			if(i == 1){ s += " AND (DESCRIPTION = '"+ta.getText()+"')"; }
			else{ s = "(DESCRIPTION = '"+ta.getText()+"')"; i = 1; }
		}
		
		table(s1+" WHERE "+s,"SELECT *FROM ");
		return i;
	}
	
	public void panel(String s){
		label(s);
		text();
		button();
		table(s," SELECT *FROM ");
		
		for(int i=0;i<=11;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 7){ p.get(i).setLayout(new FlowLayout()); }
			if(i>7 && i<=10){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 11){ p.get(i).setLayout(new GridLayout(3,1)); }
			
			if(i<=3){ p.get(i).add(l.get(i));}
		}
	
		p.get(4).add(b);
		p.get(5).add(tf);
		p.get(6).add(sp);
		p.get(7).add(sp1);

		p.get(11).add(p.get(1));
		p.get(11).add(p.get(5));
		p.get(11).add(p.get(2));
		
		p.get(8).add(p.get(11),BorderLayout.NORTH);
		p.get(8).add(p.get(6),BorderLayout.CENTER);
		
		p.get(9).add(p.get(0),BorderLayout.NORTH);
		p.get(9).add(p.get(8),BorderLayout.CENTER);
		p.get(9).add(p.get(4),BorderLayout.SOUTH);
		
		p.get(10).add(p.get(3),BorderLayout.NORTH);
		p.get(10).add(p.get(7),BorderLayout.CENTER);
	}
	
	public void action(String s){ 
		this.b.addActionListener((e) -> { 
			if( search(s) == 1){ search(s); text(); }
			else{ JOptionPane.showMessageDialog(null,"No "+s+" to search."); }
		}); 
	}
	
	public SEE(String s){
		components();
		panel(s);
		action(s);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(9));
		this.add(p.get(10));
	}
}