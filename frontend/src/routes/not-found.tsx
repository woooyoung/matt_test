import { Link } from 'react-router'

export function NotFoundPage() {
  return (
    <div className="py-16 text-center">
      <p className="text-lg font-medium">페이지를 찾을 수 없습니다</p>
      <Link to="/" className="mt-4 inline-block underline">
        공지 목록으로
      </Link>
    </div>
  )
}
