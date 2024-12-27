"use client";

import React, { useState } from 'react';
import { useForm, FormProvider } from 'react-hook-form';
import Container from '@/components/container';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import {
    Table,
    TableHeader,
    TableBody,
    TableRow,
    TableHead,
    TableCell,
} from '@/components/ui/table';
import {
    Dialog,
    DialogTrigger,
    DialogContent,
    DialogHeader,
    DialogFooter,
    DialogTitle,
    DialogClose,
} from '@/components/ui/dialog';
import { FormItem, FormLabel, FormControl } from '@/components/ui/form';
import useSWR from 'swr';
import { productService } from '@/lib/services';
import { CategoryResponse } from '@/models/product/CategoryResponse';

export default function AdminCategoryPage() {
    const [categories, setCategories] = useState<CategoryResponse[]>([]);

    const fetcher = (url: string) => {
        const jwtToken = localStorage.getItem("jwtToken");
        return productService
            .get<CategoryResponse[]>(url, {
                headers: { Authorization: `Bearer ${jwtToken}` },
            })
            .then((res) => res.data);
    };

    const { data } = useSWR('/api/categories', fetcher);

    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
    const [currentCategory, setCurrentCategory] = useState<CategoryResponse | null>(null);
    const methods = useForm<Omit<CategoryResponse, 'id'>>();

    const handleCreate = () => {
        setCurrentCategory(null);
        setIsModalOpen(true);
    };

    const handleEdit = (category: CategoryResponse) => {
        setCurrentCategory(category);
        setIsModalOpen(true);
        methods.reset(category);
    };

    const handleDelete = async (categoryId: string) => {
        const jwtToken = localStorage.getItem("jwtToken");
        await productService.delete(`/api/categories/${categoryId}`, {
            headers: { Authorization: `Bearer ${jwtToken}` },
        });
        setCategories(categories.filter((category) => category.id !== categoryId));
    };

    const handleSubmit = async (data: Omit<CategoryResponse, 'id'>) => {
        const jwtToken = localStorage.getItem("jwtToken");
        if (currentCategory) {
            const updatedCategory = await productService.put<CategoryResponse>(
                `/api/categories`,
                data,
                {
                    headers: { Authorization: `Bearer ${jwtToken}` },
                }
            );
            setCategories((prevCategories) =>
                prevCategories.map((category) =>
                    category.id === currentCategory.id ? updatedCategory.data : category
                )
            );
        } else {
            const newCategory = await productService.post<CategoryResponse>(
                '/api/categories',
                data,
                {
                    headers: { Authorization: `Bearer ${jwtToken}` },
                }
            );
            setCategories((prevCategories) => [
                ...prevCategories,
                newCategory.data,
            ]);
        }
        setIsModalOpen(false);
    };

    return (
        <Container size="lg">
            <h1 className="text-2xl font-semibold mb-5">Manage Product Categories</h1>
            <Dialog open={isModalOpen} onOpenChange={setIsModalOpen}>
                <DialogTrigger asChild>
                    <Button onClick={handleCreate} className='mb-3'>Create Category</Button>
                </DialogTrigger>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>
                            {currentCategory ? 'Edit Category' : 'Create Category'}
                        </DialogTitle>
                    </DialogHeader>
                    <FormProvider {...methods}>
                        <form onSubmit={methods.handleSubmit(handleSubmit)}>
                            <FormItem>
                                <FormLabel>Name</FormLabel>
                                <FormControl>
                                    <Input
                                        defaultValue={currentCategory?.name || ''}
                                        {...methods.register('name', { required: true })}
                                        disabled={currentCategory !== null}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Description</FormLabel>
                                <FormControl>
                                    <Input
                                        type="description"
                                        defaultValue={currentCategory?.description || ''}
                                        {...methods.register('description', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Icon</FormLabel>
                                <FormControl>
                                    <Input
                                        type="icon"
                                        defaultValue={currentCategory?.icon || ''}
                                        {...methods.register('icon', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <DialogFooter>
                                <Button type="submit">Save</Button>
                            </DialogFooter>
                        </form>
                    </FormProvider>
                </DialogContent>
            </Dialog>
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead>Name</TableHead>
                        <TableHead>Description</TableHead>
                        <TableHead>Icon</TableHead>
                        <TableHead>Actions</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {data?.map((category) => (
                        <TableRow key={category.id}>
                            <TableCell>{category.name}</TableCell>
                            <TableCell>{category.description}</TableCell>
                            <TableCell>{category.icon}</TableCell>
                            <TableCell>
                                <Button onClick={() => handleEdit(category)}>Edit</Button>
                                <Button onClick={() => handleDelete(category.id)}>Delete</Button>
                            </TableCell>
                        </TableRow>
                    ))}
                    {!data ||
                    (data.length === 0 && (
                    <TableRow>
                        <TableCell colSpan={9} className="text-center">
                        No categories
                        </TableCell>
                    </TableRow>
                    ))}
                </TableBody>
            </Table>
        </Container>
    );
}
