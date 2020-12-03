package kr.co.udid.ciderpay.model.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="company")
@Accessors(chain = true)
public class UserTypeCompany
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) private Long id;

    @Column(name = "compregno") private String compregno;

    @Column(name = "compname") private String compname;

    @Column(name = "biztype1") private String biztype1;

    @Column(name = "biztype2") private String biztype2;

    @Column(name = "comptel") private String comptel;

    @Column(name = "ceo_nm") private String ceo_nm;

    /* 1=개인 사업자, 2=법인 사업자 */
    @Column(name = "corp_type") private String corp_type;
}
