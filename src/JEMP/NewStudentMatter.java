package JEMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class NewStudentMatter extends JPanel{
	private ArrayList<JPanel> p;
	private ArrayList<JButton> b;
	private ArrayList<JLabel> l;
	private ArrayList<JTextField> tf;
	private JScrollPane sp;
	private JTabbedPane tp;
	private Conection conn;
	private ResultSet rs;
	private DefaultTableModel m;
	private JTable t;

	public void components(){
		conn = new Conection();
		
		m = new DefaultTableModel();
		t = new JTable(m);
		sp = new JScrollPane(t);
		
		tp = new JTabbedPane();
		
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
		
		l.get(0).setText("New student/matter");
		l.get(1).setText("Student(Id):");
		l.get(2).setText("Matter(Id):");
		l.get(3).setText("Period(Id):");
		l.get(4).setText("Proyect(Id):");
		l.get(5).setText("Intitution(Id):");
		l.get(6).setText("Note(Optional):");
		l.get(8).setText("Lists");
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
		for(int i=0;i<=8;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
			b.get(i).setText("Show");
			
			if(i>0 && i<=5){ b.get(i).setBorder(new LineBorder(Color.GRAY)); }
		}
		b.get(0).setText("Record");
		b.get(6).setText("API");
		b.get(7).setText("APL");
		b.get(8).setText("PASS");
	}
	
	public void table(String s) { 
		Vector<String> cols = new Vector<String> ();
		
		cols.add("Id"); 
		cols.add("Description");
		
		m.setColumnIdentifiers(cols);
		
		rs = conn.select("SELECT *FROM "+s);
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
	
	public void tableIntitution(){
		Vector<String> cols = new Vector<String> ();
		
		cols.add("Id");
		cols.add("Name");
		cols.add("Direction");
		cols.add("Phone");
		cols.add("Supervisor");
		
		m.setColumnIdentifiers(cols);
		
		rs = conn.select("SELECT *FROM INTITUTION");
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
	
	public void tableStudent(){
		Vector<String> cols = new Vector<String> ();
		
		cols.add("Id");
		cols.add("Name");
		cols.add("Last name");
		cols.add("Career");
		
		m.setColumnIdentifiers(cols);
		
		rs = conn.select("SELECT *FROM STUDENT");
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
	
	public void panel(){
		int j = 0,k = 0;
		label();
		text();
		button();
		
		for(int i=0;i<=27;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 24){ p.get(i).setLayout(new FlowLayout()); }
			if(i>24 && i<=26){ p.get(i).setLayout(new BorderLayout()); }	
			if(i == 27){ p.get(i).setLayout(new GridLayout(12,1)); }
			
			if(i<=8){p.get(i).add(l.get(i)); }
			if(i>8 && i<=13){p.get(i).add(tf.get(j)); j++; }
			if(i>13 && i<=22){p.get(i).add(b.get(k)); k++; }
		}
		p.get(23).add(sp);
		
		p.get(1).add(p.get(15));
		p.get(2).add(p.get(16));
		p.get(3).add(p.get(17));
		p.get(4).add(p.get(18));
		p.get(5).add(p.get(19));
		p.get(6).add(p.get(7));
		p.get(20).add(p.get(21));
		p.get(20).add(p.get(22));
		
		p.get(27).add(p.get(1));
		p.get(27).add(p.get(9));
		p.get(27).add(p.get(2));
		p.get(27).add(p.get(10));
		p.get(27).add(p.get(3));
		p.get(27).add(p.get(11));
		p.get(27).add(p.get(4));
		p.get(27).add(p.get(12));
		p.get(27).add(p.get(5));
		p.get(27).add(p.get(13));
		p.get(27).add(p.get(6));
		p.get(27).add(p.get(20));
		
		p.get(25).add(p.get(8),BorderLayout.NORTH);
		p.get(25).add(p.get(23),BorderLayout.CENTER);
		
		p.get(26).add(p.get(0),BorderLayout.NORTH);
		p.get(26).add(p.get(27),BorderLayout.CENTER);
		p.get(26).add(p.get(14),BorderLayout.SOUTH);
		
		p.get(24).add(p.get(26));
		p.get(24).add(p.get(25));
	}
	
	public void tabbed(){
		panel();
		
		tp.setBackground(Color.LIGHT_GRAY);
		tp.add("Student/Mater",p.get(24));
		tp.add("Seeker",new SearchBox());
	}
	
	public void action(){
		this.b.get(0).addActionListener((e) -> {
			conn.execute("INSERT INTO STUDENT_MATTER(ID_STUDENT,ID_MATTER,ID_PERIOD,NOTE,PROYECT,INTITUTION) VALUES("+tf.get(0).getText()+","+tf.get(1).getText()+","+tf.get(2).getText()+",'"+l.get(7).getText()+"',"+tf.get(3).getText()+","+tf.get(4).getText()+")");
			conn.connClose(); conn.stmtClose(); text();
			JOptionPane.showMessageDialog(null, "New student/matter Saved");
		});
		this.b.get(1).addActionListener((e) -> { 
			l.get(8).setText("Student List");
			tableStudent();
		});
		
		this.b.get(2).addActionListener((e) -> { 
			l.get(8).setText("Matter List");
			table("MATTER");
		});
		
		this.b.get(3).addActionListener((e) -> { 
			l.get(8).setText("Period List");
			table("PERIOD");
		});
		
		this.b.get(4).addActionListener((e) -> { 
			l.get(8).setText("Proyect List");
			table("PROYECT");
		});
		
		this.b.get(5).addActionListener((e) -> { 
			l.get(8).setText("Intitution List");
			tableIntitution();
		});
		
		this.b.get(6).addActionListener((e) -> { l.get(7).setText("API"); });
		this.b.get(7).addActionListener((e) -> { l.get(7).setText("APL"); });
		this.b.get(8).addActionListener((e) -> { l.get(7).setText("PASS"); });
	}
	
	public NewStudentMatter(){
		components();
		tabbed();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(tp);
	}
}