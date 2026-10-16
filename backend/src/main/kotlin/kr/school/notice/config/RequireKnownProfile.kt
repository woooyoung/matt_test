package kr.school.notice.config

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener

/**
 * Refuses to start without one of the known profiles. A silent default would let a production
 * deploy with a missing variable come up on an empty H2 database (ADR 0005).
 */
class RequireKnownProfile : ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    override fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {
        val active = event.environment.activeProfiles.toSet()
        check(active.any { it in KNOWN_PROFILES }) {
            "SPRING_PROFILES_ACTIVE must include one of $KNOWN_PROFILES, but was $active"
        }
    }

    companion object {
        val KNOWN_PROFILES = setOf("local", "test", "prod")
    }
}
