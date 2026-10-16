package kr.school.notice

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient

/** Starts the whole app on a random port and talks to it over real HTTP only. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ApiTestSupport {
    @LocalServerPort
    private var port: Int = 0

    protected val http: RestClient by lazy {
        RestClient.builder()
            .baseUrl("http://localhost:$port")
            .defaultStatusHandler(HttpStatusCode::isError) { _, _ -> }
            .build()
    }
}
