package org.scoula.ex05.domain;

public class Member {
    private String name; //캡슐화 - private접근자.
    private String userid;
    public Member() { //기본생성자
    }
    public Member(String name, String userid) { //매개변수 있는 생성자
        this.name = name;
        this.userid = userid;
    }
    //getter
    public String getName() {
        return name;
    }
    public String getUserid() {
        return userid;
    }
    //setter
    public void setName(String name) {
        this.name = name;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

}
