/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datebase;
/**
 *
 * @author a
 */
//import static com.sun.xml.internal.ws.api.pipe.Fiber.current;
import entity.Customer;
import entity.User;
import entity.Worker;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import static java.util.concurrent.ThreadLocalRandom.current;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import relation.Book;
import relation.Change;
import relation.Check;
import relation.Checkin;
import tools.DateToString;

public class DBUtil {
    private static final String URL="jdbc:mysql://localhost:3306/myhotel?useSSL=FALSE&serverTimezone=CTT&useUnicode=true&characterEncoding=utf8";
    private static final String USER="root";
    private static final String PASSWORD="123456";
    private static Connection conn ;
    private static Statement stmt;
    public static ResultSet rs;
    public static int level;
    public void Connection(){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
                        System.out.println("MySQL驱动加载失败！请检查");
		}
        try {
		conn=DriverManager.getConnection(URL, USER, PASSWORD);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
                System.out.println("数据库连接成功");
	} catch (SQLException e) {
			e.printStackTrace();
                        System.out.println("数据库连接异常！");
	}
    }
    //验证密码
    public boolean isLogin(String struserid,String strpassword){
        try{
            rs=stmt.executeQuery("select * from user where userid='"+struserid+"'and password ='"+strpassword+"'");
            if(rs.next())
            {
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
}
    //验证用户类型
    public int LoginType(String struserid){
       
        try{
            rs=stmt.executeQuery("select level from user where userid='"+struserid+"'");
            if(rs.next()){
            System.out.println(rs);
            level= rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return level;
    }
    //获取表格数据*********************************************************************
    //获取房间信息的表格数据
    public DefaultTableModel getTableData(java.util.Date start_date,java.util.Date end_date){
        //Date与String的数据转换
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
       // df1.setCalendar(null);
       // df1.setNumberFormat(null);
        String start=df1.format(start_date);
        String end=df1.format(end_date);
       /* java.util.Date start_date1=start_date;
        java.util.Date end_date1=end_date;
       DateToString datetostring=new DateToString();
        String start= datetostring.getDateString(start_date1);
        String end= datetostring.getDateString(end_date1);*/
        System.out.println(start);
        String[] col={"房间号","房间类型","房间押金","房间价格","房间状态 ","状态时间","状态开始时间","房间电话"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        
        try{
            rs=stmt.executeQuery("select "
                    + "roominfo.rno,roomtype.rtype,roomtype.deposit,"
                    + "roomtype.price,roominfo.state,roominfo.statetime,roominfo.statedate,rtel "
                    + "from roominfo,roomtype where roominfo.rt_no=roomtype.rt_no and "
                    + "state = '空闲' and statedate >= '"
                    +start+"' and statedate <= '"+end+"'");
            while(rs.next()){
                String rno=rs.getString(1);
                String rtype=rs.getString(2);
                String deposit=rs.getString(3);
                String price=rs.getString(4);
                String state=rs.getString(5);
                String statetime=String.valueOf(rs.getInt(6));
                String rtel=rs.getString(8);
                //获取时间数据
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String statedate=df.format(rs.getTimestamp(7));
                String[] str_row={rno,rtype,deposit,price,state,statetime,statedate,rtel};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取全部客户信息的表格数据
    public DefaultTableModel getCustomerTableData(){

        String[] col={"客户编号","姓名","性别","身份证号","联系方式","顾客类型"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        
        try{
            rs=stmt.executeQuery("select "
                    + "cno,cname,sex,cidcard,cphone,ctype "
                    + "from customerinfo,customertype where customerinfo.ct_no=customertype.ct_no" );
            while(rs.next()){
                String cno=rs.getString(1);
                String cname=rs.getString(2);
                String sex=rs.getString(3);
                String cidcard=rs.getString(4);
                String cphone=rs.getString(5);
                String ctype=rs.getString(6);
                String[] str_row={cno,cname,sex,cidcard,cphone,ctype};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取收入信息的表格数据
    public DefaultTableModel getCheck1TableData(java.util.Date start_date,java.util.Date end_date){
        //Date与String的数据转换
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
        String start=df1.format(start_date);
        String end=df1.format(end_date);
        System.out.println(start);
        String[] col={"日期","收入总额(元)"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        
        try{
            rs=stmt.executeQuery("select ci_date,sum(totalmoney) from check1 where ci_date >= '"
                    +start+"' and ci_date <= '"+end+"' group by ci_date");
            while(rs.next()){
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date=df.format(rs.getTimestamp(1));
                String sum=String.valueOf(rs.getFloat(2));
                String[] str_row={date,sum};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取员工信息的表格数据
    public DefaultTableModel getWorkerTableData(){

        String[] col={"员工编号","姓名","性别","年龄","联系方式","职位","登录用户名"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        
        try{
            rs=stmt.executeQuery("select "
                    + "wno,wname,sex,age,wphone,wposition,wuserid "
                    + "from workerinfo " );
            while(rs.next()){
                String wno=rs.getString(1);
                String wname=rs.getString(2);
                String sex=rs.getString(3);
                String age=String.valueOf(rs.getInt(4));
                String wphone=rs.getString(5);
                String wposition=rs.getString(6);
                String wuserid=rs.getString(7);
                String[] str_row={wno,wname,sex,age,wphone,wposition,wuserid};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取预定记录的表格数据
    public DefaultTableModel getBookHistoryTableData(){

        String[] col={"预定单号","姓名","联系方式","身份证号","房间类型","房间号","预定日期","居住时长（天）"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            rs=stmt.executeQuery("select * from book_copy " );
            while(rs.next()){
                String bno=String.valueOf(rs.getInt(2));
                String cname=rs.getString(3);
                String cphone=rs.getString(4);
                String cidcard=rs.getString(5);
                String rtype=rs.getString(6);
                String rno=rs.getString(7);
                String bdate=df.format(rs.getTimestamp(8));
                String days=String.valueOf(rs.getInt(9));
                String[] str_row={bno,cname,cphone,cidcard,rtype,rno,bdate,days};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取入住记录的表格数据
    public DefaultTableModel getCheckinHistoryTableData(){

        String[] col={"入住编号","房间号","房间类型","客户编号","客户姓名","性别","联系方式","身份证号","入住日期","居住时长（天）","押金"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            rs=stmt.executeQuery("select * from checkin_copy" );
            while(rs.next()){
                String ci_no=String.valueOf(rs.getInt(2));
                String rno=rs.getString(3);
                String rtype=rs.getString(4);
                String cno=String.valueOf(rs.getInt(5));
                String cname=rs.getString(6);
                String sex=rs.getString(7);
                String cphone=rs.getString(8);
                String cidcard=rs.getString(9);
                String ci_date=df.format(rs.getTimestamp(10));
                String ci_days=String.valueOf(rs.getInt(11));
                String deposit=String.valueOf(rs.getFloat(12));
                String[] str_row={ci_no,rno,rtype,cno,cname,sex,cphone,cidcard,ci_date,ci_days,deposit};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取换房记录的表格数据
    public DefaultTableModel getChangeHistoryTableData(){

        String[] col={"换房单号","原房间号","新房间号","姓名","联系方式"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        
        try{
            rs=stmt.executeQuery("select * from changeroom " );
            while(rs.next()){

                String cr_no=String.valueOf(rs.getInt(2));
                String roldno=rs.getString(3);
                String rnewno=rs.getString(4);
                String cname=rs.getString(5);
                String cphone=rs.getString(6);
               
                String[] str_row={cr_no,roldno,rnewno,cname,cphone};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取结账记录的表格数据
    public DefaultTableModel getCheckHistoryTableData(){

        String[] col={"结账单号","入住单号","入住时间","入住天数","账单总额（元）","客户姓名","联系方式","客户类型"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //    String statedate=df.format(rs.getTimestamp(7));
        
        try{
            rs=stmt.executeQuery("select * from check1" );
            while(rs.next()){
                String chk_no=String.valueOf(rs.getInt(2));
                String ci_no=String.valueOf(rs.getInt(3));
                String ci_date=df.format(rs.getTimestamp(4));
                String ci_days=String.valueOf(rs.getInt(5));
                String total=String.valueOf(rs.getFloat(6));
                String cname=rs.getString(7);
                String cphone=rs.getString(8);
                String ctype=rs.getString(9);
                String[] str_row={chk_no,ci_no,ci_date,ci_days,total,cname,cphone,ctype};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //获取员工的个人信息
    public String[] getWorkerinfo(String userid){
        String[] str=new String[6];
        try{
            rs=stmt.executeQuery("select * from workerinfo where wuserid='"+userid+"'");
            while(rs.next()){
                str[0]=rs.getString(8);
                str[1]=rs.getString(3);
                str[2]=rs.getString(4);
                str[3]=String.valueOf(rs.getInt(5));
                str[4]=rs.getString(6);
                str[5]=rs.getString(7);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
        return str;
    }
    //获取全部客户的收费信息
    public DefaultTableModel getCheckTableData(java.util.Date start_date,java.util.Date end_date){
        //Date与String的数据转换
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
       // df1.setCalendar(null);
       // df1.setNumberFormat(null);
        String start=df1.format(start_date);
        String end=df1.format(end_date);
       /* java.util.Date start_date1=start_date;
        java.util.Date end_date1=end_date;
       DateToString datetostring=new DateToString();
        String start= datetostring.getDateString(start_date1);
        String end= datetostring.getDateString(end_date1);*/
        System.out.println(start);
        String[] col={"房间号","房间类型","房间押金","房间价格","房间状态 ","状态时间","状态开始时间"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        
        try{
            rs=stmt.executeQuery("select "
                    + "roominfo.rno,roomtype.rtype,roomtype.deposit,"
                    + "roomtype.price,roominfo.state,roominfo.statetime,roominfo.statedate "
                    + "from roominfo,roomtype where roominfo.rt_no=roomtype.rt_no and "
                    + "state = '空闲' and statedate >= '"
                    +start+"' and statedate <= '"+end+"'");
            while(rs.next()){
                String rno=rs.getString(1);
                String rtype=rs.getString(2);
                String deposit=rs.getString(3);
                String price=rs.getString(4);
                String state=rs.getString(5);
                String statetime=String.valueOf(rs.getInt(6));
                
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String statedate=df.format(rs.getTimestamp(7));
                String[] str_row={rno,rtype,deposit,price,state,statetime,statedate};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return tm;
        
    }
    //********************************************************************************
    //预定房间功能实现******************************************************************
    //新顾客
    //更新顾客表
    public void setCustomerTable(Customer customer){
        int n=0;
       /* try{
            rs=stmt.executeQuery("select * from customerinfo");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            rs=stmt.executeQuery("select max(c_pk) from customerinfo");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
         try{
            stmt.executeUpdate("insert into customerinfo (c_pk,cno,cname,sex,cidcard,cphone,ct_no) values "
                    + "("+n+","+n+",'"+customer.getName()+"','"+customer.getSex()+"','"+customer.getIdcard()+"','"+customer.getPhone()+"',"+"1)"); 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //更新预定表
    public void setBookTable(Book book){
        int n=0;
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
        Timestamp bdate=null;
        System.out.println(book.getBdate());
        try{
            bdate=Timestamp.valueOf(book.getBdate());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*try{
            rs=stmt.executeQuery("select * from book");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            rs=stmt.executeQuery("select max(b_pk) from book");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
         try{
            stmt.executeUpdate("insert into book (b_pk,bno,cname,cphone,cidcard,rtype,rno,bdate,keeptime) values "
                    + "("+n+","+n+",'"+book.getCname()+"','"+book.getCphone()+"','"+book.getCidcard()+"','"+book.getRtype()+"','"+book.getRno()+
                    "','"+bdate+"','"+book.getKeeptime()+"')"); 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //复制预定表
     public void setBookTableCopy(Book book){
        int n=0;
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
        Timestamp bdate=null;
        System.out.println(book.getBdate());
        try{
            bdate=Timestamp.valueOf(book.getBdate());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*try{
            rs=stmt.executeQuery("select * from book");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            rs=stmt.executeQuery("select max(b_pk) from book_copy");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
         try{
            stmt.executeUpdate("insert into book_copy (b_pk,bno,cname,cphone,cidcard,rtype,rno,bdate,keeptime) values "
                    + "("+n+","+n+",'"+book.getCname()+"','"+book.getCphone()+"','"+book.getCidcard()+"','"+book.getRtype()+"','"+book.getRno()+
                    "','"+bdate+"','"+book.getKeeptime()+"')"); 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //修改房间状态，状态时间,状态开始时间
    public void changeRoomState(Book book){
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
        Timestamp bdate=null;
        System.out.println(book.getBdate());
        try{
            bdate=Timestamp.valueOf(book.getBdate());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    
        try{
            stmt.executeUpdate("update roominfo set state='已预定', statetime="+book.getKeeptime()+",statedate='"+bdate+"' where "
                    + "rno='"+book.getRno()+"'");
        }
        catch(Exception e){
            e.printStackTrace();
    }

    }
    //老顾客信息
    public String[] getOldCustomerInfo(String phone){
        String cphone=phone;
        String[] str=new String[4];
        try{
            rs=stmt.executeQuery("select cname,sex,cidcard,ctype from customerinfo,customertype where cphone='"+cphone+"' "
                    + "and customerinfo.ct_no=customertype.ct_no");
            while(rs.next()){
                str[0]=rs.getString(1);
                str[1]=rs.getString(2);
                str[2]=rs.getString(3);
                str[3]=rs.getString(4);
            }
          
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }
    //入住房间功能实现*********************************************************************
    //获取客户编号
    public int getCno(String phone){
        int cno=0;
        try{
            rs=stmt.executeQuery("select cno from customerinfo where cphone='"+phone+"'");
            while(rs.next()){
                cno=rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cno;
    }
    //更新入住表
    public void setCheckinTable(Checkin checkin){
        int n=0;
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp bdate=null;
        System.out.println(checkin.getCi_date());
        try{
            bdate=Timestamp.valueOf(checkin.getCi_date());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            rs=stmt.executeQuery("select max(ci_pk) from checkin");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
         try{
            stmt.executeUpdate("insert into checkin (ci_pk,ci_no,rno,rtype,cno,cname,sex,cphone,cidcard,ci_date,ci_days,deposit) values "
                    + "("+n+","+n+",'"+checkin.getRno()+"','"+checkin.getRtype()+"','"+checkin.getCno()+"','"+checkin.getCname()+"','"+checkin.getSex()+
                    "','"+checkin.getCphone()+"','"+checkin.getCidcard()+"','"+bdate+"',"+checkin.getCi_days()+","+Float.parseFloat(checkin.getDeposit())+")"); 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    //复制入住表
     public void setCheckinCopyTable(Checkin checkin){
        int n=0;
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp bdate=null;
        System.out.println(checkin.getCi_date());
        try{
            bdate=Timestamp.valueOf(checkin.getCi_date());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            rs=stmt.executeQuery("select max(ci_pk) from checkin_copy");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
         try{
            stmt.executeUpdate("insert into checkin_copy (ci_pk,ci_no,rno,rtype,cno,cname,sex,cphone,cidcard,ci_date,ci_days,deposit) values "
                    + "("+n+","+n+",'"+checkin.getRno()+"','"+checkin.getRtype()+"','"+checkin.getCno()+"','"+checkin.getCname()+"','"+checkin.getSex()+
                    "','"+checkin.getCphone()+"','"+checkin.getCidcard()+"','"+bdate+"',"+checkin.getCi_days()+","+Float.parseFloat(checkin.getDeposit())+")"); 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    //更新预定表
    public void deleteBookinfo(String rno,String cname){
        try{
            stmt.executeUpdate("delete from book where cname='"+cname+"' and rno='"+rno+"'");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void changeRoomState(Checkin checkin) {
       DateFormat df1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp bdate=null;
        System.out.println(checkin.getCi_date());
        try{
            bdate=Timestamp.valueOf(checkin.getCi_date());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    
        try{
            stmt.executeUpdate("update roominfo set state='已入住', statetime="+checkin.getCi_days()+",statedate='"+bdate+"' where "
                    + "rno='"+checkin.getRno()+"'");
        }
        catch(Exception e){
            e.printStackTrace();
    }
    }
    //获取顾客预定信息
    public Book getBookinfo(String phone){
        Book book=new Book();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            rs=stmt.executeQuery("select * from book where cphone='"+phone+"'");
            while(rs.next()){
                book.setBno(rs.getString(2));
                book.setCname(rs.getString(3));
                book.setCphone(phone);
                book.setCidcard(rs.getString(5));
                book.setRtype(rs.getString(6));
                book.setRno(rs.getString(7));
                book.setBdate(df.format(rs.getDate(8)));
                book.setKeeptime(rs.getInt(9));
            }
          
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return book;
    }
    
    //获取房间押金
    public float getDeposit(String rtype){
        float deposit=0;
        try{
            rs=stmt.executeQuery("select deposit from roomtype where rtype='"+rtype+"'");
            while(rs.next()){
                deposit=rs.getFloat(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return deposit;
    }
    //换房功能实现*********************************************************************    
    //更新换房表
    public void setChangeRoomTable(Change change){
        int n=0;
        /*try{
            rs=stmt.executeQuery("select * from changeroom");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            rs=stmt.executeQuery("select max(cr_pk) from changeroom");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("insert into changeroom (cr_pk,cr_no,roldno,rnewno,cname,cphone) values ("
            +n+","+n+",'"+change.getRoldno()+"','"+change.getRnewno()+"','"+change.getCname()+"','"+change.getCphone()+"')");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    //更改入住信息
    public void changeCheckinTable(String cphone,String rnewno){
        try{
            stmt.executeUpdate("update checkin set rno='"+rnewno+"' where cphone='"+cphone+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
 //更改房间信息
    public void changeRoominfoTable(String rnewno,String roldno){
        int day=0;
        try{
            rs=stmt.executeQuery("select statetime from roominfo where rno='"+roldno+"'");
            while(rs.next()){
                day=rs.getInt(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("update roominfo set state='已入住',statetime='"+day+"' where rno='"+rnewno+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("update roominfo set state='空闲',statetime=30  where rno='"+roldno+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //续住房间功能实现*****************************************************************
    //获取顾客入住信息
    public Checkin getCheckinTable(String cphone){
        Checkin checkin=new Checkin();
        checkin.setCphone(cphone);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            rs=stmt.executeQuery("select * from checkin where cphone='"+cphone+"'");
            while(rs.next()){
                checkin.setCi_no(rs.getString(2));
                checkin.setRno(rs.getString(3));
                checkin.setRtype(rs.getString(4));
                checkin.setCno(Integer.valueOf(rs.getString(5)));
                checkin.setCname(rs.getString(6));
                checkin.setSex(rs.getString(7));
                checkin.setCidcard(rs.getString(9));
                checkin.setCi_date(df.format(rs.getDate(10)));
                checkin.setCi_days(rs.getInt(11));
                checkin.setDeposit(String.valueOf(rs.getFloat(12)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        return checkin;
    }
    //更新居住时长
    public void extendTime(int day,Checkin checkin){
        int days=day+checkin.getCi_days();
        try{
            stmt.executeUpdate("update roominfo set statetime='"+days+"' where rno='"+checkin.getRno()+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("update checkin set ci_days='"+days+"' where ci_no='"+checkin.getCi_no()+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //退房结账功能实现************************************************************
    //表格选择结账
    //更新收费表
    public void updateCheckTable(Check chk) throws SQLException{
        int n=0;
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp ci_date=null;
        System.out.println(chk.getCi_date());
        try{
            ci_date=Timestamp.valueOf(chk.getCi_date());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*try{
            rs=stmt.executeQuery("select * from check1 ");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            rs=stmt.executeQuery("select max(chk_pk) from check1");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            /*stmt.executeUpdate("insert into check1 (chk_pk,chk_no,ci_no,ci_date,ci_days,totalmoney,cname,cphone,ctype) values ("+n+","+n+","+chk.getCi_no()+",'"
            +ci_date+"',"+chk.getCi_days()+",'"+chk.getTotalmomney()+"','"+chk.getCname()+"','"+chk.getCphone()+"','"+chk.getCtype()+"')");
         */
        String sql="insert into check1 (chk_pk,chk_no,ci_no,ci_date,ci_days,totalmoney,cname,cphone,ctype)"
                + " values("+
                "?,?,?,?,?,?,?,?,?)";
        PreparedStatement ptmt=conn.prepareStatement(sql);
                ptmt.setInt(1, n);
		ptmt.setInt(2, n);
		ptmt.setInt(3, chk.getCi_no());
		ptmt.setTimestamp(4, ci_date);
		ptmt.setInt(5, chk.getCi_days());
		ptmt.setFloat(6, chk.getTotalmomney());
		ptmt.setString(7, chk.getCname());
                ptmt.setString(8,chk.getCphone());
                ptmt.setString(9, chk.getCtype());
		ptmt.execute();

            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //查询客户类型
    public String getCustomerType(String cphone){
        String ctype=null;
        try{
            rs=stmt.executeQuery("select ctype from customerinfo,customertype where cphone='"+cphone+"' and customerinfo.ct_no=customertype.ct_no");
            while(rs.next()){
                ctype=rs.getString(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
    }
        return ctype;
    
}
    //通过时间比较获取结账所需的信息
     public DefaultTableModel getCheckData(java.util.Date start_date,java.util.Date end_date){
        //Date与String的数据转换
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start=df1.format(start_date);
        String end=df1.format(end_date);
       
        System.out.println(start);
        String[] col={"入住单号","入住日期","入住时长（天）","姓名","联系方式 ","客户类型","账单总额（元）"};
        DefaultTableModel tm=new DefaultTableModel(col,0);
        
        try{
            rs=stmt.executeQuery("select ci_no,ci_date,ci_days,checkin.cname,checkin.cphone,ctype,deposit "
                    + "from checkin,customerinfo,customertype "
                    + "where checkin.cno=customerinfo.cno and customerinfo.ct_no=customertype.ct_no and "
                    + "ci_date >='"+start+"' and ci_date <= '"+end+"'");
            while(rs.next()){
                String ci_no=String.valueOf(rs.getInt(1));
                String ci_date=df.format(rs.getTimestamp(2));;
                int days=rs.getInt(3);
                String ci_days=String.valueOf(days);
                String cname=rs.getString(4);
                String cphone=rs.getString(5);
                String ctype=rs.getString(6);
                float deposit=rs.getFloat(7);
                float total=0;
                if(ctype.equals("普通顾客")){
                    total=deposit*days*1*2;
                }
                else{
                    if(ctype.equals("会员顾客")){
                        total=(float) (deposit*days*0.7*2);
                    }
                }
                String totalmoney=String.valueOf(total);
                
                String[] str_row={ci_no,ci_date,ci_days,cname,cphone,ctype,totalmoney};
                tm.addRow(str_row);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
       
        return tm;
        
    }
     //更新入住表
     public void deleteCheckin(int ci_no){
          try{
            stmt.executeUpdate("delete from checkin where ci_no="+ci_no);
        }
          catch (Exception e){
              e.printStackTrace();
          }
     }
     //包含回滚的退房
     public boolean CheckRollback(Check chk,String rno){
         int n=0;
         boolean roll=true;
        DateFormat df1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp ci_date=null;
        System.out.println(chk.getCi_date());
        String date=df1.format(new Date());
        Timestamp bdate=null;
        try{
            ci_date=Timestamp.valueOf(chk.getCi_date());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            bdate=Timestamp.valueOf(date);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            rs=stmt.executeQuery("select max(chk_pk) from check1");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
        conn.setAutoCommit(false);
        stmt.addBatch("insert into check1 (chk_pk,chk_no,ci_no,ci_date,ci_days,totalmoney,cname,cphone,ctype) values ("+n+","+n+","+chk.getCi_no()+",'"
            +ci_date+"',"+chk.getCi_days()+",'"+chk.getTotalmomney()+"','"+chk.getCname()+"','"+chk.getCphone()+"','"+chk.getCtype()+"')");
        stmt.addBatch("delete from checkin where ci_no="+chk.getCi_no());
        stmt.addBatch("update roominfo set state='空闲' , statetime=30 , statedate='"+bdate+"' where rno='"+rno+"'");
        stmt.executeBatch();
        conn.commit();
        
        }
        /*catch(Exception e){
            e.printStackTrace();
            //roll=false;
        }*/
        catch(SQLException e){
            e.printStackTrace();
        try {
    // 产生的任何SQL异常都需要进行回滚,并设置为系统默认的提交方式,即为TRUE
        if (conn != null) {
            conn.rollback();
            conn.setAutoCommit(true);
            roll=false;
                            }
        
            } catch (SQLException se1) {
                     se1.printStackTrace();
                     }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                 if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn!= null) {
                    conn.close();
                    conn = null;
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
         } 
         
        } 
         return roll;
     }
     //更改房间状态
     public void updateRoomState(String rno){
         //获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=df.format(new Date());
        Timestamp bdate=null;
        try{
            bdate=Timestamp.valueOf(date);
        }
        catch(Exception e){
            e.printStackTrace();
        }
         try{
            stmt.executeUpdate("update roominfo set state='空闲' , statetime=30 , statedate='"+bdate+"' where rno='"+rno+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }
     }
     //获取房间号
     public String getRno(int ci_no){
         String rno=null;
         try{
             rs=stmt.executeQuery("select rno from checkin where ci_no="+ci_no);
             while(rs.next()){
                 rno=rs.getString(1);
             }
         }
         catch (Exception e){
             e.printStackTrace();
         }
         return rno;
     }
     //获取账单的单号
     public int getCheckno(){
         int chk_no=1;
         try{
             rs=stmt.executeQuery("select * from check1 ");
             while(rs.next()){
                 chk_no++;
             }
         }
         catch (Exception e){
             e.printStackTrace();
         }
         return chk_no;
     }
     //查询结账
     //获取客户的账单信息
    public Check getCheckData(String cphone){
        Check chk=new Check();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int ci_no=0;
        try{
            rs=stmt.executeQuery("select ci_no from checkin where cphone='"+cphone+"'");
            while(rs.next()){
                ci_no=rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            rs=stmt.executeQuery("select ci_no,ci_date,ci_days,checkin.cname,checkin.cphone,ctype,deposit "
                    + "from checkin,customerinfo,customertype "
                    + "where checkin.cno=customerinfo.cno and customerinfo.ct_no=customertype.ct_no and "
                    + "ci_no='"+ci_no+"'");
            while(rs.next()){
                chk.setCi_no(rs.getInt(1));
                chk.setCi_date(df.format(rs.getTimestamp(2)));;
                chk.setCi_days(rs.getInt(3));
                int days=rs.getInt(3);
                chk.setCname(rs.getString(4));
                chk.setCphone(rs.getString(5));
                chk.setCtype(rs.getString(6));
                String ctype=rs.getString(6);
                float deposit=rs.getFloat(7);
                float total=0;
                //计算账单总额,使用BigDecimal类型
                BigDecimal d1=BigDecimal.valueOf(deposit);
                BigDecimal d2=BigDecimal.valueOf(deposit);  
                BigDecimal d3=d1.multiply(d2);
                if(ctype.equals("普通顾客")){
                    BigDecimal d11=BigDecimal.valueOf(1);  
                    BigDecimal d12=BigDecimal.valueOf(2);  
                    total=d3.multiply(d11.multiply(d12)).floatValue();
                }
                else{
                    if(ctype.equals("会员顾客")){
                    BigDecimal d21=BigDecimal.valueOf(0.7);  
                    BigDecimal d22=BigDecimal.valueOf(2);  
                    total=d3.multiply(d21.multiply(d22)).floatValue();

                    }
                }
                chk.setTotalmomney(total);
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
   
        return chk;
    } 
    //经理发会员卡
    public void changeCustomerType(String cphone){
        try{
            stmt.executeUpdate("update customerinfo set ct_no=2 where cphone='"+cphone+"'");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //修改房间类型
    public void changeRoomType(String rno,int rt_no){
        try{
        stmt.executeUpdate("update roominfo set rt_no="+rt_no+" where rno='"+rno+"'");
    }
        catch(Exception e){
                e.printStackTrace();
                }
    }
    //增加新房间
    public void addRoom(String rno,int rt_no,String rtel){
        int n=0;
        /*try{
            rs=stmt.executeQuery("select * from roominfo ");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            rs=stmt.executeQuery("select max(r_pk) from roominfo");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
         //获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=df.format(new Date());
        Timestamp statedate=null;
        try{
            statedate=Timestamp.valueOf(date);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("insert into roominfo (r_pk,rno,rt_no,state,statetime,rtel,statedate) "
                    + "values ("+n+",'"+rno+"','"+rt_no+"','空闲',30,'"+rtel+"','"+statedate+"')");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    //改变员工职位
    public void changePosition(String name,String phone,String position){
        try{
            stmt.executeUpdate("update workerinfo set wposition='"+position+"' where wname='"+name+"' and wphone='"+phone+"'");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //增加新员工
    public void addWorker(Worker w){
        int n=1;
        try{
            rs=stmt.executeQuery("select * from workerinfo ");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("insert into workerinfo (w_pk,wno,wname,sex,age,wphone,wposition,wuserid) "
                    +"values ("+n+","+n+",'"+w.getWname()+"','"+w.getSex()+"','"+w.getAge()+"','"+w.getPhone()+"','"+
                    w.getPostion()+"','"+w.getUserid()+"')");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //增加用户
    public void addUser(User u){
        int n=0;
        /*try{
            rs=stmt.executeQuery("select * from user ");
            while(rs.next()){
                n++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            rs=stmt.executeQuery("select max(user_pk) from user");//因选出最大的加1
            while(rs.next()){
               n= rs.getInt(1)+1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("insert into user (user_pk,userid,password,level) "
                    + "values ("+n+",'"+u.getUserid()+"','"+u.getPassword()+"','"+u.getLevel()+"')/*----------");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //删除员工
    public void deleteWorker(String name,String phone,String userid){
        try{
            stmt.executeUpdate("delete from workerinfo where wphone='"+phone+"'");
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            stmt.executeUpdate("delete from user where userid='"+userid+"'");
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     //获取员工用户名
    public String getUserid(String phone){
        String userid=null;
        try{
        rs=stmt.executeQuery("select wuserid from workerinfo where wphone='"+phone+"'");
        while(rs.next()){
            userid=rs.getString(1);
        }
    }
        catch (Exception e){
            e.printStackTrace();
        }
        return userid;
    }
    //更改员工权限
    public void changeWokerLevel(int level,String userid){
        try{
            stmt.executeUpdate("update user set level="+level+" where userid='"+userid+"'");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //导出收入报表
    public void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));  
        for(int i=0; i < model.getColumnCount(); i++) {
            bWriter.write(model.getColumnName(i));
            bWriter.write("\t");
        }
        bWriter.newLine();
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                bWriter.write(model.getValueAt(i,j).toString());
                bWriter.write("\t");
            }
            bWriter.newLine();
        }
        bWriter.close();
        System.out.println("write out to: " + file);
    }
}