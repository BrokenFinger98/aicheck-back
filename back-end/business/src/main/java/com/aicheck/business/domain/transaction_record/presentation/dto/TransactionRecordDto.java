package com.aicheck.business.domain.transaction_record.presentation.dto;

import com.aicheck.business.domain.transaction_record.entity.TransactionRecord;
import com.aicheck.business.domain.transaction_record.entity.TransactionType;
import lombok.Builder;

@Builder
public record TransactionRecordDto(
        String firstCategoryName,
        String secondCategoryName,
        TransactionType transactionType,
        Long amount,
        Integer rating
) {

    public static TransactionRecordDto from(TransactionRecord transactionRecord) {
        return TransactionRecordDto.builder()
                .firstCategoryName(transactionRecord.getFirstCategory() == null ? null : String.valueOf(transactionRecord.getFirstCategory()))
                .secondCategoryName(transactionRecord.getSecondCategory() == null ? null : String.valueOf(transactionRecord.getSecondCategory()))
                .transactionType(transactionRecord.getType())
                .amount(transactionRecord.getAmount())
                .rating(transactionRecord.getRating())
                .build();
    }
}
