# Railway 자동배포를 끄고 GitHub Actions가 배포한다

Railway의 GitHub 자동배포(+ Wait for CI) 대신, Railway 자동배포를 끄고 GitHub Actions가 테스트 → `railway up --service backend --ci`(프로젝트 토큰 `RAILWAY_TOKEN`) → 스모크 체크를 한 흐름으로 실행한다. CI/CD를 Actions 한 곳에서 담당하고, 백엔드 배포 성공 뒤에 프론트를 배포하는 순서와 "모든 job과 스모크 체크가 초록이면 배포 완료"라는 기준을 한 워크플로에서 보장하기 위해서다. Railway 대시보드에서 자동배포를 다시 켜면 배포가 중복되므로 켜지 않는다.
