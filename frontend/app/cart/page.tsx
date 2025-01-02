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
import { orderService } from "@/lib/services";
import { CartResponse, CartItem } from "@/models/order/CartResponse";
import { useEffect } from "react";
import { useAuthGuard } from "@/lib/auth/use-auth";

export default function CartPage() {
  // const [cart, setCart] = useState<CartItem[]>(initialCart);
  const [cart, setCart] = useState<CartResponse | null>(null);

  const { user } = useAuthGuard({ middleware: "auth" });

  const fetchData = () => {
    if (!user) return; // Ensure user is defined before making the API call
    const jwtToken = localStorage.getItem("jwtToken");
    orderService
      .get<CartResponse>(`/api/cart/${user.id}`, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      })
      .then((res) => setCart(res.data));
  };

  useEffect(() => {
    fetchData();
  }, [user]); // Add user as a dependency to run the effect when user is defined

  const handleQuantityChange = (cartItem: CartItem, newQuantity: number) => {
    const jwtToken = localStorage.getItem("jwtToken");
    orderService.put<CartResponse>(`/api/cart/${user?.id}/update`, {
      headers: { Authorization: `Bearer ${jwtToken}` },
      data: { ...cartItem, quantity: newQuantity }
    }).then(cart => {
      setCart(cart.data);
      alert('Cart item updated successfully');
    });
  };

  const handleRemoveItem = (cartItem: CartItem) => {
    const jwtToken = localStorage.getItem("jwtToken");
    orderService.delete<CartResponse>(`/api/cart/${user?.id}/remove`, {
      headers: { Authorization: `Bearer ${jwtToken}` },
      data: cartItem
    }).then(cart => {
      setCart(cart.data);
      alert('Cart item deleted successfully');
    });
  };

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Shopping Cart</h1>

      {cart?.cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <div className="grid gap-4">
          {cart?.cartItems.map((item) => (
            <Card key={item.id}>
              <CardHeader>
                <img
                  src={item.imgUrl}
                  alt={item.name}
                  className="h-40 w-full object-cover"
                />
              </CardHeader>
              <CardContent>
                <div className="flex justify-between items-center">
                  <div>
                    <h2 className="text-lg font-semibold">{item.name}</h2>
                    <p className="text-gray-600">Price: Rp {item.price}</p>
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
                          item,
                          Math.min(Math.max(Number(e.target.value), 1), item.stock)
                        )
                      }
                      disabled={item.stock === 0}
                      className="w-16"
                    />
                    <Button
                      variant="destructive"
                      onClick={() => handleRemoveItem(item)}
                    >
                      <Trash2 className="h-4 w-4" />
                    </Button>
                  </div>
                </div>
              </CardContent>
              <CardFooter>
                <p>Total: Rp {item.price * item.quantity}</p>
              </CardFooter>
            </Card>
          ))}
        </div>
      )}

      <div className="mt-6">
        <h2 className="text-lg font-semibold">Total Price: Rp {cart?.totalPrice}</h2>
        <Button disabled={cart?.cartItems.length === 0} className="mt-4">
          Proceed to Checkout
        </Button>
      </div>
    </div>
  );
}
