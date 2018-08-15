package id.co.ichsanudinstore.student.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

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
