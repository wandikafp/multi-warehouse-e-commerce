"use client";

import { Card, CardHeader, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import Link from "next/link";
import { SetStateAction, useState } from "react";

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

export default function ProductList() {
  const [searchQuery, setSearchQuery] = useState("");

  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Product Catalog</h1>

      <Input
        placeholder="Search products..."
        value={searchQuery}
        onChange={(e: { target: { value: SetStateAction<string>; }; }) => setSearchQuery(e.target.value)}
        className="mb-6"
      />

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        {filteredProducts.map((product) => (
          <Card key={product.id}>
            <CardHeader>
              <img
                src={product.image}
                alt={product.name}
                className="h-40 w-full object-cover"
              />
              <h2 className="text-lg font-semibold">{product.name}</h2>
            </CardHeader>
            <CardContent>
              <p className="text-sm text-gray-600">{product.description}</p>
              <p className="text-lg font-bold">Price: ${product.price}</p>
              <p
                className={`text-sm ${
                  product.stock > 0 ? "text-green-500" : "text-red-500"
                }`}
              >
                {product.stock > 0 ? `Stock: ${product.stock}` : "Out of Stock"}
              </p>
            </CardContent>
            <CardFooter>
              <div className="flex justify-between items-center">
                <Button
                  disabled={product.stock === 0}
                  onClick={() => alert("Product added to cart!")}
                >
                  {product.stock > 0 ? "Add to Cart" : "Unavailable"}
                </Button>
                <Link href={`/product/${product.id}`}>
                  <Button variant="outline">View Details</Button>
                </Link>
              </div>
            </CardFooter>
          </Card>
        ))}
      </div>
    </div>
  );
}
