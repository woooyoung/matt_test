import { screen } from '@testing-library/react'
import { renderAt } from '@/test/render'

test('방문자는 알 수 없는 주소에서 404 화면을 본다', () => {
  renderAt('/no-such-page')

  expect(screen.getByText('페이지를 찾을 수 없습니다')).toBeInTheDocument()
  expect(screen.getByRole('link', { name: '공지 목록으로' })).toHaveAttribute('href', '/')
})
