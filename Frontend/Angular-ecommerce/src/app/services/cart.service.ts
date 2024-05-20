import { Injectable } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() { }

  addToCart(theCartItem: CartItem) {
    // check if we have the item in our cart already
    let alreadyExistingInCart: boolean = false;
    let existingCartItem: CartItem = undefined;

    if(this.cartItems.length > 0) {
      for (let tempCartItem of this.cartItems) {
        if (tempCartItem.id === theCartItem.id){
          existingCartItem = tempCartItem;
          break;
        }
      }
    }
    // find item in the cart based on id
    alreadyExistingInCart = (existingCartItem != undefined);

    // check if we found it
    if(alreadyExistingInCart) {
      existingCartItem.quantity! ++;
    } else {
      this.cartItems.push(theCartItem);
    }

    // Compute cart total price and quantity
    this.computeCartTotals();
  }
  computeCartTotals() {
    throw new Error('Method not implemented.');
  }
}
