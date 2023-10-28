package aplus.insurancesystem2.Insurance.controller;

import aplus.insurancesystem2.Insurance.domain.Insurance;
import aplus.insurancesystem2.Insurance.domain.Terms;
import aplus.insurancesystem2.Insurance.dto.request.insuranceCreateRequest;
import aplus.insurancesystem2.Insurance.dto.request.termsCreateRequest;
import aplus.insurancesystem2.Insurance.dto.response.InsuranceInfoResponse;
import aplus.insurancesystem2.Insurance.service.InsuranceService;
import aplus.insurancesystem2.Insurance.service.TermsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terms")
@RequiredArgsConstructor
public class TermsController {

    private final TermsService termsService;

    //전체 약관 조회
    @GetMapping("/all")
    public List<Terms> getTermsList() {
        return termsService.getTermsList();
    }

    //새 약관 등록
    @PostMapping("/create")
    public String createTerms(@Valid @RequestPart("dto") termsCreateRequest terms) {
        return termsService.createTerms(terms);
    }
}
