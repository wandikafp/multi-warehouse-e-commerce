export interface CartResponse {
    id: string;
    cartItems: CartItem[];
    totalPrice: number;
}

export interface CartItem {
    id: string;
    productId: string,
    name: string,
    stock: number,
    imgUrl: string,
    quantity: number,
    price: number,
    subTotal: number
}
  