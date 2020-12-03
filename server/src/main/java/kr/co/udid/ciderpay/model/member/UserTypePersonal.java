package kr.co.udid.ciderpay.model.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="personal")
@NoArgsConstructor
public class UserTypePersonal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) private Long id;

    @Column(name = "username") private String username;

    public UserTypePersonal (String username)
    {
        this.username = username;
    }

}
