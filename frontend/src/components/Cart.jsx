import { useEffect, useState } from 'react';
import { useCart } from './CartProvider';
import Item from './Item';
import './cart.css';
import './header.css';
import './products.css';
import './item.css'
import { SlBag } from "react-icons/sl"

export default function Cart() {
    const { cart, setCart } = useCart()

    //dynamically update page look
    window.addEventListener("storage", (e) => {
        window.location.reload()
    })

    function emptyCart() {
        if (confirm("Potvrdite da želite ukloniti sve proizvode iz košarice")) {
            setCart([])
        }
    }

    return (
        <div className="page-wrapper">
            <div className="header-wrapper cart-header-wrapper">
                <header className="site-logo">
                    Košarica
                </header>
                {
                    /* cart && cart.length > 0 &&
                    <div className="cart-options">
                        <div id="bw3" className="dropdown-button" onClick={() => emptyCart()}>Ukloni sve iz košarice</div>
                    </div> */
                }

            </div>
            <br />
            <div className="products-wrapper">
                <div className="products-subwrapper" id="products">
                    {cart.map(product => (
                        <Item openContext={"cart"} CartIcon={SlBag}
                            product={product} key={product.url} />
                    ))}
                </div>
            </div>
        </div>
    )
}