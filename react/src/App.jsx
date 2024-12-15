import { useState } from 'react'
import Header from './components/header/Header.jsx'
import Search from './components/search/Search.jsx'
import Footer from './components/footer/Footer.jsx'
import './app.css'

export default function App() {

  return (
    <div className="app-container">
      <Header />
      <Search />
      <Footer />
      <div></div> {/* gornji trokut */}
      <div></div> {/* donji trokut */}
    </div>
  )
}
