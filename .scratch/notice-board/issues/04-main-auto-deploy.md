# 04: main 자동배포와 스모크 체크

**What to build:** 변경이 `main`에 들어가면 GitHub Actions가 검사를 통과한 쪽만 운영에 배포하고, 운영 주소에서 실제로 동작하는지 확인한다. 모든 job과 스모크 체크가 초록이면 배포 완료다. 이 티켓이 끝나면 빈 게시판이 운영 주소에서 열린다.

**Blocked by:** 02, 03

**Status:** ready-for-agent

- [ ] `main` push에서 `backend/`가 바뀌면 테스트 뒤 `railway up --service backend --ci`(`RAILWAY_TOKEN`)로 배포한다
- [ ] 백엔드 스모크 체크: `/actuator/health`가 `UP`, `GET /api/notices?page=0`이 200. 30초 간격 최대 10회 재시도
- [ ] `main` push에서 `frontend/`가 바뀌면 검사·빌드 뒤 `cloudflare/wrangler-action@v4`로 `pages deploy`한다
- [ ] 프론트 스모크 체크: 운영 URL `/`과 `/notices/1`이 모두 200(SPA 폴백, `404.html` 없음)
- [ ] 두 폴더가 모두 바뀌면 프론트 배포는 백엔드 배포와 스모크 체크가 성공한 뒤에만 실행된다
- [ ] 스모크 체크가 실패하면 워크플로가 실패로 끝난다. 자동 롤백은 없다
- [ ] PR에서는 배포 job이 실행되지 않는다
- [ ] 백엔드 CORS가 `CORS_ALLOWED_ORIGINS`의 주소만 허용한다. 허용·거부를 HTTP 테스트로 검증한다
- [ ] README에 Railway·Cloudflare 대시보드 설정 순서와 변수 목록이 있다(config-as-code 파일은 쓰지 않음)
- [ ] 실제 `main` push 한 번으로 운영 Pages 주소에서 "등록된 공지가 없습니다"를 확인했다
