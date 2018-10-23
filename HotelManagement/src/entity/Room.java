/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author a
 */
public class Room {
    private String rno;
    private String rtype;
    private String price;
    private String deposit;
    private String state;
    private int statetime;
    private String tel;
    private String statedate;

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStatetime() {
        return statetime;
    }

    public void setStatetime(int statetime) {
        this.statetime = statetime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStatedate() {
        return statedate;
    }

    public void setStatedate(String statedate) {
        this.statedate = statedate;
    }
    
    
    
}
