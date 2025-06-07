# 원예 이커머스 쇼핑몰 Thymes

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
-  
- 로그인/로그아웃
- 비회원 조회 기능

### 2. 상품 판매자
- 상품 등록/수정/삭제
  - RBAC 접근 통제로 상품 판매자 아닌 이용자 접근 배제
- 판매 현황 통계 조회

### 3. 관리자 (Admin)
- 회원 관리
- 상품 및 게시물 관리
- 판매자 승인
  -판매자가 전용 회원가입 API를 이용하여 가입 요청 하면

## API

## API

### 1. MainController (페이지 렌더링)

| HTTP Method                                                                 | URI                  | 설명                                      |
|------------------------------------------------------------------------------|----------------------|-------------------------------------------|
| ![GET](https://img.shields.io/badge/GET-green)                                | `/`                  | 홈 페이지                                   |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member/login`      | 로그인 페이지                                |
| ![POST](https://img.shields.io/badge/POST-blue)                               | `/member/login`      | 로그인 처리 (폼 제출)                          |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member/signup`     | 회원가입 페이지                              |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/member/user-info`  | 회원 정보 조회 페이지                          |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shop-grid`         | 상품 목록 페이지                              |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shop-details`      | 상품 상세 페이지                              |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/shoping-cart`      | 장바구니 페이지                               |
| ![GET](https://img.shields.io/badge/GET-green)                                | `/dashboard`         | 관리자 대시보드 페이지 (판매 통계 등)               |
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

| 메인 페이지 | 로그인 | 회원가입 | 
| --- | --- | --- |
| ![메인 페이지](메인페이지.png) | ![로그인](로그인.png) | ![회원가입](회원가입.png) |

| 글작성 페이지 | 게시글 보기 | 설정 페이지 |
| --- | --- | --- |
| ![글작성 페이지](글작성페이지.png) | ![게시글 보기](게시글보기.png) | ![설정 페이지](설정페이지.png) |


