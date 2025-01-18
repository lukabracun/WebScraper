import { BiLinkExternal } from "react-icons/bi";

import './item.css'

export default function Products({ CartIcon, product }) {
    const stateStyle = {
        "Dostupno": {
            "color": "#2a3444"
        }
        ,"Nedostupno": {
            "color": "#794141"
        }
        , "U vanjskom skladištu": {
            "color": "#794141"
        }
        , "Rabljeno": {
            "color": "#794141"
        }
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
                        <span>U trgovini</span>
                        <BiLinkExternal />
                    </div>
                </a>
                <div className="product-name-wrapper">
                    <span className="product-name">{product.name}</span>
                </div>
                <span className="product-price">{product.price.toFixed(2)} €</span>
                <span className="product-condition" style={stateStyle[product.state]}>{product.state}</span>
                <div className="button-wrapper">
                    <div id="bw2">
                        <span>Dodaj u</span>
                        <CartIcon />
                    </div>
                </div>
            </div>

        </div>
    )
}