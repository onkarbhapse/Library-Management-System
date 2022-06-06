//issuebook
package lmenu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
import java.util.Calendar;
import javax.swing.JPanel.*;
import java.util.Date;
import java.text.SimpleDateFormat;  


public class issuebook implements ActionListener,MouseListener,KeyListener{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JFrame ibframe = new JFrame(" Issue Book");
	JPanel ibpanel = new JPanel();
	
	JLabel iblabel = new JLabel("Issue Book");
	
	JLabel ibmemidl = new JLabel("Member ID: ");
	JLabel ibmemnamel = new JLabel("Member Name: ");
	JLabel ibbookidl = new JLabel("Book ID: ");
	JLabel ibbooknamel = new JLabel("Book Name: ");
	JLabel ibauthorl = new JLabel("Author: ");
	JLabel ibcategoryl = new JLabel("Category: ");
	JLabel ibstatusl = new JLabel("Status: ");
	JLabel ibissuedatel = new JLabel("Issue Date: ");
	JLabel ibreturndatel = new JLabel("Return Date: ");
	
	JTextField ibmemidtf = new JTextField();
	JTextField ibmemnametf = new JTextField();
	JTextField ibbookidtf = new JTextField();
	JTextField ibbooknametf = new JTextField();
	JTextField ibauthortf = new JTextField();
	JTextField ibcategorytf = new JTextField();
	JTextField ibstatustf = new JTextField();
	
	JButton ibadd = new JButton(" Issue ");
	JButton ibupdate = new JButton(" Update ");
	JButton ibdelete = new JButton(" Delete ");
	JButton ibexit = new JButton(" Exit ");
	
	//JCalendar ibdate = new JCalendar();
	Date today = new Date();
	JSpinner ibdate = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
	JSpinner.DateEditor ibeditor = new JSpinner.DateEditor(ibdate, "EEE dd/MM/yy");
    
	
	Date date = new Date();
	JSpinner rbdate = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.MONTH));
	JSpinner.DateEditor rbeditor = new JSpinner.DateEditor(rbdate, "EEE dd/MM/yy");
	
	JPanel ibpanelt = new JPanel(new GridLayout());
	DefaultTableModel ibtm = new DefaultTableModel(new String[]{"Member ID","Member Name","Book ID","Book Name","Category","Issue Date","Return Date"},0);
	JTable ibtable = new JTable();
	
	public issuebook(){
		
		iblabel.setBounds(400,20,200,50);
		iblabel.setFont(new Font("Serif", Font.BOLD, 40));
		
		ibmemidl.setBounds(30,120,150,25);
		ibmemidl.setFont(new Font("Serif", Font.BOLD, 20));
		ibmemidtf.setBounds(170,120,250,25);
		
		ibmemnamel.setBounds(30,170,150,25);
		ibmemnamel.setFont(new Font("Serif", Font.BOLD, 20));
		ibmemnametf.setBounds(170,170,250,25);
		
		ibbookidl.setBounds(30,220,150,25);
		ibbookidl.setFont(new Font("Serif", Font.BOLD, 20));
		ibbookidtf.setBounds(170,220,250,25);
		
		ibbooknamel.setBounds(30,270,150,25);
		ibbooknamel.setFont(new Font("Serif", Font.BOLD, 20));
		ibbooknametf.setBounds(170,270,250,25);
		
		ibauthorl.setBounds(30,320,150,25);
		ibauthorl.setFont(new Font("Serif", Font.BOLD, 20));
		ibauthortf.setBounds(170,320,250,25);
		
		ibcategoryl.setBounds(30,370,150,25);
		ibcategoryl.setFont(new Font("Serif", Font.BOLD, 20));
		ibcategorytf.setBounds(170,370,250,25);
		
		ibstatusl.setBounds(30,420,150,25);
		ibstatusl.setFont(new Font("Serif", Font.BOLD, 20));
		ibstatustf.setBounds(170,420,250,25);
		
		ibissuedatel.setBounds(30,470,150,25);
		ibissuedatel.setFont(new Font("Serif", Font.BOLD, 20));
		ibdate.setBounds(170,470,250,25);
		
		ibreturndatel.setBounds(30,520,150,25);
		ibreturndatel.setFont(new Font("Serif", Font.BOLD, 20));
		rbdate.setBounds(170,520,250,25);
		
		ibdelete.setBounds(60,620,100,50);
		ibupdate.setBounds(180,620,100,50);
		ibadd.setBounds(300,620,100,50);
		ibexit.setBounds(180,700,100,50);
		
		
		
		ibpanelt.setBounds(500,120,600,550);
		ibpanelt.setBackground(Color.decode("#b3b3b3"));
		JScrollPane apl = new JScrollPane(ibtable);
		ibtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ibpanelt.add(apl);
		
		ibdate.setEditor(ibeditor);
		
		rbdate.setEditor(rbeditor);
		
		ibpanel.setBounds(20,20,1140,815);
		ibpanel.setLayout(null);
		ibpanel.setBackground(Color.decode("#d9d9d9"));
		
		ibpanel.add(iblabel);
		ibpanel.add(ibmemidl);
		ibpanel.add(ibmemnamel);
		ibpanel.add(ibbookidl);
		ibpanel.add(ibbooknamel);
		ibpanel.add(ibauthorl);
		ibpanel.add(ibcategoryl);
		ibpanel.add(ibstatusl);
		ibpanel.add(ibissuedatel);
		ibpanel.add(ibreturndatel);
		ibpanel.add(ibmemidtf);
		ibpanel.add(ibmemnametf);
		ibpanel.add(ibbookidtf);
		ibpanel.add(ibbooknametf);
		ibpanel.add(ibauthortf);
		ibpanel.add(ibcategorytf);
		ibpanel.add(ibstatustf);
		ibpanel.add(ibdate);
		ibpanel.add(rbdate);
		ibpanel.add(ibadd);
		ibpanel.add(ibupdate);
		ibpanel.add(ibdelete);
		ibpanel.add(ibexit);
		ibpanel.add(ibpanelt);
		
		
		ibadd.addActionListener(this);
		ibupdate.addActionListener(this);
		ibdelete.addActionListener(this);
		ibexit.addActionListener(this);
		
		ibtable.addMouseListener(this);
		
		ibmemidtf.addKeyListener(this);
		ibbookidtf.addKeyListener(this);
		
		ibframe.add(ibpanel);
		ibframe.setSize(1200,900);
		ibframe.setLayout(null);
		ibframe.setLocationRelativeTo(null);
		ibframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ibframe.setVisible(true);
		
		ibconn();
		ibloaddata();
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String ibstr = ae.getActionCommand();
		
		String ibmid = ibmemidtf.getText();
		String ibmname = ibmemnametf.getText();
		String ibbid = ibbookidtf.getText();
		String ibbname = ibbooknametf.getText();
		String ibcategory = ibcategorytf.getText();
		String ibibdate = new SimpleDateFormat("EEE dd/MM/yy").format(ibdate.getValue());
		String ibrbdate = new SimpleDateFormat("EEE dd/MM/yy").format(rbdate.getValue());
		
		if(ibstr.equals(" Issue ")){
			
			try{
				
				String QryString = "INSERT INTO issuebook (MemID,MemName,BookID,BookName,BookCategory,IssueDate,ReturnDate) VALUES(?,?,?,?,?,?,?)";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,ibmid);
				pstmt.setString(2,ibmname);
				pstmt.setString(3,ibbid);
				pstmt.setString(4,ibbname);
				pstmt.setString(5,ibcategory);
				pstmt.setString(6,ibibdate);
				pstmt.setString(7,ibrbdate);
				
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement("UPDATE book SET Status = ? WHERE ID = ?");
				pstmt.setString(1,"Not Available");  
				pstmt.setString(2,ibbid);
				
				pstmt.executeUpdate();
				
				JOptionPane.showMessageDialog(ibpanel,"Book Issued successfully!" );
				
				ibmemidtf.setText("");
				ibmemnametf.setText("");
				ibbookidtf.setText("");
				ibbooknametf.setText("");
				ibcategorytf.setText("");
				ibauthortf.setText("");
				ibstatustf.setText("");
				ibmemidtf.requestFocus();
				ibloaddata();
				
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(ibpanel,"SQL Error: " + sqle.getMessage());
				ibmemidtf.setText("");
				ibmemnametf.setText("");
				ibbookidtf.setText("");
				ibbooknametf.setText("");
				ibcategorytf.setText("");
				ibauthortf.setText("");
				ibstatustf.setText("");
				ibmemidtf.requestFocus();
				ibloaddata();
			}catch(Exception e){
				JOptionPane.showMessageDialog(ibpanel,"Error: " + e.getMessage());
			}
			
			
		}else if(ibstr.equals(" Update ")){
			
			try{
				
				ibtm = (DefaultTableModel)ibtable.getModel();
		
		        int ibtableindex = ibtable.getSelectedRow();
		
		        String ibdf = ibtm.getValueAt(ibtableindex,2).toString();
				
				String QryString = "UPDATE issuebook SET MemID = ?,MemName = ?,IssueDate = ?,ReturnDate = ? WHERE BookID = ?";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,ibmid);
				pstmt.setString(2,ibmname);
				pstmt.setString(3,ibibdate);
				pstmt.setString(4,ibrbdate);
				pstmt.setString(5,ibdf);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(ibpanel,"Updated successfully!" );
				
				ibmemidtf.setText("");
				ibmemnametf.setText("");
				ibbookidtf.setText("");
				ibbooknametf.setText("");
				ibcategorytf.setText("");
				ibauthortf.setText("");
				ibstatustf.setText("");
				ibmemidtf.requestFocus();
				ibloaddata();
				ibadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(ibpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(ibpanel,"Error: " + e.getMessage());
			}
			
			
			
		}else if(ibstr.equals(" Delete ")){
			
			try{
				
				ibtm = (DefaultTableModel)ibtable.getModel();
		
		        int ibtableindex = ibtable.getSelectedRow();
		
		        String ibdf = ibtm.getValueAt(ibtableindex,2).toString();
				
				String QryString = "DELETE FROM issuebook WHERE BookID = ?";
			    pstmt = conn.prepareStatement(QryString);
			
				pstmt.setString(1,ibdf);
				
				pstmt.executeUpdate();
				
				
				pstmt = conn.prepareStatement("UPDATE book SET Status = ? WHERE ID = ?");
				pstmt.setString(1,"Available");  
				pstmt.setString(2,ibdf);
				
				pstmt.executeUpdate();
				
				JOptionPane.showMessageDialog(ibpanel,"Deleted successfully!" );
				
				ibmemidtf.setText("");
				ibmemnametf.setText("");
				ibbookidtf.setText("");
				ibbooknametf.setText("");
				ibcategorytf.setText("");
				ibauthortf.setText("");
				ibstatustf.setText("");
				ibmemidtf.requestFocus();
				ibloaddata();
				ibadd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(ibpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(ibpanel,"Error: " + e.getMessage());
			}
			
			
			
		}else if(ibstr.equals(" Exit ")){
			
			int reply = JOptionPane.showConfirmDialog(ibpanel, "Are you sure to Exit?");
				
			if(reply == JOptionPane.YES_OPTION){
			ibframe.setVisible(false);
			//System.exit(0);
		    }else if(reply == JOptionPane.NO_OPTION){
				//JOptionPane.exit(0);
			}
			
		}
		
		
	}
	
	
	public void mouseClicked(MouseEvent me){
		
		ibtm = (DefaultTableModel)ibtable.getModel();
		
		int ibtableindex = ibtable.getSelectedRow();
		
		ibmemidtf.setText(ibtm.getValueAt(ibtableindex,0).toString());
		ibmemnametf.setText(ibtm.getValueAt(ibtableindex,1).toString());
		ibbookidtf.setText(ibtm.getValueAt(ibtableindex,2).toString());
		ibbooknametf.setText(ibtm.getValueAt(ibtableindex,3).toString());
		ibcategorytf.setText(ibtm.getValueAt(ibtableindex,4).toString());
		String ibd = ibtm.getValueAt(ibtableindex,5).toString();
		try{
			Date ibdmc = new SimpleDateFormat("EEE dd/MM/yy").parse(ibd);
			ibdate.setValue(ibdmc);
			}catch(Exception e){}
		
		String rbd = ibtm.getValueAt(ibtableindex,6).toString();
		try{
			Date rcdate = new SimpleDateFormat("EEE dd/MM/yy").parse(rbd);
			rbdate.setValue(rcdate);
			}catch(Exception e){}
		
		ibadd.setEnabled(false);
		
	}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
	
	public void keyPressed(KeyEvent ke){
		
		if(ke.getKeyCode() == KeyEvent.VK_ENTER){
			
			String mid = ibmemidtf.getText();
			
			try{
				
				String QryString = "SELECT * FROM member WHERE ID = ?";
				pstmt = conn.prepareStatement(QryString);
				
				pstmt.setString(1,mid);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					String mname = rs.getString("Name");
					ibmemnametf.setText(mname);
					
				}else{
				
				JOptionPane.showMessageDialog(ibpanel,"Member Not Found!");
				
			}
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(ibpanel,"Error: " + e.getMessage());
			}
		}else if(ke.getKeyCode() == KeyEvent.VK_DOWN ){
			
			String bid = ibbookidtf.getText();
			
			try{
				
				String QryString = "SELECT * FROM book WHERE ID = ?";
				pstmt = conn.prepareStatement(QryString);
				
				pstmt.setString(1,bid);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					String bname = rs.getString("Name");
					String bauthor = rs.getString("Author");
					String bcategory = rs.getString("Category");
					String bstatus = rs.getString("Status");
					
					ibbooknametf.setText(bname);
					ibauthortf.setText(bauthor);
					ibcategorytf.setText(bcategory);
					ibstatustf.setText(bstatus);
					
					
				}else{
				
				JOptionPane.showMessageDialog(ibpanel,"Book Not Found!");
				
			}
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(ibpanel,"Error: " + e.getMessage());
			}
		}
		
	}
	
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){
	}
	
public void ibconn(){
		
		try{
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/library";
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
			
			//JOptionPane.showMessageDialog(cpanel,"Connect successfully....");
			
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(ibpanel,"SQL Error: " + sqle.getMessage());
		}catch(Exception e){
			JOptionPane.showMessageDialog(ibpanel,"Error: " + e.getMessage());
		}
		
	}	
	
public void ibloaddata(){
	    int ib;
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.execute("SELECT * FROM issuebook");
			rs = stmt.getResultSet();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			ib =  rsmd.getColumnCount();
			
			ibtm.setRowCount(0);
			
			while(rs.next()){
				//atm.addRow(new Object[]{j,rs.getString("AuthorName")});
				//j++;
				Vector v = new Vector();
				
				for(int i = 1; i<=ib ;i++){
					v.add(rs.getString("MemID"));
					v.add(rs.getString("MemName"));
					v.add(rs.getString("BookID"));
					v.add(rs.getString("BookName"));
					v.add(rs.getString("BookCategory"));
					v.add(rs.getString("IssueDate"));
					v.add(rs.getString("ReturnDate"));
					
				}
				
				ibtm.addRow(v);
				
			}
			rs.last();
			
		 ibtable.setModel(ibtm);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(ibpanel,"Error: " + e.getMessage());
		}
	
	}
	
	/*
	public static void main(String args[]){
		new issuebook();
	}
	*/
}

//javac -cp mysql.jar;. issuebook.java 