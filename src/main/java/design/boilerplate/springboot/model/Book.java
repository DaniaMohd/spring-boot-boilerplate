package design.boilerplate.springboot.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKS")

public class Book {
    //Marks this variable as the ID
    @Id

    //Auto generated
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //this specifies that Book title cannot be null
    @Column(nullable = false)
	private String title;
}
