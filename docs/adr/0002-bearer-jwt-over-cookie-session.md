# 세션 쿠키 대신 Bearer JWT로 관리자 인증을 유지한다

프론트(`*.pages.dev`)와 백엔드(`*.up.railway.app`)는 서로 다른 사이트라 세션 쿠키는 `SameSite=None`과 서드파티 쿠키 차단의 영향을 받는다. 그래서 로그인 시 서버가 서명한 JWT(8시간, 리프레시 없음)를 발급하고 프론트가 `localStorage`에 보관해 `Authorization: Bearer`로 보낸다. 쿠키를 쓰지 않으므로 CSRF 방어는 끈다. 공지 본문은 React가 이스케이프하는 일반 텍스트라 XSS 노출면이 작다는 점에서 `localStorage` 보관을 받아들였다.

## Considered Options

- 커스텀 도메인으로 같은 사이트를 만들고 HttpOnly 세션 쿠키 사용: 도메인 구입·관리가 필요해 이번에는 기각.
- Cloudflare Pages Functions로 `/api`를 프록시해 같은 출처로 만들기: 별도 프록시 코드를 유지해야 해 기각.
