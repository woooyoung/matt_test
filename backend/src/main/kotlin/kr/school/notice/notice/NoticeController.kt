package kr.school.notice.notice

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/api/notices")
class NoticeController(private val notices: NoticeRepository) {
    @GetMapping
    fun list(@RequestParam(defaultValue = "0") page: Int): Page<NoticeSummary> {
        val request = PageRequest.of(page.coerceAtLeast(0), PAGE_SIZE, NEWEST_FIRST)
        return notices.findAll(request).map(NoticeSummary::from)
    }

    companion object {
        const val PAGE_SIZE = 10
        private val NEWEST_FIRST = Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("id"))
    }
}

data class NoticeSummary(val id: Long, val title: String, val createdAt: Instant, val updatedAt: Instant) {
    companion object {
        fun from(notice: Notice) = NoticeSummary(notice.id!!, notice.title, notice.createdAt, notice.updatedAt)
    }
}
