package com.aicheck.business.domain.transaction_record.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RatingRequest {

    @NotNull(message = "record_id는 필수입니다.")
    private Long recordId;

    @NotNull(message = "rating은 필수입니다.")
    @Min(value = 1, message = "rating은 1 이상이어야 합니다.")
    @Max(value = 5, message = "rating은 5 이하여야 합니다.")
    private Integer rating;
}
