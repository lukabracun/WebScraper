import { stores } from "../data/stores"
import { determinePrice } from "./determinePrice"

const processResults = function (list) {
  var newList = []
  var minPrice, maxPrice
  var states = new Map()
  list.forEach(item => {
    try {
      //price conversion
      item.price = determinePrice(item.price)
      if (!maxPrice) {
        minPrice = item.price
        maxPrice = item.price
      } else {
        if (item.price > maxPrice) {
          maxPrice = item.price
        } else if (item.price < minPrice) {
          minPrice = item.price
        }
      }

      //does image exist
      console.log("Url: ", window.location.pathname)
      if (!item.imageUrl.startsWith("http")) {
        stores.forEach((store) => {
          if (item.storeName === store.name) {
            item.imageUrl = store.img
          }
        })
      } /* else if (item.imageUrl === "https://www.ronis.hr/img/nopic.png") {
          item.imageUrl === stores[3].img //ronis slika
      } */

      //adding state to list of states if doesn't exist
      if (!states.has(item.state)) {
        states.set(item.state, 1)
      } else {
        states.set(item.state, states.get(item.state) + 1)
      }

      //add to 
      newList.push(item)
    } catch (err) {
      console.log("Eliminated:", item)
    }
  })
  var retObj = {
    "response": newList,
    "length": newList.length,
    "priceRange": { minPrice, maxPrice },
    "states": Object.fromEntries(states),
  }
  console.log("Response envelope:", retObj)
  return retObj
}

export { processResults }