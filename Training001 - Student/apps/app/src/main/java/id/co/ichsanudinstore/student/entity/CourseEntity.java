package id.co.ichsanudinstore.student.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/*
* Membuat table dengan nama CourseEntity dengan primary key id dengan tipe data long,
* serta terdapat kolom name pada table tersebut
* */
public class CourseEntity extends RealmObject {
    @PrimaryKey
    private Long id;
    private String name;

    public CourseEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
