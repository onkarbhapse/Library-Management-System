import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import lmenu.*;

public class menu implements ActionListener{
	
JFrame mframe = new JFrame("Menu");
JPanel mpanel = new JPanel();	

Label mlabel = new Label(" Library Name ");

JButton mauthor = new JButton(" Author ");
JButton mcategory = new JButton(" Category ");
JButton mbook = new JButton(" Book ");
JButton mmember = new JButton(" Member ");
JButton missue = new JButton(" Issue Book ");
JButton mrecive = new JButton(" Recive Book ");
JButton mexit = new JButton(" Exit ");

public menu(){
	
	mlabel.setAlignment(mlabel.CENTER);
	mlabel.setFont(new Font("Serif", Font.BOLD, 30));
	
	//mpanel.setBackground(Color.decode("#d9d9d9"));
	
	mpanel.add(mcategory);
	mpanel.add(mauthor);
	mpanel.add(mbook);
	mpanel.add(mmember);
	mpanel.add(missue);
	mpanel.add(mrecive);
	mpanel.add(mexit);
	
	mframe.add(mlabel);
	mframe.add(mpanel);
	
	
	mauthor.addActionListener(this);
	mcategory.addActionListener(this);
	mbook.addActionListener(this);
	mmember.addActionListener(this);
	missue.addActionListener(this);
	mrecive.addActionListener(this);
	mexit.addActionListener(this);
	
	mframe.setLayout(new GridLayout(2,1));
	mframe.setSize(700,200);
	mframe.setLocationRelativeTo(null);
    mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mframe.setVisible(true);
	
}

public void actionPerformed(ActionEvent e){
	
	String str1 = e.getActionCommand();
	
	if(str1.equals(" Exit ")){
		int reply = JOptionPane.showConfirmDialog(mexit, "Are you sure to Exit?");
				
				if(reply == JOptionPane.YES_OPTION){
                   System.exit(0);					
				}else if(reply == JOptionPane.NO_OPTION){
					
				}
	}else if(str1.equals(" Author ")){
		
		author auth = new author();
		
	}else if(str1.equals(" Category ")){
		
		category cate = new category();
		
	}else if(str1.equals(" Book ")){
		
		book books = new book();
		
	}else if(str1.equals(" Member ")){
		
		member mem = new member();
		
	}else if(str1.equals(" Issue Book ")){
		
		issuebook ibs = new issuebook();
		
	}else if(str1.equals(" Recive Book ")){
		
		recievebook rbs = new recievebook();
		
	}
	  
}	

public static void main(String args[]){
	new menu();
}

}