"use client";

import { Card, CardHeader, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import AppPagination from '@/components/app-pagination';
import {
  Dialog,
  DialogTrigger,
  DialogContent,
  DialogHeader,
  DialogFooter,
  DialogTitle,
} from '@/components/ui/dialog';
import { FormItem, FormLabel, FormControl } from '@/components/ui/form';
import { SelectValue, Select, SelectContent, SelectTrigger, SelectItem } from '@/components/ui/select';
import { SetStateAction, useState, useEffect } from "react";
import { useForm, FormProvider } from 'react-hook-form';
import { productService } from "@/lib/services";
import { PagedResponse } from '@/models/http/PagedResponse';
import { ProductResponse } from '@/models/product/ProductResponse';
import { CategoryResponse } from '@/models/product/CategoryResponse';
import { Trash2 } from 'lucide-react';

export default function ProductList() {
  const [searchQuery, setSearchQuery] = useState("");
  const [page, setPage] = useState(0);
  const [data, setData] = useState<PagedResponse<ProductResponse> | null>(null);
  const [categories, setCategories] = useState<CategoryResponse[]>([]);
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
  const [choosenProduct, setChoosenProduct] = useState<ProductResponse | null>(null);

  const methods = useForm<Omit<ProductResponse, 'id'>>();

  const fetchData = () => {
    const jwtToken = localStorage.getItem("jwtToken");
    productService
      .get<PagedResponse<ProductResponse>>(`/api/product`, {
        headers: { Authorization: `Bearer ${jwtToken}` },
        params: { page, query: searchQuery },
      })
      .then((res) => setData(res.data));
  };

  const fetchCategories = () => {
    const jwtToken = localStorage.getItem("jwtToken");
    productService
      .get<CategoryResponse[]>(`/api/categories`, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      })
      .then((res) => setCategories(res.data));
  };

  const handleSearch = () => {
    fetchData();
  };

  const handleCreate = () => {
    methods.reset();
    setChoosenProduct(null);
    setIsModalOpen(true);
  };

  const handleEdit = (product: ProductResponse) => {
    methods.reset(product);
    setChoosenProduct(product);
    setIsModalOpen(true);
  };

  const handleDelete = async (productId: string) => {
      const jwtToken = localStorage.getItem("jwtToken");
      await productService.delete(`/api/product/${productId}`, {
          headers: { Authorization: `Bearer ${jwtToken}` },
      }).then(() => alert('Product deleted successfully'));
      fetchData();
  };

  const handleSubmit = async (data: Omit<ProductResponse, 'id'>) => {
    const jwtToken = localStorage.getItem("jwtToken");
    if (choosenProduct) {
      await productService.put<ProductResponse>(
        `/api/product`,
        data,
        {
          headers: { Authorization: `Bearer ${jwtToken}` },
        }
      ).then(() => alert('Product updated successfully'));
      fetchData();
    } else {
      await productService.post<ProductResponse>(
        '/api/product',
        data,
        {
          headers: { Authorization: `Bearer ${jwtToken}` },
        }
      ).then(() => alert('Product created successfully'));
      fetchData();
    }
    setIsModalOpen(false);
  };

  // Fetch data on initial render and when page changes
  useEffect(() => {
    fetchData();
    fetchCategories();
  }, [page]);

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

      <Dialog open={isModalOpen} onOpenChange={setIsModalOpen}>
          <DialogTrigger asChild>
              <Button onClick={handleCreate} className='mb-3'>Create Product</Button>
          </DialogTrigger>
          <DialogContent>
              <DialogHeader>
                  <DialogTitle>
                      {choosenProduct ? 'Edit Product' : 'Create Product'}
                  </DialogTitle>
                  <img
                    src={choosenProduct?.imageUrl}
                    alt={choosenProduct?.name}
                    className="h-40 w-full object-cover"
                  />
              </DialogHeader>
              <FormProvider {...methods}>
                  <form onSubmit={methods.handleSubmit(handleSubmit)}>
                      <FormItem>
                          <FormLabel>Name</FormLabel>
                          <FormControl>
                              <Input
                                  defaultValue={choosenProduct?.name || ''}
                                  {...methods.register('name', { required: true })}
                              />
                          </FormControl>
                      </FormItem>
                      <FormItem>
                          <FormLabel>Description</FormLabel>
                          <FormControl>
                              <Input
                                  type="description"
                                  defaultValue={choosenProduct?.description || ''}
                                  {...methods.register('description', { required: true })}
                              />
                          </FormControl>
                      </FormItem>
                      <FormItem>
                          <FormLabel>Price</FormLabel>
                          <FormControl>
                              <Input
                                  type="price"
                                  defaultValue={choosenProduct?.price || ''}
                                  {...methods.register('price', { required: true })}
                              />
                          </FormControl>
                      </FormItem>
                      <FormItem>
                          <FormLabel>Category</FormLabel>
                          <Select
                              defaultValue={choosenProduct?.category?.id || ''}
                              onValueChange={(value) => methods.setValue('category.id', value)}
                          >
                              <FormControl>
                                  <SelectTrigger>
                                      <SelectValue placeholder="Select category" />
                                  </SelectTrigger>
                              </FormControl>
                              <SelectContent>
                                  {categories.map((category) => (
                                    <SelectItem key={category.id} value={category.id}>
                                      {category.name}
                                    </SelectItem>
                                  ))}
                              </SelectContent>
                          </Select>
                      </FormItem>
                      <DialogFooter>
                          <Button type="submit">Save</Button>
                      </DialogFooter>
                  </form>
              </FormProvider>
          </DialogContent>
      </Dialog>

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
              <p className="text-lg font-bold">Category: {product.category?.name}</p>
            </CardContent>
            <CardFooter>
              <div className="flex justify-between items-center">
                <Button onClick={() => handleEdit(product)} className="mr-2">Edit</Button>
                <Button variant="destructive" onClick={() => handleDelete(product.id)}>
                    <Trash2 />
                    Delete
                </Button>
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
