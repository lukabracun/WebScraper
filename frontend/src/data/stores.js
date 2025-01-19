import Instar_Informatika from '.././resources/Instar-Informatika-logo.png'
import Links from '.././resources/Links-logo.png'
import Sancta_Domenica from '.././resources/Sancta-domenica-logo.webp'
import Ronis from '.././resources/Ronis-logo.png'
import Mikronis from '.././resources/Mikronis-logo.png'
import Centar_Tehnike from '.././resources/Centar-tehnike-logo.png'
import Ekupi from '.././resources/ekupi-logo.png'

/*
let storesInit = {
  "Instar Informatika" : {
    img: Instar_Informatika,
    url: "https://instar-informatika.hr/"
  }, "Links" : {
    img: Links, 
    url: "https://www.links.hr/hr/"
  }, "Sancta Domenica" : {
    img: Sancta_Domenica, 
    url: "https://www.sancta-domenica.hr/"
  }, "Ronis" : {
    img: Ronis,
    url: "https://www.ronis.hr/"
  }, "Mikronis" : {
    img: Mikronis, 
    url: "https://www.mikronis.hr/"
  }, "Centar Tehnike" : {
    img: Centar_Tehnike, 
    url: "https://www.centar-tehnike.hr/"
  }, "E kupi" : {
    img: Ekupi, 
    url: "https://www.ekupi.hr/hr/"
  }
} */

/*
let storesInit = new Map()
storesInit.set("Instar Informatika", {
  img: Instar_Informatika,
  url: "https://instar-informatika.hr/"
})
storesInit.set("Links", {
  img: Links,
  url: "https://www.links.hr/hr/"
})
storesInit.set("Sancta Domenica", {
  img: Sancta_Domenica,
  url: "https://www.sancta-domenica.hr/"
})
storesInit.set("Ronis", {
  img: Ronis,
  url: "https://www.ronis.hr/"
})
storesInit.set("Mikronis", {
  img: Mikronis,
  url: "https://www.mikronis.hr/"
})
storesInit.set("Centar Tehnike", {
  img: Centar_Tehnike,
  url: "https://www.centar-tehnike.hr/"
})
storesInit.set("E kupi", {
  img: Ekupi,
  url: "https://www.ekupi.hr/hr/"
}) */

let storesInit = [{img: Instar_Informatika, name: "Instar Informatika", url: "https://instar-informatika.hr/"}, 
    {img: Links, name: "Links", url: "https://www.links.hr/hr/"}, 
    {img: Sancta_Domenica, name: "Sancta Domenica", url: "https://www.sancta-domenica.hr/"}, 
    {img: Ronis, name: "Ronis", url: "https://www.ronis.hr/"},
    {img: Mikronis, name: "Mikronis", url: "https://www.mikronis.hr/"}, 
    {img: Centar_Tehnike, name: "Centar Tehnike", url: "https://www.centar-tehnike.hr/"},
    {img: Ekupi, name: "E kupi", url: "https://www.ekupi.hr/hr/"}
] 

let i = 0
for (let store of storesInit) {
  store.key = i
  store.checked = false
  i++
}

const stores = storesInit

console.log(stores)

export { stores }