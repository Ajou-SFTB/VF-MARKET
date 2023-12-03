# VF-MARKET: 가상피팅 기능을 적용한 온라인 중고거래 마켓

VF-MARKET은 오프라인에서 직접 상품을 착용해보지 않아도 온라인 상에서 가상의 피팅 스타일을 알고 싶은 사람을 위한 1 대 1 중고 거래 서비스 입니다. 이미지 합성을 통한 가상 피팅 서비스를 통해 소비자는 예상과 다른 스타일로 인한 상품 구매 실패 횟수를 낮출 수 있습니다.

**VR Fitting 서비스**
- 구매하고자 하는 상품 가상의 피팅 스타일을 제공 합니다.
- 사용자는 가상 피팅된 이미지를 저장하여 마이페이지에서 확인 할 수 있으며, 상품 구매시에 활용 할 수 있습니다.

<br/>

## SFTB Frontend 팀원

|이름|기여 분야|연락처|
|:---:|---|:---:|
|김진혁|구매 및 판매처리, 마이페이지, 장바구니|<dog3027@ajou.ac.kr>|
|전강우|회원가입 및 로그인, 상품 상세, 결제, VR Fitting 페이지 |<kwjeon98@ajou.ac.kr>|

<br/>

## Frontend 기술 스택

<img src="https://i.ibb.co/Hh4hvZ9/2023-12-03-000236.png" alt="2023-12-03-000236" border="0">

<br/>

## 사용 프레임워크

|Framework |Ver.|Description|
|------|---|---|
|bootstrap|4.6.1|웹사이트나 웹 애플리케이션의 UI를 빠르게 구축하기 위한 프레임 워크|
|core-js|3.8.3|JavaScript의 표준 라이브러리를 보완하기 위한 모듈|
|vue.js|2.7.14|Vue.js는 사용자 인터페이스를 구축하기 위한 프로그레시브 프레임워크|

<br/>

## 사용 라이브러리

|Library |Ver.|Description|
|------|---|---|
|bootstrap-vue|2.23.1|Bootstrap 프레임워크의 모든 기능을 Vue.js 애플리케이션에서 사용할 수 있게 해주는 라이브러리|
|axios|1.6.0|axios는 웹 브라우저와 Node.js 환경에서 사용할 수 있는 Promise 기반의 HTTP 클라이언트 라이브러리|
|vue-router|3.6.5|Vue.js 애플리케이션에서 사용할 수 있는 공식 라우팅 라이브러리|
|vue-star-rating|1.7.0| Vue.js 애플리케이션에서 별점 평가 기능을 제공하는 라이브러리|

<br/>

## DevOps

|Development Tools |Ver.|Description|
|------|---|---|
|Auto Rename Tag|0.1.10|HTML, XML, PHP, JavaScript 등의 언어에서 태그를 자동으로 이름 변경하는 기능을 제공|
|Auto Close Tag|0.5.14|HTML, XML, PHP, JavaScript 등의 언어에서 태그를 자동으로 닫아주는 기능을 제공|
|Autoprefixer|3.0.1|CSS에 벤더 프리픽스를 자동으로 추가해주는 기능을 제공|
|ESLint|2.4.2|JavaScript 코드에서 문제점을 찾고 고치는 데 도움을 주는 도구|
|indent-rainbow|8.3.1|코드의 들여쓰기를 다양한 색상으로 표시하여 가독성을 향상시키는 도구|
|Prettier|10.1.0|코드를 일관된 스타일로 자동으로 정리해주는 코드 포맷터|

<br/>

## 프로젝트 구조

```
- 📂 Vue-cli
  - 📂 public
  - 📂 node_modules
  - 📂 src
    - 📂 assets
    - 📂 components
    - 📂 router
    - 📄 App.vue
    - 📄 main.js
    - 📄 event-bus.js

```


- `public`: 정적 자원을 저장하는 디렉토리. 웹팩에 의해 처리되지 않고, 빌드 과정에서 변경 없이 그대로 복사됩니다.
- `node_modules`: 프로젝트에서 사용하는 모듈들이 설치되는 디렉토리. `npm install` 명령을 실행하면, 필요한 모든 패키지와 그 의존성들이 이 디렉토리에 설치됩니다.
- `src`: 소스 코드가 저장되는 디렉토리. 이 디렉토리 안에는 다음과 같은 하위 디렉토리와 파일들이 포함됩니다:
  - `assets`: 이미지, 스타일시트, 폰트 등의 정적 파일들이 저장되는 디렉토리입니다.
  - `components`: Vue 컴포넌트 파일들이 저장되는 디렉토리입니다.
  - `router`: Vue Router 설정이 저장되는 디렉토리입니다.
  - `App.vue`: 애플리케이션의 루트 컴포넌트입니다.
  - `main.js`: 애플리케이션의 진입점. Vue 인스턴스를 생성하고, 루트 컴포넌트를 마운트합니다.
  - `event-bus.js`: 로그인시, HeaderNav에 로그인 이벤트를 전달 하기 위한 스크립트 입니다.

  
<br/>

