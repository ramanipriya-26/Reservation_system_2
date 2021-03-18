package com.example.reservationsystem2;

public class Current_Avl {
    String one_a, two_a, three_a, sl, cc;
    public Current_Avl()
    {
    }
    public Current_Avl(String onea, String twoa, String threea, String sl, String cc)
    {
        this.one_a = onea;
        this.two_a = twoa;
        this.three_a = threea;
        this.sl = sl;
        this.cc = cc;
    }

    public String getOne_a()
    {
        return one_a;
    }

    public void setOne_a(String onea)
    {
        this.one_a = onea;
    }

    public String getTwo_a()
    {
        return two_a;
    }

    public void setTwo_a(String twoa)
    {
        this.two_a = twoa;
    }

    public String getThree_a()
    {
        return three_a;
    }

    public void setThree_a(String three_a)
    {
        this.three_a = three_a;
    }

    public String getSl()
    {
        return sl;
    }

    public void setSl(String sl)
    {
        this.sl = sl;
    }

    public String getCc()
    {
        return cc;
    }

    public void setCc(String cc)
    {
        this.cc = cc;
    }
}
