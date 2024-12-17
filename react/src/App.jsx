import { useState } from 'react'
import Header from './components/header/Header.jsx'
import Search from './components/search/Search.jsx'
import Footer from './components/footer/Footer.jsx'
import block from './resources/block.png'
import './app.css'

export default function App() {

  return (
    <div className="app-container">
      <Header />
      <Search />
      <Footer />
      <div><img src={block} className="block" id="top-block"></img></div> {/* gornji trokut */}
      <div><img src={block} className="block" id="bottom-block"></img></div> {/* donji trokut */}
    </div>
  )
}
