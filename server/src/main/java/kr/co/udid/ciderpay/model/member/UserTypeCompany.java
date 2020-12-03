package kr.co.udid.ciderpay.model.member;

import kr.co.udid.ciderpay.model.member.enums.CorpType;
import kr.co.udid.ciderpay.service.util.EnumConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;

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

    @Convert(converter = CorpTypeConverter.class)
    @Column(name = "corp_type") private CorpType corp_type;
}

@Converter
class CorpTypeConverter implements AttributeConverter<CorpType, String>
{

    @Override
    public String convertToDatabaseColumn (CorpType attribute)
    {
        return attribute.getName ();
    }

    @Override
    public CorpType convertToEntityAttribute (String dbData)
    {
        CorpType result = null;

        try {
            result =  (CorpType) EnumConverter.convert (CorpType.class, dbData);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace ();
        }

        return result;
    }
}