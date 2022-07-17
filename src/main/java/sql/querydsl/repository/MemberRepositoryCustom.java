package sql.querydsl.repository;

import sql.querydsl.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberDto> findMemberList(String param);
}
