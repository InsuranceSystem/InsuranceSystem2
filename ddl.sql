CREATE DATABASE insurance;
USE insurance;

CREATE TABLE Insurance(
                          insuranceID BIGINT AUTO_INCREMENT PRIMARY KEY,
                          insuranceName VARCHAR(255),
                          type VARCHAR(255),
                          maxCompensation INT,
                          periodOfInsurance VARCHAR(255),
                          ageOfTarget VARCHAR(255),
                          basicPremium INT,
                          distributionStatus BOOL,
                          insuranceClausePeriod VARCHAR(255),
                          precaution VARCHAR(255),
                          authorization BOOL,
                          deletedAt DATETIME
);

CREATE TABLE Customer (
                          customerID BIGINT AUTO_INCREMENT PRIMARY KEY,
                          loginId VARCHAR(255),
                          customerName VARCHAR(255),
                          job VARCHAR(255),
                          pnumber VARCHAR(255),
                          birth VARCHAR(255),
                          eGender VARCHAR(255),
                          address VARCHAR(255),
                          deletedAt DATETIME,
                          password VARCHAR(255),
                          role VARCHAR(255) DEFAULT "CUSTOMER"
);

CREATE TABLE CompensationClaim (
                                   CCID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   insuranceID BIGINT,
                                   customerID BIGINT,
                                   receptionistName VARCHAR(255),
                                   receptionistPNumber VARCHAR(255),
                                   relationshipOfContractor VARCHAR(255),
                                   documentFilePath VARCHAR(255),
                                   bank VARCHAR(255),
                                   accountNumber VARCHAR(255),
                                   accountHolderName VARCHAR(255),
                                   FOREIGN KEY (customerID) REFERENCES Customer(customerID),
                                   FOREIGN KEY (insuranceID) REFERENCES Insurance(insuranceID)
);

CREATE TABLE CarAccident (
                             CCID BIGINT PRIMARY KEY,
                             type VARCHAR(255),
                             dateTime DATETIME,
                             place VARCHAR(255),
                             carNumber VARCHAR(255),
                             driverName VARCHAR(255),
                             licenseNumber VARCHAR(255),
                             accidentDetail VARCHAR(255),
                             FOREIGN KEY (CCID) REFERENCES CompensationClaim(CCID)
                                 on update cascade
);

CREATE TABLE Survey (
                        CCID BIGINT PRIMARY KEY,
                        managerName VARCHAR(255),
                        reportFilePath VARCHAR(255),
                        surveyFee INT,
                        decisionMoney INT,
                        responsibility BOOL,
                        responsibilityReason VARCHAR(255),
                        FOREIGN KEY (CCID) REFERENCES CompensationClaim(CCID)
);

CREATE TABLE InsuranceApplication (
                                      applicationID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      insuranceID BIGINT,
                                      customerID BIGINT,
                                      createdAt DATE,
                                      insurancePeriod VARCHAR(255),
                                      paymentCycle VARCHAR(255),
                                      paymentPeriod VARCHAR(255),
                                      subscriptionFilePath VARCHAR(255),
                                      premium INT,
                                      maxCompensation INT,
                                      state VARCHAR(255),
                                      reasonOfApproval VARCHAR(255),
                                      FOREIGN KEY (customerID) REFERENCES Customer(customerID),
                                      FOREIGN KEY (insuranceID) REFERENCES Insurance(insuranceID)
);

CREATE TABLE CounselApplication (
                                    counselID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    customerID BIGINT,
                                    category  VARCHAR(255),
                                    dateOfFirst DATE,
                                    dateOfSecond DATE,
                                    requirement VARCHAR(255),
                                    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Counsel (
                         counselID BIGINT PRIMARY KEY,
                         customerID BIGINT,
                         content VARCHAR(255),
                         managerName VARCHAR(255),
                         dateOfCounsel DATE,
                         FOREIGN KEY (customerID) REFERENCES Customer(customerID),
                         FOREIGN KEY (counselID) REFERENCES CounselApplication(counselID)
);

CREATE TABLE FamilyHistory(
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              customerID BIGINT,
                              diseaseName VARCHAR(255),
                              relationship VARCHAR(255),
                              FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Terms(
                      termsID BIGINT AUTO_INCREMENT PRIMARY KEY,
                      termsName VARCHAR(255),
                      calculatedMoneyMethod VARCHAR(255),
                      termsContent VARCHAR(255)
);

CREATE TABLE Guarantee(
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          insuranceID BIGINT,
                          termsID BIGINT,
                          FOREIGN KEY (insuranceID) REFERENCES Insurance(insuranceID),
                          FOREIGN KEY (termsID) REFERENCES Terms(termsID)
);

CREATE TABLE Contract (
                          contractID BIGINT AUTO_INCREMENT PRIMARY KEY,
                          insuranceID BIGINT,
                          customerID BIGINT,
                          insurancePeriod VARCHAR(255),
                          premium INT,
                          paymentCycle VARCHAR(255),
                          paymentPeriod VARCHAR(255),
                          maxCompensation INT,
                          dateOfSubscription DATE,
                          dateOfMaturity DATE,
                          maturity BOOLEAN,
                          resurrection BOOLEAN,
                          cancellation BOOLEAN,
                          FOREIGN KEY (insuranceID) REFERENCES Insurance(insuranceID),
                          FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Payment (
                         paymentID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         customerID BIGINT,
                         insuranceID BIGINT,
                         contractID BIGINT,
                         dateOfPayment DATE,
                         whetherPayment BOOLEAN,
                         FOREIGN KEY (customerID) REFERENCES Customer(customerID),
                         FOREIGN KEY (insuranceID) REFERENCES Insurance(insuranceID),
                         FOREIGN KEY (contractID) REFERENCES Contract(contractID)
);

INSERT INTO Terms (termsName, calculatedMoneyMethod, termsContent) VALUES
                                                                       ('자동차보험 약관 A', '실비지급', '자동차 사고로 인한 손해배상'),
                                                                       ('건강보험 약관 A', '실비지급', '입원비용 및 수술비용 보장'),
                                                                       ('재물보험 약관 A', '실비지급', '화재로 인한 재물 손해배상'),
                                                                       ('화재보험 약관 A', '실비지급', '화재로 인한 재물 손해배상'),
                                                                       ('종신보험 약관 A', '일시금지급', '보험기간 만료 시 사망보험금 지급'),
                                                                       ('자동차보험 약관 B', '합의지급', '자동차 사고로 인한 손해배상'),
                                                                       ('건강보험 약관 B', '합의지급', '입원비용 및 수술비용 보장'),
                                                                       ('재물보험 약관 B', '합의지급', '화재로 인한 재물 손해배상'),
                                                                       ('화재보험 약관 B', '실비지급', '화재로 인한 재물 손해배상'),
                                                                       ('종신보험 약관 B', '연금지급', '보험기간 만료 시 사망보험금 지급'),
                                                                       ('자동차보험 약관 C', '실비지급', '자동차 사고로 인한 손해배상'),
                                                                       ('건강보험 약관 C', '정액지급', '입원비용 및 수술비용 보장'),
                                                                       ('재물보험 약관 C', '합의지급', '화재로 인한 재물 손해배상'),
                                                                       ('화재보험 약관 C', '실비지급', '화재로 인한 재물 손해배상'),
                                                                       ('종신보험 약관 C', '연금지급', '보험기간 만료 시 사망보험금 지급'),
                                                                       ('자동차보험 약관 D', '실비지급', '자동차 사고로 인한 손해배상'),
                                                                       ('건강보험 약관 D', '정액지급', '입원비용 및 수술비용 보장');

INSERT INTO Insurance (insuranceName, type, maxCompensation, periodOfInsurance, ageOfTarget, basicPremium, distributionStatus, insuranceClausePeriod, precaution, authorization) VALUES
                                                                                                                                                                                     ('자동차보험A', 'Car', 10000000, '10년', '30세', 200000, False, '2년', '사고 발생시 보험금 지급을 위해 경찰 보고서 필요', True),
                                                                                                                                                                                     ('건강보험A', 'Health', 5000000, '10년', '40세', 100000, False, '없음', '선처리 요구사항 확인 필요', True),
                                                                                                                                                                                     ('재물보험A', 'Property', 50000000, '5년', '35세', 500000, False, '없음', '가입 후 30일 이내에 보험금 청구서 제출 필요', True),
                                                                                                                                                                                     ('자동차보험B', 'Car', 1000000, '4년', '25세', 200000, False, '없음', '사고 발생시 보험금 지급을 위해 경찰 보고서 필요', True),
                                                                                                                                                                                     ('화재보험A', 'Fire', 100000000, '20년', '45세', 1000000, False, '없음', '화재 발생 시 경찰과의 협조 필요', True),
                                                                                                                                                                                     ('종신보험A', 'Life', 500000000, '평생', '35세', 1000000, False, '2년', '보험가입 후 2년 동안 자살에 의한 사망은 보험금 지급 제한', True),
                                                                                                                                                                                     ('자동차보험C', 'Car', 20000000, '3년', '28세', 150000, False, '없음', '운전 중 음주로 인한 사고는 보험 적용 제한', True),
                                                                                                                                                                                     ('건강보험B', 'Health', 3000000, '5년', '35세', 80000, False, '없음', '의료기관 방문 시 의사소견서 필요', True),
                                                                                                                                                                                     ('재물보험B', 'Property', 20000000, '1년', '40세', 300000, False, '없음', '보험금 지급을 위해 재물 명세서 필요', True),
                                                                                                                                                                                     ('종신보험B', 'Life', 200000000, '8년', '32세', 3000000, False, '2년', '보험가입 후 2년 동안 자살에 의한 사망은 보험금 지급 제한', True),
                                                                                                                                                                                     ('화재보험B', 'Fire', 200000000, '15년', '50세', 800000, False, '없음', '화재 발생 시 보험금 지급을 위해 화재 경찰 조사 필요', True),
                                                                                                                                                                                     ('종신보험C', 'Life', 1000000000, '평생', '40세', 2000000, False, '5년', '보험 가입 후 3년 동안 자살에 의한 사망은 보험금 지급 제한', True),
                                                                                                                                                                                     ('자동차보험D', 'Car', 5000000, '1년', '27세', 120000, False, '없음', '운전 중 교통법규 위반으로 인한 사고는 보험 적용 제한', True),
                                                                                                                                                                                     ('건강보험C', 'Health', 1000000, '3년', '32세', 50000, False, '1년', '일부 질병은 면책 기간이 적용될 수 있음', True),
                                                                                                                                                                                     ('재물보험C', 'Property', 30000000, '3년', '38세', 350000, False, '없음', '보험금 청구 시 사고 경위에 대한 상세한 설명 필요', True),
                                                                                                                                                                                     ('자동차보험D', 'Car', 30000000, '5년', '30세', 400000, False, '없음', '운전 중 음주로 인한 사고는 보험 적용 제한', False),
                                                                                                                                                                                     ('화재보험C', 'Fire', 150000000, '10년', '55세', 700000, False, '없음', '화재 발생 시 화재 경찰 조사 결과 필요', False),
                                                                                                                                                                                     ('종신보험D', 'Life', 800000000, '평생', '42세', 1500000, False, '없음', '7년 보험 가입 후 5년 동안 자살에 의한 사망은 보험금 지급 제한', False),
                                                                                                                                                                                     ('자동차보험E', 'Car', 15000000, '2년', '31세', 180000, False, '없음', '운전 중 음주, 도주로 인한 사고는 보험 적용 제한', False),
                                                                                                                                                                                     ('건강보험D', 'Health', 2500000, '7년', '37세', 90000, False, '없음', '의료비 청구 시 영수증 및 진단서 제출 필요', False);

INSERT INTO Guarantee (insuranceID, termsID)
VALUES
    (1, 1),
    (2, 2),
    (2, 7),
    (3, 3),
    (3, 8),
    (4, 1),
    (5, 4),
    (6, 5),
    (7, 6),
    (8, 7),
    (9, 8),
    (10, 5),
    (11, 9),
    (12, 10),
    (13, 11),
    (14, 12),
    (15, 13),
    (16, 14),
    (17, 9),
    (18, 15),
    (19, 16),
    (20, 17);

INSERT INTO Customer (loginId, customerName, job, pnumber, birth, eGender, address)
VALUES ('id1', '김철수', '회사원', '01012345678', '1997-11-19', '남', '서울특별시 서대문구 거북골로 12'),
       ('id2', '김영희', '학생', '01023456789', '1997-11-19', '여', '서울특별시 서대문구 거북골로 23'),
       ('id3', '김가나', '학생', '01034567891', '1997-04-19', '여', '서울특별시 서대문구 거북골로 34'),
       ('id4', '김나다', '교사/교수', '01045678912', '1997-11-29', '여', '서울특별시 서대문구 거북골로 45'),
       ('id5', '김다라', '전문직 종사', '01056789123', '1997-11-19', '여', '서울특별시 서대문구 거북골로 56'),
       ('id6', '김마바', '가정주부', '01067891234', '1997-11-19', '여', '서울특별시 서대문구 거북골로 67'),
       ('id7', '김바사', '프리랜서', '01078912345', '1997-11-19', '여', '서울특별시 서대문구 거북골로 78'),
       ('id8', '김사아', '자영업자', '01089123456', '1997-11-09', '여', '서울특별시 서대문구 거북골로 89'),
       ('id9', '김아자', '예술가', '01091234567', '1997-11-19', '여', '서울특별시 서대문구 거북골로 90'),
       ('id10', '김자차', '자영업자', '01013243546', '1999-03-23', '남', '서울특별시 서대문구 거북골로 91');

INSERT INTO Contract (insuranceID, insurancePeriod, premium, paymentCycle, paymentPeriod, maxCompensation, dateOfSubscription, dateOfMaturity, maturity, resurrection, cancellation, customerID)
VALUES
    (1, '10년', 200000, 'MONTHLY_PAYMENT', '12개월',  3000000, '2019-02-22', '2024-08-20', 0, 0, 0, 1),
    (2, '10년', 100000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-03-29', '2024-05-23', 0, 0, 0, 1),
    (3, '5년', 500000 , 'QUARTERLY_PAYMENT', '36개월', 3000000, '2019-06-29', '2024-03-23', 0, 0, 0, 1),
    (1, '10년', 200000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-01-29', '2024-03-23', 1, 1, 0, 3),
    (3, '5년', 500000 , 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-06-29', '2024-03-23', 1, 0, 0, 4),
    (2, '10년', 100000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-07-29', '2024-03-23', 1, 1, 1, 5),
    (1, '10년', 200000, 'QUARTERLY_PAYMENT', '36개월', 3000000, '2019-08-29', '2024-03-23', 1, 1, 0, 6),
    (2, '10년', 100000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-03-29', '2024-03-23', 1, 0, 0, 7),
    (1, '10년', 200000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-02-22', '2024-03-23', 0, 1, 0, 8),
    (1, '10년', 24001, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-01-29', '2024-03-23', 1, 1, 0, 9),
    (2, '10년', 100000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-07-29', '2024-03-23', 1, 1, 1, 2),
    (1, '10년', 200000, 'QUARTERLY_PAYMENT', '36개월', 3000000, '2019-08-29', '2024-03-23', 1, 1, 0, 3),
    (2, '10년', 100000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-03-29', '2024-03-23', 1, 0, 0, 4),
    (1, '10년', 200000, 'QUARTERLY_PAYMENT', '36개월', 3000000, '2019-02-22', '2024-03-23', 0, 1, 0, 5),
    (1, '10년', 200000, 'QUARTERLY_PAYMENT', '36개월', 3000000, '2008-02-22', '2018-02-22', 1, 1, 1, 10),
    (2, '10년', 100000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2009-03-23', '2019-03-23', 1, 1, 1, 10),
    (3, '5년', 500000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2010-02-22', '2015-02-22', 1, 1, 1, 10),
    (9, '1년', 300000, 'QUARTERLY_PAYMENT', '36개월', 20000000, '2019-02-22', '2020-02-22', 1, 1, 0, 10),
    (13, '1년', 120000, 'MONTHLY_PAYMENT', '12개월', 5000000, '2020-06-07', '2021-06-07', 1, 1, 0, 10),
    (14, '3년', 50000, 'MONTHLY_PAYMENT', '12개월', 1000000, '2020-05-19', '2023-05-19', 1, 1, 0, 10),
    (11, '15년', 800000, 'QUARTERLY_PAYMENT', '36개월', 200000000, '2022-04-30', '2037-04-30', 0, 0, 0, 10),
    (18, '평생', 1500000, 'QUARTERLY_PAYMENT', '36개월', 800000000, '2019-02-22', '2099-12-31', 0, 0, 0, 10),
    (8, '5년', 80000, 'MONTHLY_PAYMENT', '12개월', 3000000, '2019-08-02', '2024-08-02', 0, 0, 0, 10),
    (10, '8년', 3000000, 'QUARTERLY_PAYMENT', '36개월', 200000000, '2020-09-30', '2028-09-30', 0, 0, 1, 10),
    (12, '평생', 2000000, 'QUARTERLY_PAYMENT', '36개월', 1000000000, '2023-12-06', '2099-12-31', 0, 0, 1, 10),
    (19, '2년', 180000, 'MONTHLY_PAYMENT', '12개월', 15000000, '2023-12-06', '2025-12-06', 0, 0, 1, 10);

INSERT INTO Payment (customerID, insuranceID, ContractID, dateOfPayment, whetherPayment)
VALUES
    (1, 1, 1, '2023-06-22', 0),
    (1, 2, 2,'2023-05-20', 1),
    (1, 3, 3, '2023-06-25', 0),
    (2, 2, 11, '2023-05-30', 0),
    (3, 1, 12, '2023-05-29', 1),
    (4, 3, 5, '2023-05-29', 1),
    (5, 2, 6, '2023-05-28', 0),
    (10, 1, 15, '2008-02-22', 1),
    (10, 1, 15, '2008-06-22', 1),
    (10, 1, 15, '2008-10-22', 1),
    (10, 1, 15, '2009-02-22', 1),
    (10, 1, 15, '2009-06-22', 1),
    (10, 1, 15, '2009-10-22', 1),
    (10, 1, 15, '2010-02-22', 1),
    (10, 1, 15, '2010-06-22', 1),
    (10, 1, 15, '2010-10-22', 1),
    (10, 2, 16, '2009-03-23', 1),
    (10, 2, 16, '2009-04-23', 1),
    (10, 2, 16, '2009-05-23', 1),
    (10, 2, 16, '2009-06-23', 1),
    (10, 2, 16, '2009-07-23', 1),
    (10, 2, 16, '2009-08-23', 1),
    (10, 2, 16, '2009-09-23', 1),
    (10, 2, 16, '2009-10-23', 1),
    (10, 2, 16, '2009-11-23', 1),
    (10, 2, 16, '2009-12-23', 1),
    (10, 2, 16, '2010-01-23', 1),
    (10, 2, 16, '2010-02-23', 1),
    (10, 3, 17, '2010-02-22', 1),
    (10, 3, 17, '2010-03-22', 1),
    (10, 3, 17, '2010-04-22', 1),
    (10, 3, 17, '2010-05-22', 1),
    (10, 3, 17, '2010-06-22', 1),
    (10, 3, 17, '2010-07-22', 1),
    (10, 3, 17, '2010-08-22', 1),
    (10, 3, 17, '2010-09-22', 1),
    (10, 3, 17, '2010-10-22', 1),
    (10, 3, 17, '2010-11-22', 1),
    (10, 3, 17, '2010-12-22', 1),
    (10, 3, 17, '2011-01-22', 1),
    (10, 9, 18, '2019-02-22', 1),
    (10, 9, 18, '2019-06-22', 1),
    (10, 9, 18, '2019-10-22', 1),
    (10, 9, 18, '2020-02-22', 1),
    (10, 9, 18, '2020-06-22', 1),
    (10, 9, 18, '2020-10-22', 1),
    (10, 9, 18, '2021-02-22', 1),
    (10, 9, 18, '2021-06-22', 1),
    (10, 9, 18, '2021-10-22', 1),
    (10, 13, 19, '2020-06-07', 1),
    (10, 13, 19, '2020-07-07', 1),
    (10, 13, 19, '2020-08-07', 1),
    (10, 13, 19, '2020-09-07', 1),
    (10, 13, 19, '2020-10-07', 1),
    (10, 13, 19, '2020-11-07', 1),
    (10, 13, 19, '2020-12-07', 1),
    (10, 13, 19, '2021-01-07', 1),
    (10, 13, 19, '2021-02-07', 1),
    (10, 13, 19, '2021-03-07', 1),
    (10, 13, 19, '2021-04-07', 1),
    (10, 13, 19, '2021-05-07', 1),
    (10, 14, 20, '2020-05-19', 1),
    (10, 14, 20, '2020-06-19', 1),
    (10, 14, 20, '2020-07-19', 1),
    (10, 14, 20, '2020-08-19', 1),
    (10, 14, 20, '2020-09-19', 1),
    (10, 14, 20, '2020-10-19', 1),
    (10, 14, 20, '2020-11-19', 1),
    (10, 14, 20, '2020-12-19', 1),
    (10, 14, 20, '2021-01-19', 1),
    (10, 14, 20, '2021-02-19', 1),
    (10, 14, 20, '2021-03-19', 1),
    (10, 14, 20, '2021-04-19', 1),
    (10, 11, 21, '2022-04-30', 1),
    (10, 11, 21, '2022-08-30', 1),
    (10, 11, 21, '2022-12-30', 1),
    (10, 11, 21, '2023-04-30', 1),
    (10, 11, 21, '2023-08-30', 1),
    (10, 11, 21, '2023-12-30', 0),
    (10, 11, 21, '2024-04-30', 0),
    (10, 11, 21, '2024-08-30', 0),
    (10, 11, 21, '2024-12-30', 0),
    (10, 18, 22, '2019-02-22', 1),
    (10, 18, 22, '2019-06-22', 1),
    (10, 18, 22, '2019-10-22', 1),
    (10, 18, 22, '2020-02-22', 1),
    (10, 18, 22, '2020-06-22', 1),
    (10, 18, 22, '2020-10-22', 1),
    (10, 18, 22, '2021-02-22', 1),
    (10, 18, 22, '2021-06-22', 1),
    (10, 18, 22, '2021-10-22', 1),
    (10, 8, 23, '2019-08-02', 1),
    (10, 8, 23, '2019-09-02', 1),
    (10, 8, 23, '2019-10-02', 1),
    (10, 8, 23, '2019-11-02', 1),
    (10, 8, 23, '2019-12-02', 1),
    (10, 8, 23, '2020-01-02', 1),
    (10, 8, 23, '2020-02-02', 1),
    (10, 8, 23, '2020-03-02', 1),
    (10, 8, 23, '2020-04-02', 1),
    (10, 8, 23, '2020-05-02', 1),
    (10, 8, 23, '2020-06-02', 1),
    (10, 8, 23, '2020-07-02', 1),
    (10, 10, 24, '2020-09-30', 1),
    (10, 10, 24, '2021-01-30', 1),
    (10, 10, 24, '2021-05-30', 1),
    (10, 10, 24, '2021-09-30', 1),
    (10, 10, 24, '2022-01-30', 1),
    (10, 10, 24, '2022-05-30', 1),
    (10, 10, 24, '2022-09-30', 1),
    (10, 10, 24, '2023-01-30', 1),
    (10, 10, 24, '2023-05-30', 1),
    (10, 12, 25, '2023-12-06', 1),
    (10, 12, 25, '2024-04-06', 0),
    (10, 12, 25, '2024-08-06', 0),
    (10, 12, 25, '2024-12-06', 0),
    (10, 12, 25, '2025-04-06', 0),
    (10, 12, 25, '2025-08-06', 0),
    (10, 12, 25, '2025-12-06', 0),
    (10, 12, 25, '2026-04-06', 0),
    (10, 12, 25, '2026-08-06', 0),
    (10, 19, 26, '2023-12-06', 1),
    (10, 19, 26, '2024-01-06', 0),
    (10, 19, 26, '2024-02-06', 0),
    (10, 19, 26, '2024-03-06', 0),
    (10, 19, 26, '2024-04-06', 0),
    (10, 19, 26, '2024-05-06', 0),
    (10, 19, 26, '2024-06-06', 0),
    (10, 19, 26, '2024-07-06', 0),
    (10, 19, 26, '2024-08-06', 0),
    (10, 19, 26, '2024-09-06', 0),
    (10, 19, 26, '2024-10-06', 0),
    (10, 19, 26, '2024-11-06', 0);



INSERT INTO CounselApplication(counselID, category, customerID, dateOfFirst, dateOfSecond, requirement)
VALUES
    (1, '금액', 1, '2023-02-14', '2023-03-14', '금액표'),
    (2, '질병', 2, '2023-02-14', '2023-03-14', '상세질병표'),
    (3, '상해', 3, '2023-02-14', '2023-03-14', '상해안내표'),
    (4, '가족', 4, '2023-02-14', '2023-03-14', '가족상담표'),
    (5, '상담', 5, '2023-02-14', '2023-03-14', '상담표'),
    (6, '금액', 6, '2023-02-14', '2023-03-14', '금액표'),
    (7, '질병', 7, '2023-02-14', '2023-03-14', '상세질병표'),
    (8, '상해', 8, '2023-02-14', '2023-03-14', '상해안내표'),
    (9, '가족', 9, '2023-02-14', '2023-03-14', '가족상담표');

INSERT INTO Counsel(counselID, customerID, content, managerName, dateOfCounsel)
VALUES
    (1, 1, '금액상담', '김기린', '2023-03-24'),
    (2, 2, '질병상담', '김사자', '2023-03-25'),
    (3, 3, '질병상담', '김기택', '2023-03-26'),
    (4, 4, '분납상담', '김파하', '2023-03-27'),
    (5, 5, '가족상담', '김카타', '2023-03-28'),
    (6, 6, '질병상담', '김타파', '2023-03-24'),
    (7, 7, '금액상담', '김길이', '2023-03-29'),
    (8, 8, '입원상담', '김상아', '2023-03-30'),
    (9, 9, '치료상담', '김강남', '2023-03-21');

INSERT INTO FamilyHistory(customerID, diseaseName, relationship)
VALUES
    (1, '당뇨병', '부'),
    (1, '위경색', '모'),
    (2, '심근경색', '모'),
    (2, '당뇨병', '모'),
    (3, '위염', '본인'),
    (4, '폐렴', '본인'),
    (5, '자궁암', '본인'),
    (5, '폐암', '본인'),
    (6, '백혈병', '본인');

INSERT INTO CompensationClaim (insuranceID, customerID, receptionistName, receptionistPNumber, relationshipOfContractor, documentFilePath, bank, accountNumber, accountHolderName)
VALUES
    (1, 1, '김철수', '01012345678', '본인', 'DocumentFilePath1', 'KB국민은행', '1101231234232222', '김철수'),
    (2, 2, '김영희', '01023456789', '본인', 'DocumentFilePath2', '신한은행', '333316243556888', '김영희'),
    (3, 3, '김가나', '01034567891', '본인', 'DocumentFilePath3', '하나은행', '160025532634532', '김가나'),
    (2, 4, '김나다', '01045678912', '본인', 'DocumentFilePath5', '하나은행', '1106696221551234', '김나다'),
    (3, 5, '김다라', '01056789123', '본인', 'DocumentFilePath6', '신한은행', '333326937434523456', '김다라');

INSERT INTO CarAccident (CCID, type, dateTime, place, carNumber, driverName, licenseNumber, accidentDetail)
VALUES
    (1, '경상사고', '2023-06-07 16:30','명지대학교 주차장 내','12가1234','김철수','929066666622','출차하려는데 입차하던 차가 박음');

INSERT INTO Survey (CCID, ManagerName, ReportFilePath, SurveyFee, DecisionMoney, Responsibility, ResponsibilityReason)
VALUES
    (1, '김담당', 'report1.pdf','3800','400000','0','블랙박스 확인 결과 고객의 책임이 아니라고 판단됨.');
