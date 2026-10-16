package kr.school.notice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import tools.jackson.databind.JsonNode

class ErrorResponseApiTest : ApiTestSupport() {
    @Test
    fun `없는 API 경로는 404 ProblemDetail로 응답한다`() {
        val response = http.get().uri("/api/no-such-thing").retrieve().toEntity(JsonNode::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(response.headers.contentType).isEqualTo(MediaType.APPLICATION_PROBLEM_JSON)
        assertThat(response.body!!["status"].asInt()).isEqualTo(404)
    }
}
