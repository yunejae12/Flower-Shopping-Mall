# 원예 버티컬 오픈마켓

## 개발 동기
원예 활동에 관심이 있어 관련 쇼핑몰 사이트를 자주 이용하던 중,  
사용자 입장에서 아쉬웠던 점들을 발견하게 되었습니다.  
이러한 불편함을 개선해보고자 직접 쇼핑몰 웹사이트를 제작하게 되었습니다.
## 기술 스택

![Spring](https://img.shields.io/badge/Spring-6DB33F?logo=spring&logoColor=white)  ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white)  ![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=black) ![HTML5](https://img.shields.io/badge/HTML5-E34F26?logo=html5&logoColor=white) ![SCSS](https://img.shields.io/badge/SCSS-CC6699?logo=sass&logoColor=white)  

## 작업 내용 (이용자 중심)

### 1. 소비자
- 회원가입 기능
- 상품 조회 및 장바구니
- 로그인/로그아웃
- 비회원 조회 기능
### 2. 상품 판매자
- 상품 등록/수정/삭제
  - RBAC 접근 통제로 상품 판매자 아닌 이용자 접근 배제

### 3. 관리자 (Admin)
- 회원 관리
- 상품 및 게시물 관리

## API

### 1. MainController (페이지 렌더링)

| HTTP Method                                                                 | URI                  | 설명                                      |
|------------------------------------------------------------------------------|----------------------|-------------------------------------------|
| ![GET](https://img.shields.io/badge/GET-green)                                | `/`                  | 메인 페이지                                   |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member/login`      | 로그인 페이지                                |
| ![POST](https://img.shields.io/badge/POST-blue)                               | `/member/login`      | 로그인 처리 (폼 제출)                          |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member/signup`     | 회원가입 페이지                              |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member/user-info`  | 회원 정보 조회 페이지                          |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shop-grid`         | 상품 목록 페이지                              |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shop-details`      | 상품 상세 페이지                              |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shoping-cart`      | 장바구니 페이지                               |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/dashboard`         | 관리자 대시보드 페이지                |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/seller`            | 판매자 대시보드 페이지                           |

### 2. MemberController (회원 관리 API)

| HTTP Method                                                                 | URI                          | 설명                                           |
|------------------------------------------------------------------------------|------------------------------|------------------------------------------------|
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member`                    | 모든 회원 조회                                    |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member/{memberId}`         | 특정 회원 조회 (`memberId` 기준)                    |
| ![POST](https://img.shields.io/badge/POST-blue)                               | `/member`                    | 회원 생성 (회원가입 처리)                             |
| ![PUT](https://img.shields.io/badge/PUT-orange)                                | `/member/{id}`               | 회원 정보 수정 (`id` 기준)                          |
| ![DELETE](https://img.shields.io/badge/DELETE-red)                             | `/member/{id}`               | 회원 삭제 (`id` 기준)                               |
| ![POST](https://img.shields.io/badge/POST-blue)                               | `/member/id-check`           | 아이디 중복 확인 (`request` 바디에 “username” 필드)       |

### 3. ShopGridController (상품 조회 API)

| HTTP Method                                                                 | URI                         | 설명                                         |
|------------------------------------------------------------------------------|-----------------------------|----------------------------------------------|
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shop-grid`                | 전체 상품 목록 조회                             |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shop-grid/{id}`           | 특정 상품 상세 조회 (`id` 기준)                    |



## 📺 화면 구성

| 메인 페이지 | 회원가입 | 
| --- | --- |
| ![메인페이지](/readme_assets/index.png) |![회원가입](/readme_assets/signup.png)

| 판매 목록 페이지 | 물건 상세화면 페이지 | 
| --- | --- |
| ![판매목록페이지](/readme_assets/item_catalog.png) | ![물건 상세화면 페이지](/readme_assets/item_details.png)

## 🛠️문제해결
### 1.상품 게시물 조회수 중복 방지
- User-Agent 기반 봇 탐지 필터 추가하여 봇은 조회수 증가를 발생시키지 않음
- 방문시 쿠키에 방문한 게시글 id와 방문시각을 기록하여 30분이 지나면 재갱신하거나 만료됨
- 조회수 증가 카운팅은 동시성 유지를 위해 벌크연산 수행
  
### 2.회원가입 시 실시간 유효성 검사 도입으로 사용자 편의성 향상  
기존 쇼핑몰은 회원가입시 서버사이드 렌더링으로 서버에서 데이터 유효성 검사를 받아 인증결과를 받기 전까지  
회원가입 양식을 맞게 입력했는지 알 수 없었습니다.  
이를 해결하기 위해 서버에서는 @Valid 어노테이션과 유효성 검사 라이브러리를 활용하여 입력값 검증을 구성하고  
클라이언트 측에서는 JavaScript를 이용한 정규식 검증을 이용하여 입력 즉시 피드백이 가능하도록 UX를 개선하였습니다.  
### 3.상품의 품종 혼용 문제
소비자 입장에서는 버티컬 커머스 플랫폼이 특정 상품군에 특화되어 전문적으로 제품을 판매해 주기를 기대합니다.  
그러나 식물 판매 업계에서는 상품의 품종명을 정할 때 다른 식물의 이름을 차용하는 관행이 있어,  
검색 시 원하지 않는 유형의 제품까지 결과에 나타나는 경우가 발생합니다.  
이런 문제를 해결하기 위해 판매자가 상품의 카테고리를 설정하여 소비자가 카테고리 별로 세부 검색을 할 수 있도록 구현했습니다.  


