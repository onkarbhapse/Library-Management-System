import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class login implements ActionListener{
	
	JFrame lframe = new JFrame("Library Login...");	
        JPanel lpanel = new JPanel();
		JLabel namepanel = new JLabel("Login ");
		JLabel usernamelabel = new JLabel("Username: ");
		JLabel passwordlabel = new JLabel("Password: ");
		JTextField usernametf = new JTextField();
		JPasswordField passwordtf = new JPasswordField();
		JButton cancelbutton = new JButton(" Cancel ");
		JButton loginbutton = new JButton(" Login ");
	
	public login(){
		
		
		//lpanel.setSize(200,200);
		lpanel.setLayout(null);
		lpanel.setBackground(Color.decode("#d9d9d9"));
		lpanel.setBounds(25,25,600,300);
		
		namepanel.setBounds(20,15,200,40);
		namepanel.setFont(new Font("Serif", Font.BOLD, 30));
		
		usernamelabel.setBounds(70,80,100,50);
		usernamelabel.setFont(new Font("Serif", Font.BOLD, 20));
		
		passwordlabel.setBounds(70,130,100,50);
		passwordlabel.setFont(new Font("Serif", Font.BOLD, 20));
		
		
		usernametf.setBounds(200,93,200,25);
		passwordtf.setBounds(200,142,200,25);
		
		cancelbutton.setBounds(175,230,100,35);
		loginbutton.setBounds(325,230,100,35);
		
		lpanel.add(namepanel);
		lpanel.add(usernamelabel);
		lpanel.add(passwordlabel);
		lpanel.add(usernametf);
		lpanel.add(passwordtf);
		lpanel.add(cancelbutton);
		lpanel.add(loginbutton);
		
		
		
		
		cancelbutton.addActionListener(this);
		loginbutton.addActionListener(this);
		usernametf.addActionListener(this);
		passwordtf.addActionListener(this);

		lframe.add(lpanel);
		lframe.setLayout(null);
		lframe.setSize(680,400);
		lframe.setLocationRelativeTo(null);
		lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lframe.setVisible(true);
		
		
		
	}
	
	
	
	public void actionPerformed(ActionEvent ae){
			
			String username,password;
		    String str = ae.getActionCommand();
			username = usernametf.getText();
			password = passwordtf.getText();
			
			if(str.equals(" Login ")){
				if(username.equals("abc")&& password.equals("123")){
					//JOptionPane.showMessageDialog(loginbutton,"Login successfully....");
				    lframe.hide();
					menu nj = new menu();
					  
				}else{
					JOptionPane.showMessageDialog(loginbutton, "Username and Password Do Not Match...","Select an Option",JOptionPane.ERROR_MESSAGE);
					usernametf.setText("");
					passwordtf.setText("");
					usernametf.requestFocus();
				}
			}else if(str.equals(" Cancel ")){
				int reply = JOptionPane.showConfirmDialog(cancelbutton, "Are you sure to Exit?");
				
				if(reply == JOptionPane.YES_OPTION){
                   System.exit(0);					
				}else if(reply == JOptionPane.NO_OPTION){
					//JOptionPane.exit(0);
				}
				
			}
			
		}
	
	
	public static void main(String args[]){
		
	 new login();
	}
	
}
//javac -cp mysql.jar;. login.java