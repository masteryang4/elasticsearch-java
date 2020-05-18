package com.atguigu.bean;

public class Stu {

    private String id;
    private String name;

    public Stu() {
    }

    public Stu(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stu stu = (Stu) o;

        if (id != null ? !id.equals(stu.id) : stu.id != null) return false;
        return name != null ? name.equals(stu.name) : stu.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
