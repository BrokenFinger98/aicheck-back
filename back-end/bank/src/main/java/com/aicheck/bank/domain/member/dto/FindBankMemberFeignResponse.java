package com.aicheck.bank.domain.member.dto;

import com.aicheck.bank.domain.member.entity.Member;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class FindBankMemberFeignResponse {
    private Long id;
    private String email;
    private LocalDate birth;
    private String name;
    private String gender;

    public static FindBankMemberFeignResponse from(Member member) {
        return FindBankMemberFeignResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .birth(member.getBirth())
                .name(member.getName())
                .gender(member.getGender().name())
                .build();
    }
}
