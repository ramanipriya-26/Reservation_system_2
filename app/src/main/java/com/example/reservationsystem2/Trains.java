package com.example.reservationsystem2;

public class Trains {
    String tid,tname,tno,source,destination,time,platform;

    public Trains()
    {
    }

    public Trains(String tid, String tname, String tno, String source, String destination, String time)
    {
        this.tid = tid;
        this.tname = tname;
        this.tno = tno;
        this.source = source;
        this.destination = destination;
        this.time = time;
    }
    public Trains(String tid, String tname, String tno, String source, String destination, String time,String platform)
    {
        this.tid = tid;
        this.tname = tname;
        this.tno = tno;
        this.source = source;
        this.destination = destination;
        this.time = time;
        this.platform=platform;
    }

    public String getTid()
    {
        return tid;
    }

    public void setTid(String tid)
    {
        this.tid = tid;
    }

    public String getTname()
    {
        return tname;
    }

    public void setTname(String tname)
    {
        this.tname = tname;
    }

    public String getTno()
    {
        return tno;
    }

    public void setTno(String tno)
    {
        this.tno = tno;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
    public void setPlatform(String platform){
        this.platform=platform;
    }
    public String getPlatform()
    {
        return platform;
    }
}

