package JEMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class DeleteStudentMatter extends JPanel{
	private ArrayList<JPanel> p;
	private ArrayList<JButton> b;
	private ArrayList<JLabel> l;
	private JTextField tf;
	private Conection conn;
	private ResultSet rs;
	
	public void components(){
		conn = new Conection();
		
		tf = new JTextField(15);
		
		b = new ArrayList<JButton>();
		p = new ArrayList<JPanel>();
		l = new ArrayList<JLabel>();
	}
	
	public void label(){
		for(int i=0;i<=13;i++){
			l.add(new JLabel("n/d"));
			
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
		l.get(6).setText("Note:");
		l.get(7).setText("Id Student:");
	}
	
	public void text(){	
		tf.setText("");
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){
		for(int i=0;i<=2;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
			b.get(i).setText("Delete");	
		}
		b.get(0).setText("Search");
		b.get(2).setText("Delete all student/matter");
	}
	
	public void search(){
		rs = conn.select("SELECT *FROM STUDENT_MATTER WHERE ID_STUDENT = "+tf.getText());
		
		try{
			rs.next();
			l.get(8).setText(rs.getString("ID_STUDENT"));
			l.get(9).setText(rs.getString("ID_MATTER"));
			l.get(10).setText(rs.getString("ID_PERIOD"));
			l.get(11).setText(rs.getString("PROYECT"));
			l.get(12).setText(rs.getString("INTITUTION"));
			l.get(13).setText(rs.getString("NOTE"));
		}
		catch(SQLException e){System.out.println("Error a llenar los label "+e);}
	}
	
	public void delete(String s){
		int i = JOptionPane.showConfirmDialog(null, "Are your shure?");
		if(i == JOptionPane.YES_OPTION){ conn.execute(s); }
	}
	
	public void panel(){
		int j = 0;
		text();
		button();
		label();
		
		for(int i=0;i<=20;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 17){ p.get(i).setLayout(new FlowLayout()); }
			if(i>17 && i<=19){ p.get(i).setLayout(new BorderLayout()); }
			if(i == 20){ p.get(i).setLayout(new GridLayout(7,2)); } 

			if(i<=13){p.get(i).add(l.get(i));}
			if(i>13 && i<=16){ p.get(i).add(b.get(j)); j++; }
		}
		
		p.get(17).add(tf);
		
		p.get(20).add(p.get(1));
		p.get(20).add(p.get(8));
		p.get(20).add(p.get(2));
		p.get(20).add(p.get(9));
		p.get(20).add(p.get(3));
		p.get(20).add(p.get(10));
		p.get(20).add(p.get(4));
		p.get(20).add(p.get(11));
		p.get(20).add(p.get(5));
		p.get(20).add(p.get(12));
		p.get(20).add(p.get(6));
		p.get(20).add(p.get(13));
		p.get(20).add(p.get(15));
		p.get(20).add(p.get(16));

		p.get(18).add(p.get(7),BorderLayout.NORTH);
		p.get(18).add(p.get(17),BorderLayout.CENTER);;
		
		p.get(19).add(p.get(0),BorderLayout.NORTH);
		p.get(19).add(p.get(18),BorderLayout.CENTER);
		p.get(19).add(p.get(14),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.get(0).addActionListener((e) -> { 
			if(tf.getText().length() == 0){ 
				JOptionPane.showMessageDialog(null,"Insert id to search."); 
			}
			else{ search(); text(); }
		});
		
		this.b.get(1).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				delete("DELETE FROM STUDENT_MATTER WHERE ID_STUDENT = "+l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student/matter to delete."); }
		});
		
		this.b.get(2).addActionListener((e) -> { 
			delete("DELETE FROM STUDENT_MATTER"); 
			label();
		});
	}
	
	public DeleteStudentMatter(){
		components();
		panel();
		action();

		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(19));
		this.add(p.get(20));
	}
}