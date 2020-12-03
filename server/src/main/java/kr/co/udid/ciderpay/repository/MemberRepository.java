package kr.co.udid.ciderpay.repository;

import kr.co.udid.ciderpay.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>
{
    boolean existsByUserid (String userid);

    boolean existsByParentMemberID (String parentMemberID);

    Member findByUserid (String userid);

}
