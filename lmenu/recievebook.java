//recievebook
package lmenu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat; 

public class recievebook implements ActionListener,MouseListener,KeyListener{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JFrame rbframe = new JFrame("Recieve Book");
	JPanel rbpanel = new JPanel();
	
	JLabel rblabel = new JLabel("Recieve Book");
	
	JLabel rbbookidl = new JLabel("Book ID: ");
	JLabel rbbooknamel = new JLabel("Book Name: ");
	JLabel rbbookcategoryl = new JLabel("Category: ");
	JLabel rbmemidl = new JLabel("Member ID: ");
	JLabel rbmemnamel = new JLabel("Member Name: ");
	JLabel rbissuedatel = new JLabel("Issue Date: ");
	JLabel rbreturndatel = new JLabel("Return Date: ");
	JLabel rbfinel = new JLabel("Fine: ");
	
	JTextField rbbookidtf = new JTextField();
	JTextField rbbooknametf = new JTextField();;
	JTextField rbmemidtf = new JTextField();
	JTextField rbbookcategorytf = new JTextField();
	JTextField rbmemnametf = new JTextField();
	JLabel issuedatel = new JLabel("EEE DD/MM/YYYY");
	JLabel returndatel = new JLabel("EEE DD/MM/YYYY");
	JTextField rbfinetf = new JTextField();
	
	JButton rbadd = new JButton(" Receive ");
	JButton rbupdate = new JButton(" Update ");
	JButton rbdelete = new JButton(" Delete ");
	JButton rbexit = new JButton(" Exit ");
	
	
	Date todaydate = new Date();  
    SimpleDateFormat formatter = new SimpleDateFormat("EEE dd/MM/yyyy");  
	
	
	JPanel rbpanelt = new JPanel(new GridLayout());
	DefaultTableModel rbtm = new DefaultTableModel(new String[]{"Sr.No.","Member ID","Member Name","Book ID","Book Name","Category","Issue Date","Return Date","Recieved Date","Fine"},0);
	JTable rbtable = new JTable();
	
	public recievebook(){
		
		rblabel.setBounds(380,20,230,50);
		rblabel.setFont(new Font("Serif", Font.BOLD, 40));
		
		rbbookidl.setBounds(30,120,150,25);
		rbbookidl.setFont(new Font("Serif", Font.BOLD, 20));
		rbbookidtf.setBounds(170,120,250,25);
		
		rbbooknamel.setBounds(30,170,150,25);
		rbbooknamel.setFont(new Font("Serif", Font.BOLD, 20));
		rbbooknametf.setBounds(170,170,250,25);
		
		rbbookcategoryl.setBounds(30,220,150,25);
		rbbookcategoryl.setFont(new Font("Serif", Font.BOLD, 20));
		rbbookcategorytf.setBounds(170,220,250,25);
		
		rbmemidl.setBounds(30,270,150,25);
		rbmemidl.setFont(new Font("Serif", Font.BOLD, 20));
		rbmemidtf.setBounds(170,270,250,25);
		
		rbmemnamel.setBounds(30,320,150,25);
		rbmemnamel.setFont(new Font("Serif", Font.BOLD, 20));
		rbmemnametf.setBounds(170,320,250,25);
		
		rbissuedatel.setBounds(30,370,150,25);
		rbissuedatel.setFont(new Font("Serif", Font.BOLD, 20));
		issuedatel.setBounds(170,370,250,25);
		issuedatel.setFont(new Font("Serif", Font.BOLD, 20));
		
		rbreturndatel.setBounds(30,420,150,25);
		rbreturndatel.setFont(new Font("Serif", Font.BOLD, 20));
		returndatel.setBounds(170,420,250,25);
		returndatel.setFont(new Font("Serif", Font.BOLD, 20));
		
		rbfinel.setBounds(30,470,150,25);
		rbfinel.setFont(new Font("Serif", Font.BOLD, 20));
		rbfinetf.setBounds(170,470,250,25);
		
		rbdelete.setBounds(60,550,100,50);
		rbupdate.setBounds(180,550,100,50);
		rbadd.setBounds(300,550,100,50);
		rbexit.setBounds(180,620,100,50);
		
		
		rbpanelt.setBounds(470,120,550,500);
		rbpanelt.setBackground(Color.decode("#b3b3b3"));
		JScrollPane rbpl = new JScrollPane(rbtable);
		rbtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		rbpanelt.add(rbpl);
		
		
		rbpanel.setBackground(Color.decode("#d9d9d9"));
		rbpanel.setBounds(20,20,1040,715);
		rbpanel.setLayout(null);
		
		rbpanel.add(rblabel);
		rbpanel.add(rbbookidl);
		rbpanel.add(rbbookidtf);
		rbpanel.add(rbbooknamel);
		rbpanel.add(rbbooknametf);
		rbpanel.add(rbbookcategoryl);
		rbpanel.add(rbbookcategorytf);
		rbpanel.add(rbmemidl);
		rbpanel.add(rbmemidtf);
		rbpanel.add(rbmemnamel);
		rbpanel.add(rbmemnametf);
		rbpanel.add(rbissuedatel);
		rbpanel.add(issuedatel);
		rbpanel.add(rbreturndatel);
		rbpanel.add(returndatel);
		rbpanel.add(rbfinel);
		rbpanel.add(rbfinetf);
		rbpanel.add(rbadd);
		rbpanel.add(rbupdate);
		rbpanel.add(rbdelete);
		rbpanel.add(rbexit);
		rbpanel.add(rbpanelt);
		
		rbadd.addActionListener(this);
		rbupdate.addActionListener(this);
		rbdelete.addActionListener(this);
		rbexit.addActionListener(this);
		
		rbtable.addMouseListener(this);
		rbbookidtf.addKeyListener(this);
		
		rbframe.add(rbpanel);
		
		rbframe.setSize(1100,800);
		rbframe.setLayout(null);
		rbframe.setLocationRelativeTo(null);
		rbframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rbframe.setVisible(true);
		
		rbconn();
		rbloaddata();
	}
	
	
	public void actionPerformed(ActionEvent ae){
		String rbstr = ae.getActionCommand();
		
		String today = formatter.format(todaydate);
		
		String rbmid = rbmemidtf.getText();
		String rbmname = rbmemnametf.getText();
		String rbbid = rbbookidtf.getText();
		String rbbname = rbbooknametf.getText();
		String rbcategory = rbbookcategorytf.getText();
		String rbissdate = issuedatel.getText();
		String rbrecidate= returndatel.getText();
		String rbfine = rbfinetf.getText();
		
		if(rbstr.equals(" Receive ")){
			
			try{
				
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.execute("SELECT * FROM recievebook");
				rs = stmt.getResultSet();
				rs.last();
				int norows = rs.getInt("SrNo") + 1;
				System.out.println(norows + 1 );
				System.out.println(today );
				
				
				String QryString = "INSERT INTO recievebook (SrNo,MemID,MemName,BookID,BookName,BookCategory,IssueDate,ReturnDate,RecievedDate,Fine) VALUES(?,?,?,?,?,?,?,?,?,?)";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setInt(1,norows);
			    pstmt.setString(2,rbmid);
				pstmt.setString(3,rbmname);
				pstmt.setString(4,rbbid);
				pstmt.setString(5,rbbname);
				pstmt.setString(6,rbcategory);
				pstmt.setString(7,rbissdate);
				pstmt.setString(8,rbrecidate);
				pstmt.setString(9,today);
				pstmt.setString(10,rbfine);
				
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement("UPDATE book SET Status = ? WHERE ID = ?");
				pstmt.setString(1,"Available");  
				pstmt.setString(2,rbbid);
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement("DELETE FROM issuebook WHERE BookID = ?");
				pstmt.setString(1,rbbid);
				pstmt.executeUpdate();
				
				
				JOptionPane.showMessageDialog(rbpanel,"Book Recieved successfully!" );
				
				rbmemidtf.setText("");
				rbmemnametf.setText("");
				rbbookidtf.setText("");
				rbbooknametf.setText("");
				rbbookcategorytf.setText("");
				issuedatel.setText("EEE DD/MM/YYYY");
				returndatel.setText("EEE DD/MM/YYYY");
				rbfinetf.setText("");
				rbbookidtf.requestFocus();
				rbloaddata();
				
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(rbpanel,"SQL Error: " + sqle.getMessage());
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(rbpanel,"Error: " + e.getMessage());
			}
			
			
			
		}else if(rbstr.equals(" Update ")){
			
			try{
				
				rbtm = (DefaultTableModel)rbtable.getModel();
		
		        int rbtableindex = rbtable.getSelectedRow();
		
		        String rbdf = rbtm.getValueAt(rbtableindex,0).toString();
				int rbdf1 = Integer.parseInt(rbdf);
				String QryString = "UPDATE recievebook SET Fine = ? WHERE SrNo = ?";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,rbfine);
				pstmt.setInt(2,rbdf1);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(rbpanel,"Updated successfully!" );
				
				rbmemidtf.setText("");
				rbmemnametf.setText("");
				rbbookidtf.setText("");
				rbbooknametf.setText("");
				rbbookcategorytf.setText("");
				issuedatel.setText("EEE DD/MM/YYYY");
				returndatel.setText("EEE DD/MM/YYYY");
				rbfinetf.setText("");
				rbbookidtf.requestFocus();
				rbloaddata();
				rbadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(rbpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(rbpanel,"Error: " + e.getMessage());
			}
			
			
		}else if(rbstr.equals(" Delete ")){
			
			try{
				
				rbtm = (DefaultTableModel)rbtable.getModel();
		
		        int rbtableindex = rbtable.getSelectedRow();
		
		        String rbdf = rbtm.getValueAt(rbtableindex,0).toString();
				int rbdf2 = Integer.parseInt(rbdf);
				String QryString = "DELETE FROM recievebook WHERE SrNo = ?";
			    pstmt = conn.prepareStatement(QryString);
			
				pstmt.setInt(1,rbdf2);
				
				pstmt.executeUpdate();
				
				
				String QryString1 = "INSERT INTO issuebook (MemID,MemName,BookID,BookName,BookCategory,IssueDate,ReturnDate) VALUES(?,?,?,?,?,?,?)";
			    pstmt = conn.prepareStatement(QryString1);
			
			    pstmt.setString(1,rbmid);
				pstmt.setString(2,rbmname);
				pstmt.setString(3,rbbid);
				pstmt.setString(4,rbbname);
				pstmt.setString(5,rbcategory);
				pstmt.setString(6,rbissdate);
				pstmt.setString(7,rbrecidate);
				pstmt.executeUpdate();
				
				
				pstmt = conn.prepareStatement("UPDATE book SET Status = ? WHERE ID = ?");
				pstmt.setString(1,"Not Available");  
				pstmt.setString(2,rbbid);
				
				pstmt.executeUpdate();
				
				JOptionPane.showMessageDialog(rbpanel,"Deleted successfully!" );
				
				rbmemidtf.setText("");
				rbmemnametf.setText("");
				rbbookidtf.setText("");
				rbbooknametf.setText("");
				rbbookcategorytf.setText("");
				issuedatel.setText("EEE DD/MM/YYYY");
				returndatel.setText("EEE DD/MM/YYYY");
				rbfinetf.setText("");
				rbbookidtf.requestFocus();
				rbloaddata();
				rbadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(rbpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(rbpanel,"Error: " + e.getMessage());
			}
			
			
		}else if(rbstr.equals(" Exit ")){
			int reply = JOptionPane.showConfirmDialog(rbpanel, "Are you sure to Exit?");
				
			if(reply == JOptionPane.YES_OPTION){
			rbframe.setVisible(false);
			//System.exit(0);
		    }else if(reply == JOptionPane.NO_OPTION){
				//JOptionPane.exit(0);
			}
		}
		
	}
	
	public void mouseClicked(MouseEvent me){
		
		rbtm = (DefaultTableModel)rbtable.getModel();
		
		int rbtableindex = rbtable.getSelectedRow();
		
		rbmemidtf.setText(rbtm.getValueAt(rbtableindex,1).toString());
		rbmemnametf.setText(rbtm.getValueAt(rbtableindex,2).toString());
		rbbookidtf.setText(rbtm.getValueAt(rbtableindex,3).toString());
		rbbooknametf.setText(rbtm.getValueAt(rbtableindex,4).toString());
		rbbookcategorytf.setText(rbtm.getValueAt(rbtableindex,5).toString());
		issuedatel.setText(rbtm.getValueAt(rbtableindex,6).toString());
		returndatel.setText(rbtm.getValueAt(rbtableindex,7).toString());
		rbfinetf.setText(rbtm.getValueAt(rbtableindex,9).toString());
		//ibmemidtf.setText(ibtm.getValueAt(ibtableindex,0).toString());
		//ibmemidtf.setText(ibtm.getValueAt(ibtableindex,0).toString());
		
		
		rbadd.setEnabled(false);
		
	}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
	
	public void keyPressed(KeyEvent ke){
		
		if(ke.getKeyCode() == KeyEvent.VK_ENTER){
			
			String bid = rbbookidtf.getText();
			//System.out.println(bid);
			try{
				
				String QryString = "SELECT * FROM issuebook WHERE BookID = ?";
				pstmt = conn.prepareStatement(QryString);
				
				pstmt.setString(1,bid);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					String kpbname = rs.getString("BookName");
					String kpbcate = rs.getString("BookCategory");
					String kpmid = rs.getString("MemID");
					String kpmname = rs.getString("MemName");
					String id = rs.getString("IssueDate");
					String rd = rs.getString("ReturnDate");
					
				    rbbooknametf.setText(kpbname);
					rbbookcategorytf.setText(kpbcate);
					rbmemidtf.setText(kpmid);
					rbmemnametf.setText(kpmname);
					issuedatel.setText(id);
					returndatel.setText(rd);
					
				}else{
					
					JOptionPane.showMessageDialog(rbpanel,"Book Not Found!");
				}
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(rbpanel,"Error: " + e.getMessage());
			}
		}
	}
	
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}
	
	
	public void rbconn(){
		
		try{
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/library";
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
			
			//JOptionPane.showMessageDialog(cpanel,"Connect successfully....");
			
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(rbpanel,"SQL Error: " + sqle.getMessage());
		}catch(Exception e){
			JOptionPane.showMessageDialog(rbpanel,"Error: " + e.getMessage());
		}
		
	}	
	
public void rbloaddata(){
	    int rb;
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.execute("SELECT * FROM recievebook");
			rs = stmt.getResultSet();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			rb =  rsmd.getColumnCount();
			
			rbtm.setRowCount(0);
			rs.next();
			while(rs.next()){
				//atm.addRow(new Object[]{j,rs.getString("AuthorName")});
				//j++;
				Vector v = new Vector();
				
				for(int i = 1; i<=rb ;i++){
					v.add(rs.getString("SrNo"));
					v.add(rs.getString("MemID"));
					v.add(rs.getString("MemName"));
					v.add(rs.getString("BookID"));
					v.add(rs.getString("BookName"));
					v.add(rs.getString("BookCategory"));
					v.add(rs.getString("IssueDate"));
					v.add(rs.getString("ReturnDate"));
					v.add(rs.getString("RecievedDate"));
					v.add(rs.getString("Fine"));
					
				}
				
				rbtm.addRow(v);
				
			}
			rs.last();
			
		 rbtable.setModel(rbtm);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(rbpanel,"Error: " + e.getMessage());
		}
	
	}	
	/*
public static void main(String args[]){
	
	new recievebook();
	
}	
	*/
}
//javac -cp mysql.jar;. recievebook.java 