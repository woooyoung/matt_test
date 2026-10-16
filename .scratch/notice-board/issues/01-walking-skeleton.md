# 01: 걸어 다니는 뼈대: 빈 공지 목록이 화면에 뜨기

**What to build:** 개발자가 `backend/`와 `frontend/`를 각각 한 명령으로 띄우면, 방문자 화면 `/`에 "등록된 공지가 없습니다"가 보인다. 이 화면은 실제 백엔드 API를 거쳐서 나와야 하고, 이후 모든 티켓이 기댈 프로젝트 뼈대와 테스트 지점이 이 티켓에서 생긴다. 명세: `.scratch/notice-board/spec.md`.

**Blocked by:** None (can start immediately)

**Status:** ready-for-agent

- [x] `backend/`: Spring Boot 4.1.1, Kotlin 2.3.21(`plugin.spring`, `plugin.jpa`), Java 25 toolchain, Gradle 9 Kotlin DSL과 자체 wrapper
- [x] 프로필 `local`(H2 파일, `backend/data/`, gitignore), `test`(H2 인메모리), `prod`(PostgreSQL, `SPRING_DATASOURCE_*`)가 있다. H2는 `MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH`
- [x] 프로필을 지정하지 않으면 백엔드가 시작에 실패한다. `bootRun`은 `local`, 테스트는 `test`를 자동으로 켠다
- [x] Flyway 첫 마이그레이션이 공지 테이블과 `(created_at DESC, id DESC)` 인덱스를 만들고, JPA는 `ddl-auto=validate`다
- [x] `GET /api/notices?page=0`이 200과 `{content:[], page:{number,size,totalElements,totalPages}}`를 돌려준다(`via-dto`)
- [x] 없는 API 경로는 404 ProblemDetail(`application/problem+json`)을 돌려준다
- [x] `/actuator/health`만 노출되고 세부 정보가 없으며, `server.port=${PORT:8080}`이다
- [x] `frontend/`: Node 24(`.nvmrc`), npm, Vite 8, React 19, TS, Tailwind v4, shadcn/ui, React Router
- [x] `npm run dev`(5173)에서 `/api`가 `localhost:8080`으로 프록시되고, 배포 빌드는 `VITE_API_BASE_URL`을 쓴다
- [x] `/`에 공지가 없으면 "등록된 공지가 없습니다"가 보이고, 알 수 없는 프론트 경로는 404 화면을 보여준다
- [x] 백엔드: 앱 전체를 띄워 HTTP로 빈 목록 응답을 검증하는 첫 테스트가 통과한다
- [x] 프론트: Vitest + Testing Library + MSW로 `/`를 렌더링해 빈 목록 문구를 검증하는 첫 테스트가 통과한다
- [x] 루트 README에 두 폴더의 로컬 실행 방법이 있다
