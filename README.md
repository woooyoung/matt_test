# 고등학교 공지사항 게시판

방문자는 공지 목록과 상세를 보고, 관리자 한 명이 공지를 등록·수정·삭제한다.
용어는 [`CONTEXT.md`](CONTEXT.md), 결정 이유는 [`docs/adr/`](docs/adr/)에 있다.

| 폴더 | 역할 | 배포 |
|---|---|---|
| `backend/` | Spring Boot + Kotlin API | Railway |
| `frontend/` | Vite + React + TypeScript 화면 | Cloudflare Pages |

두 폴더는 서로의 파일을 참조하지 않는다. 둘 사이의 계약은 HTTP API뿐이다.

## 로컬 실행

필요한 도구: JDK 25, Node 24.

```bash
# 백엔드: http://localhost:8080, local 프로필, H2 파일 DB(backend/data/)
cd backend
./gradlew bootRun

# 프론트: http://localhost:5173, /api 요청은 8080으로 프록시된다
cd frontend
npm install
npm run dev
```

백엔드는 실행 프로필(`local`, `test`, `prod`)이 없으면 시작하지 않는다. `bootRun`은 `local`, 테스트는 `test`를 자동으로 켠다.

## 검사

```bash
cd backend && ./gradlew test build
cd frontend && npm run lint && npm run typecheck && npm test && npm run build
```
