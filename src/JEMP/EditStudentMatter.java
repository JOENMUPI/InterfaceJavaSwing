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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class EditStudentMatter extends JPanel{
	private ArrayList<JPanel> p;
	private ArrayList<JButton> b;
	private ArrayList<JLabel> l;
	private JTextField tf;
	private JScrollPane sp;
	private Conection conn;
	private ResultSet rs;
	
	public void components(){
		conn = new Conection();
		
		sp = new JScrollPane(new JTable());
		
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
		l.get(4).setText("Project(Id):");
		l.get(5).setText("Intitution(Id):");
		l.get(6).setText("Note(Optional):");
		l.get(7).setText("Id Student:");
	}
	
	public void text(){	
		tf.setText("");
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
		
		sp.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void button(){
		for(int i=0;i<=6;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
			b.get(i).setText("Edit");	
		}
		b.get(0).setText("Search");
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
	
	public void panel(){
		int j = 0;
		text();
		button();
		label();
		
		for(int i=0;i<=24;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 21){ p.get(i).setLayout(new FlowLayout()); }
			if(i>21 && i<=23){ p.get(i).setLayout(new BorderLayout()); }
			if(i == 24){ p.get(i).setLayout(new GridLayout(6,3)); } 

			if(i<=13){p.get(i).add(l.get(i));}
			if(i>13 && i<=20){ p.get(i).add(b.get(j)); j++; }
		}
		
		p.get(21).add(tf);
		
		p.get(24).add(p.get(1));
		p.get(24).add(p.get(8));
		p.get(24).add(p.get(15));
		p.get(24).add(p.get(2));
		p.get(24).add(p.get(9));
		p.get(24).add(p.get(16));
		p.get(24).add(p.get(3));
		p.get(24).add(p.get(10));
		p.get(24).add(p.get(17));
		p.get(24).add(p.get(4));
		p.get(24).add(p.get(11));
		p.get(24).add(p.get(18));
		p.get(24).add(p.get(5));
		p.get(24).add(p.get(12));
		p.get(24).add(p.get(19));
		p.get(24).add(p.get(6));
		p.get(24).add(p.get(13));
		p.get(24).add(p.get(20));

		p.get(22).add(p.get(7),BorderLayout.NORTH);
		p.get(22).add(p.get(21),BorderLayout.CENTER);;
		
		p.get(23).add(p.get(0),BorderLayout.NORTH);
		p.get(23).add(p.get(22),BorderLayout.CENTER);
		p.get(23).add(p.get(14),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.get(0).addActionListener((e) -> {
			if(tf.getText().length() == 0){
				JOptionPane.showMessageDialog(null,"No student to search.");
			}
			else { label(); search(); text(); }
		});
		
		this.b.get(1).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				new EditElement("Student","STUDENT_MATTER","ID_STUDENT",l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student/matter to edit."); }  
		});
		
		this.b.get(2).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				new EditElement("Matter","STUDENT_MATTER","ID_MATTER",l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student/matter to edit."); }   
		});
		
		this.b.get(3).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				new EditElement("Period","STUDENT_MATTER","ID_PERIOD",l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student/matter to edit."); }   
		});
		
		this.b.get(4).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				new EditElement("Project","STUDENT_MATTER","PROYECT",l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student/matter to edit."); }  
		});
		
		this.b.get(5).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				new EditElement("Intitution","STUDENT_MATTER","INTITUTION",l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student/matter to edit."); }   
		});
		
		this.b.get(6).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				new EditNote(l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student/matter to edit."); }  
		});
	}
	
	public EditStudentMatter(){
		components();
		panel();
		action();

		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(23));
		this.add(p.get(24));
	}
}