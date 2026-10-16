# 02: PR 검사 CI

**What to build:** 개발자가 PR을 올리면 GitHub Actions가 바뀐 폴더만 자동으로 검사해서, 깨진 코드가 `main`에 들어가기 전에 알려준다. 배포는 하지 않는다.

**Blocked by:** 01

**Status:** ready-for-agent

- [ ] 워크플로는 `.github/workflows/ci-cd.yml` 하나다
- [ ] `backend/`가 바뀐 PR에서 `./gradlew test build`가 Java 25로 실행된다
- [ ] `frontend/`가 바뀐 PR에서 `npm ci`, lint, `tsc --noEmit`, `npm test`, build가 `.nvmrc`의 Node로 실행된다
- [ ] 한쪽 폴더만 바뀌면 다른 쪽 job은 건너뛴다
- [ ] 문서만 바뀐 PR에서는 두 job 모두 건너뛴다
- [ ] 일부러 실패하는 테스트를 넣은 PR에서 워크플로가 실패한다
