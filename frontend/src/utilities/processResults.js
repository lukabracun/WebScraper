import { stores } from "../data/stores"
import { determinePrice } from "./determinePrice"

const processResults = function (list) {
  var newList = []
  var minPrice, maxPrice
  var states = new Map()
  var i = 0
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

      //if image doesn't exist, add logo
      if (!item.imageUrl.startsWith("http")) {
        stores.forEach((store) => {
          if (item.storeName === store.name) {
            item.imageUrl = store.img
          }
        })
      }

      //adding state to list of states if doesn't exist
      if (!states.has(item.state)) {
        states.set(item.state, 1)
      } else {
        states.set(item.state, states.get(item.state) + 1)
      }

      //add to 
      newList.push(Object.assign({}, item, { id: i, visible: true }))
    } catch (err) {
      console.log("Eliminated:", item)
    }
  })
  var retObj = {
    response: newList,
    length: newList.length,
    minPrice: minPrice,
    maxPrice: maxPrice,
    states: states,
  }
  console.log("Response envelope:", retObj)
  return retObj
}

export { processResults }