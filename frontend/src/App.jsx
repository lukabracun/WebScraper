import { useState, useEffect } from 'react'
import Header from './components/Header.jsx'
import Sidebar from './components/Sidebar.jsx'
import Products from './components/Products.jsx'

import { SlBag } from "react-icons/sl"
import './app.css'
import { stores } from './data/stores.js'

import { exampleDataSet } from './data/exampleDataSet.js'

export default function App() {
  const [appState, setAppState] = useState(0) //stanje aplikacije
  // 0 = aplikacija je u stanju pretrazivanja
  // 1 = aplikacija prikazuje rezultate pretrage
  const [cart, setCart] = useState(() => {
    let localCart = localStorage.getItem("cart")
    if (localCart == null) return []
    else
      return JSON.parse(localCart)
  })  //stanje koje implementira kosaricu

  //perzistencija korisnicke kosarice
  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(cart))
  }, [cart])

  const [query, setQuery] = useState("")  //stanje koje implementira korisnicki upit
  const [storeSelection, setStoreSelection] = useState(stores)  //stanje koje implemetira odabir trgovina

  /* komunikacija s posluziteljem */
  const [response, setResponse] = useState(exampleDataSet)  //stanje koje sadrzi odgovor na korisnicki upit
  const [loading, setLoading] = useState(false)   //stanje koje je true kada se dohvaca sadrzaj s API-ja
  const [sendRequest, setSendRequest] = useState(false)   //stanje cija promjena inducira slanje zahtjeva na API

  useEffect(() => {
    const fetchData = async () => {
      const BACKEND_URL = "https://localhost:8080/api"  //URL posluziteljske strane
      try {
        setLoading(true)

        //objekt koji se salje na posluzitelja
        let sendObject = {
          query: query,
          stores: storeSelection
        }
        console.log(sendObject) 
        
        const fetchResponse = await fetch(`${BACKEND_URL}`, {
          method: "POST",
          body: JSON.stringify(sendObject)
        })

        if (!fetchResponse.ok) {
          throw new Error(`${fetchResponse.status} ${fetchResponse.statusText}`)
        } else {

          const response = await fetchResponse.json();
          setResponse(response)
        }

      } catch (error) {
        console.log(error.message)
      }
      setLoading(false)
    }

    { /* neka se doda error state za ako se npr. dućan ne javi unutar 10 sekundi */ }

    if ((query.length > 0) && (storeSelection.length > 0)) {  //sadrzaj se dohvaca samo ako postoji upit
                                                              //i barem jedna odabrana trgovina
      fetchData()
    }
    
  }, [sendRequest])

  return (
    <div className="app-container">
      <Header CartIcon={SlBag}
        appState={appState} setAppState={setAppState}
        query={query} setQuery={setQuery}
        cart={cart} 
        sendRequest={sendRequest} setSendRequest={setSendRequest}/>
      <Sidebar appState={appState}
        storeSelection={storeSelection} setStoreSelection={setStoreSelection} />
      <Products CartIcon={SlBag}
        appState={appState}
        cart={cart} setCart={setCart}
        query={query}
        storeSelection={storeSelection}
        response={response} setResponse={setResponse}
        loading={loading} />
    </div>
  )
}
