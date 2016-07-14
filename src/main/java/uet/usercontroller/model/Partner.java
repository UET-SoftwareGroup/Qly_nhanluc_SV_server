package uet.usercontroller.model;

import javax.persistence.*;

/**
 * Created by Tu on 03-May-16.
 */
@Entity
@Table(name="Partner")
public class Partner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
//    @Column(name = "user_id")
//    private int user_id;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
//    public int getUser_id() {
//        return user_id;
//    }
}

