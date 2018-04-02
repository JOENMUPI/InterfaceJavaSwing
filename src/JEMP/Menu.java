package JEMP;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Menu extends JFrame{
	private JMenu m,m1,m2,m3;
	private ArrayList<JMenuItem> mi;
	private JMenuBar mb;
	private JPanel p;
	private JLabel l;
	private JTabbedPane tp;
	
	public void components(){
		mi = new ArrayList<JMenuItem>();

		m = new JMenu("New");
		m1= new JMenu("See");
		m2 = new JMenu("Edit");
		m3 = new JMenu("Delete");
		
		mb = new JMenuBar();
		
		l = new JLabel("Welcome");
		l.setFont(new Font("Action Man",Font.BOLD,45));
		l.setForeground(Color.GRAY);
		
		p = new JPanel();
		p.setBackground(Color.LIGHT_GRAY);
		p.add(l);
	}
	public void tabbe(){
		tp = new JTabbedPane();
		
		tp.setBackground(Color.LIGHT_GRAY);
		tp.setForeground(Color.DARK_GRAY);
	}
	
	public void menu(){
		mi.add(new JMenuItem("Student"));
		mi.add(new JMenuItem("Supervisor"));
		mi.add(new JMenuItem("Intitution"));
		mi.add(new JMenuItem("Career"));
		mi.add(new JMenuItem("Matter"));
		mi.add(new JMenuItem("Level"));
		mi.add(new JMenuItem("Position"));
		mi.add(new JMenuItem("Period"));
		mi.add(new JMenuItem("Project"));
		mi.add(new JMenuItem("Student/Matter"));
		mi.add(new JMenuItem("Students"));
		mi.add(new JMenuItem("Supervisors"));
		mi.add(new JMenuItem("Intitutions"));
		mi.add(new JMenuItem("Careers"));
		mi.add(new JMenuItem("Matters"));
		mi.add(new JMenuItem("Levels"));
		mi.add(new JMenuItem("Positions"));
		mi.add(new JMenuItem("Periods"));
		mi.add(new JMenuItem("Projects"));
		mi.add(new JMenuItem("Student/Matter"));
		mi.add(new JMenuItem("Student"));
		mi.add(new JMenuItem("Supervisor"));
		mi.add(new JMenuItem("Intitution"));
		mi.add(new JMenuItem("Career"));
		mi.add(new JMenuItem("Matter"));
		mi.add(new JMenuItem("Level"));
		mi.add(new JMenuItem("Position"));
		mi.add(new JMenuItem("Period"));
		mi.add(new JMenuItem("Project"));
		mi.add(new JMenuItem("Student/Matter"));
		mi.add(new JMenuItem("Student"));
		mi.add(new JMenuItem("Supervisor"));
		mi.add(new JMenuItem("Intitution"));
		mi.add(new JMenuItem("Career"));
		mi.add(new JMenuItem("Matter"));
		mi.add(new JMenuItem("Level"));
		mi.add(new JMenuItem("Position"));
		mi.add(new JMenuItem("Period"));
		mi.add(new JMenuItem("Project"));
		mi.add(new JMenuItem("Student/Matter"));
		
		for(int i=0;i<mi.size();i++){
			mi.get(i).getAccessibleContext().getAccessibleComponent().setForeground(Color.GRAY);
			if(i<=9){ m.add(mi.get(i)); }
			if(i>9 && i<=19){ m1.add(mi.get(i)); }
			if(i>19 && i<=29){ m2.add(mi.get(i)); }
			if(i>29 && i<=39){ m3.add(mi.get(i)); }
		}
		m.getAccessibleContext().getAccessibleComponent().setForeground(Color.GRAY);
		m1.getAccessibleContext().getAccessibleComponent().setForeground(Color.GRAY);
		m2.getAccessibleContext().getAccessibleComponent().setForeground(Color.GRAY);
		m3.getAccessibleContext().getAccessibleComponent().setForeground(Color.GRAY);
		
		mb.add(m);
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
	}
	
	public void panelNew(String s){ p.removeAll(); p.add(new NEW(s)); pack(); }
		
	public void panelSee(String s){ p.removeAll(); p.add(new SEE(s)); pack(); }
	
	public void panelEdit(String s){ p.removeAll(); p.add(new EDIT(s)); pack(); }
	
	public void panelDelete(String s){ p.removeAll(); p.add(new DELETE(s)); pack(); }
	
	public void action(){
		this.mi.get(0).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new NewStudent()); 
			pack();
		});
		
		this.mi.get(1).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new NewSupervisor()); 
			pack();
		});
		
		this.mi.get(2).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new NewIntitution()); 
			pack();
		});
		
		this.mi.get(3).addActionListener((e) -> { panelNew("career"); });
		
		this.mi.get(4).addActionListener((e) -> { panelNew("matter"); });
		
		this.mi.get(5).addActionListener((e) -> { panelNew("Level"); });
		
		this.mi.get(6).addActionListener((e) -> { panelNew("Position"); });
		
		this.mi.get(7).addActionListener((e) -> { panelNew("Period"); });
		
		this.mi.get(8).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new NewProject()); 
			pack();
		});
		
		this.mi.get(9).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new NewStudentMatter()); 
			pack();
		});
		
		this.mi.get(10).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new SeeStudent()); 
			pack();
		});
		
		this.mi.get(11).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new SeeSupervisor()); 
			pack();
		});
		
		this.mi.get(12).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new SeeIntitution()); 
			pack();
		});
		
		this.mi.get(13).addActionListener((e) -> { panelSee("career"); });
		
		this.mi.get(14).addActionListener((e) -> { panelSee("matter"); });
		
		this.mi.get(15).addActionListener((e) -> { panelSee("level"); });
		
		this.mi.get(16).addActionListener((e) -> { panelSee("position"); });
		
		this.mi.get(17).addActionListener((e) -> { panelSee("period"); });
		
		this.mi.get(18).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new SeeProject()); 
			pack();
		});
		
		this.mi.get(19).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new SeeStudentMatter()); 
			pack();
		});
		
		this.mi.get(20).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new EditStudent()); 
			pack();
		});
		
		this.mi.get(21).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new EditSupervisor()); 
			pack();
		});
		
		this.mi.get(22).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new EditIntitution()); 
			pack();
		});
		
		this.mi.get(23).addActionListener((e) -> { panelEdit("career"); });
		
		this.mi.get(24).addActionListener((e) -> { panelEdit("matter"); });
		
		this.mi.get(25).addActionListener((e) -> { panelEdit("level"); });
		
		this.mi.get(26).addActionListener((e) -> { panelEdit("position"); });
		
		this.mi.get(27).addActionListener((e) -> { panelEdit("period"); });
		
		this.mi.get(28).addActionListener((e) -> { 
			this.getContentPane().remove(p);
			p = new EditProject();
			this.add(p);
			pack();
		});
		
		this.mi.get(29).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new EditStudentMatter()); 
			pack();
		});
		
		this.mi.get(30).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new DeleteStudent()); 
			pack();
		});
		
		this.mi.get(31).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new DeleteSupervisor()); 
			pack();
		});
		
		this.mi.get(32).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new DeleteIntitution()); 
			pack();
		});
		
		this.mi.get(33).addActionListener((e) -> { panelDelete("career"); });
		
		this.mi.get(34).addActionListener((e) -> { panelDelete("matter"); });
		
		this.mi.get(35).addActionListener((e) -> { panelDelete("level"); });
		
		this.mi.get(36).addActionListener((e) -> { panelDelete("position"); });
		
		this.mi.get(37).addActionListener((e) -> { panelDelete("period"); });
		
		this.mi.get(38).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new DeleteProject()); 
			pack();
		});
		
		this.mi.get(39).addActionListener((e) -> { 
			p.removeAll(); 
			p.add(new DeleteStudentMatter()); 
			pack();
		});
	}
	
	public void frame(){
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("S.C 0.1 By Solutions");
		this.pack();
		this.setVisible(true);
	}
	
	public Menu(){
		components();
		tabbe();
		menu();
		action();
		
		this.setJMenuBar(mb);
		this.add(p);
		
		frame();
	}
}