package build;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
//Colleague interface
interface Command {
    void execute();
}
 
//Concrete mediator
class Mediator {
 
    BtnView btnView;
    BtnSearch btnSearch;
    BtnBook btnBook;
    LblDisplay show;
    
    UI ui;
 
    //....
    void registerView(BtnView v) {
        btnView = v;
    }
 
    void registerSearch(BtnSearch s) {
        btnSearch = s;
    }
 
    void registerBook(BtnBook b) {
        btnBook = b;
    }
 
    void registerDisplay(LblDisplay d) {
        show = d;
    }
    
    void registerUI(UI u) {
    	ui = u;
    }
    
    String readFromUI() {
    	return ui.test.getName();
    }
 
    void book() {
        btnBook.setEnabled(false);
        btnView.setEnabled(true);
        btnSearch.setEnabled(true);
        show.setText("booking...");
    }
 
    void view() {
        btnView.setEnabled(false);
        btnSearch.setEnabled(true);
        btnBook.setEnabled(true);
        show.setText("viewing...");
    }
 
    void search() {
        btnSearch.setEnabled(false);
        btnView.setEnabled(true);
        btnBook.setEnabled(true);
        show.setText("searching...");
    }
 
}
 
//A concrete colleague
class BtnView extends JButton implements Command {
 
    Mediator med;
 
    BtnView(ActionListener al, Mediator m) {
        super("View");
        addActionListener(al);
        med = m;
        med.registerView(this);
    }
 
    public void execute() {
        med.view();
    }
 
}
 
//A concrete colleague
class BtnSearch extends JButton implements Command {
 
    Mediator med;
 
    BtnSearch(ActionListener al, Mediator m) {
        super("Search");
        addActionListener(al);
        med = m;
        med.registerSearch(this);
    }
 
    public void execute() {
        med.search();
    }
 
}
 
//A concrete colleague
class BtnBook extends JButton implements Command {
 
    Mediator med;
 
    BtnBook(ActionListener al, Mediator m) {
        super("Book");
        addActionListener(al);
        med = m;
        med.registerBook(this);
    }
 
    public void execute() {
        med.book();
    }
 
}
 
class LblDisplay extends JLabel {
 
    Mediator med;
 
    LblDisplay(Mediator m) {
        super("Just start...");
        med = m;
        med.registerDisplay(this);
        setFont(new Font("Arial", Font.BOLD, 24));
    }
 
}

class UI extends JPanel implements ActionListener, Command{
	Mediator med;
	JButton test;
	JTextField text;
	
	UI(Mediator m) {
		med = m;
		med.registerUI(this);
		
		test = new JButton("Im inside panel");
		text = new JTextField("I want to get updated");
		add(test);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		test.setText("Im clicked");
	}

	@Override
	public void execute() {
		String ret = med.readFromUI();
		
	}
	
}

 
class MediatorDemo extends JFrame implements ActionListener {
 
    Mediator med = new Mediator();
 
    MediatorDemo() {
        JPanel p = new JPanel();
        p.add(new BtnView(this, med));
        p.add(new BtnBook(this, med));
        p.add(new BtnSearch(this, med));
        
        getContentPane().add(new LblDisplay(med), "North");
        getContentPane().add(p, "South");
        getContentPane().add(new UI(med), "East");
        setSize(400, 200);
        setVisible(true);
    }
 
    public void actionPerformed(ActionEvent ae) {
        Command comd = (Command) ae.getSource();
        comd.execute();
    }
 
    public static void main(String[] args) {
        new MediatorDemo();
    }
 
}

