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

public class DELETE extends JPanel{
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
	
	public void label(String s){
		for(int i=0;i<=5;i++){
			l.add(new JLabel("n/d"));
			
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Delete "+s);
		l.get(1).setText("Id "+s);
		l.get(2).setText("Id:");
		l.get(3).setText("Description:");
	}
	
	public void button(String s){
		for(int i=0;i<=2;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
			b.get(i).setText("Delete all "+s+"s");
		}
		b.get(0).setText("Search");
		b.get(1).setText("Delete");
	}
	
	public void text(){	
		tf.setText("");
		tf.setForeground(Color.GRAY);
		tf.setBorder(new LineBorder(Color.GRAY));
	}
	
	public void search(String s){
		rs = conn.select("SELECT *FROM "+s+" WHERE ID_"+s+" = "+tf.getText());
		
		try{
			rs.next();
			l.get(4).setText(rs.getString("ID_"+s));
			l.get(5).setText(rs.getString("DESCRIPTION"));
		}
		catch(SQLException e){System.out.println("Error a llenar los label "+e);}
	}
	
	public void delete(String s){
		int i = JOptionPane.showConfirmDialog(null, "Are your shure?");
		if(i == JOptionPane.YES_OPTION){ conn.execute(s); }
	}
	
	public void panel(String s){
		int j = 0;
		text();
		label(s);
		button(s);
		
		for(int i=0;i<=12;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 9){ p.get(i).setLayout(new FlowLayout()); }
			if(i>9 && i<=11){ p.get(i).setLayout(new BorderLayout()); }
			if(i == 12){ p.get(i).setLayout(new GridLayout(3,2)); } 

			if(i<=5){p.get(i).add(l.get(i));}
			if(i>5 && i<=8){ p.get(i).add(b.get(j)); j++; }
		}
		
		p.get(9).add(tf);
		
		p.get(12).add(p.get(2));
		p.get(12).add(p.get(4));
		p.get(12).add(p.get(3));
		p.get(12).add(p.get(5));
		p.get(12).add(p.get(7));
		p.get(12).add(p.get(8));
		
		p.get(10).add(p.get(1),BorderLayout.NORTH);
		p.get(10).add(p.get(9),BorderLayout.CENTER);;
		
		p.get(11).add(p.get(0),BorderLayout.NORTH);
		p.get(11).add(p.get(10),BorderLayout.CENTER);
		p.get(11).add(p.get(6),BorderLayout.SOUTH);
	}
	
	public void action(String s){
		this.b.get(0).addActionListener((e) -> { 
			if(tf.getText().length() == 0){ 
				JOptionPane.showMessageDialog(null,"Insert id to search."); 
			}
			else{ search(s); text(); }
		});
		
		this.b.get(1).addActionListener((e) -> { 
			if(l.get(4).getText() != "n/d"){
				delete("DELETE FROM "+s+" WHERE ID_"+s+" = "+l.get(4).getText());
				label(s);
			}
			else{ JOptionPane.showMessageDialog(null,"No "+s+" to delete."); }
		});
		
		this.b.get(2).addActionListener((e) -> { 
			delete("DELETE FROM "+s); 
			label(s);
		});
	}
	
	public DELETE(String s){
		components();
		panel(s);
		action(s);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(11));
		this.add(p.get(12));
	}
}