package aplus.insurancesystem.domain.compensationClaim.service;

import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;

public interface CarAccidentService {
    CarAccidentResponse getCarAccidentDetail(Long ccid);
    void createCarAccident(Long contractId,CreateCarAccidentRequest request);

}
