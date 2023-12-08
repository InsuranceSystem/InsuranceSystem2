package aplus.insurancesystem.domain.compensationClaim.service;


import aplus.insurancesystem.common.service.FileService;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCompensationClaimRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CompensationClaimResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.SurveyResponse;
import aplus.insurancesystem.domain.compensationClaim.entity.CarAccident;
import aplus.insurancesystem.domain.compensationClaim.entity.CompensationClaim;
import aplus.insurancesystem.domain.compensationClaim.entity.Survey;
import aplus.insurancesystem.domain.compensationClaim.exception.CarAccidentNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.exception.CompensationClaimNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.exception.SurveyNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.repository.CarAccidentRepository;
import aplus.insurancesystem.domain.compensationClaim.repository.CompensationClaimRepository;
import aplus.insurancesystem.domain.compensationClaim.repository.SurveyRepository;
import aplus.insurancesystem.domain.contract.entity.Contract;
import aplus.insurancesystem.domain.contract.exception.ContractNotFoundException;
import aplus.insurancesystem.domain.contract.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompensationClaimServiceImpl implements CompensationClaimService {
    private final CompensationClaimRepository compensationClaimRepository;
    private final CarAccidentRepository carAccidentRepository;
    private final ContractRepository contractRepository;
    private final FileService fileService;


    @Override
    public List<CompensationClaimResponse> getAllCompensationClaim() {
        return compensationClaimRepository.findAll()
                .stream()
                .map(compensationClaim -> CompensationClaimResponse.of(
                        compensationClaim,
                        compensationClaim.getContract().getInsurance().getInsuranceName(),
                        compensationClaim.getContract().getInsurance().getType()))
                .toList();
    }
    @Override
    public List<CompensationClaimResponse> getCompensationClaim(Long customerId) {
        List<CompensationClaimResponse> compensationClaimResponseList = new ArrayList<>();
        for (Contract contract : contractRepository.findByCustomerId(customerId)) {
            List<CompensationClaimResponse> compensationClaims = compensationClaimRepository.findAllByContractId(contract.getId())
                    .stream()
                    .map(compensationClaim -> CompensationClaimResponse.of(
                            compensationClaim,
                            compensationClaim.getContract().getInsurance().getInsuranceName(),
                            compensationClaim.getContract().getInsurance().getType()))
                    .toList();
            compensationClaimResponseList.addAll(compensationClaims);

        }
        return compensationClaimResponseList;
    }
    @Override
    public CompensationClaimResponse getCompensationClaimDetail(Long ccid) {
        return compensationClaimRepository.findById(ccid)
                .map(compensationClaim -> CompensationClaimResponse.of(
                        compensationClaim,
                        compensationClaim.getContract().getInsurance().getInsuranceName(),
                        compensationClaim.getContract().getInsurance().getType()))
                .orElseThrow(CompensationClaimNotFoundException::new);
    }
    @Override
    public CarAccidentResponse getCarAccidentDetail(Long ccid) {
        return carAccidentRepository.findById(ccid)
                .map(CarAccidentResponse::of)
                .orElseThrow(CarAccidentNotFoundException::new);
    }

    @Override
    public InputStreamResource getDocument(Long ccid) {
        CompensationClaim compensationClaim =
                compensationClaimRepository.findById(ccid).orElseThrow(CompensationClaimNotFoundException::new);
        InputStream inputStream = fileService.downloadFile(compensationClaim.getDocumentFilePath());
        return new InputStreamResource(inputStream);
    }

    @Override
    @Transactional
    public void createCompensationClaim(Long contractId, CreateCompensationClaimRequest request) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(ContractNotFoundException::new);

        MultipartFile documentFile = request.getDocumentFile();
        String documentFilePath = documentFile.getOriginalFilename() + LocalDateTime.now();
        fileService.uploadFile(documentFile, documentFilePath);

        CompensationClaim compensationClaim = CompensationClaim.builder()
                .contract(contract)
                .receptionistName(request.getReceptionistName())
                .receptionistPNumber(request.getReceptionistPNumber())
                .relationshipOfContractor(request.getRelationshipOfContractor())
                .documentFilePath(documentFilePath)
                .bank(request.getBank())
                .accountNumber(request.getAccountNumber())
                .accountHolderName(request.getAccountHolderName())
                .isSurveyed(false)
                .build();
        compensationClaimRepository.save(compensationClaim);
    }

    @Override
    @Transactional
    public void createCarAccident(Long contractId, CreateCarAccidentRequest request) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(ContractNotFoundException::new);

        MultipartFile documentFile = request.getDocumentFile();
        String documentFilePath = documentFile.getOriginalFilename() + LocalDateTime.now();
        fileService.uploadFile(documentFile, documentFilePath);

        CompensationClaim compensationClaim = CompensationClaim.builder()
                .contract(contract)
                .receptionistName(request.getReceptionistName())
                .receptionistPNumber(request.getReceptionistPNumber())
                .relationshipOfContractor(request.getRelationshipOfContractor())
                .documentFilePath(documentFilePath)
                .bank(request.getBank())
                .accountNumber(request.getAccountNumber())
                .accountHolderName(request.getAccountHolderName())
                .isSurveyed(false)
                .build();
        compensationClaimRepository.saveAndFlush(compensationClaim);
        CarAccident carAccident = CarAccident.builder()
                .id(compensationClaim.getId())
                .compensationClaim(compensationClaim)
                .type(request.getType())
                .dateTime(request.getDateTime())
                .place(request.getPlace())
                .carNumber(request.getCarNumber())
                .driverName(request.getDriverName())
                .licenseNumber(request.getLicenseNumber())
                .accidentDetail(request.getAccidentDetail())
                .build();
        carAccidentRepository.save(carAccident);
    }
}
