"use client";

import React, { useState, useEffect } from "react";
import { useAuthGuard } from "@/lib/auth/use-auth";
import { userService } from "@/lib/services";
import { UserAddressResponse } from "@/models/user/UserAddressResponse";
import { CartItem } from "@/models/order/CartResponse";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Select, SelectTrigger, SelectContent, SelectItem, SelectValue } from "@/components/ui/select";
import { Separator } from "@/components/ui/separator";

export default function CheckoutPage() {
  const { user } = useAuthGuard({ middleware: "auth" });
  const [addresses, setAddresses] = useState<UserAddressResponse[] | null>(null);
  const [selectedAddress, setSelectedAddress] = useState<UserAddressResponse | null>(null);
  const [orderItems, setOrderItems] = useState<CartItem[]>([]);
  const [totalPrice, setTotalPrice] = useState<number>(0);

  const fetchAddresses = async () => {
    const jwtToken = localStorage.getItem("jwtToken");
    const response = await userService.get<UserAddressResponse[]>(`/api/user-address`, {
      headers: { Authorization: `Bearer ${jwtToken}` },
      params: { userId: user?.id },
    });
    setAddresses(response.data);
    setSelectedAddress(response.data[0]);
  };

  const fetchOrderItems = async () => {
    // Simulate fetching order items
    const items: CartItem[] = [
      {
          id: "1", name: "Item 1", price: 10, quantity: 2,
          productId: "",
          stock: 0,
          imgUrl: "",
          subTotal: 0
      },
      {
          id: "2", name: "Item 2", price: 20, quantity: 1,
          productId: "",
          stock: 0,
          imgUrl: "",
          subTotal: 0
      },
    ];
    setOrderItems(items);
    setTotalPrice(items.reduce((total, item) => total + item.price * item.quantity, 0));
  };

  useEffect(() => {
    fetchAddresses();
    fetchOrderItems();
  }, [user]);

  const handleAddressChange = (addressId: string) => {
    const address = addresses?.find((addr) => addr.id === addressId) || null;
    setSelectedAddress(address);
  };

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Checkout</h1>
      <Card>
        <CardHeader>
          <CardTitle>Delivery Address</CardTitle>
        </CardHeader>
        <CardContent>
          {selectedAddress && (
            <div>
              <p>{selectedAddress.street}, {selectedAddress.city}, {selectedAddress.province}, {selectedAddress.postalCode}</p>
              <Select onValueChange={handleAddressChange} defaultValue={selectedAddress.id}>
                <SelectTrigger>
                  <SelectValue placeholder="Select an address" />
                </SelectTrigger>
                <SelectContent>
                  {addresses?.map((address) => (
                    <SelectItem key={address.id} value={address.id}>
                      {address.street}, {address.city}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
          )}
        </CardContent>
      </Card>
      <Separator className="my-4" />
      <Card>
        <CardHeader>
          <CardTitle>Order Items</CardTitle>
        </CardHeader>
        <CardContent>
          {orderItems.map((item) => (
            <div key={item.id} className="flex justify-between">
              <span>{item.name} (x{item.quantity})</span>
              <span>Rp {item.price * item.quantity}</span>
            </div>
          ))}
        </CardContent>
      </Card>
      <Separator className="my-4" />
      <div className="flex justify-between">
        <h2 className="text-xl font-bold">Total Price</h2>
        <h2 className="text-xl font-bold">Rp {totalPrice}</h2>
      </div>
      <Button className="mt-4">Place Order</Button>
    </div>
  );
}
