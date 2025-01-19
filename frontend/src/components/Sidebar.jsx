import { TiArrowBack } from "react-icons/ti";
import { BiCheckbox } from "react-icons/bi";
import { BiCheckboxSquare } from "react-icons/bi";
import { BiRadioCircle } from "react-icons/bi";
import { BiRadioCircleMarked } from "react-icons/bi";
import { AiFillGithub } from "react-icons/ai";

import { stores } from '../data/stores.js';
import './sidebar.css';
import { useState } from "react";

export default function Sidebar({ appState, setAppState,
    storeSelection, setStoreSelection,
    responseEnvelope, setResponseEnvelope,
    filterOptions, setFilterOptions,
    newFilter, setNewFilter,
    loading }) {

    const [minPrice, setMinPrice] = useState(responseEnvelope.minPrice)
    const [maxPrice, setMaxPrice] = useState(responseEnvelope.maxPrice)

    function changeView() {
        setAppState(0)
    }

    function handleCheckboxClick(key, checked) {
        setStoreSelection((currentStoreSelection) => {
            return currentStoreSelection.map(store => {
                if (store.key === key) {
                    return { ...store, checked };
                } else {
                    return store;
                }
            });
        });
    }

    return (
        <div className='sidebar-wrapper'>
            <div className="header-and-stores-wrapper">
                {(appState === 1 && !loading) ? (
                    <>
                        <div className="stores-header-wrapper">
                            <div className="return-to-stores-wrapper" onClick={changeView}>
                                <TiArrowBack className="return-icon" />
                                <span>Završi obradu i vrati na odabir trgovina</span>
                            </div>
                        </div>
    
                        <div className="stores-wrapper">
                            <div className="stores-subwrapper" id="result-stores-subwrapper">
                                <div className="store-wrapper" id="sort">
                                    <span className="return-to-stores-wrapper" >
                                        Sortiranje:
                                    </span>
                                    <div className="sort-option-wrapper">
                                        <div>Po cijeni uzlazno</div>
                                        {filterOptions["price-asc"] ?
                                            <BiRadioCircleMarked className="store-checkbox" onClick={
                                                handleRadioButtonClick("price-asc", false)
                                            }/>
                                            : <BiRadioCircle className="store-checkbox" onClick={
                                                handleRadioButtonClick("price-asc", true)
                                            }/>
                                        }
                                    </div>
                                    <div className="sort-option-wrapper">
                                        <div>Po cijeni silazno</div>
                                        {filterOptions["price-desc"] ?
                                            <BiRadioCircleMarked className="store-checkbox" />
                                            : <BiRadioCircle className="store-checkbox" />
                                        }
                                    </div>
                                    <div className="sort-option-wrapper">
                                        <div>Po imenu uzlazno</div>
                                        {filterOptions["name-asc"] ?
                                            <BiRadioCircleMarked className="store-checkbox" />
                                            : <BiRadioCircle className="store-checkbox" />
                                        }
                                    </div>
                                    <div className="sort-option-wrapper">
                                        <div>Po imenu silazno</div>
                                        {filterOptions["name-desc"] ?
                                            <BiRadioCircleMarked className="store-checkbox" />
                                            : <BiRadioCircle className="store-checkbox" />
                                        }
                                    </div>
    
                                </div>
    
                                <div className="store-wrapper" id="filter-price">
                                    <span className="return-to-stores-wrapper" >
                                        Cijena:
                                    </span>
                                    <div className="price-wrapper">
                                        <div>
                                            Min:
                                        </div>
                                        <input value={minPrice}
                                            type="text"
                                            placeholder={responseEnvelope.minPrice}
                                            onChange={(e) => setMinPrice(e.target.value)}
                                            className="search-field price-search-field"></input>
                                        <div>
                                            Max:
                                        </div>
                                        <input value={maxPrice}
                                            type="text"
                                            className="search-field price-search-field"
                                            onChange={(e) => setMaxPrice(e.target.value)}
                                            placeholder={responseEnvelope.maxPrice}></input>
                                    </div>
                                </div>
    
                                <div className="store-wrapper" id="filter-state">
                                    <span className="return-to-stores-wrapper" >
                                        Stanje:
                                    </span>
                                    {
                                        responseEnvelope.states.forEach((state) => {
                                            <div>
                                                <span>1</span>
                                            </div>
                                        })
                                    }
                                </div>
    
                            </div>
                        </div>
                    </>
                ) : null}
                {appState === 1 && loading ? (
                    <>
                        <div className="stores-header-wrapper">
                        </div>
                        <div className='stores-wrapper'>
                        </div>
                    </>
                ) : null}
                {appState === 0 ? (
                    <>
                        <div className="stores-header-wrapper">
                            <span className='stores-description'>Odaberite trgovine koje želite uključiti u pretragu:</span>
                        </div>
                        <div className='stores-wrapper'>
                            <div className='stores-subwrapper'>
                                {stores.map(store => (
                                    <div key={store.key} className="store-wrapper">
                                        <div className="store-logo-wrapper" href={store.url}>
                                            <a href={store.url} target="_">
                                                <img className="store-logo" src={store.img} alt={store.name}></img>
                                            </a>
                                        </div>
                                        {storeSelection[store.key].checked ? (
                                            <div>
                                                <BiCheckboxSquare className="store-checkbox"
                                                    onClick={(e) => handleCheckboxClick(store.key, false)} />
                                            </div>
                                        ) : (
                                            <div>
                                                <BiCheckbox className="store-checkbox"
                                                    onClick={(e) => handleCheckboxClick(store.key, true)} />
                                            </div>
                                        )}
                                    </div>
                                ))}
                            </div>
                        </div>
                    </>
                ) : null}
            </div>
            <div className='stores-footer-wrapper'>
                <a href="https://github.com/lukabracun/WebScraper/tree/master" target="_blank">
                    <AiFillGithub />
                    <span>GitHub</span>
                </a>
                <span>
                    Usluga Scrapify napravljena je u okviru predmeta Projekt R
                    na Fakultetu Elektrotehnike i Računarstva
                </span>
            </div>
        </div>
    );
}