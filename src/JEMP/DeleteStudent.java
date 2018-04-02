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

public class DeleteStudent extends JPanel{
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
		for(int i=0;i<=9;i++){
			l.add(new JLabel());
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
			l.get(i).setText("n/d");
		}
		
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Edit student");
		l.get(1).setText("Id student:");
		l.get(2).setText("Name:");
		l.get(3).setText("Last name:");
		l.get(4).setText("Id:");
		l.get(5).setText("Career(id):");
	}
	
	public void button(){
		for(int i=0;i<=2 ;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
			b.get(i).setText("Delete all students");
		}
		b.get(0).setText("Search");
		b.get(1).setText("Delete");
	}
	
	public void text(){	
		tf.setText("");
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void search(){
		rs = conn.select("SELECT *FROM STUDENT WHERE ID_STUDENT = "+tf.getText());
		
		try{
			rs.next();
			l.get(6).setText(rs.getString("NAME"));
			l.get(7).setText(rs.getString("LAST_NAME"));
			l.get(8).setText(rs.getString("ID_STUDENT"));
			l.get(9).setText(rs.getString("CAREER_ID"));
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
		
		for(int i=0;i<=16;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 13){ p.get(i).setLayout(new FlowLayout()); }
			if(i>13 && i<=15){ p.get(i).setLayout(new BorderLayout()); }
			if(i == 16){ p.get(i).setLayout(new GridLayout(5,2)); } 

			if(i<=9){p.get(i).add(l.get(i));}
			if(i>9 && i<=12){ p.get(i).add(b.get(j)); j++; }
		}
		
		p.get(13).add(tf);
		
		p.get(16).add(p.get(2));
		p.get(16).add(p.get(6));
		p.get(16).add(p.get(3));
		p.get(16).add(p.get(7));
		p.get(16).add(p.get(4));
		p.get(16).add(p.get(8));
		p.get(16).add(p.get(5));
		p.get(16).add(p.get(9));
		p.get(16).add(p.get(11));
		p.get(16).add(p.get(12));

		p.get(14).add(p.get(1),BorderLayout.NORTH);
		p.get(14).add(p.get(13),BorderLayout.CENTER);;
		
		p.get(15).add(p.get(0),BorderLayout.NORTH);
		p.get(15).add(p.get(14),BorderLayout.CENTER);
		p.get(15).add(p.get(10),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.get(0).addActionListener((e) -> { search(); text(); });
		
		this.b.get(1).addActionListener((e) -> { 
			if(l.get(8).getText() != "n/d"){
				delete("DELETE FROM STUDENT WHERE ID_STUDENT = "+l.get(8).getText());
				label();
			}
			else{ JOptionPane.showMessageDialog(null,"No student to delete."); }
		});
		
		this.b.get(2).addActionListener((e) -> { 
			delete("DELETE FROM STUDENT"); 
			label();
		});
	}
	
	public DeleteStudent(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(15));
		this.add(p.get(16));
	}
}