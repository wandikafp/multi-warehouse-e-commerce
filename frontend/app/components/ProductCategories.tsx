"use client";

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { productService } from "@/lib/services";
import { CategoryResponse } from "@/models/product/CategoryResponse";
import useSWR from "swr";

export default function ProductCategories() {

  const fetcher = (url: string) => {
    const jwtToken = localStorage.getItem("jwtToken");
    // if (!jwtToken) throw new Error("Unauthorized");
    return productService
      .get<CategoryResponse[]>(url, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      })
      .then((res) => res.data);
  };

  const { data } = useSWR('/api/categories', fetcher);

  return (
    <div className="py-12 bg-gray-100">
      <div className="container mx-auto px-6">
        <h2 className="text-3xl font-bold text-center mb-8">Product Categories</h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {data?.map((category) => (
            <Card
              key={category.id}
              className="hover:shadow-lg transition-shadow duration-300"
            >
              <CardHeader className="text-center">
                <div
                  className="text-5xl mb-4"
                  role="img"
                  aria-label={category.name}
                >
                  {category.icon}
                </div>
                <CardTitle className="text-xl font-semibold">
                  {category.name}
                </CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-center text-gray-600">
                  {category.description}
                </p>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </div>
  );
}
