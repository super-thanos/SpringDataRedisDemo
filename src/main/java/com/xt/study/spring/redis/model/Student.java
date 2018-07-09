package com.xt.study.spring.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("student")
public class Student {
  @Id
  long studentNo;
  String name;
  byte sex;

  public Student() {
  }

  public Student(long studentNo, String name, byte sex) {
    this.studentNo = studentNo;
    this.name = name;
    this.sex = sex;
  }

  public long getStudentNo() {
    return studentNo;
  }

  public void setStudentNo(long studentNo) {
    this.studentNo = studentNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte getSex() {
    return sex;
  }

  public void setSex(byte sex) {
    this.sex = sex;
  }

  @Override public String toString() {
    return "Student{" +
        "studentNo=" + studentNo +
        ", name='" + name + '\'' +
        ", sex=" + sex +
        '}';
  }
}