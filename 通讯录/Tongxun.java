package peopleex;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;
import java.io.*;
import java.sql.*;
public class Tongxun extends JFrame implements ActionListener
{
	Panel p=new Panel();
	JLabel jlb=new JLabel();
	Label l1=new Label("������");
    Label l2=new Label("���䣺");
    Label l3=new Label("�Ա�");
    Label l4=new Label("ѧ����");
    Label l5=new Label("�绰��");
    TextField tf1=new TextField();
    TextField tf2=new TextField();
    TextField tf3=new TextField();
    TextField tf4=new TextField();
    TextField tf5=new TextField();
  //  TextField tf6=new TextField();
	JMenuBar jmb=new JMenuBar();
	JMenu jm=new JMenu("�ļ�");
//	JButton jme=new JButton("�༭");
	JMenuItem jme2=new JMenuItem("����");
	JMenuItem jmi[]= {
    new JMenuItem("�½�"),
    new JMenuItem("ɾ��")};
	//�б��
	JList jl=new JList();
	JScrollPane jspz=new JScrollPane(jl);
	//��ǩ
	JLabel jla=new JLabel();
	JScrollPane jspy=new JScrollPane(jla);
	//�ָ�
	JSplitPane jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspz,jspy);
	Connection con;
	public Tongxun()
	{
		this.setTitle("ͨѶ¼");
		this.setJMenuBar(jmb);
		jmb.add(jm);
		
	
		jm.add(jme2);
		
		jla.add(l1);
		jla.add(l2);
		jla.add(l4);
		jla.add(l3);
		jla.add(l5);
		jla.add(tf1);
		jla.add(tf2);
		jla.add(tf3);
		jla.add(tf4);
		jla.add(tf5);
	//	jla.add(tf6);
		jla.add(p);
		p.add(jlb);
		p.setBounds(200,40,100,120);
		jlb.setBounds(0,0,100,120);
		l1.setBounds(40,30,40,20);
		tf1.setBounds(80,30,100,20);
		l2.setBounds(40,60,40,20);
		tf2.setBounds(80,60,100,20);
		l3.setBounds(40,90,40,20);
		tf3.setBounds(80,90,100,20);
		l4.setBounds(40,120,40,20);
		tf4.setBounds(80,120,100,20);
		l5.setBounds(40,150,40,20);
		tf5.setBounds(80,150,100,20);
	//	tf6.setBounds(220,30,100,100);
		jme2.addActionListener(this);
		for(int i=0;i<jmi.length;i++)
		{
			jm.add(jmi[i]);
			jmi[i].addActionListener(this);
		}
		jla.setHorizontalAlignment(JLabel.CENTER);
		jsp.setDividerLocation(150);
		jsp.setDividerSize(4);
		this.add(jsp);
		this.getConnection();
		this.initList();
		this.setBackground(Color.cyan);
		this.setBounds(100,100,600,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	/*	 public void setPic(String pid) {// ���ø���ͼ����ʾ  
        String sql = "select pphoto from picData where pid='" + pid + "'";  
        String Pic = Tongxun.getPic(sql);// �����ݿ�õ����˵�ͼ��  
        // �����ݿ�õ����˵�ͼ��  
        if (Pic != null) {// �������ϵ���ϴ���ͼ��  
            picPath = Pic;  
            JPimg.setIcon(new ImageIcon(Pic));// ��ͼ����ʾ��JLabel  
            JPimg.setHorizontalAlignment(JLabel.CENTER);// ����ͼƬˮƽ������ʾ  
            JPimg.setVerticalAlignment(JLabel.CENTER);// ����ͼƬ��ֱ���������ʾ  
        } else {// ���ͼ��Ϊ�գ�����ʾ  
            JPimg.setIcon(null);  
        }  
    }  */
	public void getConnection()
	{
		try
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			con=DriverManager.getConnection
					(
							"jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=UTF-8",
							"root",
							""
					);
		}
			catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	public void actionPerformed(ActionEvent ea)
	{
	if(ea.getSource()==jmi[0])                                                    
	{
		new XinJian(this);
		this.repaint();
	}
	if(ea.getSource()==jme2)                                                       
	{
		try
		{
		/*	int size=(int)f.length();
	     	byte[] data=new byte[size];*/
			String pname=(String)jl.getSelectedValue();
	        String sql="update txl set pname=?,sage=?,sxb=?,sxl=?,stc=? where pname='"+pname+"'";
	        String sqll="update txl set pdada=?";
	        PreparedStatement ps=con.prepareStatement(sql);
	        PreparedStatement pss=con.prepareStatement(sqll);
		
			ps.setString(1,tf1.getText());
			int a=Integer.parseInt(tf2.getText());
			ps.setInt(2,a);
			ps.setString(3,tf3.getText());
			ps.setString(4,tf4.getText());
			ps.setString(5,tf5.getText());
		//	pss.setByte(1,jla);
	        ps.executeUpdate();
	        pss.executeUpdate();
	        Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery("select pname from txl");
		    Vector<String> vname=new Vector<String>();
		    while(rs.next())
			{
				vname.add(rs.getString(1));
			}
				jl.setListData(vname);
			ps.close();
			pss.close();
			rs.close();
			st.close();
	
		tf2.setText("");tf3.setText("");tf4.setText("");tf5.setText("");
		}
	    catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	if(ea.getSource()==jmi[1])                                                    //ɾ��  
	{
		 String pname=(String)jl.getSelectedValue();      
		 String sql="delete from ryglxt where pname='"+pname+"'";
		 try
		 {
		 	 Statement st=con.createStatement();
		     st.executeUpdate(sql);   
		     ResultSet rs=st.executeQuery("select pname from txl");
		    Vector<String> vname=new Vector<String>();
		    while(rs.next())
			{
				vname.add(rs.getString(1));
			}
			jl.setListData(vname);
		tf2.setText("");tf3.setText("");tf4.setText("");tf5.setText("");
		 
         
		st.close();
		rs.close();
		 }
          catch(Exception ed)
		  {
				ed.printStackTrace();
		  }                          
	}
	
	}
	public void initList()                                                           //ѡ��
	{
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select pname from txl");
			Vector<String> vname=new Vector<String>();
			while(rs.next())
			{
				vname.add(rs.getString(1));
			}
			rs.close();
			st.close();
			jl.setListData(vname);
			jl.addListSelectionListener(
					new ListSelectionListener()
					{
					public void valueChanged(ListSelectionEvent e)
					{
						try
						{
							String pname=(String)jl.getSelectedValue();
							String sql1="select pdata from txl where pname='"+pname+"'";
							Statement st=con.createStatement();
							ResultSet rs;
						    tf1.setText(pname);
						    String sql2="select sage,sxb,sxl,stc from txl where pname='"+pname+"'";
						   	rs=st.executeQuery(sql2);
					        while(rs.next())
		                	{
			                	String sage=rs.getString(1);//�� Java ��������� String ����ʽ��ȡ�� ResultSet ����ĵ�ǰ����ָ���е�ֵ��
			                 	String sxb=rs.getString(2);
				                String sxl=rs.getString(3);
				                String stc=rs.getString(4);
				                tf2.setText(sage);
			                tf3.setText(sxb);
			                tf4.setText(sxl);
			                tf5.setText(stc);
			    
			                }
			                rs=st.executeQuery(sql1);
							if(rs.next())
							{
								byte[] data=rs.getBytes(1);
								if(data==null)
								{
									
								}else
								{
								ImageIcon ii=new ImageIcon(data); 
								System.out.println(ii);
							    jlb.setIcon(ii);
								}
								
							}
			         		rs.close();
							st.close();
						}
						catch(Exception ed)
						{
							ed.printStackTrace();
						}
					}
					}
					);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

  public static void main(String args[])
  {
	  new Tongxun();
  }
}
class XinJian extends JFrame implements ActionListener
{
	byte[] data=null;
	Label l1=new Label("������");
    Label l2=new Label("���䣺");
    Label l3=new Label("�Ա�");
    Label l4=new Label("ѧ����");
    Label l5=new Label("�绰��");
    Button po=new Button("�����Ƭ");
    Button tj=new Button("����");
    TextField tf1=new TextField();
    TextField tf2=new TextField();
    TextField tf3=new TextField();
    TextField tf4=new TextField();
    TextField tf5=new TextField();
    Connection conn;
    JFileChooser jfc=new JFileChooser();//�����ļ�ѡ������
      Tongxun e; 
	public XinJian(Tongxun e)
	{
		this.e=e;
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		this.add(l5);
		this.add(po);
		this.add(tj);
		this.add(tf1);
		this.add(tf2);
		this.add(tf3);
		this.add(tf4);
		this.add(tf5);
		l1.setBounds(40,30,40,20);
		tf1.setBounds(80,30,100,20);
		l2.setBounds(40,60,40,20);
		tf2.setBounds(80,60,100,20);
		l3.setBounds(40,90,40,20);
		tf3.setBounds(80,90,100,20);
		l4.setBounds(40,120,40,20);
		tf4.setBounds(80,120,100,20);
		l5.setBounds(40,150,40,20);
		tf5.setBounds(80,150,100,20);
		po.setBounds(320,180,50,20);
		tj.setBounds(120,180,40,20);
		po.addActionListener(this);
		tj.addActionListener(this);
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(400,200,450,300);
	}
	public File getPic()
	{
		int i=jfc.showSaveDialog(this);
		if(i==JFileChooser.APPROVE_OPTION)
		{
			return jfc.getSelectedFile();
		}
		return null;
	}
	public void actionPerformed(ActionEvent eb)
	{
		
		try
		{
			
		
		if(eb.getSource()==po)
		{
			
            File f=this.getPic();
            int size=(int)f.length();
            data=new byte[size];
            FileInputStream fin=new FileInputStream(f);
            fin.read(data);
            fin.close();
            System.out.println(data);
            }
		if(eb.getSource()==tj)
		{
		
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection
					(
							"jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=UTF-8",
							"root",
							""
					);
			String sql="insert into txl values(?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,tf1.getText());
			int a=Integer.parseInt(tf2.getText());
			ps.setInt(2,a);
			ps.setString(3,tf3.getText());
			ps.setString(4, tf4.getText());
			ps.setString(5,tf5.getText());
			ps.setBytes(6,data);
			System.out.println(data);
			ps.executeUpdate(); 
			Statement st=conn.createStatement();
		    ResultSet rs=st.executeQuery("select pname from txl");
		    Vector<String> vname=new Vector<String>();
		    while(rs.next())
			{
				vname.add(rs.getString(1));
			}
				e.jl.setListData(vname);
				st.close();
				rs.close();
			ps.close();
			conn.close();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
