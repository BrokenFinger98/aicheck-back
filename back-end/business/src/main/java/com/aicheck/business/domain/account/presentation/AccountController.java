package com.aicheck.business.domain.account.presentation;

import static java.time.YearMonth.*;

import com.aicheck.business.domain.account.application.service.AccountService;
import com.aicheck.business.domain.account.dto.AccountInfoResponse;
import com.aicheck.business.domain.account.dto.AccountNoResponse;
import com.aicheck.business.domain.account.dto.ChildAccountInfoResponse;
import com.aicheck.business.domain.account.dto.ChildrenAccountsResponse;
import com.aicheck.business.domain.account.dto.DescriptionRatioResponse;
import com.aicheck.business.domain.account.dto.FindAccountFeignResponse;
import com.aicheck.business.domain.account.dto.RegisterMainAccountRequest;
import com.aicheck.business.domain.account.dto.VerifyAccountPasswordRequest;
import com.aicheck.business.domain.account.dto.VerifyAccountResponse;
import com.aicheck.business.domain.transaction_record.application.TransactionRecordService;
import com.aicheck.business.global.auth.annotation.CurrentMemberId;

import java.time.YearMonth;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionRecordService transactionRecordService;

    @GetMapping
    public ResponseEntity<List<FindAccountFeignResponse>> findMyAccounts(@CurrentMemberId Long memberId) {
        return ResponseEntity.ok(accountService.findMyAccounts(memberId));
    }

    @PostMapping
    public ResponseEntity<VerifyAccountResponse> registerMainAccount(@CurrentMemberId Long memberId,
                                                                     @RequestBody RegisterMainAccountRequest request) {
        accountService.registerMainAccount(memberId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check")
    public ResponseEntity<Void> verifyAccountPassword(@CurrentMemberId Long memberId,
                                                      @RequestBody VerifyAccountPasswordRequest request) {
        accountService.verifyAccountPassword(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<AccountInfoResponse> findMyAccountsInfo(@CurrentMemberId Long memberId) {
        AccountInfoResponse account = accountService.findMyMainAccountInfo(memberId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{childId}")
    public ResponseEntity<AccountInfoResponse> findMyChildAccountsInfo(@CurrentMemberId Long memberId,
                                                                       @PathVariable Long childId) {
        AccountInfoResponse account = accountService.findMyChildAccountInfo(memberId, childId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/children")
    public ResponseEntity<ChildrenAccountsResponse> findChildrenAccountsInfo(@CurrentMemberId Long memberId) {
        List<ChildAccountInfoResponse> accounts = accountService.findMyChildAccounts(memberId);
        ChildrenAccountsResponse response = ChildrenAccountsResponse.builder()
                .accounts(accounts)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/number/{memberId}")
    public ResponseEntity<AccountNoResponse> getAccountNumber(@PathVariable Long memberId) {
        return ResponseEntity.ok(accountService.findAccountNoByMemberId(memberId));
    }

    @GetMapping("/children/internal/{memberId}")
    public ResponseEntity<List<ChildAccountInfoResponse>> getChildrenAccountsInfo(@PathVariable Long memberId) {
        List<ChildAccountInfoResponse> children = accountService.findMyChildAccounts(memberId);
        return ResponseEntity.ok(children);
    }

    @GetMapping("/description-ratio")
    public ResponseEntity<DescriptionRatioResponse> getDescriptionRatio(@CurrentMemberId Long memberId){
        return ResponseEntity.ok(transactionRecordService.getDescriptionRatio(memberId, now()));
    }
}
