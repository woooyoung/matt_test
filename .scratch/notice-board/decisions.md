# 공지사항 게시판: 확정된 결정 (2026-10-16)

grill-with-docs 세션에서 합의한 결정이다. to-spec의 입력으로 쓴다.

## 라운드 1 결정 (2026-10-16 전부 확정)
1. **용어**: 공지(Notice), 방문자(Visitor), 관리자(Admin)
2. **공지 필드**
   - 공지는 제목, 본문, 작성일시, 수정일시를 가진다.
   - 본문은 일반 텍스트이고 줄바꿈을 유지한다.
   - 작성자와 조회수는 두지 않는다.
   - 수정된 공지는 "수정됨"을 표시한다.
3. **관리자 계정**
   - 운영: 환경변수 `ADMIN_USERNAME`과 `ADMIN_PASSWORD_HASH`(BCrypt)를 쓰고, 사용자 테이블은 두지 않는다.
   - 로컬과 테스트: 프로필 설정에 고정 계정을 둔다.
4. **인증 유지**
   - 로그인하면 JWT를 발급하고, 프론트는 `localStorage`에 보관해 Bearer 헤더로 보낸다.
   - 유효기간은 8시간이고 리프레시 토큰은 없다.
   - CSRF 방어는 끈다.
5. **권한**
   - 공지 GET API는 공개한다.
   - 공지 POST/PUT/DELETE는 `ROLE_ADMIN`만 쓸 수 있다.
   - `/api/auth/login`은 공개한다.
   - 나머지 경로는 모두 거부한다.
   - 프론트의 `/admin/*` 화면은 숨기기만 한다. 실제 권한 판단은 서버가 한다.
6. **로그인 실패 제한**
   - 같은 IP에서 5번 연속 실패하면 15분 동안 `429`를 돌려준다. 실패 기록은 메모리에 둔다.
   - 실패 메시지는 원인과 상관없이 똑같다.
7. **삭제와 동시 수정**
   - 하드 삭제이고, 삭제 전에 확인 다이얼로그를 띄운다.
   - 동시 수정은 마지막 저장이 이긴다.
   - 없는 공지를 요청하면 `404`를 돌려준다.
8. **입력 제한**
   - 제목은 trim 후 1–100자, 본문은 trim 후 1–10,000자다.
   - 로그인은 아이디 최대 50자, 비밀번호 최대 100자다.
   - 요청 본문은 64KB까지 받는다.
   - 최종 검증은 서버가 한다.
9. **오류**
   - 형식은 RFC 9457 ProblemDetail이다.
   - 상태 코드는 400(필드 오류 목록 포함), 401, 403, 404, 429, 500을 쓴다.
   - 401을 받으면 프론트가 토큰을 지우고 `/admin/login?expired=1`로 이동한다.
10. **페이지네이션**
    - 정렬은 작성일시 내림차순이고, 같으면 id 내림차순이다.
    - API는 `?page=0`부터 세고 `size`는 10으로 고정한다.
    - 응답은 `{content, page:{number,size,totalElements,totalPages}}` 모양이다.
    - 화면 URL은 `?page=1`부터 센다.
    - 이동 UI는 shadcn Pagination으로 이전, 번호 최대 5개, 다음을 보여준다.
    - 숫자가 아니거나 1보다 작은 페이지는 `?page=1`로 바꾼다. 전체 페이지 수보다 크면 "이 페이지에는 공지가 없습니다"와 1페이지 링크를 보여준다. 페이지가 1개뿐이면 이동 UI를 숨긴다. "목록으로"는 보던 페이지로 돌아간다.
11. **시간**: 저장은 UTC(`timestamptz`)로 하고, 화면은 Asia/Seoul 기준으로 보여준다.
12. **폴더와 실행 경로**
    - 루트에는 공용 빌드 도구가 없다.
    - `backend/`는 Gradle KTS와 자체 wrapper로 동작한다. `bootRun`은 8080 포트에서 local 프로필로 실행하고, H2 파일은 `backend/data/`에 두며 gitignore한다.
    - `frontend/`는 npm으로 동작한다. dev 서버는 5173 포트이고 `/api`를 8080으로 프록시한다.
    - 배포 환경의 API 주소는 `VITE_API_BASE_URL`로 넣는다.
13. **도메인과 CORS**
    - 커스텀 도메인은 쓰지 않는다.
    - CORS는 Pages 운영 URL 하나만 허용하고, 값은 `CORS_ALLOWED_ORIGINS`로 넣는다.
    - PR 미리보기 배포는 만들지 않는다.
14. **스키마**
    - 모든 환경에서 Flyway를 쓰고, JPA는 `ddl-auto=validate`로 둔다.
    - H2는 `MODE=PostgreSQL`로 실행한다.

## 공식 문서 확인 사실 (2026-10-16)
- **Spring Boot**: 최신 GA는 4.1.1(2026-08-20)이다.
  - Java 17–26을 지원한다.
  - Gradle 8.14 이상 또는 9.x가 필요하다.
  - Kotlin은 2.2 이상이 필요하고, Boot가 관리하는 버전은 2.3.21이다.
  - 함께 관리되는 버전: Spring Security 7.1, Hibernate 7.4, H2 2.4.240, Flyway 12.4(PostgreSQL은 `flyway-database-postgresql` 모듈이 별도로 필요하다).
- **Kotlin**
  - 최신 안정판은 2.4.21이다.
  - 2.3.20부터 `plugin.jpa`가 엔티티 all-open까지 적용한다. 그래서 `plugin.spring`과 `plugin.jpa`만 있으면 된다.
- **Java**: 최신 LTS는 25다. JDK 27은 non-LTS이고 Boot 지원 범위 밖이다.
- **Railway**
  - 빌더는 Railpack이다. `gradlew`를 보고 Java 프로젝트로 인식하고, 기본 JDK는 21이며 `RAILPACK_JDK_VERSION`으로 바꿀 수 있다.
  - Root Directory로 모노레포를 지원한다.
  - Postgres 변수 `DATABASE_URL`은 `postgresql://` 형식이라 JDBC URL이 아니다. 대신 `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`를 쓸 수 있다.
  - `PORT`를 주입한다.
  - 헬스체크는 배포 시점에만 동작하고 기본 타임아웃은 300s다.
  - 자동배포는 GitHub 연동에 Wait for CI 옵션을 붙이는 방식과, Actions에서 `railway up --ci`(`RAILWAY_TOKEN` 프로젝트 토큰)를 쓰는 방식이 있다.
  - config-as-code는 폐지 예정이라는 정보가 있으나 검색 요약으로만 확인했다.
- **Cloudflare**
  - Pages 문서에 "새 프로젝트는 Workers로 시작하라"는 배너가 있다. Pages는 여전히 동작한다.
  - Actions에서는 `cloudflare/wrangler-action@v4`로 `pages deploy`를 실행한다. 시크릿 `CLOUDFLARE_API_TOKEN`과 `CLOUDFLARE_ACCOUNT_ID`가 필요하다.
  - Pages는 404.html이 없으면 SPA 폴백을 자동으로 한다.
- **프론트**
  - Vite 8은 Node 20.19 이상 또는 22.12 이상이 필요하다.
  - React는 19.3이다.
  - shadcn은 Vite에서 Tailwind v4(`@tailwindcss/vite`)를 쓰고, 설치는 `shadcn@latest init`으로 한다.
  - Node LTS는 24다.
- **Spring Data**
  - Page 직렬화는 `spring.data.web.pageable.serialization-mode=via-dto`로 설정한다.
  - 이렇게 하면 응답이 `{content, page:{...}}` 모양이 된다. 라운드 1 Q10에서 정한 형식과 같다.
- **H2**: 권장 URL 옵션은 `MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH`다.

## 라운드 2 결정 (2026-10-16 전부 확정)
15. **버전**
    - 백엔드: Spring Boot 4.1.1, Kotlin 2.3.21(Boot 관리 버전), Java 25(toolchain, `RAILPACK_JDK_VERSION=25`), Gradle 9 KTS
    - 프론트: Node 24(`.nvmrc`), Vite 8, React 19, Tailwind v4, shadcn, npm
16. **프론트 배포**
    - Cloudflare Pages에 `wrangler-action@v4`로 `pages deploy frontend/dist`를 실행한다.
    - `404.html`은 두지 않는다. 그래서 SPA 폴백이 자동으로 동작한다.
17. **백엔드 배포**
    - Railway 자동배포는 끈다. Actions가 `railway up --service backend --ci`로 배포하고, 토큰은 `RAILWAY_TOKEN`(운영 환경 프로젝트 토큰)이다.
    - Railway 서비스 설정
      - Root Directory는 `/backend`이고 빌더는 Railpack이다.
      - 헬스체크 경로는 `/actuator/health`다.
      - `server.port=${PORT:8080}`으로 실행한다.
    - Railway 대시보드 설정은 README에 문서로 남긴다.
18. **DB 전환**
    - 프로필은 `local`(H2 파일 `./data/notice`), `test`(H2 인메모리), `prod`(PostgreSQL)다. H2 옵션은 위 사실 목록과 같다.
    - prod의 JDBC URL은 PG* 참조 변수로 조립한다.
    - 프로필 지정이 없으면 앱이 시작에 실패하게 한다. `bootRun`과 test는 프로필을 자동으로 켠다.
    - 마이그레이션 SQL은 두 DB가 공통으로 이해하는 문법만 쓴다. IDENTITY, VARCHAR(100), VARCHAR(10000), TIMESTAMP WITH TIME ZONE, `(created_at DESC, id DESC)` 인덱스를 쓴다.
    - Flyway PostgreSQL 모듈과 PostgreSQL 드라이버는 runtimeOnly 의존성이다.
19. **PostgreSQL 검증**
    - CI에 Postgres를 따로 두지 않는다. Flyway가 실패하면 헬스체크가 실패하고, Railway는 새 배포로 전환하지 않는다.
    - 배포된 마이그레이션 파일은 수정하지 않고, 바꿀 일이 있으면 새 버전 파일을 추가한다.
20. **CI/CD와 완료 기준**: 워크플로는 `ci-cd.yml` 하나다.
    - PR에서는 바뀐 폴더만 검사하고 배포하지 않는다.
    - `main`에 push하면 배포한다.
      - 백엔드: 테스트 → 배포 → 스모크 체크(health `UP`, `/api/notices` 200, 30초 간격 최대 10회 재시도)
      - 프론트: lint, tsc, build → 배포 → 스모크 체크(`/` 200, `/notices/1` 200)
      - 두 폴더가 모두 바뀌었으면 백엔드 배포가 성공한 뒤에 프론트를 배포한다.
    - 모든 job과 스모크 체크가 초록이면 완료다. 자동 롤백은 하지 않는다.
    - Actuator는 health만 노출하고 세부 정보는 숨긴다.
    - 변수 위치
      - Railway 변수: `JWT_SECRET`, `ADMIN_*`, `CORS_ALLOWED_ORIGINS`
      - GitHub 저장소 변수: `VITE_API_BASE_URL`
      - GitHub 시크릿: `RAILWAY_TOKEN`, `CLOUDFLARE_*`

