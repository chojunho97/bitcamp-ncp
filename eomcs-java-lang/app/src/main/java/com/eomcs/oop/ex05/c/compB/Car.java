package com.eomcs.oop.ex05.c.compB;

public class Car {

  public String model;
  public String maker;
  public int capacity;

  public boolean fixed;
  public boolean water;


  public boolean sunroof;
  public boolean auto;
  public int accel;

  public Car() {}

  public Car(String model, String maker, int capacity) {
    this.model = model;
    this.maker = maker;
    this.capacity = capacity;
  }

  public Car(String model, String maker, int capacity, boolean fixed, boolean water) {
    this(model, maker, capacity);
  }


  public void run() {
    System.out.println("달린다!");
  }
}


