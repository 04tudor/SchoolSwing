package Models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NaturalId
    @Column(name = "Code_Student")
    private String code_Student;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Group_ID")
    private Groups groups;
    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", code_Student='" + code_Student + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", groups=" + groups +
                '}';
    }
}
