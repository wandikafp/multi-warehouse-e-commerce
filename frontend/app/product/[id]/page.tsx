"use client";

import { Card, CardHeader, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { useRouter } from "next/navigation";

const products = [
  {
    id: 1,
    name: "Product A",
    description: "This is Product A.",
    price: 100,
    stock: 10,
    image: "/images/productA.jpg",
  },
  {
    id: 2,
    name: "Product B",
    description: "This is Product B.",
    price: 150,
    stock: 0,
    image: "/images/productB.jpg",
  },
  {
    id: 3,
    name: "Product C",
    description: "This is Product C.",
    price: 200,
    stock: 5,
    image: "/images/productC.jpg",
  },
];

interface Params {
  id: string;
}

export default function ProductDetail({ params }: { params: Params }) {
  const router = useRouter();
  const { id } = params;

  const product = products.find((p) => p.id === parseInt(id));

  if (!product) {
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
            src={product.image}
            alt={product.name}
            className="h-60 w-full object-cover mb-4"
          />
          <h1 className="text-3xl font-bold">{product.name}</h1>
        </CardHeader>
        <CardContent>
          <p className="text-lg">{product.description}</p>
          <p className="text-xl font-semibold mt-4">Price: ${product.price}</p>
          <p
            className={`text-lg mt-2 ${
              product.stock > 0 ? "text-green-500" : "text-red-500"
            }`}
          >
            {product.stock > 0
              ? `Stock Available: ${product.stock}`
              : "Out of Stock"}
          </p>
        </CardContent>
        <CardFooter className="mt-4">
          <Button
            disabled={product.stock === 0}
            onClick={() => alert("Product added to cart!")}
          >
            {product.stock > 0 ? "Add to Cart" : "Unavailable"}
          </Button>
        </CardFooter>
      </Card>
    </div>
  );
}
