import { screen } from '@testing-library/react'
import { http, HttpResponse } from 'msw'
import { server } from '@/test/server'
import { renderAt } from '@/test/render'

test('방문자는 공지가 없으면 안내 문구를 본다', async () => {
  server.use(
    http.get('*/api/notices', () =>
      HttpResponse.json({
        content: [],
        page: { number: 0, size: 10, totalElements: 0, totalPages: 0 },
      }),
    ),
  )

  renderAt('/')

  expect(await screen.findByText('등록된 공지가 없습니다')).toBeInTheDocument()
})
