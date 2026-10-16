import type { RouteObject } from 'react-router'
import { Layout } from './layout'
import { NotFoundPage } from './not-found'
import { NoticeListPage } from './notice-list'

export const routes: RouteObject[] = [
  {
    element: <Layout />,
    children: [
      { index: true, element: <NoticeListPage /> },
      { path: '*', element: <NotFoundPage /> },
    ],
  },
]
