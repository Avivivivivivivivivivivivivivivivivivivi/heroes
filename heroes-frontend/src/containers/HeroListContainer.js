import { useEffect, useState } from 'react'
import List from '@mui/material/List'
import ListItem from '@mui/material/ListItem'
import { Grid } from '@mui/material'

const HeroListContainer = () => {
  const [heroes, setHeroes] = useState([])

  useEffect(() => {
    fetch('http://localhost:82/api/v1/hero')
      .then((res) => res.json())
      .then((res) => {
        setHeroes(res)
      })
  }, [])
  return (
    <>
      <h1>Hero list page</h1>
      <Grid container>
        <List>
          {heroes.map((hero) => {
            const { id, name } = hero
            return (
              <ListItem key={`hero-${id}`} id={`hero-${id}`}>
                {name}
              </ListItem>
            )
          })}
        </List>
      </Grid>
    </>
  )
}

export default HeroListContainer
