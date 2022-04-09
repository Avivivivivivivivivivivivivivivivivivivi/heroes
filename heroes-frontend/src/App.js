import './App.css'
import { Link, Outlet, Route, Routes } from 'react-router-dom'
import { Nav, Navbar } from 'react-bootstrap'
import HeroListContainer from './containers/HeroListContainer'

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
    <>
      <Navbar bg="dark" variant="dark">
        <Nav.Link as={Link} to="/">
          <Navbar.Brand href="">Heroes</Navbar.Brand>
        </Nav.Link>

        <Nav className="me-auto">
          <Nav.Link as={Link} to="hero">
            Hero List
          </Nav.Link>
        </Nav>
      </Navbar>
      <Outlet />
    </>
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

export default App
