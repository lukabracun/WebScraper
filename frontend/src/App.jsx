import { useState, useEffect } from 'react'
import Header from './components/Header.jsx'
import Sidebar from './components/Sidebar.jsx'
import Products from './components/Products.jsx'

import { SlBag } from "react-icons/sl"
import './app.css'
import { stores } from './data/stores.js'
import { processResults } from './utilities/processResults.js'

export default function App() {
  const [appState, setAppState] = useState(0) // state of the application
  // 0 = application is in search state
  // 1 = application displays search results
  const [cart, setCart] = useState(() => {
    let localCart = localStorage.getItem("cart")
    if (localCart == null) return []
    else
      return JSON.parse(localCart)
  })  // state that implements the cart

  // persistence of the user's cart
  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(cart))
  }, [cart])

  const [query, setQuery] = useState("")  // state that implements the user's query
  const [previousQuery, setPreviousQuery] = useState("")
  const [storeSelection, setStoreSelection] = useState(stores)  // state that implements store selection
  const [storeSelectionLength, setStoreSelectionLength] = useState(0)

  /* communication with the server */
  const [response, setResponse] = useState([])  // state that contains the response to the user's query
  const [responseEnvelope, setResponseEnvelope] = useState({
    response: [],
    length: 0,
    minPrice: 0,
    maxPrice: Number.MAX_VALUE,
    states: new Map()
  })
  const [loading, setLoading] = useState(false)   // state that is true when content is being fetched from the API
  const [sendRequest, setSendRequest] = useState(false)   // state whose change induces sending a request to the API
  const [filterOptions, setFilterOptions] = useState({
      "price-asc": false
      ,"price-desc": false
      ,"name-asc": false
      ,"name-desc": false
      ,"min-price": null
      ,"max-price": null
      ,"Dostupno": true
      ,"U vanjskom skladištu": true
      ,"Nedostupno": true
      ,"Rabljeno": true
  })
  const [newFilter, setNewFilter] = useState(null)

  useEffect(() => {
    const fetchData = async () => {
      const BACKEND_URL = "http://localhost:8080/api"  // URL of the server-side
      try {
        setLoading(true)

        // object to be sent to the server
        let sendObject = {
          query: query,
          stores: storeSelection
        }
        console.log("Sending:", sendObject)

        const fetchResponse = await fetch(`${BACKEND_URL}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(sendObject)
        })

        if (!fetchResponse.ok) {
          throw new Error(`${fetchResponse.status} ${fetchResponse.statusText}`)
        } else {
          const response = await fetchResponse.json()
          setResponse(response)
          setStoreSelectionLength(storeSelection.filter(store => store.checked).length)
          /* console.log("Response: ")
          console.log(response) */
          setResponseEnvelope(processResults(response))
          setPreviousQuery(query)
          setQuery("")
        }

      } catch (error) {
        console.log(error.message)
      }
      setLoading(false)
    }

    // fetch content only if there is a query and at least one selected store
    if ((query.length > 0) && (storeSelection.length > 0)) {
      fetchData()
    }

  }, [sendRequest])

  return (
    <div className="app-container" onKeyDown={(e) => {
      if (e.key === "Enter") {
        setSendRequest(!sendRequest);
        /* setAppState(1); */ // Update appState when search is performed
      }
    }}>
      <Header CartIcon={SlBag}
        appState={appState} setAppState={setAppState}
        query={query} setQuery={setQuery}
        cart={cart}
        sendRequest={sendRequest} setSendRequest={setSendRequest} />
      <Sidebar appState={appState} setAppState={setAppState}
        storeSelection={storeSelection} setStoreSelection={setStoreSelection}
        responseEnvelope={responseEnvelope} setResponseEnvelope={setResponseEnvelope}
        filterOptions={filterOptions} setFilterOptions={setFilterOptions}
        newFilter={newFilter} setNewFilter={setNewFilter}
        loading={loading} />
      <Products CartIcon={SlBag}
        appState={appState}
        cart={cart} setCart={setCart}
        query={previousQuery}
        storeSelectionLength={storeSelectionLength}
        storeSelection={storeSelection}
        response={response} setResponse={setResponse}
        filterOptions={filterOptions}
        responseEnvelope={responseEnvelope} setResponseEnvelope={setResponseEnvelope}
        newFilter={newFilter}
        loading={loading} />
    </div>
  )
}