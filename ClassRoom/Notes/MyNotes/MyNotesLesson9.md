# NOTES FOR CLASS 9

```java

// Very important to make sure to use POST if Idempotent
// meaning if you execute it multiple times it will have negative impact on the application
// as if you execute add 2 times you will end up with duplicate items

@GetMapping("/ShoppingCart/{}")
getShoppingCar(cartNumber)
{
	return new ResponseEntity<ShoppingCartResult>(shoppingCartResult, HttpStatus.OK);
}

@PostMapping("/ShoppingCart")
public ResponseEntity<?> addProductToShoppingCart(cartNumber, productNumber, quantity)
{
	return new ResponseEntity<ShoppingCartResult>(shoppingCartResult, HttpStatus.OK);
}

@PostMapping("/ShoppingCart")
public ResponseEntity<?> removeProductFromShoppingCart(cart Number, productNumber, quantity)
{
	return new ResponseEntity<ShoppingCartResult>(, HttpStatus.OK);
}

@PutMapping("/ShoppingCart")
public ResponseEntity<?> changeQuantityInShoppingCart(cartNumber, productNumber, quantity )
{
	return new ResponseEntity<ShoppingCartResult>(shoppingCartResult, HttpStatus.OK);
}

@DeleteMapping("/ShoppingCart")
public ResponseEntity<?> clearShoppingCart(cartNumber)
{
	return new ResponseEntity<ShoppingCartResult>(, HttpStatus.OK);
}

@PostMapping("/ShoppingCart/Checkout")
public ResponseEntity<?> checkoutShoppingCart(cart Number)
{
	return new ResponseEntity<ShoppingCartResult>(shoppingCartResult, HttpStatus.OK);
}
```
