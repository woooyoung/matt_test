# 03: [사람 작업] Railway와 Cloudflare 준비

**What to build:** 자동배포가 실제 운영 환경에 배포할 수 있도록, 대시보드와 토큰이 필요한 준비를 사람이 마친다. 에이전트는 계정과 대시보드에 접근할 수 없다.

**Blocked by:** None (can start immediately)

**Status:** ready-for-human

- [ ] Railway 프로젝트에 PostgreSQL과 `backend` 서비스가 있다
- [ ] `backend` 서비스: GitHub 자동배포 꺼짐, Root Directory `/backend`, 빌더 Railpack, 헬스체크 `/actuator/health`
- [ ] `backend` 변수: `SPRING_PROFILES_ACTIVE=prod`, `RAILPACK_JDK_VERSION=25`, `JWT_SECRET`(32바이트 이상 무작위), `ADMIN_USERNAME`, `ADMIN_PASSWORD_HASH`(BCrypt), `CORS_ALLOWED_ORIGINS`(Pages 운영 URL)
- [ ] `backend` 변수: `SPRING_DATASOURCE_URL=jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}`, `SPRING_DATASOURCE_USERNAME=${{Postgres.PGUSER}}`, `SPRING_DATASOURCE_PASSWORD=${{Postgres.PGPASSWORD}}`
- [ ] `backend` 서비스에 공개 도메인(`*.up.railway.app`)이 생성되어 있다
- [ ] 운영 환경용 Railway 프로젝트 토큰을 발급했다
- [ ] Cloudflare Pages 프로젝트(직접 업로드 방식)를 만들었고 운영 URL을 알고 있다
- [ ] Cloudflare API 토큰(Account > Cloudflare Pages > Edit)과 Account ID를 확보했다
- [ ] GitHub 시크릿: `RAILWAY_TOKEN`, `CLOUDFLARE_API_TOKEN`, `CLOUDFLARE_ACCOUNT_ID`
- [ ] GitHub 저장소 변수: `VITE_API_BASE_URL`(Railway 공개 URL), Pages 프로젝트 이름, 운영 URL 두 개(스모크 체크용)
