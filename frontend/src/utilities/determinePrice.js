const determinePrice = function (priceString) {
  return parseFloat(
    priceString.replace(' €', '').replace('.', '').replace(',', '.')
  )
}

export { determinePrice }