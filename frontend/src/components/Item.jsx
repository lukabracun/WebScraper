import { useState, useContext } from 'react';
import { BiLinkExternal } from "react-icons/bi";

import './item.css'
import { useCart } from './CartProvider';

export default function Item({ CartIcon, product, openContext }) {
    const { cart, setCart } = useCart()
    const stateStyle = {
        "Dostupno": {
            "color": "#2a3444"
        }
        , "Nedostupno": {
            "color": "#794141"
        }
        , "U vanjskom skladištu": {
            "color": "#794141"
        }
        , "Rabljeno": {
            "color": "#794141"
        }
    }

    const handleCartAdd = (product) => {
        setCart([...cart, product])
        console.log("Added to cart:", product)
    }

    const handleCartRemove = (product) => {
        alert("Attempting to remove product:", product.name)
    }

    return (
        <div key={product.id} className="product-wrapper">
            <div className="product-subwrapper">
                <div className="product-image-wrapper">
                    <img src={product.imageUrl}
                        className="product-image"
                        onClick={(e) => window.open(product.imageUrl)}></img>
                </div>
                <a href={product.url} target="_blank" className="button-wrapper">
                    <div id="bw1">
                        <span>U trgovinu</span>
                        <BiLinkExternal />
                    </div>
                </a>
                <div className="product-name-wrapper">
                    <span className="product-name">{product.name}</span>
                </div>
                <span className="product-price">{product.price.toFixed(2)} €</span>
                <span className="product-condition" style={stateStyle[product.state]}>{product.state}</span>
                <div className="button-wrapper">
                    {
                        openContext ?
                            <div id="bw3" onClick={() => handleCartRemove(product)}>
                                <span>Ukloni s</span>
                                <CartIcon />
                            </div>
                        :
                            <div id="bw2" onClick={() => handleCartAdd(product)}>
                                <span>Dodaj u</span>
                                <CartIcon />
                            </div>
                    }
                </div>
            </div>
        </div>
    )
}