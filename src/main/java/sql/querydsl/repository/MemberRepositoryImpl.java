package sql.querydsl.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import sql.querydsl.domain.Member;
import sql.querydsl.domain.QMember;
import sql.querydsl.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryImpl (JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<MemberDto> findMemberList(String param) {

        List<Member> memberList = new ArrayList<>();
        memberList = jpaQueryFactory
                .selectFrom(QMember.member)
                .where(returnTrue()
                        .and(containName(param))
                        .or(containAge(param)))
                .orderBy(QMember.member.createdAt.desc()).fetch();


        if (memberList.size() ==0) {
            return null;
        }

        List<MemberDto> memberDtoList = makeDto(memberList);
        return memberDtoList;
    }

    private BooleanExpression returnTrue() {
        return Expressions.TRUE.isTrue();
    }

    private BooleanExpression containName(String name) {
        if(!StringUtils.hasText(name)) {
            return null;
        }
        return QMember.member.name.containsIgnoreCase(name);
    }

    private BooleanExpression containAge(String age) {
        if(!StringUtils.hasText(age)) {
            return null;
        }
        return QMember.member.name.containsIgnoreCase(age);
    }

    private List<MemberDto> makeDto (List<Member> memberList) {
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member member : memberList) {
            MemberDto memberDto = new MemberDto(member.getName(), member.getAge(), member.getCreatedAt(), member.getModifiedAt());
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }
}
