# Transit - 서울시 교통정보 이슈 모니터링 시스템

서울시 공공 API를 활용하여 실시간 교통 이슈(사고, 공사 등) 정보를 수집하고 관리하는 Spring Boot 애플리케이션입니다.

## 주요 기능

- **실시간 데이터 수집**: 서울시 교통정보 API를 통한 교통 이슈 자동 수집
- **자동 동기화**: 5분 간격으로 최신 정보 업데이트
- **이슈 분류**: 사고, 공사, 기상, 기타 카테고리별 분류
- **상태 관리**: 진행 중/완료 상태 자동 업데이트

## 기술 스택

- **Language**: Java 17
- **Framework**: Spring Boot 3.5.6
- **Database**: MySQL 8.3.0
- **ORM**: Spring Data JPA, QueryDSL 5.0.0
- **HTTP Client**: Spring WebFlux (WebClient)
- **Build Tool**: Gradle

## 프로젝트 마일스톤

### Phase 1: 데이터 수집
- [ ] 공공 API 연동
- [ ] Scheduler 구현 (주기적 데이터 수집)
- [ ] MySQL에 원본 데이터 저장
- [ ] Dockerize & GitHub Actions로 Staging 배포 자동화
- [ ] 데이터 수집 안정성 테스트

### Phase 2: Dashboard 기능
- [ ] MySQL 원본 데이터 조회 API 구현
- [ ] 기본 Dashboard UI 구현
- [ ] 조회 성능 테스트 및 최적화
- [ ] Staging 환경에서 Dashboard 테스트

### Phase 3: 이벤트 기반 전환 (Kafka 도입)
- [ ] Collection Service에서 Kafka 이벤트 발행
- [ ] Dashboard Service에서 Kafka 이벤트 수신 및 처리 (MySQL 구독 형태)
- [ ] Docker 이미지/배포 단위 준비
- [ ] Staging 환경에서 이벤트 흐름 테스트

### Phase 4: 서비스 분리 및 MSA 전환
- [ ] Collection, API, Dashboard Service로 분리
- [ ] Kafka 이벤트 기반 데이터 흐름 적용
- [ ] ElasticSearch 연동 (검색/실시간 분석)
- [ ] Production 환경 배포 및 테스트

### Phase 5: 모니터링 및 안정화
- [ ] Sentry 연동 (에러 추적)
- [ ] Datadog 연동 (성능 모니터링)
- [ ] 배포별 에러율 및 성능 분석
- [ ] CI/CD 안정화
- [ ] 문서화 및 README 업데이트
