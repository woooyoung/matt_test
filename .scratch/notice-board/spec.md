# 고등학교 공지사항 게시판

Status: ready-for-agent

## Problem Statement

학교는 학생과 학부모에게 알릴 소식을 한곳에 모아 둘 곳이 없다. 방문자는 지금 어떤 공지가 있는지 최신순으로 훑어보고, 필요한 공지를 열어 내용을 읽을 수 있어야 한다. 공지를 올리고 고치고 지우는 일은 관리자 한 명만 할 수 있어야 하고, 아무나 공지를 바꿀 수 있어서는 안 된다.

## Solution

누구나 로그인 없이 볼 수 있는 공지 게시판을 웹에 연다.
- 방문자는 최신순으로 정렬된 공지 목록을 10개씩 페이지를 넘기며 보고, 공지를 눌러 상세 내용을 읽는다.
- 관리자는 숨겨진 로그인 화면으로 들어와 공지를 등록·수정·삭제한다.
- 로그인은 8시간 유지되고, 만료되면 다시 로그인하라고 안내한다.
- 잘못된 입력, 없는 공지, 서버 오류는 방문자와 관리자가 이해할 수 있는 문구로 알린다.
- `main`에 반영된 변경은 GitHub Actions가 자동으로 검사·배포하고, 배포 후 실제 운영 주소를 확인해야 배포가 끝난 것으로 본다.

## User Stories

### 방문자: 목록

1. As a 방문자, I want 공지 목록을 로그인 없이 보기, so that 학교 소식을 바로 확인할 수 있다
2. As a 방문자, I want 최신 공지가 맨 위에 오기, so that 새 소식을 먼저 본다
3. As a 방문자, I want 같은 시각에 등록된 공지도 항상 같은 순서로 보기, so that 새로고침할 때마다 순서가 바뀌지 않는다
4. As a 방문자, I want 공지를 수정해도 목록 순서가 바뀌지 않기, so that 오래된 공지가 갑자기 맨 위로 올라와 혼란스럽지 않다
5. As a 방문자, I want 목록에서 각 공지의 제목과 작성일(날짜)을 보기, so that 열어 보지 않고도 무엇을 읽을지 고를 수 있다
6. As a 방문자, I want 목록을 한 페이지에 10개씩 보기, so that 화면이 너무 길어지지 않는다
7. As a 방문자, I want 이전·다음 버튼과 페이지 번호(최대 5개)로 페이지를 이동하기, so that 원하는 페이지로 빨리 간다
8. As a 방문자, I want 지금 보고 있는 페이지 번호가 강조되기, so that 내가 어디에 있는지 안다
9. As a 방문자, I want 첫 페이지에서는 "이전", 마지막 페이지에서는 "다음"이 비활성화되기, so that 없는 페이지로 가려다 헤매지 않는다
10. As a 방문자, I want 공지가 10개 이하일 때 페이지 이동 UI가 보이지 않기, so that 쓸모없는 버튼에 신경 쓰지 않는다
11. As a 방문자, I want 페이지 번호가 주소(`?page=3`)에 나타나기, so that 새로고침하거나 뒤로 가거나 링크를 공유해도 같은 페이지를 본다
12. As a 방문자, I want 주소의 페이지 값이 숫자가 아니거나 1보다 작으면 1페이지로 바뀌기, so that 잘못된 링크로 들어와도 목록을 본다
13. As a 방문자, I want 마지막 페이지보다 큰 페이지로 들어오면 "이 페이지에는 공지가 없습니다"와 1페이지 링크를 보기, so that 빈 화면에서 길을 잃지 않는다
14. As a 방문자, I want 공지가 하나도 없을 때 "등록된 공지가 없습니다"를 보기, so that 화면이 고장 난 것이 아님을 안다

### 방문자: 상세

15. As a 방문자, I want 목록에서 공지를 눌러 상세 화면을 보기, so that 전체 내용을 읽는다
16. As a 방문자, I want 상세 화면에서 제목, 본문, 작성일시(`2026.10.16 14:30`)를 보기, so that 언제 올라온 소식인지 안다
17. As a 방문자, I want 본문의 줄바꿈이 그대로 보이기, so that 관리자가 나눈 문단대로 읽는다
18. As a 방문자, I want 본문에 HTML이나 기호가 들어 있어도 글자 그대로 보이기, so that 공지가 화면을 깨뜨리거나 이상한 동작을 하지 않는다
19. As a 방문자, I want 수정된 공지에 "수정됨 · 수정일시"가 보이기, so that 내용이 바뀌었음을 안다
20. As a 방문자, I want 모든 날짜와 시각이 한국 시간으로 보이기, so that 시차 때문에 날짜를 잘못 읽지 않는다
21. As a 방문자, I want "목록으로"를 누르면 원래 보던 페이지로 돌아가기, so that 다시 페이지를 찾지 않아도 된다
22. As a 방문자, I want 상세 주소(`/notices/{id}`)를 직접 열거나 새로고침해도 화면이 뜨기, so that 공지 링크를 공유할 수 있다
23. As a 방문자, I want 없는 공지 주소로 들어가면 "존재하지 않는 공지입니다"와 목록 링크를 보기, so that 삭제된 공지 링크로 와도 헤매지 않는다
24. As a 방문자, I want 알 수 없는 주소로 들어가면 404 화면을 보기, so that 잘못된 주소임을 안다
25. As a 방문자, I want 서버나 네트워크 오류 때 일반 안내 문구와 "다시 시도" 버튼을 보기, so that 내부 오류 내용 대신 할 수 있는 행동을 안다
26. As a 방문자, I want 공개 화면에서 관리자 버튼이나 링크를 보지 않기, so that 내게 필요 없는 기능에 혼란스럽지 않다

### 관리자: 로그인과 인증 유지

27. As a 관리자, I want `/admin/login`에서 아이디와 비밀번호로 로그인하기, so that 공지를 관리할 수 있다
28. As a 관리자, I want 로그인한 상태로 공지 목록과 상세를 보면 "새 공지", "수정", "삭제" 버튼이 보이기, so that 방문자 화면에서 바로 관리한다
29. As a 관리자, I want 로그인이 8시간 동안 유지되기, so that 하루 업무 중 매번 다시 로그인하지 않는다
30. As a 관리자, I want 브라우저를 닫았다 열어도 8시간 안이면 로그인이 유지되기, so that 잠깐 창을 닫아도 다시 로그인하지 않는다
31. As a 관리자, I want 로그인이 만료된 뒤 작업하면 로그인 화면으로 이동하고 "다시 로그인해 주세요"를 보기, so that 왜 작업이 안 됐는지 안다
32. As a 관리자, I want 로그아웃하기, so that 공용 컴퓨터에서 다른 사람이 공지를 바꾸지 못한다
33. As a 관리자, I want 아이디나 비밀번호가 틀리면 어느 쪽이 틀렸는지 알려주지 않는 같은 문구를 보기, so that 공격자가 아이디를 알아내지 못한다
34. As a 관리자, I want 같은 곳에서 로그인에 5번 연속 실패하면 15분 동안 로그인이 막히기, so that 비밀번호를 대입하는 공격을 늦춘다
35. As a 관리자, I want 로그인이 막혔을 때 그 사실을 알리는 문구를 보기, so that 잠시 뒤에 다시 시도하면 된다는 것을 안다
36. As a 관리자, I want 아이디 50자, 비밀번호 100자를 넘는 입력이 거부되기, so that 비정상적인 요청이 서버를 괴롭히지 못한다

### 관리자: 등록·수정·삭제

37. As a 관리자, I want 제목과 본문을 입력해 공지를 등록하기, so that 방문자에게 소식을 알린다
38. As a 관리자, I want 등록이 끝나면 새 공지의 상세 화면으로 이동하기, so that 올라간 모습을 바로 확인한다
39. As a 관리자, I want 기존 공지의 제목과 본문을 수정하기, so that 잘못된 내용을 고친다
40. As a 관리자, I want 수정 화면이 기존 내용으로 채워져 있기, so that 처음부터 다시 쓰지 않는다
41. As a 관리자, I want 공지를 삭제하기 전에 확인 다이얼로그를 보기, so that 실수로 지우지 않는다
42. As a 관리자, I want 삭제가 끝나면 목록으로 이동하기, so that 공지가 사라졌음을 확인한다
43. As a 관리자, I want 제목은 1–100자, 본문은 1–10,000자만 허용되기, so that 목록과 상세 화면이 깨지지 않는다
44. As a 관리자, I want 앞뒤 공백은 잘려서 저장되고 공백만 입력하면 거부되기, so that 빈 공지가 올라가지 않는다
45. As a 관리자, I want 입력 중에 글자 수 제한을 바로 안내받기, so that 저장 버튼을 누르기 전에 고친다
46. As a 관리자, I want 서버가 입력을 거부하면 해당 입력 칸 아래에 이유가 보이기, so that 무엇을 고칠지 안다
47. As a 관리자, I want 이미 삭제된 공지를 수정·삭제하려 하면 "존재하지 않는 공지입니다"를 보기, so that 다른 창에서 지운 공지임을 안다
48. As a 관리자, I want 두 창에서 같은 공지를 고치면 마지막에 저장한 내용이 남기, so that 동작을 예측할 수 있다

### 보안

49. As a 방문자, I want 로그인하지 않은 사람의 등록·수정·삭제 요청이 서버에서 거부되기, so that 화면을 우회해도 공지를 바꿀 수 없다
50. As a 관리자, I want 허용된 운영 프론트 주소에서 온 브라우저 요청만 API를 호출할 수 있기, so that 다른 사이트가 내 브라우저로 API를 부르지 못한다
51. As a 관리자, I want 관리자 비밀번호가 저장소나 DB에 평문으로 남지 않기, so that 유출되어도 비밀번호가 드러나지 않는다

### 개발자: 실행·배포

52. As a 개발자, I want `backend/`에서 한 명령으로 로컬 백엔드를 띄우면 로컬 H2 파일 DB와 테스트용 관리자 계정이 준비되기, so that 별도 설정 없이 개발을 시작한다
53. As a 개발자, I want `frontend/`에서 한 명령으로 개발 서버를 띄우면 API 요청이 로컬 백엔드로 전달되기, so that CORS 설정 없이 로컬에서 화면을 개발한다
54. As a 개발자, I want 실행 프로필을 지정하지 않으면 백엔드가 시작을 거부하기, so that 운영에서 설정을 빠뜨렸을 때 데이터가 조용히 H2로 사라지지 않는다
55. As a 개발자, I want 로컬, 테스트, 운영 DB가 모두 같은 마이그레이션으로 스키마를 만들기, so that 환경마다 스키마가 달라지지 않는다
56. As a 개발자, I want PR을 올리면 바뀐 폴더의 테스트·린트·타입 검사·빌드가 자동으로 돌기, so that 깨진 코드가 `main`에 들어가지 않는다
57. As a 개발자, I want `main`에 반영된 백엔드 변경이 테스트 통과 후 Railway에 자동 배포되기, so that 수동 배포를 하지 않는다
58. As a 개발자, I want `main`에 반영된 프론트 변경이 검사 통과 후 Cloudflare Pages에 자동 배포되기, so that 수동 배포를 하지 않는다
59. As a 개발자, I want 백엔드와 프론트가 함께 바뀌면 백엔드 배포가 성공한 뒤에 프론트가 배포되기, so that 새 화면이 아직 없는 API를 부르지 않는다
60. As a 개발자, I want 배포 후 운영 주소에 스모크 체크가 돌고 모두 통과해야 워크플로가 초록이 되기, so that "배포 완료"가 실제로 서비스가 동작한다는 뜻이 된다
61. As a 개발자, I want 운영 마이그레이션이 실패하면 새 버전으로 전환되지 않고 이전 버전이 계속 서비스되기, so that 잘못된 배포가 서비스를 멈추지 않는다

## Implementation Decisions

결정 원본은 `.scratch/notice-board/decisions.md`, 되돌리기 어려운 결정은 `docs/adr/0001`–`0005`에 있다. 아래는 구현에 필요한 내용만 요약한다.

### 저장소 구조와 런타임

- `backend/`와 `frontend/`는 서로 참조하지 않는 독립 프로젝트이고, 계약은 HTTP API뿐이다(ADR 0001).
- 백엔드: Spring Boot 4.1.1, Kotlin 2.3.21(Boot 관리 버전, `plugin.spring` + `plugin.jpa`), Java 25 toolchain, Gradle 9 Kotlin DSL(자체 wrapper), Spring Security 7, JPA, Flyway, Actuator.
- 프론트: Node 24(`.nvmrc`), npm, Vite 8, React 19, TypeScript, Tailwind v4, shadcn/ui, React Router.

### 백엔드 모듈

- **공지 모듈**: 공지 엔티티, 저장소, 서비스, REST 컨트롤러. 공지는 id, 제목, 본문, 작성일시, 수정일시를 가진다. 작성자·조회수는 없다.
- **인증 모듈**: 로그인 엔드포인트, JWT 발급·검증, 환경변수 기반 단일 관리자(ADR 0003), 로그인 실패 제한기.
- **보안 설정**: 경로별 권한, CORS, CSRF 끔, 세션 없음(stateless), Bearer 토큰 필터.
- **오류 처리**: 모든 오류를 RFC 9457 ProblemDetail로 바꾸는 전역 처리기.
- **시계**: 현재 시각은 주입된 시계 하나에서만 얻는다. 작성·수정일시, 토큰 만료, 로그인 차단 시간이 모두 이 시계를 쓴다. 테스트는 이 시계를 바꿔 끼운다.

### API 계약

| 메서드와 경로 | 권한 | 성공 응답 |
|---|---|---|
| `GET /api/notices?page={0부터}` | 공개 | 200, `{content:[{id,title,createdAt,updatedAt}], page:{number,size,totalElements,totalPages}}` |
| `GET /api/notices/{id}` | 공개 | 200, `{id,title,body,createdAt,updatedAt}` |
| `POST /api/notices` | 관리자 | 201, 상세와 같은 모양, `Location` 헤더 |
| `PUT /api/notices/{id}` | 관리자 | 200, 상세와 같은 모양 |
| `DELETE /api/notices/{id}` | 관리자 | 204 |
| `POST /api/auth/login` | 공개 | 200, `{accessToken, expiresAt}` |
| `GET /actuator/health` | 공개 | 200, `{status:"UP"}`(세부 정보 없음) |

- 그 밖의 경로는 모두 거부한다.
- 목록은 `size`를 10으로 고정하고, 클라이언트가 보낸 `size`와 정렬 파라미터는 무시한다. 정렬은 작성일시 내림차순, 같으면 id 내림차순이다.
- 페이지 응답 모양은 Spring Data의 `via-dto` 직렬화 모드로 만든다.
- 시각은 모두 UTC ISO-8601 문자열로 주고받는다. 화면 표시는 프론트가 Asia/Seoul로 바꾼다.
- 등록·수정 요청 본문은 `{title, body}`다.
- 수정 시 `updatedAt`만 바뀌고 `createdAt`은 그대로다. 등록 직후에는 `updatedAt`이 `createdAt`과 같고, 프론트는 두 값이 다를 때만 "수정됨"을 보여준다.

### 입력 제한

- 제목: 앞뒤 공백을 뺀 뒤 1–100자. 본문: 앞뒤 공백을 뺀 뒤 1–10,000자. 서버는 잘라낸 값을 저장한다.
- 로그인: 아이디 최대 50자, 비밀번호 최대 100자, 둘 다 필수.
- 요청 본문은 64KB를 넘으면 거부한다.
- 프론트는 같은 규칙으로 미리 안내하지만 최종 판단은 서버가 한다.

### 오류 계약

| 상태 | 언제 | 응답 내용 |
|---|---|---|
| 400 | 입력 검증 실패, 잘못된 JSON | ProblemDetail + `errors:[{field, message}]` |
| 401 | 토큰 없음·위조·만료, 로그인 실패 | ProblemDetail. 로그인 실패 문구는 원인과 상관없이 같다 |
| 403 | 인증됐지만 권한 없음 | ProblemDetail |
| 404 | 없는 공지, 없는 경로 | ProblemDetail |
| 413 | 요청 본문 64KB 초과 | ProblemDetail |
| 429 | 로그인 차단 중 | ProblemDetail + `Retry-After` 헤더 |
| 500 | 그 밖의 오류 | ProblemDetail, 내부 정보(스택 트레이스, SQL) 없음 |

### 인증

- 로그인 성공 시 서버 비밀키(`JWT_SECRET`)로 서명한 JWT를 발급한다. 유효기간 8시간, 리프레시 토큰 없음(ADR 0002).
- 프론트는 토큰을 `localStorage`에 보관하고 `Authorization: Bearer`로 보낸다. 로그아웃은 토큰 삭제다. 서버 쪽 무효화는 없다.
- 관리자는 `ADMIN_USERNAME`과 `ADMIN_PASSWORD_HASH`(BCrypt)로 정의한다. 로컬·테스트 프로필은 설정 파일의 고정 계정을 쓴다.
- 로그인 실패 제한: 같은 IP에서 연속 5번 실패하면 15분 동안 429. 성공하면 그 IP의 실패 횟수를 0으로 되돌린다. 기록은 메모리에 두고 재시작하면 사라진다(인스턴스 1개 전제).
- Railway 뒤에서 실제 클라이언트 IP를 얻으려면 프록시가 붙이는 전달 헤더를 신뢰하도록 설정해야 한다.

### DB와 스키마

- 프로필: `local`(H2 파일, `backend/data/`, gitignore), `test`(H2 인메모리), `prod`(PostgreSQL). 프로필이 없으면 시작에 실패한다(ADR 0005).
- H2 URL 옵션: `MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH`.
- 운영 JDBC URL은 Railway의 `PGHOST`, `PGPORT`, `PGDATABASE` 참조 변수로 조립하고, 사용자와 비밀번호는 `PGUSER`, `PGPASSWORD`를 참조한다.
- Flyway가 스키마를 만들고, JPA는 `ddl-auto=validate`다. PostgreSQL용 Flyway 모듈과 드라이버는 runtimeOnly다.
- 첫 마이그레이션은 공지 테이블 하나를 만든다.
  - id: `BIGINT GENERATED BY DEFAULT AS IDENTITY` 기본 키
  - title: `VARCHAR(100) NOT NULL`
  - body: `VARCHAR(10000) NOT NULL`
  - created_at, updated_at: `TIMESTAMP WITH TIME ZONE NOT NULL`
  - 인덱스: `(created_at DESC, id DESC)`
- 배포된 마이그레이션 파일은 수정하지 않는다.

### 프론트엔드

- 라우트: `/`(목록, `?page`는 1부터), `/notices/{id}`(상세), `/admin/login`, `/admin/notices/new`, `/admin/notices/{id}/edit`, 그 밖은 404 화면.
- **API 클라이언트**: 모든 요청이 지나는 한 곳. 베이스 URL(`VITE_API_BASE_URL`, 로컬은 비워 두고 Vite 프록시 사용)과 Bearer 헤더를 붙이고, ProblemDetail을 해석한다. 401을 받으면 토큰을 지우고 `/admin/login?expired=1`로 보낸다.
- **인증 상태**: 토큰 보관·삭제, 로그인 여부, 만료 시각 확인. 관리자 버튼 노출과 `/admin/*` 접근 제어는 편의 기능일 뿐이다.
- **페이지 번호 변환**: 화면의 1부터 세는 번호와 API의 0부터 세는 번호를 바꾸는 한 곳. 잘못된 값은 1페이지로 바꾸고, 범위를 넘으면 빈 페이지 안내를 보여준다.
- 목록 이동 UI는 shadcn Pagination으로, 현재 페이지 중심 최대 5개 번호를 보여준다.
- 상세 화면은 본문을 텍스트로 렌더링하고 줄바꿈을 유지한다(`white-space: pre-wrap`). HTML로 해석하지 않는다.
- 날짜는 Asia/Seoul로 표시한다. 목록은 `yyyy.MM.dd`, 상세는 `yyyy.MM.dd HH:mm`.
- 상세로 갈 때 목록 페이지 번호를 넘겨서 "목록으로"가 원래 페이지로 돌아가게 한다.
- `404.html`을 두지 않아 Cloudflare Pages가 SPA 폴백을 하게 한다.

### 배포와 CI/CD

- 워크플로는 `.github/workflows/ci-cd.yml` 하나다.
- PR: 바뀐 폴더만 검사한다. 백엔드는 `./gradlew test build`, 프론트는 `npm ci`, lint, `tsc --noEmit`, `npm test`, build. 배포는 없다.
- `main` push:
  - 백엔드가 바뀌면: 테스트 → `railway up --service backend --ci`(`RAILWAY_TOKEN`) → 스모크 체크(health `UP`, `GET /api/notices?page=0` 200, 30초 간격 최대 10회 재시도).
  - 프론트가 바뀌면: 검사·빌드 → `cloudflare/wrangler-action@v4`로 `pages deploy` → 스모크 체크(`/` 200, `/notices/1` 200).
  - 둘 다 바뀌면 백엔드 배포가 성공한 뒤에 프론트를 배포한다.
- 모든 job과 스모크 체크가 초록이면 배포 완료다. 자동 롤백은 없다.
- Railway: 자동배포 끔(ADR 0004), Root Directory `/backend`, Railpack, `RAILPACK_JDK_VERSION=25`, 헬스체크 `/actuator/health`, `server.port=${PORT:8080}`, `SPRING_PROFILES_ACTIVE=prod`.
- 변수 위치
  - Railway 변수: `JWT_SECRET`, `ADMIN_USERNAME`, `ADMIN_PASSWORD_HASH`, `CORS_ALLOWED_ORIGINS`(Pages 운영 URL 하나), `SPRING_DATASOURCE_*`
  - GitHub 저장소 변수: `VITE_API_BASE_URL`
  - GitHub 시크릿: `RAILWAY_TOKEN`, `CLOUDFLARE_API_TOKEN`, `CLOUDFLARE_ACCOUNT_ID`
- Railway와 Cloudflare 대시보드 설정 순서는 README에 남긴다.

## Testing Decisions

### 좋은 테스트의 기준

- 바깥에서 보이는 동작만 검증한다. 백엔드는 HTTP 요청과 응답(상태 코드, 본문, 헤더), 프론트는 사용자가 보는 화면과 URL을 확인한다.
- 내부 클래스, 저장소 메서드, 컴포넌트 상태, 호출 횟수는 검증하지 않는다. 리팩터링으로 깨지는 테스트는 잘못된 테스트다.
- 테스트 이름은 `CONTEXT.md`의 용어(공지, 방문자, 관리자)를 쓴다.

### 테스트 지점

1. **백엔드 HTTP API (주 테스트 지점)**
   - `test` 프로필과 H2 인메모리로 앱 전체를 띄우고 HTTP로만 검증한다.
   - 대상: 권한(방문자의 쓰기 요청 401, 위조·만료 토큰 401), 입력 제한(경계값 0·1·100·101자, 공백만, 앞뒤 공백 자르기, 64KB 초과), ProblemDetail 형식과 `errors` 필드, 404, 페이지네이션(10개 단위, 범위 밖 페이지, `size` 무시), 정렬(같은 작성일시에서 id 내림차순, 수정해도 순서 유지), 로그인 성공·실패 문구, 5회 실패 후 429와 `Retry-After`, 성공 시 실패 횟수 초기화, CORS 허용·거부, health 응답에 세부 정보 없음.
   - 테스트끼리 데이터가 섞이지 않게 테스트마다 공지 테이블을 비운다.
2. **시계 주입 (1번 안의 보조 지점)**: 테스트가 시계를 바꿔 끼워 8시간 만료 직전·직후, 15분 차단 직전·직후를 기다리지 않고 확인한다. 작성일시가 같은 공지를 만들어 정렬 동률 처리도 확인한다.
3. **프론트 라우트 단위 + MSW**
   - Vitest와 Testing Library로 앱을 특정 URL에서 렌더링하고, API 응답은 MSW가 대신한다.
   - 대상: `?page` 변환(1부터 ↔ 0부터, 잘못된 값 → 1페이지, 범위 밖 안내), 페이지 이동 UI(비활성화, 숨김), 빈 목록 문구, 상세의 줄바꿈과 HTML 이스케이프, "수정됨" 표시, 404·500 화면, 401 시 토큰 삭제와 로그인 화면 이동, 폼 검증 안내와 서버 `errors` 표시, 로그인 상태별 관리자 버튼 노출, "목록으로"가 원래 페이지로 돌아가기.
4. **배포 후 스모크 체크**: CI/CD 워크플로가 운영 주소에서 확인한다(위 배포 절 참고).

### 선례

- 저장소에 아직 코드가 없어 따를 기존 테스트가 없다. 첫 테스트가 이후 테스트의 선례가 된다.

## Out of Scope

- 회원가입, 관리자 여러 명, 화면에서 비밀번호 변경
- 댓글, 첨부파일, 검색, 초안, 상단 고정
- 조회수, 작성자 표시, 카테고리
- 리프레시 토큰, 서버 쪽 토큰 무효화
- 공지 삭제 복구(하드 삭제)
- 동시 수정 충돌 감지(마지막 저장이 이긴다)
- 커스텀 도메인, PR 미리보기 배포
- CI에서 실제 PostgreSQL로 테스트하기
- 배포 실패 시 자동 롤백
- 로그인 실패 기록의 영속화와 여러 인스턴스 사이 공유

## Further Notes

- 로그인 실패 제한은 인스턴스 1개를 전제로 메모리에 기록한다. Railway에서 인스턴스를 늘리면 이 결정을 다시 열어야 한다.
- Railway의 config-as-code 폐지 예정 정보는 검색 요약으로만 확인했다. 그래서 대시보드 설정을 README에 문서로 남긴다.
- Cloudflare는 새 프로젝트에 Workers 정적 에셋을 권장한다. 빌드 산출물이 같아서 나중에 옮기기는 쉽다.
- 프론트 테스트(`npm test`)는 이 명세에서 CI에 추가된다. `decisions.md`의 Q20에는 lint, tsc, build만 적혀 있었다.
