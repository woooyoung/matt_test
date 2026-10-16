import { Link, Outlet } from 'react-router'

export function Layout() {
  return (
    <div className="mx-auto max-w-3xl px-4">
      <header className="border-b py-6">
        <Link to="/" className="text-2xl font-bold">
          공지사항
        </Link>
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  )
}
