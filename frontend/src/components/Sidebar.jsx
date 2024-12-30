import { TiArrowBack } from "react-icons/ti";
import { BiCheckbox } from "react-icons/bi";
import { BiCheckboxSquare } from "react-icons/bi";
import { AiFillGithub } from "react-icons/ai";

import { stores } from '../data/stores.js';
import './sidebar.css';

export default function Sidebar({ appState, storeSelection, setStoreSelection }) {
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
            <div className="stores-header-wrapper">
                {appState === 1 ? (
                    <div className="return-to-stores-wrapper">
                        <TiArrowBack className="return-icon" />
                        <span>Vrati na odabir dućana</span>
                    </div>
                ) : null}
                {appState === 0 ? (
                    <span className='stores-description'>Odaberite trgovine koje želite uključiti u pretragu:</span>
                ) : null}
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
            <div className='stores-footer-wrapper'>
                <a href="https://github.com/lukabracun/WebScraper/tree/master">
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