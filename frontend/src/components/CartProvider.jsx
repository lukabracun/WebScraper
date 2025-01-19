import { createContext, useContext } from "react"
import useLocalStorage from "../hooks/useLocalStorage"

const CartContext = createContext()

export default function CartProvider( { children } ) {
    const [cart, setCart] = useLocalStorage("cart", [])
    const value = { cart, setCart }
    return <CartContext.Provider value={value}>{children}</CartContext.Provider>
}

export function useCart() {
    const context = useContext(CartContext)
	if (context === undefined) {
		throw new Error('useCount must be used within a CountProvider')
	}
	return context
}