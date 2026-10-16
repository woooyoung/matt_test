import { render } from '@testing-library/react'
import { createMemoryRouter, RouterProvider } from 'react-router'
import { routes } from '@/routes'

export function renderAt(url: string) {
  const router = createMemoryRouter(routes, { initialEntries: [url] })
  return render(<RouterProvider router={router} />)
}
