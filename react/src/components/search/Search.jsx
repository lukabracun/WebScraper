import { IconContext } from "react-icons/lib"
import { SlMagnifier } from "react-icons/sl"

export default function Search() {
    return (
        <div className="search-container-flex">
            <div className="search-container">
                <input type="text" className="search-field" placeholder="Pretražite Mall.hr, Links i 5 drugih"></input>
                <IconContext.Provider value={{ color: 'rgb(179, 69, 0)' }}>
                    <div>
                        <SlMagnifier className="search-icon"/>
                    </div>
                </IconContext.Provider>
            </div>
        </div>
    )
}