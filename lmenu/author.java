package lmenu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class author implements ActionListener,MouseListener{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JFrame aframe = new JFrame("Author");
	JPanel apanel = new JPanel();
	
	JLabel alabel = new JLabel("Authors");
	
	JLabel aauthorname = new JLabel("Author Name: ");
	JTextField aauthortf = new JTextField(60);
	
	JButton aadd = new JButton(" Add ");
	JButton aupdate = new JButton(" Update ");
	JButton adelete = new JButton(" Delete ");
	JButton aexit = new JButton(" Exit ");
	
	JPanel apanelt = new JPanel(new GridLayout());
	DefaultTableModel atm = new DefaultTableModel(new String[]{"Author Name"},0);
	JTable atable = new JTable();
	
	public author(){
		
		alabel.setBounds(400,20,200,50);
		alabel.setFont(new Font("Serif", Font.BOLD, 40));
		
		aauthorname.setBounds(30,120,150,25);
		aauthorname.setFont(new Font("Serif", Font.BOLD, 20));
		aauthortf.setBounds(170,120,250,25);
		
		adelete.setBounds(60,220,100,50);
		aupdate.setBounds(180,220,100,50);
		aadd.setBounds(300,220,100,50);
		aexit.setBounds(180,300,100,50);
		
		apanelt.setBounds(500,120,400,350);
		//apanelt.setLayout(null);
		apanelt.setBackground(Color.decode("#b3b3b3"));
		
		//atable.setSize(300,200);
		JScrollPane apl = new JScrollPane(atable);
		//apl.setPreferredSize(300,200);
		apanelt.add(apl);
		
		
		apanel.setBackground(Color.decode("#d9d9d9"));
		apanel.setBounds(20,20,940,515);
		apanel.setLayout(null);
		
		apanel.add(alabel);
		apanel.add(aauthorname);
		apanel.add(aauthortf);
		apanel.add(adelete);
		apanel.add(aupdate);
		apanel.add(aadd);
		apanel.add(aexit);
		apanel.add(apanelt);
		
		adelete.addActionListener(this);
		aadd.addActionListener(this);
		aexit.addActionListener(this);
		aupdate.addActionListener(this);
		
		atable.addMouseListener(this);
		
		aframe.add(apanel);
		aframe.setSize(1000,600);
		aframe.setLayout(null);
		aframe.setLocationRelativeTo(null);
		aframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aframe.setVisible(true);
		aconn();
		aloaddata();
	}
	
	
	public void actionPerformed(ActionEvent ae){
		String astr = ae.getActionCommand();
		
		String authorname = aauthortf.getText();
		
		if(astr.equals(" Add ")){
			
			try{
				
				String QryString = "INSERT INTO author (AuthorName) VALUES(?)";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,authorname);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(apanel,"Author added successfully!" );
				
				aauthortf.setText("");
				aauthortf.requestFocus();
				aloaddata();
				
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(apanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(apanel,"Error: " + e.getMessage());
			}
			
		}else if(astr.equals(" Update ")){
			
			try{
				
				atm = (DefaultTableModel)atable.getModel();
		
		        int atableindex = atable.getSelectedRow();
		
		        String adf = atm.getValueAt(atableindex,0).toString();
				
				String QryString = "UPDATE author SET AuthorName = ? WHERE AuthorName = ?";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,authorname);
				pstmt.setString(2,adf);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(apanel,"Updated successfully!" );
				
				aauthortf.setText("");
				aauthortf.requestFocus();
				aloaddata();
				aadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(apanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(apanel,"Error: " + e.getMessage());
			}
		}else if(astr.equals(" Delete ")){
			
			try{
				
				atm = (DefaultTableModel)atable.getModel();
		
		        int atableindex = atable.getSelectedRow();
		
		        String df = atm.getValueAt(atableindex,0).toString();
				
				String QryString = "DELETE FROM author WHERE AuthorName = ?";
			    pstmt = conn.prepareStatement(QryString);
			
				pstmt.setString(1,df);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(apanel,"Deleted successfully!" );
				
				aauthortf.setText("");
				aauthortf.requestFocus();
				aloaddata();
				aadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(apanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(apanel,"Error: " + e.getMessage());
			}
			
			
		}else if(astr.equals(" Exit ")){
			
			int reply = JOptionPane.showConfirmDialog(apanel, "Are you sure to Exit?");
				
			if(reply == JOptionPane.YES_OPTION){
			aframe.setVisible(false);
			//System.exit(0);
		    }else if(reply == JOptionPane.NO_OPTION){
				//JOptionPane.exit(0);
			}
		}
	}
	
	public void mouseClicked(MouseEvent me){
		
		atm = (DefaultTableModel)atable.getModel();
		
		int atableindex = atable.getSelectedRow();
		
		aauthortf.setText(atm.getValueAt(atableindex,0).toString());
		aadd.setEnabled(false);
		
	}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
	
	
	public void aconn(){
		
		try{
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/library";
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
			
			//JOptionPane.showMessageDialog(cpanel,"Connect successfully....");
			
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(apanel,"SQL Error: " + sqle.getMessage());
		}catch(Exception e){
			JOptionPane.showMessageDialog(apanel,"Error: " + e.getMessage());
		}
		
	}
	
	public void aloaddata(){
		
		int a;
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.execute("SELECT * FROM author");
			rs = stmt.getResultSet();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			a =  rsmd.getColumnCount();
			
			atm.setRowCount(0);
			
			while(rs.next()){
				//atm.addRow(new Object[]{j,rs.getString("AuthorName")});
				//j++;
				Vector v = new Vector();
				
				for(int i = 1; i<=a ;i++){
					v.add(rs.getString("AuthorName"));
					
				}
				
				atm.addRow(v);
				
			}
			rs.last();
			
		atable.setModel(atm);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(apanel,"Error: " + e.getMessage());
		}
	}
	/*
	public static void main(String args[]){
		
		new author();
	}
	*/
}

//javac -cp mysql.jar;. author.java 