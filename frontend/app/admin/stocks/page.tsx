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
} from '@/components/ui/dialog';
import { FormItem, FormLabel, FormControl } from '@/components/ui/form';

interface Stock {
    id: string;
    productName: string;
    quantity: number;
}

const initialStocks: Stock[] = [
    { id: '1', productName: 'Product 1', quantity: 100 },
    { id: '2', productName: 'Product 2', quantity: 200 },
];

export default function AdminStocksPage() {
    const [stocks, setStocks] = useState<Stock[]>(initialStocks);
    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
    const [currentStock, setCurrentStock] = useState<Stock | null>(null);
    const methods = useForm<Pick<Stock, 'productName' | 'quantity'>>();

    const handleCreate = () => {
        setCurrentStock(null);
        setIsModalOpen(true);
        methods.reset({ productName: '', quantity: 0 });
    };

    const handleEdit = (stock: Stock) => {
        setCurrentStock(stock);
        setIsModalOpen(true);
        methods.reset({ quantity: stock.quantity });
    };

    const handleDelete = (stockId: string) => {
        setStocks(stocks.filter((stock) => stock.id !== stockId));
    };

    const handleSubmit = (data: Pick<Stock, 'productName' | 'quantity'>) => {
        if (currentStock) {
            setStocks(stocks.map((stock) =>
                stock.id === currentStock.id ? { ...stock, quantity: data.quantity } : stock
            ));
        } else {
            setStocks([...stocks, { id: (stocks.length + 1).toString(), ...data }]);
        }
        setIsModalOpen(false);
    };

    return (
        <Container size="lg">
            <h1 className="text-2xl font-semibold mb-5">Manage Product Stocks</h1>
            <Dialog open={isModalOpen} onOpenChange={setIsModalOpen}>
                <DialogTrigger asChild>
                    <Button onClick={handleCreate} className='mb-3'>Add Stock</Button>
                </DialogTrigger>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>
                            {currentStock ? `Update Stock for ${currentStock.productName}` : 'Add Stock'}
                        </DialogTitle>
                    </DialogHeader>
                    <FormProvider {...methods}>
                        <form onSubmit={methods.handleSubmit(handleSubmit)}>
                            {!currentStock && (
                                <FormItem>
                                    <FormLabel>Product Name</FormLabel>
                                    <FormControl>
                                        <Input
                                            {...methods.register('productName', { required: true })}
                                        />
                                    </FormControl>
                                </FormItem>
                            )}
                            <FormItem>
                                <FormLabel>Quantity</FormLabel>
                                <FormControl>
                                    <Input
                                        type="number"
                                        {...methods.register('quantity', { required: true })}
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
                        <TableHead>Product Name</TableHead>
                        <TableHead>Quantity</TableHead>
                        <TableHead>Actions</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {stocks.map((stock) => (
                        <TableRow key={stock.id}>
                            <TableCell>{stock.productName}</TableCell>
                            <TableCell>{stock.quantity}</TableCell>
                            <TableCell>
                                <Button onClick={() => handleEdit(stock)}>Edit</Button>
                                <Button onClick={() => handleDelete(stock.id)} className="ml-2">Delete</Button>
                            </TableCell>
                        </TableRow>
                    ))}
                    {stocks.length === 0 && (
                        <TableRow>
                            <TableCell colSpan={3} className="text-center">
                                No stocks available
                            </TableCell>
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </Container>
    );
}
