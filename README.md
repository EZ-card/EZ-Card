# 나만을 위한 카드 추천 챗봇 서비스, EZ:card
<p align="center"><img width="1470" alt="로고" src="https://github.com/EZ-card/EZ-Card/assets/77954741/6d645b12-4c87-4748-8509-415159cad6c9"></p>

# 프로젝트 소개
## 진행 기간
23.08.01 - 23.08.31 (4주) <br>

## 팀원 소개

**[이명준](https://github.com/myje0319) (Front-end, Team-Leader)**

- 메인 페이지 제작
- 채팅 페이지 제작
- 네비게이션 메뉴바 제작

**[이건우](https://github.com/gunnu3226) (Back-end, Front-end)**

- 챗봇 기능 구현
- 위시 카드 기능 구현
- 카드 상세페이지 기능 구현
- 카드 비교 기능 구현
- 카드 비교 페이지 제작

**[김민지](https://github.com/ghi512) (Back-end, Front-end)**

- 회원가입 기능 구현
- 로그인/로그아웃 기능 구현
- 카드 전체 목록 기능 구현
- 카드목록 페이지 제작
- 배포 - AWS RDS, AWS EC2

**[안정민](https://github.com/jungmin0514) (Front-end)**

- 전체 UI 디자인 
- 회원가입 페이지 제작
- 위시 카드 페이지 제작

**김다은 (기획 및 자료조사)** 

- 카드 정보 수집
- 카드 정보 가공
<br>

## 기획 배경

### 시장 현황 분석
![시장 현황 분석](https://github.com/EZ-card/EZ-Card/assets/77954741/b2149d4e-cf4e-4da4-99b0-100d474cdf88)

### 기존 서비스 분석
![기존 서비스 분석](https://github.com/EZ-card/EZ-Card/assets/77954741/c45326ee-f8d6-4016-af75-1984dc76e27a)

### EZ:card는!
GPT를 연동한 챗봇 서비스인 EZ:bot은 사용자의 상황에 맞는 카드를 추천해주고,<br>
이를 통해 사용자는 쉽고 편하게 본인에게 맞는 카드를 확인할 수 있다.

<br><br>

## 화면 설계 (레이아웃)
<img width="823" alt="화면 레이아웃" src="https://github.com/EZ-card/EZ-Card/assets/77954741/25eb26ba-c4e9-4e13-a91d-b2b3dc904350">

## API 명세서
[API 명세서 보러가기](https://www.notion.so/04e0aa1e7c1a47c8a774a484014f399e?pvs=21)
![api 명세서](https://github.com/EZ-card/EZ-Card/assets/77954741/3ea1f126-edb4-47b1-b122-78fd243939c4)

## ERD
[ERD 보러가기](https://www.erdcloud.com/p/JafoPJhhR2SBF6XpS)
![ERD](https://github.com/EZ-card/EZ-Card/assets/77954741/6813415f-fbac-47a0-a5bc-17392b24cab4)

## 기술 스택
> #### UI
<!-- figma -->
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> 

> #### 활용 언어
<!-- java, html, css, javascript-->
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=openjdk&logoColor=white" /> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white" /> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white" /> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" />

> #### 활용 프레임워크
<!-- react, springboot -->
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black" /> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />


> #### 데이터베이스
<!-- mysql -->
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />

> #### 배포
<!-- ec2, rds -->
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white" /> <img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white" />

## Server-Architecture
<img width="779" alt="서버" src="https://github.com/EZ-card/EZ-Card/assets/77954741/a535bc8b-7320-4d52-80ad-9fe513a139c9">

<br>

# 기능 소개
<img width="840" alt="기능" src="https://github.com/EZ-card/EZ-Card/assets/77954741/b8c0dbf1-f30b-4b39-aa39-d2d2b8e987b4">

### 1. 회원가입 및 로그인
- 이메일, 비밀번호, 닉네임 입력
- 개인 맞춤 카드 추천을 위한 성별, 직업, 나이대 관련 정보 수집


### 2. 메인 페이지
- 맞춤 카드 상담이 가능한 EZ:bot 페이지로 이동 가능한 배너
- 회원가입 시 입력한 필수 정보 중 나이대를 기반으로 한 맞춤 카드 추천
- 오늘의 TOP 3
    - 사용자들이 많이 찜한 카드와 실제 카드사로 이동해서 발급받은 수를 기준으로 TOP3 선정

### 3. EZ:bot (챗봇)
- 사용자의 자유로운 채팅 입력
- GPT-3.5-turbo 모델이 사용자의 입력에 적합한 카드를 찾아 답변의 형태로 출력
- GPT가 선택한 카드의 정보를 DB에서 찾아 화면에 출력
    - 해당 채팅을 클릭하면 카드 상세페이지로 이동 가능

### 4. 카드 상세 페이지
- 카드의 자세한 혜택 내용 확인 가능
- 회원은 관심 있는 카드에 하트를 눌러 찜 목록에 추가
- '신청하러 가기' 버튼을 통해, 해당 카드사의 카드 발급 페이지로 이동

### 5. 찜 목록
- 회원은 상세 페이지에서 찜한 카드 확인 가능
- 하트 버튼을 다시 누르면 찜 목록에서 삭제

### 6. 카드 비교
- 플러스 버튼을 눌러서 비교하고 싶은 2개의 카드 선택
- 두 카드의 정보를 한 눈에 비교 가능

### 7. 카드 목록
- 전체 카드 목록 확인
- 카드 종류별(신용, 체크, 복지)로 확인 가능
- 원하는 카드를 클릭하면 상세 페이지로 이동

