package kr.school.notice

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class StartupProfileTest {
    @Test
    fun `실행 프로필이 없으면 앱이 시작을 거부한다`() {
        assertThatThrownBy { main(arrayOf("--spring.profiles.active=", "--server.port=0")) }
            .hasMessageContaining("SPRING_PROFILES_ACTIVE")
    }

    @Test
    fun `알 수 없는 프로필만 있으면 앱이 시작을 거부한다`() {
        assertThatThrownBy { main(arrayOf("--spring.profiles.active=staging", "--server.port=0")) }
            .hasMessageContaining("SPRING_PROFILES_ACTIVE")
    }
}
