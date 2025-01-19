import { useState, useContext } from 'react';
import { SlMagnifier } from "react-icons/sl";
import { useCart } from './CartProvider';
import './header.css';
import Item from './Item';

export default function Header({ CartIcon,
    appState, setAppState,
    query, setQuery,
    sendRequest, setSendRequest,
    storeSelection}) {

    const { cart, setCart } = useCart()
    
    return (
        <div className="header-wrapper">
            <div className="left-wrapper">
                <div className="site-logo">Scrapify</div>
                <div className="site-logo-description">Web usluga za prikupljanje podataka o proizvodima koristeći web scraping.</div>
            </div>
            <div className="right-wrapper">
                <div className="searchbox-wrapper">
                    <div className="searchbox-subwrapper">
                        <input value={query}
                            onChange={(e) => setQuery(e.target.value)} type="text"
                            className="search-field"
                            placeholder={ storeSelection.filter(store => store.checked).length === 0 ? "Odaberite barem 1 trgovinu"
                                : "Pretražite odabrane trgovine"}></input>
                        <SlMagnifier className="search-icon"
                            onClick={() => {
                                setSendRequest(!sendRequest);
                                /* setAppState(1); */ // Update appState when search is performed
                            }} />
                    </div>
                </div>
                <div className="cart-wrapper">
                    <CartIcon className="cart-icon" onClick={() => window.open("/cart")}/>
                    <div className="cart-number">{cart.length > 0 && cart.length}</div>
                </div>
            </div>
        </div>
    );
}