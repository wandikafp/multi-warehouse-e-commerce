"use client";

import { Card, CardHeader, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { useRouter } from "next/navigation";
import { productService, userService, orderService } from "@/lib/services";
import { ProductResponse } from "@/models/product/ProductResponse";
import useSWR from 'swr';
import { useAuthGuard } from "@/lib/auth/use-auth";
import { CartItemCommand } from "@/models/order/CartItemCommand";

interface Params {
  id: string;
}

export default function ProductDetail({ params }: { params: Params }) {
  const router = useRouter();
  const { user } = useAuthGuard({ middleware: "auth" });
  const { id } = params;

  const fetcher = (url: string) => {
    const jwtToken = localStorage.getItem("jwtToken");
    return productService
        .get<ProductResponse>(url, {
            headers: { Authorization: `Bearer ${jwtToken}` },
        })
        .then((res) => res.data);
  };

  const { data } = useSWR(`/api/product/${id}`, fetcher);

  const handleAddToCart = async () => {
    const jwtToken = localStorage.getItem("jwtToken");
    if (!jwtToken) {
      alert("Unauthorized");
      return;
    }
    try {
      await orderService.post(
        `/api/cart/${user?.id}/add`,
        {
          productId: data?.id,
          name: data?.name,
          price: data?.price,
          stock: data?.stockQuantity,
          imgUrl: data?.imageUrl,
          quantity: 1,
          subTotal: data?.price,
        },
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            "Content-Type": "application/json",
          },
        }
      );
      alert("Product added to cart!");
    } catch (error) {
      alert("Failed to add product to cart");
    }
  };

  if (!data) {
    return (
      <div className="container mx-auto p-6">
        <h1 className="text-2xl font-bold">Product not found</h1>
        <Button onClick={() => router.back()}>Go Back</Button>
      </div>
    );
  }

  return (
    <div className="container mx-auto p-6">
      <Card>
        <CardHeader>
          <img
            src={data.imageUrl}
            alt={data.name}
            className="h-60 w-full object-cover mb-4"
          />
          <h1 className="text-3xl font-bold">{data.name}</h1>
        </CardHeader>
        <CardContent>
          <p className="text-lg">{data.description}</p>
          <p className="text-xl font-semibold mt-4">Price: ${data.price}</p>
          <p
            className={`text-lg mt-2 ${
              data.stockQuantity > 0 ? "text-green-500" : "text-red-500"
            }`}
          >
            {data.stockQuantity > 0
              ? `Stock Available: ${data.stockQuantity}`
              : "Out of Stock"}
          </p>
        </CardContent>
        <CardFooter className="mt-4">
          <Button
            disabled={data.stockQuantity === 0}
            onClick={handleAddToCart}
          >
            {data.stockQuantity > 0 ? "Add to Cart" : "Unavailable"}
          </Button>
        </CardFooter>
      </Card>
    </div>
  );
}
