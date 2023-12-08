package aplus.insurancesystem.domain.compensationClaim.service;


import aplus.insurancesystem.common.service.FileService;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem.domain.compensationClaim.entity.CarAccident;
import aplus.insurancesystem.domain.compensationClaim.entity.CompensationClaim;
import aplus.insurancesystem.domain.compensationClaim.exception.CarAccidentNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.exception.CompensationClaimNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.repository.CarAccidentRepository;
import aplus.insurancesystem.domain.compensationClaim.repository.CompensationClaimRepository;
import aplus.insurancesystem.domain.contract.entity.Contract;
import aplus.insurancesystem.domain.contract.exception.ContractNotFoundException;
import aplus.insurancesystem.domain.contract.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarAccidentServiceImpl implements CarAccidentService {
    private final CompensationClaimRepository compensationClaimRepository;
    private final CarAccidentRepository carAccidentRepository;
    private final ContractRepository contractRepository;
    private final FileService fileService;


    @Override
    public CarAccidentResponse getCarAccidentDetail(Long ccid) {
        CompensationClaim compensationClaim = compensationClaimRepository.findById(ccid).orElseThrow(CompensationClaimNotFoundException::new);
        return carAccidentRepository.findById(ccid)
                .map(carAccident -> CarAccidentResponse.of(
                        compensationClaim,
                        compensationClaim.getContract().getInsurance().getInsuranceName(),
                        compensationClaim.getContract().getInsurance().getType(),
                        carAccident))
                .orElseThrow(CarAccidentNotFoundException::new);
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
