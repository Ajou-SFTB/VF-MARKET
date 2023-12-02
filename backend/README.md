# VF-MARKET: 가상피팅 기능을 적용한 온라인 중고거래 마켓

<img src="../assets/logo.png" width="240" height="300">

## 팀

### 팀명: SFTB(Start From The Bottom)

### 팀원

| 이름   | 학번      | 담당             |
| ------ | --------- | ---------------- |
| 이동현 | 202020983 | 백엔드           |
| 이정훈 | 201720770 | 머신러닝, 백엔드 |

## 목차

1. [프로젝트 소개](#프로젝트-소개)
2. [사용한 기술](#사용한-기술)

## 프로젝트 소개

- 이 프로젝트는 아주대학교 캡스톤디자인 과목의 프로젝트를 위해 작성됨
- 본 프로젝트는 가상 피팅을 활용한 온라인 중고거래 마켓을 기획한 프로젝트임
- 여러 상품에 대해 자신의 스타일과 어울리는지 비교를 해보고 싶은 경우
- 온라인으로 상품을 구매했을 때 상품이 구매자의 예상과 맞지 않는 것을 피하고 싶은 경우
- 위와 같은 사람들을 위해서 Virtual fitting 기능을 통한 간접 경험을 제공하고자 'VR-MARKET'을 기획하게 되었다.

## 사용한 기술

<img src="../assets/springboot.png" height="100%">

- Spring Boot

<img src="../assets/springsecurity.png" height="100%">

- Spring Security
  - 로그인 시 JWT를 써서 인증 토큰을 발급한다.
  - SecurityContextHolder에 Authentication 정보를 저장한다.
  - 저장된 인증 정보를 통해 로그인한 유저를 식별한다.

## 프로젝트 백엔드 구조

- 기능에 맞게 Controller, Domain, Exceptions, Global, Payment, Repository, Service를 필요한 만큼 생성하여 사용
  - controller: 각 기능 별 URL 매핑을 위해 사용하는 RestController
  - domain: DB와 애플리케이션 간에 전송되는 데이터를 정의하기 위해 사용
    - dto: 데이터 전송을 위한 객체
    - entity: DB의 테이블을 생성하고 데이터를 정의한다.
  - exceptions: Custom 예외 처리를 위해 생성
  - global: 애플리케이션 전반에 전역적으로 사용되는 부분
    - config: 보안이나 웹 설정을 위한 부분
    - jwt: 인증을 위한 JWT 토큰을 발행하고 인증하기 위한 부분
    - login: 로그인 정보 처리를 위한 부분
  - payment: 외부 API인 PortOne API를 사용하고 있기 때문에 별도로 패키지를 분리해서 사용했다.
  - repository: JPA를 통해 DB와 통신하기 위한 부분. DAO 역할.
  - service: DB와의 통신 전후로 데이터를 처리하기 위한 부분

## 사용한 외부 API

### 1-1. Google Gmail SMTP Server

<img src="../assets/gmail.png" width="200" height="200">

- Google Gmail의 SMTP 서버를 통해 회원에게 인증번호 등의 메일을 보낸다.

### 1-2. PortOne 결제

<img src="../assets/portone.png" width="200" height="200">

- PortOne의 결제 시스템을 통해 상품 결제를 구현했다.
- payment 패키지에서 결제 시스템의 백엔드 처리를 담당한다.
