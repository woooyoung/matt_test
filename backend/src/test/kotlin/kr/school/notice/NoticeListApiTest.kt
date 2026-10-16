package kr.school.notice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import tools.jackson.databind.JsonNode

class NoticeListApiTest : ApiTestSupport() {
    @Test
    fun `방문자는 공지가 없으면 빈 첫 페이지를 받는다`() {
        val response = http.get().uri("/api/notices?page=0").retrieve().toEntity(JsonNode::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        val body = response.body!!
        assertThat(body["content"].size()).isZero()
        assertThat(body["page"]["number"].asInt()).isZero()
        assertThat(body["page"]["size"].asInt()).isEqualTo(10)
        assertThat(body["page"]["totalElements"].asInt()).isZero()
        assertThat(body["page"]["totalPages"].asInt()).isZero()
    }
}
