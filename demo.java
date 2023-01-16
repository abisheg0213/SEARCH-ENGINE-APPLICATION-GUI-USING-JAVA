import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
class customernotfoundException extends Exception
{
    public String toString()
    {
        return "The Corresponding Customer is not Found";
    }
}
class customer  //customer class with specified attributes has been created
{
    String customername;
    int customerno;
    String city;
    String state;
    int pincode;
    customer(){}
    customer(String customername,int customerno,String city,String state,int pincode)
    {
        this.customername=customername;
        this.customerno=customerno;
        this.city=city;
        this.state=state;
        this.pincode=pincode;
    }
}
class customermanagement //class customer management is created with arraylist to hold customer details
{
    ArrayList<customer> list;
    customermanagement()
    {
        list=new ArrayList<>();
    }
    void addcustomer(customer c) //a customer object is taken as a parameter and added to the arraylist
    {
        list.add(c);
    }
    customer searchcustomer(int customer_number) throws customernotfoundException
    {
        customer c=new customer();
        int flag=0;
        for(customer i:list)
        {
            if(i.customerno==customer_number)
            {
                flag=1;
                c=i;
            }
        }
        if(flag==0)
        {
            throw new customernotfoundException();
        }
        return c;
    }
}
class frame extends JFrame implements ActionListener
{
    customermanagement cusobj=new customermanagement();
    JLabel l1,l2,l3,l4,l5;  //components are created for getting input
    JTextField t1,t2,t3,t4,t5;
    JButton b1,b2,b3;
    JTextArea ta;
    frame()
    {
        super("CUSTOMER MANAGEMENT APP");
        l1=new JLabel("CustomerName:");
        l2=new JLabel("CustomerNumber:");
        l3=new JLabel("City:");
        l4=new JLabel("State:");
        l5=new JLabel("Pincode:");
        t1=new JTextField(20);
        t2=new JTextField(20);
        t3=new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        b1=new JButton("Add customer");  //3 buttons are created for their respective actions
        b2=new JButton("Search Customer");
        b3=new JButton("Display Customer");
        ta=new JTextArea(20,20);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        l1.setBounds(10,10,100,20);
        add(l1);
        t1.setBounds(170,10,150,25);
        add(t1);
        l2.setBounds(10,50,150,20);
        add(l2);
        t2.setBounds(170,50,150,25);
        add(t2);
       l3.setBounds(10,90,100,20);
       add(l3);
       t3.setBounds(170,90,150,25);
       add(t3);
       l4.setBounds(10,130,100,20);
       add(l4);
       t4.setBounds(170,130,150,25);
       add(t4);
       l5.setBounds(10,170,150,20);
       add(l5);
       t5.setBounds(170,170,150,25);
       add(t5);
       JPanel p=new JPanel();
       p.add(b1);
       p.add(b2);
       p.add(b3);
       p.setBounds(30,210,400,50);
       add(p);
        ta.setBounds(50,260,250,150);
        add(ta);
       addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
             System.exit(0);
           }
       });
        setLayout(null);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1) //details of the customer are taken and stored ina arraylist
        {
            String a=t1.getText();
            int b=Integer.parseInt(t2.getText());
            String c=t3.getText();
            String d=t4.getText();
            int f=Integer.parseInt(t5.getText());
            cusobj.addcustomer(new customer(a,b,c,d,f));
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
        }
        if(e.getSource()==b2)
        {
            int cusno=Integer.parseInt(t2.getText());  //customer no is got from the user and search method is called
          try
          {
              customer o=cusobj.searchcustomer(cusno);
              FileOutputStream fout =new FileOutputStream("C:\\Users\\abish\\Desktop\\JAVA END SEM EXAMINATION\\customer.txt");
              fout.write((o.customername).getBytes());
              fout.close();
              ta.setText("");
          }catch(customernotfoundException ce){String str=ce.toString();
          ta.setText(str);}
          catch (IOException ie){}
        }
        if(e.getSource()==b3)
        {
            try {
                //the name from the file is taken and displayed in textArea
                FileInputStream fin = new FileInputStream("C:\\Users\\abish\\Desktop\\JAVA END SEM EXAMINATION\\customer.txt");
                byte b[] = new byte[fin.available()];
                fin.read(b);
                String str=new String(b);
                ta.setText("FOUND CUSTOMER NAME:"+str);
                fin.close();
            }catch (IOException de)
            {}
        }
    }
}
public class demo {
    public static void main(String args[])
    {
        frame f=new frame();
        f.setSize(500,500);
        f.setVisible(true);
    }
}
