package sql.querydsl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String age;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
