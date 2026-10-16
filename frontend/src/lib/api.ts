export type Page<T> = {
  content: T[]
  page: { number: number; size: number; totalElements: number; totalPages: number }
}

export type NoticeSummary = {
  id: number
  title: string
  createdAt: string
  updatedAt: string
}

const baseUrl = import.meta.env.VITE_API_BASE_URL || window.location.origin

async function getJson<T>(path: string): Promise<T> {
  const response = await fetch(new URL(path, baseUrl))
  if (!response.ok) throw new Error(`API ${response.status}`)
  return response.json() as Promise<T>
}

export function fetchNoticePage(page: number): Promise<Page<NoticeSummary>> {
  return getJson(`/api/notices?page=${page}`)
}
