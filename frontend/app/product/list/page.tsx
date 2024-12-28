"use client";

import { Card, CardHeader, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import Link from "next/link";
import AppPagination from '@/components/app-pagination';
import { SetStateAction, useState } from "react";
import { productService } from "@/lib/services";
import { PagedResponse } from '@/models/http/PagedResponse';
import { ProductResponse } from '@/models/product/ProductResponse';
import useSWR from 'swr';

export default function ProductList() {
  const [searchQuery, setSearchQuery] = useState("");
  const [page, setPage] = useState(0);
  const [data, setData] = useState<PagedResponse<ProductResponse> | null>(null);

  const fetcher = (url: string) => {
    const jwtToken = localStorage.getItem("jwtToken");
    // if (!jwtToken) throw new Error("Unauthorized");
    return productService
        .get<PagedResponse<ProductResponse>>(url, {
            headers: { Authorization: `Bearer ${jwtToken}` },
            params: { page },
        })
        .then((res) => res.data);
  };

  const { data: apiData } = useSWR(`/api/product?page=${page}`, fetcher, {
    onSuccess: (fetchedData) => setData(fetchedData),
  });

  const handleSearch = () => {
    productService
      .get<PagedResponse<ProductResponse>>(`/api/product`, {
        headers: { Authorization: `Bearer ${localStorage.getItem("jwtToken")}` },
        params: { page, query: searchQuery },
      })
      .then((res) => setData(res.data));
  };

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Product Catalog</h1>

      <div className="flex mb-6">
        <Input
          placeholder="Search products..."
          value={searchQuery}
          onChange={(e: { target: { value: SetStateAction<string>; }; }) => setSearchQuery(e.target.value)}
          className="mr-2 mb-6"
        />
        <Button onClick={handleSearch}>Search</Button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        {data?.data.map((product) => (
          <Card key={product.id}>
            <CardHeader>
              <img
                src={product.imageUrl}
                alt={product.name}
                className="h-40 w-full object-cover"
              />
              <h2 className="text-lg font-semibold">{product.name}</h2>
            </CardHeader>
            <CardContent>
              <p className="text-lg font-bold">Price: Rp {product.price}</p>
              <p
                className={`text-sm ${
                  product.stockQuantity > 0 ? "text-green-500" : "text-red-500"
                }`}
              >
                {product.stockQuantity > 0 ? `Stock: ${product.stockQuantity}` : "Out of Stock"}
              </p>
            </CardContent>
            <CardFooter>
              <div className="flex justify-between items-center">
                <Button
                  disabled={product.stockQuantity === 0}
                  onClick={() => alert("Product added to cart!")}
                >
                  {product.stockQuantity > 0 ? "Add to Cart" : "Unavailable"}
                </Button>
                <Link href={`/product/${product.id}`}>
                  <Button variant="outline">View Details</Button>
                </Link>
              </div>
            </CardFooter>
          </Card>
        ))}
      </div>
      {data && (
          <AppPagination
              hasNext={data?.page + 1 < data?.totalPages}
              hasPrevious={data?.page >= 1}
              onNextPage={() => setPage(page + 1)}
              onPreviousPage={() => setPage(page - 1)}
          />
      )}
    </div>
  );
}
