package coco.ide.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class EmailVerificationResult {
    private final boolean verified;

    public static EmailVerificationResult of(boolean verified) {
        return EmailVerificationResult.builder()
                .verified(verified)
                .build();
    }
}
