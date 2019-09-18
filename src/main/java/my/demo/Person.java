package my.demo;

import java.util.Objects;
import java.util.Random;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Person {

    private static final Random r = new Random();

    @NotNull
    public long id;

    @NotNull
    @Size(min = 2, max = 50)
    public String name;

    @NotNull
    @PositiveOrZero
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = r.nextLong();
    }

    @JsonbCreator
    public Person(@JsonbProperty("name") String name,
            @JsonbProperty("age") int age,
            @JsonbProperty("id") long id) {
        this(name, age);
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Person))
            return false;
        Person other = (Person) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(name, other.name) &&
               Objects.equals(age, other.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return name + " is " + age;
    }
}
