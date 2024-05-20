import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/common/cart-item';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.listCartDetails();
  }

  listCartDetails() {
    // get a handle to the cart items
    this.cartItems = this.cartService.cartItems;
    // subscribe to cart total price
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );
    // subscribe to cart total quantity
    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );
    // compute cart total price & cuantity
    this.cartService.computeCartTotals();
  }

}
