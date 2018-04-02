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

public class NewStudent extends JPanel{
	private ArrayList<JPanel> p;
	private DefaultTableModel m;
	private JButton b,b1;
	private ArrayList<JLabel> l;
	private ArrayList<JTextField> tf;
	private JScrollPane sp;
	private Conection conn;
	private ResultSet rs;
	int j = 0;
	
	public void components(){
		conn = new Conection();
		
		m = new DefaultTableModel();
		sp = new JScrollPane(new JTable(m));
		
		b = new JButton("Record");
		b1 = new JButton("Show");
		
		tf = new ArrayList<JTextField>();
		p = new ArrayList<JPanel>();
		l = new ArrayList<JLabel>();
		
	}
	public void label(){
		for(int i=0;i<=5;i++){
			l.add(new JLabel());
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("New student");
		l.get(1).setText("Name:");
		l.get(2).setText("Last name:");
		l.get(3).setText("Id:");
		l.get(4).setText("Career(Id):");
		l.get(5).setText("Career List");
	}
	
	public void table(){
		m.setNumRows(0);
		m.setRowCount(0);
		
		if(j == 0){ m.addColumn("Id"); m.addColumn("Description"); j++;}
		
		rs = conn.select("SELECT *FROM CAREER");
		try{
			while(rs.next()){
				 Object[] o = new Object[2];
				 o[0] = rs.getInt("ID_CAREER");
	             o[1] = rs.getString("DESCRIPTION");
	             m.addRow(o);
			}
		}
		catch(Exception e){ System.out.println("Error al llenar la tabla"+e); }
		conn.stmtClose();
		conn.connClose();
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
	
	public void button(){
		b.setBackground(Color.GRAY);
		
		b1.setBackground(Color.GRAY);
		b1.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void panel(){
		int j = 0;
		label();
		text();
		button();
		
		for(int i=0;i<=15;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 12){ p.get(i).setLayout(new FlowLayout()); }
			if(i>12 && i<=14){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 15){ p.get(i).setLayout(new GridLayout(8,1)); } 
			
			if(i<=5){ p.get(i).add(l.get(i)); }
			if(i>7  && i<=11){ p.get(i).add(tf.get(j)); j++;}
		}
		
		p.get(6).add(b);
		p.get(7).add(b1);
		p.get(12).add(sp);
		
		p.get(4).add(p.get(7));
		p.get(15).add(p.get(1));
		p.get(15).add(p.get(8));
		p.get(15).add(p.get(2));
		p.get(15).add(p.get(9));
		p.get(15).add(p.get(3));
		p.get(15).add(p.get(10));
		p.get(15).add(p.get(4));
		p.get(15).add(p.get(11));
		
		p.get(13).add(p.get(5),BorderLayout.NORTH);
		p.get(13).add(p.get(12),BorderLayout.WEST);
		
		p.get(14).add(p.get(0),BorderLayout.NORTH);
		p.get(14).add(p.get(15),BorderLayout.WEST);
		p.get(14).add(p.get(6),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.addActionListener((e) -> {
			conn.execute("INSERT INTO STUDENT(NAME,LAST_NAME,ID_STUDENT,CAREER_ID) VALUES('"+tf.get(0).getText()+"','"+tf.get(1).getText()+"',"+tf.get(2).getText()+","+tf.get(3).getText()+")");
			conn.connClose(); conn.stmtClose(); text();
			JOptionPane.showMessageDialog(null, "New student Saved.");
		});
		
		this.b1.addActionListener((e) -> { table(); });
	}

	public NewStudent(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(14));
		this.add(p.get(13));
	}
}