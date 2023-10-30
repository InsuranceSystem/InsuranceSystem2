package aplus.insurancesystem2.domain.Insurance.controller;

import aplus.insurancesystem2.domain.Insurance.domain.Terms;
import aplus.insurancesystem2.domain.Insurance.dto.request.termsCreateRequest;
import aplus.insurancesystem2.domain.Insurance.service.TermsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
