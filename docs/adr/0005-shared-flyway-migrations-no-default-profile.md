# H2와 PostgreSQL에 같은 Flyway 마이그레이션을 쓰고 기본 프로필을 두지 않는다

로컬(H2 파일), 테스트(H2 인메모리), 운영(PostgreSQL)이 모두 같은 Flyway 마이그레이션으로 스키마를 만든다. H2는 `MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH`로 띄우고, SQL은 두 DB가 공통으로 이해하는 문법만 쓰며, JPA는 `ddl-auto=validate`다. CI에 PostgreSQL을 두지 않는 대신, 운영 마이그레이션 실패는 Railway 헬스체크 실패로 막는다(PostgreSQL DDL은 트랜잭션이라 롤백된다). 배포된 마이그레이션 파일은 수정하지 않는다.

기본 Spring 프로필은 두지 않는다. `SPRING_PROFILES_ACTIVE`가 없으면 앱이 시작에 실패한다. 기본값이 있으면 운영에서 변수를 빠뜨렸을 때 앱이 조용히 H2로 떠서 데이터가 사라지기 때문이다.
