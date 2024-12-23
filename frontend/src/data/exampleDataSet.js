import image from "../cache/mobitel-apple-iphone-16-128gb-black-60317-ins-59295.webp"

let exampleObject = {
  groupId: 1,
  id: 0,
  store: "Instar Informatika",
  name: 'Mobitel Apple iPhone 16, 256GB, Black',
  url: "https://instar-informatika.hr/mobitel-apple-iphone-16-256gb-black/262169/product/",
  imageUrl: "https://www.instar-informatika.hr/slike/velike/mobitel-apple-iphone-16-128gb-black-60317-ins-59295.webp",
  price: "1.199,00",
  state: "U vanjskom skladištu",
  discounted: false
}

let returnDataInit = [exampleObject]

for (let i = 1; i < 17; i++) {
  var tmpObject = Object.assign({}, exampleObject)
  tmpObject.id = i
  returnDataInit.push(tmpObject)
}

const exampleDataSet = returnDataInit

export { exampleDataSet }