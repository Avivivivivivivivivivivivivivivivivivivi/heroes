import './App.css'
import { Link, Outlet, Route, Routes } from 'react-router-dom'

const App = () => {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="hero" element={<HeroListContainer />} />
          <Route path="hero/:id" element={<HeroContainer />} />
        </Route>
      </Routes>
    </div>
  )
}

const Layout = () => {
  return (
    <div>
      {/* A "layout route" is a good place to put markup you want to
          share across all the pages on your site, like navigation. */}
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/hero">HeroList</Link>
          </li>
        </ul>
      </nav>

      <hr />

      {/* An <Outlet> renders whatever child route is currently active,
          so you can think about this <Outlet> as a placeholder for
          the child routes we defined above. */}
      <Outlet />
    </div>
  )
}

const HeroContainer = () => {
  return (
    <>
      <h1>Hero page</h1>
    </>
  )
}

const Home = () => {
  return (
    <>
      <h1>Home page</h1>
    </>
  )
}

const HeroListContainer = () => {
  return (
    <>
      <h1>Hero list page</h1>
    </>
  )
}

export default App
