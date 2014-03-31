package be.vdab.starter.model;

public class Person {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String someName) {
        name = someName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int someAge) {
        age = someAge;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
