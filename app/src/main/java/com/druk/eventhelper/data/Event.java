package com.druk.eventhelper.data;



public class Event {

    private String name;
    private String description;
    private String time;

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public String getTime() {
        return time;
    }

    public Event(String name, String description, String time) {

        this.name = name;
        this.description = description;
        this.time = time;
    }


}


