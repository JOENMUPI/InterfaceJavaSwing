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

public class NewIntitution extends JPanel{
	private ArrayList<JLabel> l;
	private JButton b,b1;
	private ArrayList<JPanel> p;
	private ArrayList<JTextField> tf;
	private JScrollPane sp,sp1;
	private JTextArea ta;
	private Conection conn;
	private ResultSet rs;
	private DefaultTableModel m;
	
	public void components(){
		conn = new Conection();
		
		ta = new JTextArea(5,15);
		sp = new JScrollPane(ta);
		
		m = new DefaultTableModel();
		sp1 = new JScrollPane(new JTable(m));
		
		b = new JButton("Record");
		b1 = new JButton("Show");
		
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
		ta.setText("");
		
		sp.setBorder(new LineBorder(Color.GRAY));
		sp1.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){
		b.setBackground(Color.GRAY);
		
		b1.setBackground(Color.GRAY);
		b1.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void label(){
		for(int i=0;i<=6;i++){
			l.add(new JLabel());
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		l.get(0).setForeground(Color.GRAY);
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		
		l.get(0).setText("New Intitution");
		l.get(1).setText("Name:");
		l.get(2).setText("Phone:");
		l.get(3).setText("Id:");
		l.get(4).setText("Direction:");
		l.get(5).setText("Supervisor(Id)");
		l.get(6).setText("Supervisor List");
	}
	
	public void table(){
		m.addColumn("Id");
		m.addColumn("Name");
		m.addColumn("Last name");
		m.addColumn("Level");
		m.addColumn("Position");
		
		rs = conn.select("SELECT *FROM SUPERVISOR");
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
	
	public void panel(){
		int j = 0;
		label();
		text();
		button();
		
		for(int i=0;i<=19;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 14){ p.get(i).setLayout(new FlowLayout()); }
			if(i>14 && i<=18){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 19){ p.get(i).setLayout(new GridLayout(7,1)); } 
			
			if(i<=6){ p.get(i).add(l.get(i)); }
			if(i>6 && i<=10){p.get(i).add(tf.get(j)); j++; }
		}
		p.get(11).add(sp);
		p.get(12).add(sp1);
		p.get(13).add(b);
		p.get(14).add(b1);
		
		p.get(5).add(p.get(14));
		p.get(19).add(p.get(1));
		p.get(19).add(p.get(7));
		p.get(19).add(p.get(2));
		p.get(19).add(p.get(8));
		p.get(19).add(p.get(3));
		p.get(19).add(p.get(9));
		p.get(19).add(p.get(4));
		
		p.get(15).add(p.get(5),BorderLayout.NORTH);
		p.get(15).add(p.get(10),BorderLayout.WEST);
		
		p.get(16).add(p.get(19),BorderLayout.NORTH);
		p.get(16).add(p.get(11),BorderLayout.CENTER);
		p.get(16).add(p.get(15),BorderLayout.SOUTH);
		
		p.get(17).add(p.get(0),BorderLayout.NORTH);
		p.get(17).add(p.get(16),BorderLayout.CENTER);
		p.get(17).add(p.get(13),BorderLayout.SOUTH);
		
		p.get(18).add(p.get(6),BorderLayout.NORTH);
		p.get(18).add(p.get(12),BorderLayout.CENTER);
	}
	
	public void action(){
		this.b.addActionListener((e) -> {
			conn.execute("INSERT INTO INTITUTION(NAME,PHONE,ID_INTITUTION,DIRECCTION,SUPERVISOR_ID) VALUES('"+tf.get(0).getText()+"','"+tf.get(1).getText()+"',"+tf.get(2).getText()+",'"+ta.getText()+"',"+tf.get(3).getText()+")");
			conn.connClose(); conn.stmtClose(); text();
			JOptionPane.showMessageDialog(null, "New intituiton Saved.");
		});
		
		this.b1.addActionListener((e) -> { table(); });
	}
	
	public NewIntitution(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(17));
		this.add(p.get(18));
	}
}