import { useState, useEffect, createContext } from 'react'
import Header from './components/Header.jsx'
import Sidebar from './components/Sidebar.jsx'
import Products from './components/Products.jsx'
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Cart from './components/Cart.jsx'
import Home from './components/Home.jsx'
import CartProvider from './components/CartProvider.jsx'

export default function App() {
  return (
    <CartProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="/cart" element={<Cart />}/>
        </Routes>
      </BrowserRouter>
    </CartProvider>
  )
}