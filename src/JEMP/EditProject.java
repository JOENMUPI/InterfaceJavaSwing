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

public class EditProject extends JPanel{
	private ArrayList<JLabel> l;
	private ArrayList<JButton> b;
	private ArrayList<JPanel> p;
	private JTextField tf;
	private Conection conn;
	private ResultSet rs;
	
	public void components(){
		conn = new Conection();
		
		l = new ArrayList<JLabel>();
		b = new ArrayList<JButton>();
		p = new ArrayList<JPanel>();
		tf = new JTextField(15);
	}
	
	public void label(){
		for(int i=0;i<=7;i++){
			l.add(new JLabel());
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
			l.get(i).setText("n/d");
		}
		
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Edit project");
		l.get(1).setText("Id project");
		l.get(2).setText("Id:");
		l.get(3).setText("Tittle: ");
		l.get(4).setText("Activities: ");
	}
	
	public void button(){
		for(int i=0;i<=3;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
			b.get(i).setText("Edit");
		}
		b.get(0).setText("Search");
	}
	
	public void text(){	
		tf.setText("");
		tf.setForeground(Color.GRAY); 
		tf.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void search(){
		rs = conn.select("SELECT *FROM PROYECT WHERE ID_PROYECT = "+tf.getText());
		
		try{
			rs.next();
			l.get(5).setText((rs.getString("ID_PROYECT")));
			l.get(6).setText(rs.getString("TITTLE"));
			l.get(7).setText(rs.getString("ACTIVITIES"));
		}
		catch(SQLException e){System.out.println("Error a llenar los label "+e);}
	}
	
	public void panel(){
		int j = 0;
		text();
		button();
		label();
		
		for(int i=0;i<=15;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 12){ p.get(i).setLayout(new FlowLayout()); }
			if(i>12 && i<=14){ p.get(i).setLayout(new BorderLayout()); }
			if(i == 15){ p.get(i).setLayout(new GridLayout(3,3)); } 

			if(i<=7){p.get(i).add(l.get(i));}
			if(i>7 && i<=11){ p.get(i).add(b.get(j)); j++; }
		}
		
		p.get(12).add(tf);
		
		p.get(15).add(p.get(2));
		p.get(15).add(p.get(5));
		p.get(15).add(p.get(9));
		p.get(15).add(p.get(3));
		p.get(15).add(p.get(6));
		p.get(15).add(p.get(10));
		p.get(15).add(p.get(4));
		p.get(15).add(p.get(7));
		p.get(15).add(p.get(11));

		p.get(13).add(p.get(1),BorderLayout.NORTH);
		p.get(13).add(p.get(12),BorderLayout.CENTER);;
		
		p.get(14).add(p.get(0),BorderLayout.NORTH);
		p.get(14).add(p.get(13),BorderLayout.CENTER);
		p.get(14).add(p.get(8),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.get(0).addActionListener((e) -> {
			if(tf.getText().length() == 0){
				JOptionPane.showMessageDialog(null,"No project to search.");
			}
			else { label(); search(); text(); }
		});
		
		this.b.get(1).addActionListener((e) -> { 
			if(l.get(5).getText() != "n/d"){
				new EditElement("Id","PROYECT","ID_PROYECT",l.get(5).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No project to edit."); }
		});
		
		this.b.get(2).addActionListener((e) -> { 
			if(l.get(5).getText() != "n/d"){
				new EditElement("Tittle","PROYECT","ID_PROYECT",l.get(5).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No project to edit."); }
		});
		
		this.b.get(3).addActionListener((e) -> { 
			if(l.get(5).getText() != "n/d"){
				new EditElement("Activities","PROYECT","ACTIVITIES",l.get(5).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No project to edit."); }
		});
	}
	
	public EditProject(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(14));
		this.add(p.get(15));
	}
}