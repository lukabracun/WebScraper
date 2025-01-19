import { useEffect } from 'react';
import { useCart } from './CartProvider';
import Item from './Item';
import './cart.css';
import './header.css';
import './products.css'
import { SlBag } from "react-icons/sl"

export default function Cart() {
    const { cart, setCart } = useCart()
    console.log("Cart:", cart)

    /* useEffect(() => {
        window.location.reload()

        return window.location.reload()
    }, [cart]) */
    return (
        <div className="page-wrapper">
            <div className="header-wrapper cart-header-wrapper">
                <header className="site-logo">
                    Košarica
                </header>
            </div>
            <br />
            <div className="products-wrapper">
                <div className="products-subwrapper" id="products">
                        { cart.map(product => (
                            <Item openContext={"cart"} CartIcon={SlBag} product={product} key={product.id}/>
                        ))}
                </div>
            </div>
        </div>
    )
}