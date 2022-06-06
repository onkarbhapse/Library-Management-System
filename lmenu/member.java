package lmenu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class member implements ActionListener,MouseListener{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JFrame mframe = new JFrame("Members");
	
	JPanel mpanel = new JPanel();
	
	JLabel mlabel = new JLabel("Members");
	
	JLabel mnamel = new JLabel("Name: ");
	JLabel midl = new JLabel("ID: ");
    JLabel mphonel = new JLabel("Phone: ");	
	JLabel memail = new JLabel("Email: ");
	
	JTextField mnametf = new JTextField(50);
	JTextField midtf = new JTextField(15);
	JTextField mphonetf = new JTextField(10);
	JTextField memailtf = new JTextField(50);
	
	JButton madd = new JButton(" Add ");
	JButton mupdate = new JButton(" Update ");
	JButton mdelete = new JButton(" Delete ");
	JButton mexit = new JButton(" Exit ");
	
	JPanel mpanejt = new JPanel(new GridLayout());
	DefaultTableModel mtm = new DefaultTableModel(new String[]{"ID","Name","Phone","Email"},0);
	JTable mtable = new JTable();
	
	
	public member(){
		
		
		mlabel.setBounds(400,20,200,50);
		mlabel.setFont(new Font("Serif", Font.BOLD, 40));
		
		mnamel.setBounds(30,120,150,25);
		mnamel.setFont(new Font("Serif", Font.BOLD, 20));
		mnametf.setBounds(170,120,250,25);
		
		midl.setBounds(30,170,150,25);
		midl.setFont(new Font("Serif", Font.BOLD, 20));
		midtf.setBounds(170,170,250,25);
		
		mphonel.setBounds(30,220,150,25);
		mphonel.setFont(new Font("Serif", Font.BOLD, 20));
		mphonetf.setBounds(170,220,250,25);
		
		memail.setBounds(30,270,150,25);
		memail.setFont(new Font("Serif", Font.BOLD, 20));
		memailtf.setBounds(170,270,250,25);
		
		mdelete.setBounds(60,350,100,50);
		mupdate.setBounds(180,350,100,50);
		madd.setBounds(300,350,100,50);
		mexit.setBounds(180,420,100,50);
		
		
		mpanejt.setBounds(470,120,450,350);
		mpanejt.setBackground(Color.decode("#b3b3b3"));
		JScrollPane mpl = new JScrollPane(mtable);
		mtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		mpanejt.add(mpl);
		
		
		mpanel.setLayout(null);
		mpanel.setBackground(Color.decode("#d9d9d9"));
		mpanel.setBounds(20,20,940,515);
		
		mpanel.add(mlabel);
		mpanel.add(mnamel);
		mpanel.add(midl);
		mpanel.add(mphonel);
		mpanel.add(memail);
		mpanel.add(mnametf);
		mpanel.add(midtf);
		mpanel.add(mphonetf);
		mpanel.add(memailtf);
		mpanel.add(madd);
		mpanel.add(mupdate);
		mpanel.add(mdelete);
		mpanel.add(mexit);
		mpanel.add(mpanejt);
		
		madd.addActionListener(this);
		mupdate.addActionListener(this);
		mdelete.addActionListener(this);
		mexit.addActionListener(this);
		
		mtable.addMouseListener(this);
		
		mframe.add(mpanel);
		mframe.setSize(1000,600);
		mframe.setLayout(null);
		mframe.setLocationRelativeTo(null);
		mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mframe.setVisible(true);
		
		mconn();
		mloaddata();
	}
	
	
	public void actionPerformed(ActionEvent ae){
		
		String mstr = ae.getActionCommand();
		
		String name = mnametf.getText();
		String id = midtf.getText();
		String phone = mphonetf.getText();
		String email = memailtf.getText();
		
		if(mstr.equals(" Add ")){
			
			try{
				
				String QryString = "INSERT INTO member (ID,Name,Phone,Email) VALUES(?,?,?,?)";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,id);
				pstmt.setString(2,name);
				pstmt.setString(3,phone);
				pstmt.setString(4,email);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(mpanel,"Member added successfully!" );
				
				mnametf.setText("");
				midtf.setText("");
				mphonetf.setText("");
				memailtf.setText("");
				mnametf.requestFocus();
				mloaddata();
				
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(mpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(mpanel,"Error: " + e.getMessage());
			}
			
		}else if(mstr.equals(" Update ")){
			
			try{
				
				mtm = (DefaultTableModel)mtable.getModel();
		
		        int mtableindex = mtable.getSelectedRow();
		
		        String mdf = mtm.getValueAt(mtableindex,0).toString();
				
				String QryString = "UPDATE member SET ID = ?, Name  = ?, Phone = ?, Email = ? WHERE ID = ?";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,id);
				pstmt.setString(2,name);
				pstmt.setString(3,phone);
				pstmt.setString(4,email);
				pstmt.setString(5,mdf);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(mpanel,"Updated successfully!" );
				
				mnametf.setText("");
				midtf.setText("");
				mphonetf.setText("");
				memailtf.setText("");
				mnametf.requestFocus();
				mloaddata();
				madd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(mpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(mpanel,"Error: " + e.getMessage());
			}
			
		}else if(mstr.equals(" Delete ")){
			
			try{
				
				mtm = (DefaultTableModel)mtable.getModel();
		
		        int mtableindex = mtable.getSelectedRow();
		
		        String mdf = mtm.getValueAt(mtableindex,0).toString();
				
				String QryString = "DELETE FROM member WHERE ID = ?";
			    pstmt = conn.prepareStatement(QryString);
			
				pstmt.setString(1,mdf);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(mpanel,"Deleted successfully!" );
				
				mnametf.setText("");
				midtf.setText("");
				mphonetf.setText("");
				memailtf.setText("");
				mnametf.requestFocus();
				mloaddata();
				madd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(mpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(mpanel,"Error: " + e.getMessage());
			}
			
		}else if(mstr.equals(" Exit ")){
			int reply = JOptionPane.showConfirmDialog(mpanel, "Are you sure to Exit?");
			
			if(reply == JOptionPane.YES_OPTION){
			mframe.setVisible(false);
			//System.exit(0);
		    }else if(reply == JOptionPane.NO_OPTION){
				//JOptionPane.exit(0);
			}
		}
		
	}
	
	
	public void mouseClicked(MouseEvent me){
		
		mtm = (DefaultTableModel)mtable.getModel();
		
		int mtableindex = mtable.getSelectedRow();
		
		midtf.setText(mtm.getValueAt(mtableindex,0).toString());
		mnametf.setText(mtm.getValueAt(mtableindex,1).toString());
		mphonetf.setText(mtm.getValueAt(mtableindex,2).toString());
        memailtf.setText(mtm.getValueAt(mtableindex,3).toString());
		madd.setEnabled(false);
		
	}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
	
	
	public void mconn(){
		
		try{
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/library";
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
			
			//JOptionPane.showMessageDialog(mpanel,"Connect successfully....");
			
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(mpanel,"SQL Error: " + sqle.getMessage());
		}catch(Exception e){
			JOptionPane.showMessageDialog(mpanel,"Error: " + e.getMessage());
		}
		
	}
	
	public void mloaddata(){
	    int m;
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.execute("SELECT * FROM member");
			rs = stmt.getResultSet();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			m =  rsmd.getColumnCount();
			
			mtm.setRowCount(0);
			
			while(rs.next()){
				//atm.addRow(new Object[]{j,rs.getString("AuthorName")});
				//j++;
				Vector v = new Vector();
				
				for(int i = 1; i<=m ;i++){
					v.add(rs.getString("ID"));
					v.add(rs.getString("Name"));
					v.add(rs.getString("Phone"));
					v.add(rs.getString("Email"));
					
				}
				
				mtm.addRow(v);
				
			}
			rs.last();
			
		 mtable.setModel(mtm);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(mpanel,"Error: " + e.getMessage());
		}
	
	}
	
	/*
	public static void main(String args[]){
		new member();
	}
	*/
}
//javac -cp mysql.jar;. member.java 