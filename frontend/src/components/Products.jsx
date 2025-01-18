import { useState, useEffect } from 'react';
import Item from './Item.jsx';
import { stores } from '../data/stores.js';
import { trgovinaMap } from '../data/trgovinaMap.js';
import './products.css';

export default function Products({ CartIcon,
    appState,
    query,
    cart, setCart,
    storeSelection,
    storeSelectionLength,
    response, setResponse,
    responseEnvelope, setResponseEnvelope,
    filterOptions,
    newFilter,
    loading }) {

    const [searchPerformed, setSearchPerformed] = useState(false);
    const [searchQuery, setSearchQuery] = useState("");

    useEffect(() => {
        if (response.length > 0 || loading) {
            setSearchPerformed(true);
            setSearchQuery(query);
        }
    }, [response, loading]);


    /* // Utility to convert the price from "190,00 €" to a numeric value
    const parsePrice = (priceString) => {
        if (!priceString) return 0;
        return parseFloat(priceString.replace(" €", "").replace(".", "").replace(",", "."));
    }; */

    const handleSort = (option) => {
        var sortedResponse = [...response]
        if (option === "price-asc" || option === "price-desc") {
            sortedResponse.sort((a, b) => {
                const priceA = a.price;
                const priceB = b.price;

                if (option === "price-asc") {
                    return priceA - priceB;
                } else {
                    return priceB - priceA;
                }
            })
        } else {
            sortedResponse.sort((a, b) => {
                const nameA = a.name;
                const nameB = b.name;

                if (option === "name-asc") {
                    return nameA - nameB;
                } else {
                    return nameB - nameA;
                }
            })
        }
        setResponse(sortedResponse)
    }

    const handleFilter = (option) => {

    }

    useEffect(() => {
        if (newFilter === "price-asc") {
            handleSort("price-asc")
        } else if (newFilter === "price-desc") {
            handleSort("price-desc")
        } else if (newFilter === "name-asc") {
            handleSort("name-asc")
        } else if (newFilter === "name-desc") {
            handleSort("name-desc")
        } else if (newFilter === "price-change") {
            handleFilter("price-change")
        } else if (newFilter === "state-change") {
            handleFilter("state-change")
        }
    }, [newFilter])

    return (
        <div className="products-wrapper">
            {(loading) ? (
                <div className="loader">Učitivam podatke o proizvodima...</div>
            ) : (
                <>
                    {(responseEnvelope.length === 0 && searchPerformed) ? (
                        <span className="error-text">Pretraga nije vratila niti jedan rezultat.</span>
                    ) : (
                        <>
                            {searchPerformed && (
                                <div className="response-metadata-wrapper">
                                    <div className="response-count">{responseEnvelope.length} {responseEnvelope.length % 10 === 1 ? "rezultat" : "rezultata"}</div>
                                    <div className="user-query">
                                        Upit "{query}" pretražen u {storeSelectionLength} {trgovinaMap[storeSelectionLength]}
                                        {/* <button onClick={() => setSortOrder(sortOrder === "asc" ? "desc" : "asc")}>
                                            Sort by price: {sortOrder === "asc" ? "Ascending" : "Descending"}
                                        </button> */}
                                    </div>
                                    <div className="sort-container">
                                        <button className="dropdown-button">
                                            Odaberi sortiranje
                                        </button>
                                        <div className="sort-options">
                                            <div>Cijena, uzlazno</div>
                                            <div>Cijena, silazno</div>
                                            <div>Ime, uzlazno</div>
                                            <div>Ime, silazno</div>
                                        </div>
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
