package JEMP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EditNote extends JFrame{
	private ArrayList<JLabel> l;
	private ArrayList<JButton> b;
	private ArrayList<JPanel> p;
	private Conection conn;
	
	public void components(){
		conn = new Conection();
		
		l = new ArrayList<JLabel>();
		b = new ArrayList<JButton>();
		p = new ArrayList<JPanel>();
	}
	
	public void label(){
		for(int i=0;i<=1;i++){
			l.add(new JLabel());
			l.get(i).setFont(new Font("Console",Font.BOLD,15));
			l.get(i).setForeground(Color.DARK_GRAY);
		}
		l.get(0).setFont(new Font("Console",Font.BOLD,25));
		l.get(0).setForeground(Color.GRAY);
		
		l.get(0).setText("Edit Note");
		l.get(1).setText("");
	}
	
	public void button(){
		for(int i=0;i<=3;i++){
			b.add(new JButton());
			b.get(i).setBackground(Color.GRAY);
		}
		b.get(0).setText("Search");
		b.get(1).setText("API");
		b.get(2).setText("APL");
		b.get(3).setText("PASS");
	}
	
	public void edit(String s){
		conn.select("UPDATE STUDENT_MATTER SET NOTE = '"+l.get(1).getText()+"' WHERE ID_STUDENT = "+s);
		JOptionPane.showMessageDialog(null,"Student/Matter edited");
	}
	
	public void panel(){
		int j = 0;
		button();
		label();
		
		for(int i=0;i<=7;i++){
			p.add(new JPanel());
			p.get(i).setBackground(Color.LIGHT_GRAY);
			
			if(i <= 5){ p.get(i).setLayout(new FlowLayout()); }
			if(i>5 && i<=7){ p.get(i).setLayout(new BorderLayout()); }
			
			if(i<=1){ p.get(i).add(l.get(i)); }
			if(i>1 && i<=5){ p.get(i).add(b.get(j)); j++; }
		}
		p.get(3).add(p.get(4));
		p.get(3).add(p.get(5));
		
		p.get(6).add(p.get(1),BorderLayout.NORTH);
		p.get(6).add(p.get(3),BorderLayout.CENTER);

		p.get(7).add(p.get(0),BorderLayout.NORTH);
		p.get(7).add(p.get(6),BorderLayout.CENTER);
		p.get(7).add(p.get(2),BorderLayout.SOUTH);
	}
	
	public void action(String s){
		this.b.get(0).addActionListener((e) -> {edit(s); this.dispose(); });
		
		this.b.get(1).addActionListener((e) -> { l.get(1).setText("API"); });
		
		this.b.get(2).addActionListener((e) -> { l.get(1).setText("APL"); });
		
		this.b.get(3).addActionListener((e) -> { l.get(1).setText("PASS"); });
	}
	
	public void frame(){
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setTitle("S.C 0.1 By Solutions");
		this.pack();
		this.setVisible(true);
	}
	
	public EditNote(String s){
		components();
		panel();
		action(s);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.add(p.get(7));
		
		frame();
	}
}