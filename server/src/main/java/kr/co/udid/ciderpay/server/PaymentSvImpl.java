package kr.co.udid.ciderpay.server;

import kr.co.udid.ciderpay.Repository.PaymentRequestRepository;
import kr.co.udid.ciderpay.Repository.PaymentRequestStatusRepository;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.PaymentRequestStatus;
import kr.co.udid.ciderpay.model.data.PaymentRequestFail;
import kr.co.udid.ciderpay.model.data.PaymentRequestSuccess;
import kr.co.udid.ciderpay.model.data.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSvImpl implements PaymentSv {
    final private PaymentRequestStatusRepository requestStatusRepository;
    final private PaymentRequestRepository requestRepository;

    @Override
    public void insertTestData(PaymentRequest request) {
        PaymentRequestStatus requestStatus = new PaymentRequestStatus();

        requestStatus.setMemberID(request.getMemberID());
        requestStatus.setPrice(request.getPrice());
        requestStatus.setTaxPrice(request.getTaxPrice());
        requestStatus.setTaxFreePrice(request.getTaxFreePrice());
        requestStatus.setGoodName(request.getGoodName());
        requestStatus.setMemberID(request.getMobile());
        requestStatus.setCustomName(request.getCustomName());
        requestStatus.setCustomNo(request.getCustomNo());
        requestStatus.setEmail(request.getEmail());
        requestStatus.setFeedbackurl(request.getFeedbackurl());
        requestStatus.setReturnurl(request.getReturnurl());
        requestStatus.setReturnmode(request.getReturnmode());
        requestStatus.setVar1(request.getVar1());
        requestStatus.setVar2(request.getVar2());
        requestStatus.setSmsuse(request.getSmsuse());
        requestStatus.setWhereFrom(request.getWhereFrom());
        requestStatus.setSellerMemo(request.getSellerMemo());

        requestStatus.setCreateDate(new Date());

        try {
            requestRepository.save(request);

            requestStatus.setStatus(Status.SUCCESS);
        }
        catch (Exception e)
        {
            requestStatus.setStatus(Status.FAIL);
        }

        System.out.println(requestStatus);
        requestStatusRepository.save(requestStatus);

    }

    @Override
    public List<PaymentRequestStatus> findAll() {
        return requestStatusRepository.findAll();
    }


}
