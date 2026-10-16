# 06: 공지 등록과 상세 보기

**What to build:** 관리자가 "새 공지"에서 제목과 본문을 입력해 등록하면 그 공지의 상세 화면으로 이동하고, 방문자는 상세 주소로 들어와 공지를 읽는다. 잘못된 입력은 입력 칸 아래에서, 없는 공지는 안내 화면으로 알려준다.

**Blocked by:** 05

**Status:** ready-for-agent

- [ ] `POST /api/notices`(관리자): `{title, body}`를 받아 201, `Location` 헤더, `{id,title,body,createdAt,updatedAt}`를 돌려준다. 처음엔 `updatedAt == createdAt`
- [ ] 제목·본문은 앞뒤 공백을 잘라 저장한다. 제목 1–100자, 본문 1–10,000자, 공백만은 거부. 경계값(0·1·100·101자 등)을 HTTP 테스트로 검증한다
- [ ] 검증 실패는 400 ProblemDetail과 `errors:[{field, message}]`, 잘못된 JSON도 400이다
- [ ] `GET /api/notices/{id}`(공개): 200과 상세. 없는 id는 404 ProblemDetail
- [ ] 시각은 UTC ISO-8601 문자열로 주고받는다
- [ ] 프론트 `/admin/notices/new`: 같은 규칙으로 입력 중 글자 수를 안내하고, 서버 `errors`를 해당 칸 아래에 보여준다. 등록 후 상세로 이동
- [ ] 프론트 `/notices/{id}`: 제목, 본문, 작성일시(`yyyy.MM.dd HH:mm`, Asia/Seoul)를 보여준다
- [ ] 본문 줄바꿈이 유지되고, 본문의 `<b>` 같은 HTML은 글자 그대로 보인다(MSW 테스트)
- [ ] 없는 공지 주소에서는 "존재하지 않는 공지입니다"와 목록 링크가 보인다
- [ ] 상세 주소를 직접 열거나 새로고침해도 화면이 뜬다
