# 🚀 "ROPA" - 2조 파이널 프로젝트

<div style="display: grid; grid-template-columns: 1fr; gap: 10px; text-align: center;">
  <div>
    <img src="https://github.com/chugue/ropa-admin/assets/30003848/70af4146-2333-412b-b2fd-8eb1be45f8e7" style="width: 50%; height: auto;"/>
  </div>
</div>


# 👉 코디 아이템 중개 플랫폼
---
> ### 개발기간: 2024.04.17 ~ 2024.05.22

<br>

## 배포 주소

> #### 프론트 서버 : [https://github.com/chugue/ropa-admin](https://github.com/chugue/ropa-admin)<br>
> #### 백엔드 서버 : [http://voluntain.cs.skku.edu:2223/](http://voluntain.cs.skku.edu:2223/)<br>
<br>

# 👉 개발팀 소개

---

|                                     김성훈(팀장)                                      |                                     김주혁(프론트)                                      |                                    양승호(프론트)                                     |                                     김완준(백엔드)                                     |                                     박선규(백엔드)                                      |
|:--------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|
| <img width="160px" src="https://avatars.githubusercontent.com/u/30003848?v=4" /> | <img width="160px" src="https://avatars.githubusercontent.com/u/153582123?v=4" /> | <img width="160px" src="https://avatars.githubusercontent.com/u/97007464?v=4"/> | <img width="160px" src="https://avatars.githubusercontent.com/u/81667935?v=4" /> | <img width="160px" src="https://avatars.githubusercontent.com/u/153582360?v=4" /> |
|                       [@chugue](https://github.com/chugue)                       |                      [@kjh5848](https://github.com/kjh5848)                       |                   [@LifeIsOne](https://github.com/LifeIsOne)                    |                   [@tkffkels93](https://github.com/tkffkels93)                   |                       [@p4rksk](https://github.com/p4rksk)                        |

<br>

# 👉프로젝트 소개 (핵심로직 설명)

---
> #### 로파는 의류를 판매하는 쇼핑몰 앱입니다.
> #### 브랜드는 로파 플랫폼에 입점을 하여서 상품을 등록하고
> #### 개성있는 크리에이터들은 자신의 코디를 자랑하며 보다 활용성 있게 브랜드 제품을 홍보하고,
> #### 사용자는 맘에 드는 코디를 발견하면 해당 아이템을 바로 구매 할 수 있습니다.
> #### 코디를 통해 판매된 제품은 해당 브랜드가 크리에이터와 플랫폼에게 수수료를 지불합니다.
> #### 때문에 브랜드는 홍보비를 아낄 수 있고, 크리에이터에겐 자신의 표현과 수익을 얻을 수 있는 환경을 제공합니다.


<div style="display: grid; grid-template-columns: 1fr; gap: 10px;">
  <div>
    <img src="https://github.com/chugue/ropa-admin/assets/30003848/014532ae-1d72-4dcd-b99d-a6fc5dbccb51" alt="시연02" style="width: 100%; height: auto;"/>
  </div>
</div>
<br>

# 👉 Stacks

---

![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-007ACC?style=for-the-badge&logo=Visual%20Studio%20Code&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![BootStrap](https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Flutter](https://img.shields.io/badge/flutter-02569B?style=for-the-badge&logo=flutter&logoColor=white)
![AmazonAWS](https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Dart](https://img.shields.io/badge/dart-%230175C2.svg?style=for-the-badge&logo=dart&logoColor=white)    ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

### Communication

![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white)

<br>

# 👉 Dependencies

---

```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-mustache'
    implementation group: 'org.apache.commons', name: 'commons-lang3', version: '3.0'
    implementation group: 'com.auth0', name: 'java-jwt', version: '4.3.0'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-aop'
    implementation 'com.google.code.gson:gson:2.10.1'
    implementation group: 'org.qlrm', name: 'qlrm', version: '4.0.1'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'com.mysql:mysql-connector-j'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    implementation 'commons-codec:commons-codec:1.15'
}
```

<br>

# 👉 테이블 설계

---
![테이블 소개](https://github.com/chugue/ropa-admin/assets/30003848/275af45f-d141-44cb-a0dd-8cb45488dd8a)



<br>

# 👉 프로젝트 기능 정리

---

> ### 1단계   (🟦: WEB, 🟨:APP)

* #### 🟦 화면 구축
* #### 🟨 화면 구축
* #### 🟦 로그인 인터셉터 - 세션 기반
* #### 🟨 로그인 인터셉터 - JWT 토큰 인증
* #### 🟦 아이템 등록 구현
* #### 🟨 크리에이터 지원하기
* #### 🟦 크리에이터 지원자 조회 / 승인
* #### 🟨 크리에이터 코디 등록
* #### 🟨 크리에이터 코디 - 아이템 연결
* #### 🟨 일반사용자 아이템 상세보기 / 구매
* #### 🟦 브랜드 수수료 지급 로직 구현

> ### 2단계   (🟦: WEB, 🟨:APP)

* #### 🟦 MultiPartFile 사진 CRUD 구현
* #### 🟨 Base64 사진 CRUD 구현
* #### 🟨 API 문서 작성 GitBook 활용
* #### 🟦 검색어 검색 구현
* #### 🟦 날짜 기간 검색 구현
* #### 🟦 좋아요 기능 구현
* #### 🟦 Exception Handler 구현
* #### 🟦 AOP 구현
* #### 🟦 장바구니 CRUD 구현
* #### 🟨 ImagePicker 적용
* #### 🟨 Dio 데이터 송수신 & 바인딩
* #### 🟨 RiverPod을 활용한 MVVM패턴 적용
* #### 🟨 Splash Screen / 자동로그인 구현

> ### 3단계 (🟦: WEB, 🟨:APP)

* #### 🟦 단위 테스트 적용
* #### 🟦 RestDoc API 문서 작성
* #### 🟦 통합 테스트
* #### 🟦 CI / CD 배포
* #### 🟨 CopyWith 적용으로 코드 가독성 관리
<br>

# 👉 컨벤션 정리

---

<br>

![코드 컨벤션 정리 링크](https://www.notion.so/stephenkiim/137f2114decc444bbf308520a340238e)

<br>


# 👉 팀 블로깅  

---
![팀 블로그 링크](https://www.notion.so/stephenkiim/23dafc6dac1649318bc1bc1ece61c2fd)
