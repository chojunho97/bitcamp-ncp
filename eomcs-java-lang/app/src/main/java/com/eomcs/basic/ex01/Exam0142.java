// Object 클래스 - hashCode() 오버라이딩
package com.eomcs.basic.ex01;

import java.util.Objects;

public class Exam0142 {
  public static void main(String[] args) {
    My obj1 = new My();
    obj1.name = "홍길동";
    obj1.age = 20;

    My obj2 = new My();
    obj2.name = "홍길동";
    obj2.age = 20;

    System.out.println(obj1 == obj2); // false
    System.out.println(obj1.equals(obj2)); // true

    System.out.println(Integer.toHexString(obj1.hashCode()));
    System.out.println(Integer.toHexString(obj2.hashCode()));

    System.out.println(obj1);
    System.out.println(obj2);
  }

  static class My {
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      My other = (My) obj;
      return age == other.age && Objects.equals(name, other.name);
    }

    String name;
    int age;

    @Override
    public int hashCode() {
      return Objects.hash(age, name);
    }
  }

}







