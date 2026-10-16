import { useEffect, useState } from 'react'
import { fetchNoticePage, type NoticeSummary, type Page } from '@/lib/api'

export function NoticeListPage() {
  const [page, setPage] = useState<Page<NoticeSummary> | null>(null)

  useEffect(() => {
    fetchNoticePage(0).then(setPage)
  }, [])

  if (!page) return null

  if (page.content.length === 0) {
    return <p className="py-16 text-center text-muted-foreground">등록된 공지가 없습니다</p>
  }

  return (
    <ul>
      {page.content.map((notice) => (
        <li key={notice.id}>{notice.title}</li>
      ))}
    </ul>
  )
}
