package lmenu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class category implements ActionListener,MouseListener{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	DefaultTableModel tm = new DefaultTableModel(new String[]{"Category Name", "Status"},0);
	
	JFrame cframe = new JFrame("Category");
	JPanel cpanel = new JPanel();
	
	JLabel cpanell = new JLabel("Category");
	JLabel ccategorynamel = new JLabel("Category Name: "); 
	JLabel cstatusl = new JLabel("Status: "); 
	
	JButton cadd = new JButton(" Add ");
	JButton cupdate = new JButton(" Update ");
	JButton cdelete = new JButton(" Delete ");
	JButton ccancel = new JButton(" Exit ");
	
	JTextField ccategorytf = new JTextField();
    String state[]	= {"Active","Deactive"}; 
	JComboBox cstatuscb = new JComboBox(state);
	
	JPanel p = new JPanel(new GridLayout());
	JTable ctable = new JTable();
	
	
	
	public category(){
	
		
		cpanell.setFont(new Font("Serif", Font.BOLD, 40));
		cpanell.setBounds(400,20,200,50);
		
		ccategorynamel.setBounds(30,120,150,25);
		ccategorynamel.setFont(new Font("Serif", Font.BOLD, 20));
		
		cstatusl.setBounds(30,170,150,25);
		cstatusl.setFont(new Font("Serif", Font.BOLD, 20));
		
		
		ccategorytf.setBounds(170,120,250,25);
		cstatuscb.setBounds(170,170,250,25);
		
		
		cdelete.setBounds(60,260,100,50);
		cupdate.setBounds(180,260,100,50);
		cadd.setBounds(300,260,100,50);
		ccancel.setBounds(180,340,100,50);
		
		p.setBounds(500,120,400,350);
		//p.setLayout(null);
		p.setBackground(Color.decode("#d9d9d9"));
		//ctable.setBounds(25,25,600,500);
		//ctable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//ctable.setBounds(25,25,600,500);
		JScrollPane pl = new JScrollPane(ctable);
		//pl.setLayout(new ScrollPaneLayout());
		// pl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        pl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		p.add(pl);
		
		cpanel.add(cpanell);
		cpanel.add(ccategorynamel);
		cpanel.add(cstatusl);
		cpanel.add(ccategorytf);
		cpanel.add(cstatuscb);
		cpanel.add(cdelete);
		cpanel.add(cupdate);
		cpanel.add(cadd);
		cpanel.add(ccancel);
		cpanel.add(p);
		
		
		cpanel.setBounds(20,20,940,515);
		cpanel.setBackground(Color.decode("#d9d9d9"));
		cpanel.setLayout(null);
		
		cadd.addActionListener(this);
		cupdate.addActionListener(this);
		cdelete.addActionListener(this);
		ccancel.addActionListener(this);
		
		ctable.addMouseListener(this);
		
		cframe.add(cpanel);
		cframe.setSize(1000,600);
		cframe.setLayout(null);
		cframe.setLocationRelativeTo(null);
		cframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cframe.setVisible(true);
		
		connect();
		loaddata();
		
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String str = ae.getActionCommand();
		
		String category = ccategorytf.getText();
        String status = cstatuscb.getSelectedItem().toString();
		
		
		if(str.equals(" Add ")){
			
			try{
				
				String QryString = "INSERT INTO category (CategoryName,Status) VALUES(?,?)";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,category);
			    pstmt.setString(2,status);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(cpanel,"Category Name added successfully!" );
				
				ccategorytf.setText("");
				cstatuscb.setSelectedIndex(0);
				ccategorytf.requestFocus();
				loaddata();
				
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(cpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(cpanel,"Error: " + e.getMessage());
			}
			
			
		}else if(str.equals(" Update ")){
			
			try{
				
				tm = (DefaultTableModel)ctable.getModel();
		
		        int tableindex = ctable.getSelectedRow();
		
		        String df = tm.getValueAt(tableindex,0).toString();
				
				String QryString = "UPDATE category SET CategoryName = ?, Status = ? WHERE CategoryName = ?";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,category);
			    pstmt.setString(2,status);
				pstmt.setString(3,df);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(cpanel,"Updated successfully!" );
				
				ccategorytf.setText("");
				cstatuscb.setSelectedIndex(0);
				ccategorytf.requestFocus();
				loaddata();
				cadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(cpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(cpanel,"Error: " + e.getMessage());
			}
		}else if(str.equals(" Delete ")){
			
			try{
				
				tm = (DefaultTableModel)ctable.getModel();
		
		        int tableindex = ctable.getSelectedRow();
		
		        String df = tm.getValueAt(tableindex,0).toString();
				
				String QryString = "DELETE FROM category WHERE CategoryName = ?";
			    pstmt = conn.prepareStatement(QryString);
			
				pstmt.setString(1,df);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(cpanel,"Deleted successfully!" );
				
				ccategorytf.setText("");
				cstatuscb.setSelectedIndex(0);
				ccategorytf.requestFocus();
				loaddata();
				cadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(cpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(cpanel,"Error: " + e.getMessage());
			}
			
		}else if(str.equals(" Exit ")){
			
			int reply = JOptionPane.showConfirmDialog(cpanel, "Are you sure to Exit?");
				
			if(reply == JOptionPane.YES_OPTION){
			cframe.setVisible(false);
		    }else if(reply == JOptionPane.NO_OPTION){
				//JOptionPane.exit(0);
			}
			
		}
		
	}
	
	public void mouseClicked(MouseEvent me){
		
		tm = (DefaultTableModel)ctable.getModel();
		
		int tableindex = ctable.getSelectedRow();
		
		ccategorytf.setText(tm.getValueAt(tableindex,0).toString());
		cstatuscb.setSelectedItem(tm.getValueAt(tableindex,1).toString());
		cadd.setEnabled(false);
	}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
	
	public void connect(){
		
		try{
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/library";
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
			
			//JOptionPane.showMessageDialog(cpanel,"Connect successfully....");
			
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(cpanel,"SQL Error: " + sqle.getMessage());
		}catch(Exception e){
			JOptionPane.showMessageDialog(cpanel,"Error: " + e.getMessage());
		}
		
	}
	
	public void loaddata(){
		
		int c;
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.execute("SELECT * FROM category");
			rs = stmt.getResultSet();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			c =  rsmd.getColumnCount();
			
			tm.setRowCount(0);
			
			while(rs.next()){
				//tm.addRow(new Object[]{j,rs.getString("CategoryName"),rs.getString("Status")});
				//j++;
				Vector v = new Vector();
				
				for(int i = 1; i<=c ;i++){
					v.add(rs.getString("CategoryName"));
					v.add(rs.getString("Status"));
					
				}
				
				tm.addRow(v);
				
			}
			rs.last();
			
		ctable.setModel(tm);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(cpanel,"Error: " + e.getMessage());
		}
		
	}
	/*
	
	public static void main(String args[]){
		
		new category();
	}
	*/
}

//javac -cp mysql.jar;. category.java