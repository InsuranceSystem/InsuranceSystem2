package aplus.insurancesystem2.domain.compensationClaim.controller;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem2.domain.compensationClaim.dto.request.CreateCompensationClaimRequest;
import aplus.insurancesystem2.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem2.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem2.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem2.domain.compensationClaim.dto.response.CompensationClaimResponse;
import aplus.insurancesystem2.domain.compensationClaim.dto.response.SurveyResponse;
import aplus.insurancesystem2.domain.compensationClaim.service.CompensationClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compensation-claim")
@RequiredArgsConstructor
public class CompensationClaimController {
    private final CompensationClaimService compensationClaimService;

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<CompensationClaimResponse>>> getAllCompensationClaim() {
        return SuccessResponse.of(
                compensationClaimService.getAllCompensationClaim()
        ).asHttp(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<List<CompensationClaimResponse>>> getCompensationClaim(@PathVariable("id") Long customerId) {
        return SuccessResponse.of(
                compensationClaimService.getCompensationClaim(customerId)
        ).asHttp(HttpStatus.OK);
    }

    @GetMapping("/detail/{ccid}")
    public ResponseEntity<SuccessResponse<CompensationClaimResponse>> getCompensationClaimDetail(@PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                compensationClaimService.getCompensationClaimDetail(CCID)
        ).asHttp(HttpStatus.OK);
    }

    @GetMapping("/detail/car/{ccid}")
    public ResponseEntity<SuccessResponse<CarAccidentResponse>> getCarAccidentDetail(@PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                compensationClaimService.getCarAccidentDetail(CCID)
        ).asHttp(HttpStatus.OK);
    }

    @PostMapping("/claim")
    public ResponseEntity<Void> createCompensationClaim(@RequestBody CreateCompensationClaimRequest request) {
        compensationClaimService.createCompensationClaim(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/claim/car")
    public ResponseEntity<Void> createCarAccident(@RequestBody CreateCarAccidentRequest request) {
        compensationClaimService.createCarAccident(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //손해사정

    @GetMapping("/survey/{ccid}")
    public ResponseEntity<SuccessResponse<SurveyResponse>> getSurvey(@PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                compensationClaimService.getSurvey(CCID)
        ).asHttp(HttpStatus.OK);
    }

    @PostMapping("/survey")
    public ResponseEntity<Void> createSurvey(@RequestBody CreateSurveyRequest request) {
        compensationClaimService.createSurvey(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/survey/{ccid}")
    public ResponseEntity<Void> updateSurvey(@PathVariable("ccid") Long ccid, @RequestBody UpdateSurveyRequest request) {
        compensationClaimService.updateSurvey(ccid, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
