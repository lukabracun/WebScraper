import fer_logo from '../../resources/FER_logo_1.png'

export default function Footer() {
    return (
        <div className="footer-container">
            <img src={fer_logo} className="fer-logo"></img>
            <div className="footer-text">GitHub</div>
            <div className="footer-text">Dokumentacija</div>
            <div> {/* element je skriven */} </div> 
            <div> {/* element je skriven */} </div>
        </div>
    )
}