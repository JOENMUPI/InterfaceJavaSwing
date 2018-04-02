package JEMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SearchBox extends JPanel{
	private JComboBox<String> cb;
	private ArrayList<JPanel> p;
	private JLabel l,l1;
	private JButton b;
	
	public void components(){
		cb = new JComboBox<String>();
		
		p = new ArrayList<JPanel>();
		
		b =new JButton("Ok");
		l = new JLabel("Seeker");
		l1 = new JLabel("Search to");
	}
	
	public void combo(){
		cb.setBorder(new LineBorder(Color.GRAY));
		cb.setForeground(Color.GRAY);
		
		cb.addItem("Student");
		cb.addItem("Matter");
		cb.addItem("Period");
		cb.addItem("Proyect");
		cb.addItem("Intitution");
	}
	
	public void label(){
		l.setFont(new Font("Console",Font.BOLD,25));
		l.setForeground(Color.GRAY);
		
		l1.setFont(new Font("Console",Font.BOLD,15));
		l1.setForeground(Color.DARK_GRAY);
	}
	
	public void button(){ b.setBackground(Color.GRAY); }
	
	public void panel(){
		button();
		label();
		combo();
		
		for(int i=0;i<=5;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 4){ p.get(i).setLayout(new FlowLayout()); }
			if(i == 5){ p.get(i).setLayout(new BorderLayout()); }	
		}
		
		p.get(1).add(l);
		p.get(2).add(l1);
		p.get(3).add(cb);
		p.get(4).add(b);
		
		p.get(2).add(p.get(3));
		p.get(2).add(p.get(4));
		
		p.get(5).add(p.get(1),BorderLayout.NORTH);
		p.get(5).add(p.get(2),BorderLayout.CENTER);
		p.get(5).add(p.get(0),BorderLayout.SOUTH);
	}
	
	public void action(){
		this.b.addActionListener((e) -> {
			if(cb.getSelectedIndex() == 0){
				p.get(0).removeAll(); 
				p.get(0).add(new SeeStudent()); 
				panel(); 
			}
			
			else if(cb.getSelectedIndex() == 1){ 
				p.get(0).removeAll(); 
				p.get(0).add(new SEE("Matter")); 
				panel(); 
			}
			
			else if(cb.getSelectedIndex() == 2){ 
				p.get(0).removeAll(); 
				p.get(0).add(new SEE("Period")); 
				panel(); 
			}
			
			else if(cb.getSelectedIndex() == 3){
				p.get(0).removeAll(); 
				p.get(0).add(new SeeProject()); 
				panel(); 
			}
			
			else if(cb.getSelectedIndex() == 4){ 
				p.get(0).removeAll(); 
				p.get(0).add(new SeeIntitution()); 
				panel(); 
			}
			
			else{ System.out.println("Error combobox"); }
		});
	}
	
	public SearchBox(){
		components();
		panel();
		action();
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(5));
	}
}