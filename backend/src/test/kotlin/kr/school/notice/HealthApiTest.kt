package kr.school.notice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import tools.jackson.databind.JsonNode

class HealthApiTest : ApiTestSupport() {
    @Test
    fun `health는 상태만 알려주고 세부 정보는 숨긴다`() {
        val response = http.get().uri("/actuator/health").retrieve().toEntity(JsonNode::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body!!["status"].asString()).isEqualTo("UP")
        assertThat(response.body!!.propertyNames()).doesNotContain("components", "details")
    }

    @Test
    fun `health 말고 다른 actuator 엔드포인트는 노출되지 않는다`() {
        val response = http.get().uri("/actuator/env").retrieve().toBodilessEntity()

        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }
}
