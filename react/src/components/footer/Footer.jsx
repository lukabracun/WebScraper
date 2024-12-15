import fer_logo from '../../resources/FER_logo_1.png'

export default function Footer() {
    return (
        <div className="footer-container">
            <div><img src={fer_logo}></img></div>
            <div className="footer-text">GitHub</div>
            <div className="footer-text">Dokumentacija</div>
        </div>
    )
}