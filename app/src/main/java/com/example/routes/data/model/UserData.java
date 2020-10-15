package com.example.routes.data.model;

import android.content.SharedPreferences;

public class UserData {
    private static UserData userData;
    String name ="", id = "", team = "", area = "", todayCount = "", totalCount = "";
    public static UserData getInstance()
    {
        if(userData == null)
        {
            userData = new UserData();
        }
        return userData;
    }

    public UserData(){};

    public static UserData creatUser(String name, String id)
    {
        if(userData == null)
        {
            userData = new UserData(name,id);
        }
        return userData;
    }

    public UserData(String name, String id)
    {
        this.name = name;
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public String getTodayCount() {
        return todayCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setTodayCount(String todayCount) {
        this.todayCount = todayCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

}

