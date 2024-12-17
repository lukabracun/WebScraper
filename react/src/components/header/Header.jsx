import logo from "../../resources/Logotip.png"
import { SlBag } from "react-icons/sl"

export default function Header() {
    return (
        <div className="header-container">
            <div> {/* element je skriven */} </div> 
            <div> {/* element je skriven */} </div> 
            <img src={logo} className="logo"></img>
            <div className="cart-container">
                <div className="cart-total">15</div>
                <SlBag className="cart-icon" />
            </div>
        </div>
    )
}