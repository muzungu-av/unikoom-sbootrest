package ru.avv.test.unikoomapp.data.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The class of the Person entity.
 * Needs a customized SEQUENCE in postgres:
 * CREATE SEQUENCE JPA_SEQUENCE START WITH 1 INCREMENT BY 1 NO CYCLE;
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @Column(name = "ID")
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "fio")
    private String fio;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "sex")
    private boolean sex;

    public Person() {
    }

    public Person(String userName, String fio, String email, Date birthDate, boolean sex) {
        this.userName = userName;
        this.fio = fio;
        this.email = email;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getId() == person.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
