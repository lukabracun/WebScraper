import { useState, useEffect } from 'react';
import Item from './Item.jsx';
import { stores } from '../data/stores.js';
import './products.css';

export default function Products({ CartIcon,
                                     appState,
                                     query,
                                     cart, setCart,
                                     storeSelection,
                                     response, setResponse,
                                     loading }) {

    const [searchPerformed, setSearchPerformed] = useState(false);
    const [searchQuery, setSearchQuery] = useState("");
    const [sortOrder, setSortOrder] = useState("asc");

    useEffect(() => {
        if (response.length > 0 || loading) {
            setSearchPerformed(true);
            setSearchQuery(query);
        }
    }, [response, loading]);

    // Utility to convert the price from "190,00 €" to a numeric value
    const parsePrice = (priceString) => {
        if (!priceString) return 0;
        return parseFloat(priceString.replace(" €", "").replace(".", "").replace(",", "."));
    };

    const handleSort = () => {
        const sortedResponse = [...response].sort((a, b) => {
            const priceA = parsePrice(a.price);
            const priceB = parsePrice(b.price);

            if (sortOrder === "asc") {
                return priceA - priceB;
            } else {
                return priceB - priceA;
            }
        });
        setResponse(sortedResponse);
    };

    useEffect(() => {
        if (response.length > 0) {
            handleSort();
        }
    }, [sortOrder]);

    return (
        <div className="products-wrapper">
            {(loading) ? (
                <div className="loader">Učitivam podatke o proizvodima...</div>
            ) : (
                <>
                    {(response.length === 0 && searchPerformed) ? (
                        <span className="error-text">Pretraga nije vratila niti jedan rezultat.</span>
                    ) : (
                        <>
                            {searchPerformed && (
                                <div className="response-metadata-wrapper">
                                    <div className="response-count">{response.length} rezultata</div>
                                    <div className="user-query">
                                        Upit "{searchQuery}" pretražen u {storeSelection.filter(store => store.checked).length}
                                        {response.length === 1 ? " 1 trgovini" : " trgovina"}
                                        <button onClick={() => setSortOrder(sortOrder === "asc" ? "desc" : "asc")}>
                                            Sort by price: {sortOrder === "asc" ? "Ascending" : "Descending"}
                                        </button>
                                    </div>
                                </div>
                            )}
                            <div className="products-subwrapper">
                                {response.map(product => (
                                    <Item CartIcon={CartIcon} product={product} key={product.id} />
                                ))}
                            </div>
                        </>
                    )}
                </>
            )}
        </div>
    );
}
