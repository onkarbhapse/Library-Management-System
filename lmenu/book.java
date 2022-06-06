package lmenu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class book implements ActionListener,MouseListener{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JFrame bframe = new JFrame("Books");
	
	JPanel bpanel = new JPanel();
	
	JLabel blabel = new JLabel("Books");
	
	JLabel bname = new JLabel("Name: ");
	JLabel bcategory = new JLabel("Category: ");
	JLabel bauthor = new JLabel("Author: ");
	JLabel bpublisher = new JLabel("Publisher: ");
	JLabel bpages = new JLabel("No of Pages: ");
	JLabel bedition = new JLabel("Edition: ");
	JLabel bid = new JLabel("ID: ");
	JLabel bstatus = new JLabel("Status: ");
	
	JTextField bnametf = new JTextField(100);
	
	JComboBox bcategorycb = new JComboBox();
	JComboBox bauthorcb = new JComboBox();
	
	JTextField bpublishertf = new JTextField(100);
	JTextField bpagestf = new JTextField(15);
	JTextField beditiontf = new JTextField(15);
	JTextField bidtf = new JTextField(15);
	String cbitem[] = {"Available","Not Available"};
	JComboBox bstatuscb = new JComboBox(cbitem);
	
	JButton badd = new JButton(" Add ");
	JButton bupdate = new JButton(" Update ");
	JButton bdelete = new JButton(" Delete ");
	JButton bexit = new JButton(" Exit ");
	
	JPanel bpanelt = new JPanel(new GridLayout());
	DefaultTableModel btm = new DefaultTableModel(new String[]{"ID","Name","Category","Author","Publisher","No of Pages","Edition","Status"},0);
	JTable btable = new JTable();
	
	public book(){
		
		blabel.setBounds(530,20,200,50);
		blabel.setFont(new Font("Serif", Font.BOLD, 40));
		
		bname.setBounds(30,120,150,25);
		bname.setFont(new Font("Serif", Font.BOLD, 20));
		bnametf.setBounds(170,120,250,25);
		
		bcategory.setBounds(30,170,150,25);
		bcategory.setFont(new Font("Serif", Font.BOLD, 20));
		bcategorycb.setBounds(170,170,250,25);
		
		bauthor.setBounds(30,220,150,25);
		bauthor.setFont(new Font("Serif", Font.BOLD, 20));
		bauthorcb.setBounds(170,220,250,25);
		
		bpublisher.setBounds(30,270,150,25);
		bpublisher.setFont(new Font("Serif", Font.BOLD, 20));
		bpublishertf.setBounds(170,270,250,25);
		
		bpages.setBounds(30,320,150,25);
		bpages.setFont(new Font("Serif", Font.BOLD, 20));
		bpagestf.setBounds(170,320,250,25);
		
		bedition.setBounds(30,370,150,25);
		bedition.setFont(new Font("Serif", Font.BOLD, 20));
		beditiontf.setBounds(170,370,250,25);
		
		bid.setBounds(30,420,150,25);
		bid.setFont(new Font("Serif", Font.BOLD, 20));
		bidtf.setBounds(170,420,250,25);
		
		bstatus.setBounds(30,470,150,25);
		bstatus.setFont(new Font("Serif", Font.BOLD, 20));
		bstatuscb.setBounds(170,470,250,25);
		
		bdelete.setBounds(60,520,100,50);
		bupdate.setBounds(180,520,100,50);
		badd.setBounds(300,520,100,50);
		bexit.setBounds(180,600,100,50);
		
		
		bpanelt.setBounds(460,120,600,520);
		bpanelt.setBackground(Color.decode("#b3b3b3"));
		JScrollPane bpl = new JScrollPane(btable);
		btable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//bpl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        //bpl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		bpanelt.add(bpl);
		
		
		bpanel.setBounds(20,20,1140,715);
		bpanel.setBackground(Color.decode("#d9d9d9"));
		bpanel.setLayout(null);
		
		bpanel.add(blabel);
		bpanel.add(bname);
		bpanel.add(bcategory);
		bpanel.add(bauthor);
		bpanel.add(bpublisher);
		bpanel.add(bpages);
		bpanel.add(bedition);
		bpanel.add(bid);
		bpanel.add(bstatus);
		bpanel.add(bnametf);
		bpanel.add(bcategorycb);
		bpanel.add(bauthorcb);
		bpanel.add(bpublishertf);
		bpanel.add(bpagestf);
		bpanel.add(beditiontf);
		bpanel.add(bidtf);
		bpanel.add(bstatuscb);
		bpanel.add(badd);
		bpanel.add(bupdate);
		bpanel.add(bdelete);
		bpanel.add(bexit);
		bpanel.add(bpanelt);
		
		badd.addActionListener(this);
		bupdate.addActionListener(this);
		bdelete.addActionListener(this);
		bexit.addActionListener(this);
		
		btable.addMouseListener(this);
		
		bframe.add(bpanel);
		bframe.setSize(1200,800);
		bframe.setLayout(null);
		bframe.setLocationRelativeTo(null);
		bframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bframe.setVisible(true);
		
		bconn();
		bcategory();
		bauthor();
		bloaddata();
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		String bstr = ae.getActionCommand();
		
		String bookname = bnametf.getText();
		
		String bcategorya = bcategorycb.getSelectedItem().toString();
		String cauthora = bauthorcb.getSelectedItem().toString();
		String bpublishera = bpublishertf.getText();
		String bpagesa = bpagestf.getText();
		String beditiona = beditiontf.getText();
		String vida = bidtf.getText();
		String bstatusa = bstatuscb.getSelectedItem().toString();
		if(bstr.equals(" Add ")){
			
			try{
				int bpagesaa = Integer.parseInt(bpagesa);
				int beditionaa = Integer.parseInt(beditiona);
				
				String QryString = "INSERT INTO book (ID,Name,Category,Author,Publisher,Pages,Edition,Status) VALUES(?,?,?,?,?,?,?,?)";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,vida);
				pstmt.setString(2,bookname);
				pstmt.setString(3,bcategorya);
				pstmt.setString(4,cauthora);
				pstmt.setString(5,bpublishera);
				pstmt.setInt(6,bpagesaa);
				pstmt.setInt(7,beditionaa);
				pstmt.setString(8,bstatusa);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(bpanel,"Book added successfully!" );
				
				bnametf.setText("");
				bcategorycb.setSelectedIndex(0);
				bauthorcb.setSelectedIndex(0);
				bpublishertf.setText("");
				bpagestf.setText("");
				beditiontf.setText("");
				bidtf.setText("");
				bstatuscb.setSelectedIndex(0);
				bnametf.requestFocus();
				bloaddata();
				
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(bpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(bpanel,"Error: " + e.getMessage());
			}
		
		}else if(bstr.equals(" Update ")){
			
			int bpagesau = Integer.parseInt(bpagesa);
			int beditionau = Integer.parseInt(beditiona);
			
			try{
				
				btm = (DefaultTableModel)btable.getModel();
		
		        int btableindex = btable.getSelectedRow();
		
		        String bdf = btm.getValueAt(btableindex,0).toString();
				//System.out.println(bdf);
				
				String QryString = "UPDATE book SET ID = ?,Name = ?,Category = ?,Author = ?,Publisher = ?,Pages = ?,Edition =?, Status = ? WHERE ID = ?";
			    pstmt = conn.prepareStatement(QryString);
			
			    pstmt.setString(1,vida);
				pstmt.setString(2,bookname);
				pstmt.setString(3,bcategorya);
				pstmt.setString(4,cauthora);
				pstmt.setString(5,bpublishera);
				pstmt.setInt(6,bpagesau);
				pstmt.setInt(7,beditionau);
				pstmt.setString(8,bstatusa);
				pstmt.setString(9,bdf);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(bpanel,"Updated successfully!" );
				
				bnametf.setText("");
				bcategorycb.setSelectedIndex(0);
				bauthorcb.setSelectedIndex(0);
				bpublishertf.setText("");
				bpagestf.setText("");
				beditiontf.setText("");
				bidtf.setText("");
				bstatuscb.setSelectedIndex(0);
				bnametf.requestFocus();
				bloaddata();
				badd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(bpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(bpanel,"Error: " + e.getMessage());
			}
			
		}else if(bstr.equals(" Delete ")){
			
			try{
				
				btm = (DefaultTableModel)btable.getModel();
		
		        int btableindex = btable.getSelectedRow();
		
		        String bdf = btm.getValueAt(btableindex,0).toString();
				
				String QryString = "DELETE FROM book WHERE ID = ?";
			    pstmt = conn.prepareStatement(QryString);
			
				pstmt.setString(1,bdf);
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(bpanel,"Deleted successfully!" );
				
				bnametf.setText("");
				bcategorycb.setSelectedIndex(0);
				bauthorcb.setSelectedIndex(0);
				bpublishertf.setText("");
				bpagestf.setText("");
				beditiontf.setText("");
				bidtf.setText("");
				bstatuscb.setSelectedIndex(0);
				bnametf.requestFocus();
				bloaddata();
				badd.setEnabled(true);
				
			}catch(SQLException sqle){
				JOptionPane.showMessageDialog(bpanel,"SQL Error: " + sqle.getMessage());
			}catch(Exception e){
				JOptionPane.showMessageDialog(bpanel,"Error: " + e.getMessage());
			}
			
		}else if(bstr.equals(" Exit ")){
				
		    int reply = JOptionPane.showConfirmDialog(bpanel, "Are you sure to Exit?");
				
			if(reply == JOptionPane.YES_OPTION){
				 bframe.setVisible(false);
				//System.exit(0);
			}else if(reply == JOptionPane.NO_OPTION){
				//JOptionPane.exit(0);
			}
				
		}
	}
	
	public void mouseClicked(MouseEvent me){
		
		btm = (DefaultTableModel)btable.getModel();
		
		int btableindex = btable.getSelectedRow();
		
		bnametf.setText(btm.getValueAt(btableindex,1).toString());
		bcategorycb.setSelectedItem(btm.getValueAt(btableindex,2).toString());
		bauthorcb.setSelectedItem(btm.getValueAt(btableindex,3).toString());
		bpublishertf.setText(btm.getValueAt(btableindex,4).toString());
		bpagestf.setText(btm.getValueAt(btableindex,5).toString());
		beditiontf.setText(btm.getValueAt(btableindex,6).toString());
		bidtf.setText(btm.getValueAt(btableindex,0).toString());
		bstatuscb.setSelectedItem(btm.getValueAt(btableindex,7).toString());
		
		badd.setEnabled(false);
		
	}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
	
	
	public void bconn(){
		
		try{
			String username = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/library";
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
			
			//JOptionPane.showMessageDialog(cpanel,"Connect successfully....");
			
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(bpanel,"SQL Error: " + sqle.getMessage());
		}catch(Exception e){
			JOptionPane.showMessageDialog(bpanel,"Error: " + e.getMessage());
		}
		
	}
	
	public void bcategory(){
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    stmt.execute("SELECT * FROM category");
		    rs = stmt.getResultSet();
		
		    bcategorycb.removeAllItems();
		
		      while(rs.next()){
				  bcategorycb.addItem(rs.getString("CategoryName"));
				}
				rs.last();
				
		   // bcategorycb.setSelectedIndex(-1);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(bpanel,"Error: " + e.getMessage());
		}
	}
	
	public void bauthor(){
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    stmt.execute("SELECT * FROM author");
		    rs = stmt.getResultSet();
		
		    bauthorcb.removeAllItems();
		
		      while(rs.next()){
				  bauthorcb.addItem(rs.getString("AuthorName"));
				}
				rs.last();
				
		    //bauthorcb.setSelectedIndex(-1);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(bpanel,"Error: " + e.getMessage());
		}
	}
	
	public void bloaddata(){
		
		int a;
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.execute("SELECT * FROM book");
			rs = stmt.getResultSet();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			a =  rsmd.getColumnCount();
			
			btm.setRowCount(0);
			
			while(rs.next()){
				//atm.addRow(new Object[]{j,rs.getString("AuthorName")});
				//j++;
				Vector v = new Vector();
				
				for(int i = 1; i<=a ;i++){
					v.add(rs.getString("ID"));
					v.add(rs.getString("Name"));
					v.add(rs.getString("Category"));
					v.add(rs.getString("Author"));
					v.add(rs.getString("Publisher"));
					v.add(rs.getInt("Pages"));
					v.add(rs.getInt("Edition"));
					v.add(rs.getString("Status"));
				}
				
				btm.addRow(v);
				
			}
			rs.last();
			
		btable.setModel(btm);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(bpanel,"Error: " + e.getMessage());
		}
		
		
	}
	/*
	public static void main(String args[]){
		new book();
	}
	*/
}
//javac -cp mysql.jar;. book.java 