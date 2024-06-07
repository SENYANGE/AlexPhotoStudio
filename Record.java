/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alex_photo_studio;

/**
 *
 * @author Programmer
 */
public class Record {
    int id;
    private String name;
    private String comment;
    private String size;
    private Integer number;
    private String Ldate;
    private Integer paid;
    private Integer unit_price;
    private Integer balance;
    private Integer total_amount;
    //constructor
    //empty construcot for Record object creation

    public Record() {
    }
    
//for inserting records
    public Record(String name, String size, Integer number, Integer unit_price,Integer t_amount, Integer paid, String comment, String Ldate) {
        this.name = name;
        this.comment = comment;
        this.size = size;
        this.number = number;
        this.Ldate = Ldate;
        this.paid = paid;
        this.unit_price = unit_price;
        this.total_amount=t_amount;
    }
//constructor for setting fetched records
    public Record(int id,String name, String size, Integer number, Integer unit_price,Integer t_amount, Integer paid, String comment, String Ldate) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.size = size;
        this.number = number;
        this.Ldate = Ldate;
        this.paid = paid;
        this.unit_price = unit_price;
        this.total_amount=t_amount;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getBalance() {
        total_amount=unit_price*number;
        
        balance=total_amount-paid;
        
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getLdate() {
        return Ldate;
    }

    public void setLdate(String Ldate) {
        this.Ldate = Ldate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

  

    
}
