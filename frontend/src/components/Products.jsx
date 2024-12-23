import Item from './Item.jsx'
import { stores } from '../data/stores.js'
import './products.css'

export default function Products({ CartIcon,
    appState,
    query,
    cart, setCart,
    storeSelection,
    response, setResponse,
    loading }) {

    return (
        <div className="products-wrapper">
            {(loading) ?
                <div className="loader">Učitivam podatke o proizvodima...</div>
                : <>
                    {(response.length == 0)
                        ? <span className="error-text">Pretraga nije vratila niti jedan rezultat.</span>
                        :
                        <>
                            <div className="response-metadata-wrapper">
                                <div className="response-count">{response.length} rezultata</div>
                                <div className="user-query">Upit "{query}" pretražen u {storeSelection.length}
                                    {response.length == 1 ? " 1 trgovini" : " trgovina"}</div>
                                <div className="align-text">{response.length} rezultata</div>
                            </div>
                            <div className="products-subwrapper">
                                {response.map(product => (
                                    <Item CartIcon={CartIcon} product={product} />
                                ))}
                            </div>
                        </>
                    }
                </>
            }
        </div>
    )
}