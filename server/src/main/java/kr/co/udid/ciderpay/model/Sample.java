package kr.co.udid.ciderpay.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="store")
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;


    /*@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<BusinessTime> businessTimes;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Holiday> holidays;*/
}
