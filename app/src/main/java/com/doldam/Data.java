package com.doldam;

import java.util.ArrayList;

/**
 * Created by user on 2017-07-25.
 */

public class Data {
    private String pj_name;
    private String university;
    private String major;
    private ArrayList<String> member_list;
    private String summary;
    private ArrayList<String> tech_list;
    boolean like = false;

    Data(){
        like = false;
        member_list = new ArrayList<>();
        tech_list = new ArrayList<>();
    }

    Data(String pj_name,String university,String major,String summary){
        like = false;
        member_list = new ArrayList<>();
        tech_list = new ArrayList<>();
        setPj_name(pj_name);
        setUniversity(university);
        setMajor(major);
        setSummary(summary);
    }

    public String getPj_name() {
        return pj_name;
    }

    public void setPj_name(String pj_name) {
        this.pj_name = pj_name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void addMember(String member){
        member_list.add(member);
    }
    public String getMember(int index){
        return member_list.get(index);
    }

    public void addTech(String tech){
        tech_list.add(tech);
    }
    public String getTech(int index){
        return tech_list.get(index);
    }

}
