package kr.co.udid.ciderpay.model;

import kr.co.udid.ciderpay.model.data.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name="payment_request_status")
public class PaymentRequestStatus{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false) private Long id;

    @Column(name = "create_date") private Date createDate;

    @Column(name = "status") private Status status;
}
