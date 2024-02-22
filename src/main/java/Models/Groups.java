package Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NaturalId
    @Column(name = "Code_Group")
    private String code_Group;
    @Column(name = "Name")
    private String name;
    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", code_Group='" + code_Group + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
