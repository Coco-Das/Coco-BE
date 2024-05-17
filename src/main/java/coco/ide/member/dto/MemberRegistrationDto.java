package coco.ide.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberRegistrationDto {
    private final String email;
    private final String nickname;
    private final String password;
}
