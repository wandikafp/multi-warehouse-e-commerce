"use client";

import { useState } from "react";
import {
  Card,
  CardHeader,
  CardContent,
  CardFooter,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Trash2 } from "lucide-react";

type CartItem = {
  id: number;
  name: string;
  price: number;
  quantity: number;
  stock: number;
  image: string;
};

const initialCart: CartItem[] = [
  {
    id: 1,
    name: "Product A",
    price: 100,
    quantity: 2,
    stock: 10,
    image: "/images/productA.jpg",
  },
  {
    id: 2,
    name: "Product B",
    price: 150,
    quantity: 1,
    stock: 0,
    image: "/images/productB.jpg",
  },
];

export default function CartPage() {
  const [cart, setCart] = useState<CartItem[]>(initialCart);

  const handleQuantityChange = (id: number, newQuantity: number) => {
    setCart((prevCart) =>
      prevCart.map((item) =>
        item.id === id ? { ...item, quantity: newQuantity } : item
      )
    );
  };

  const handleRemoveItem = (id: number) => {
    setCart((prevCart) => prevCart.filter((item) => item.id !== id));
  };

  const totalPrice = cart.reduce(
    (total, item) => total + item.price * item.quantity,
    0
  );

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Shopping Cart</h1>

      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <div className="grid gap-4">
          {cart.map((item) => (
            <Card key={item.id}>
              <CardHeader>
                <img
                  src={item.image}
                  alt={item.name}
                  className="h-40 w-full object-cover"
                />
              </CardHeader>
              <CardContent>
                <div className="flex justify-between items-center">
                  <div>
                    <h2 className="text-lg font-semibold">{item.name}</h2>
                    <p className="text-gray-600">Price: ${item.price}</p>
                    <p
                      className={`text-sm ${
                        item.stock > 0 ? "text-green-500" : "text-red-500"
                      }`}
                    >
                      {item.stock > 0
                        ? `Stock: ${item.stock}`
                        : "Out of Stock"}
                    </p>
                  </div>
                  <div className="flex items-center space-x-2">
                    <Input
                      type="number"
                      min={1}
                      max={item.stock}
                      value={item.quantity}
                      onChange={(e) =>
                        handleQuantityChange(
                          item.id,
                          Math.min(Math.max(Number(e.target.value), 1), item.stock)
                        )
                      }
                      disabled={item.stock === 0}
                      className="w-16"
                    />
                    <Button
                      variant="destructive"
                      onClick={() => handleRemoveItem(item.id)}
                    >
                      <Trash2 className="h-4 w-4" />
                    </Button>
                  </div>
                </div>
              </CardContent>
              <CardFooter>
                <p>Total: ${item.price * item.quantity}</p>
              </CardFooter>
            </Card>
          ))}
        </div>
      )}

      <div className="mt-6">
        <h2 className="text-lg font-semibold">Total Price: ${totalPrice}</h2>
        <Button disabled={cart.length === 0} className="mt-4">
          Proceed to Checkout
        </Button>
      </div>
    </div>
  );
}
