/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relation;

/**
 *
 * @author a
 */
public class Check {
    private int ci_no;
    private String ci_date;
    private int ci_days;
    private float totalmomney;
    private String chk_state;
    private String cname;
    private String cphone;
    private String ctype;
    private float totaldeposit;
    private static int chk_no;

    public static int getChk_no() {
        return chk_no;
    }

    public static void setChk_no(int chk_no) {
        Check.chk_no = chk_no;
    }
    

    public float getTotaldeposit() {
        return totaldeposit;
    }

    public void setTotaldeposit(float totaldeposit) {
        this.totaldeposit = totaldeposit;
    }
    

    public int getCi_no() {
        return ci_no;
    }

    public void setCi_no(int ci_no) {
        this.ci_no = ci_no;
    }

    public String getCi_date() {
        return ci_date;
    }

    public void setCi_date(String ci_date) {
        this.ci_date = ci_date;
    }

    
    public int getCi_days() {
        return ci_days;
    }

    public void setCi_days(int ci_days) {
        this.ci_days = ci_days;
    }

    public float getTotalmomney() {
        return totalmomney;
    }

    public void setTotalmomney(float totalmomney) {
        this.totalmomney = totalmomney;
    }

    public String getChk_state() {
        return chk_state;
    }

    public void setChk_state(String chk_state) {
        this.chk_state = chk_state;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
    
}
