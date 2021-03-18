package com.example.reservationsystem2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {
    String Name1;
    public int no_of_persons;
    public List<String> name =new ArrayList<String>();
    public List<Integer> age =new ArrayList<Integer>();
    public List<String> gender =new ArrayList<String>();
    public String starting,destination;
    public String date_of_journey;
    Ticket()
    {}
    Ticket(String Name1,int no_of_persons, List<String> name,List<Integer> age,List<String> gender,String starting,String destination,String date_of_journey)
    {
        this.Name1=Name1;
        this.no_of_persons=no_of_persons;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.starting=starting;
        this.destination=destination;
        this.date_of_journey=date_of_journey;
    }
    public int getNo_of_persons() {
        return no_of_persons;
    }


}
