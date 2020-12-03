package kr.co.udid.ciderpay.model;

import kr.co.udid.ciderpay.model.enums.BizKind;
import kr.co.udid.ciderpay.model.enums.SellerType;
import kr.co.udid.ciderpay.model.enums.UserType;
import kr.co.udid.ciderpay.model.notYet.UserTypeCompany;
import kr.co.udid.ciderpay.model.notYet.UserTypePersonal;
import kr.co.udid.ciderpay.service.common.EnumConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="member")
@Accessors(chain = true)
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) private Long id;

    @NotBlank
    @Column(name = "parent_member_id") private String parentMemberID;

    @NotNull
    @Convert(converter = SellerTypeConverter.class)
    @Column(name = "seller_type") private SellerType seller_type;

    @NotBlank
    @Column(name = "userid") private String userid;

    @NotBlank
    @Column(name = "userpwd") private String userpwd;

    @NotBlank
    @Column(name = "sellername") private String sellername;

    @NotBlank
    @Column(name = "phone") private String phone;

    @NotBlank
    @Column(name = "email") private String email;

    @Column(name = "homepage") private String homepage;

    @Convert(converter = BizKindConverter.class) @NotNull
    @Column(name = "bizkind") private BizKind bizkind;

    @Column(name = "wherefrom") private String wherefrom;

    @Column(name = "zipcode") private String zipcode;

    @Column(name = "addr1") private String addr1;

    @Column(name = "addr2") private String addr2;

    /* 1=개인, 2=사업자 */
    @Convert(converter = UserTypeConverter.class)
    @Column(name = "usertype") private UserType usertype;

    @Column(name = "compbank") private String compbank;

    @Column(name = "compbanknum") private String compbanknum;

    @Column(name = "compbankname") private String compbankname;



    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "company_type", referencedColumnName = "id")
    private UserTypeCompany companyType;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "personal_type", referencedColumnName = "id")
    private UserTypePersonal personalType;
}

@Converter
class BizKindConverter implements AttributeConverter<BizKind, String>
{

    @Override
    public String convertToDatabaseColumn (BizKind attribute)
    {
        return attribute.getName ();
    }

    @Override
    public BizKind convertToEntityAttribute (String dbData)
    {
        BizKind result = null;

        try {
            result =  (BizKind) EnumConverter.convert (BizKind.class, dbData);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace ();
        }

        return result;
    }
}

@Converter
class SellerTypeConverter implements AttributeConverter<SellerType, String>
{

    @Override
    public String convertToDatabaseColumn (SellerType attribute)
    {
        return attribute.toString ();
    }

    @Override
    public SellerType convertToEntityAttribute (String dbData)
    {
        return SellerType.valueOf (dbData);
    }
}

@Converter
class UserTypeConverter implements AttributeConverter<UserType, String>
{

    @Override
    public String convertToDatabaseColumn (UserType attribute)
    {
        return attribute.getName ();
    }

    @Override
    public UserType convertToEntityAttribute (String dbData)
    {
        UserType result = null;

        try {
            result =  (UserType) EnumConverter.convert (UserType.class, dbData);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace ();
        }

        return result;
    }
}
