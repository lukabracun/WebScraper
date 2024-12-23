import { BiLinkExternal } from "react-icons/bi";

import './item.css'

export default function Products({ CartIcon, product }) {
    return (
        <div key={product.id} className="product-wrapper">
            <div className="product-subwrapper">
                <div className="product-image-wrapper">
                    <img src={product.imageUrl} 
                        loading="lazy" 
                        className="product-image" 
                        onClick={(e) => window.open(product.imageUrl)}></img>
                </div>
                <a href={product.url} className="button-wrapper">
                    <div id="bw1">
                        <span>U trgovini</span>
                        <BiLinkExternal />
                    </div>

                </a>
                <div className="product-name-wrapper">
                    <span className="product-name">{product.name}</span>
                </div>
                <span className="product-price">{product.price} €</span>
                <span className="product-condition">{product.state}</span>
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