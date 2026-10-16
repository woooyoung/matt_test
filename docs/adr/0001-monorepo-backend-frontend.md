# 하나의 저장소에 backend/와 frontend/를 독립 프로젝트로 둔다

백엔드(Spring Boot + Kotlin, Railway)와 프론트(Vite + React, Cloudflare Pages)를 같은 저장소에 두되, 루트에는 공용 빌드 도구를 두지 않는다. 각 폴더는 자기 빌드 도구(Gradle wrapper / npm)로만 동작하고 서로의 파일을 참조하지 않으며, 둘 사이의 유일한 계약은 HTTP API다. 배포 대상이 다른 두 런타임을 한 저장소에서 함께 변경·리뷰하되, 빌드와 배포는 폴더 단위로 분리하기 위해서다.
