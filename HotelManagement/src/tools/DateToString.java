/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author a
 */
public class DateToString {
    public String getDateString(Date date){
        String datestring = null;
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        df.setCalendar(null); 
        df.setNumberFormat(null);
        try{
            datestring=df.format(date);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return datestring;
        
    }
    
    
}
