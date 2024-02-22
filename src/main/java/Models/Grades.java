package Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "grades")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NaturalId
    @Column(name = "Code_Grade")
    private String code_Grade;
    @OneToOne()
    @JoinColumn(name = "Student_ID")
    private Students students;
    @Column(name = "Semester_Grade")
    private double semester_Grade;

    @Override
    public String toString() {
        return "Grades{" +
                "id=" + id +
                ", code_Grade='" + code_Grade + '\'' +
                ", students=" + students +
                ", semester_Grade=" + semester_Grade +
                '}';
    }
}
