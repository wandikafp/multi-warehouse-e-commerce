"use client";

import { useState, useEffect } from 'react';
import { useForm, FormProvider } from 'react-hook-form';
import Container from '@/components/container';
import AppPagination from '@/components/app-pagination';
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
import { warehouseService } from '@/lib/services';
import { PagedResponse } from '@/models/http/PagedResponse';
import { WarehouseResponse } from '@/models/warehouse/WarehouseResponse';

export default function AdminWarehousePage() {
    const [warehouses, setWarehouses] = useState<PagedResponse<WarehouseResponse> | null>(null);
    const [page, setPage] = useState(0);
    const [choosenWarehouse, setChoosenWarehouse] = useState<WarehouseResponse | null>(null);
    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
    const methods = useForm<Omit<WarehouseResponse, 'id'>>();

    const fetchData = () => {
        const jwtToken = localStorage.getItem("jwtToken");
        warehouseService
            .get<PagedResponse<WarehouseResponse>>(`/api/warehouse`, {
                headers: { Authorization: `Bearer ${jwtToken}` },
                params: { page },
            })
            .then((res) => setWarehouses(res.data));
    };

    const handleCreate = () => {
        setChoosenWarehouse(null);
        setIsModalOpen(true);
    };

    const handleEdit = (warehouse: WarehouseResponse) => {
        setChoosenWarehouse(warehouse);
        setIsModalOpen(true);
        methods.reset(warehouse);
    };

    const handleDelete = async (warehouseId: string) => {
        const jwtToken = localStorage.getItem("jwtToken");
        await warehouseService.delete(`/api/warehouse/${warehouseId}`, {
            headers: { Authorization: `Bearer ${jwtToken}` },
        }).then(() => alert('Warehouse deleted successfully'));
        fetchData();
    };

    const handleSubmit = async (data: Omit<WarehouseResponse, 'id'>) => {
        const jwtToken = localStorage.getItem("jwtToken");
        if (choosenWarehouse) {
            await warehouseService.put<WarehouseResponse>(
            `/api/warehouse`,
            data,
            {
                headers: { Authorization: `Bearer ${jwtToken}` },
            }
            ).then(() => alert('Warehouse updated successfully'));
            fetchData();
        } else {
            await warehouseService.post<WarehouseResponse>(
            '/api/warehouse',
            data,
            {
                headers: { Authorization: `Bearer ${jwtToken}` },
            }
            ).then(() => alert('Warehouse created successfully'));
            fetchData();
        }
        setIsModalOpen(false);
    };

    useEffect(() => {
        fetchData();
    }, [page]);

    return (
        <Container size="lg">
            <h1 className="text-2xl font-semibold mb-5">Manage Warehouse Data</h1>
            <Dialog open={isModalOpen} onOpenChange={setIsModalOpen}>
                <DialogTrigger asChild>
                    <Button onClick={handleCreate} className='mb-3'>Create Warehouse</Button>
                </DialogTrigger>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>
                            {choosenWarehouse ? 'Edit Warehouse' : 'Create Warehouse'}
                        </DialogTitle>
                    </DialogHeader>
                    <FormProvider {...methods}>
                        <form onSubmit={methods.handleSubmit(handleSubmit)}>
                            <FormItem>
                                <FormLabel>Name</FormLabel>
                                <FormControl>
                                    <Input
                                        defaultValue={choosenWarehouse?.name || ''}
                                        {...methods.register('name', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Street</FormLabel>
                                <FormControl>
                                    <Input
                                        defaultValue={choosenWarehouse?.street || ''}
                                        {...methods.register('street', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>City</FormLabel>
                                <FormControl>
                                    <Input
                                        defaultValue={choosenWarehouse?.city || ''}
                                        {...methods.register('city', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Province</FormLabel>
                                <FormControl>
                                    <Input
                                        defaultValue={choosenWarehouse?.province || ''}
                                        {...methods.register('province', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Postal Code</FormLabel>
                                <FormControl>
                                    <Input
                                        defaultValue={choosenWarehouse?.postalCode || ''}
                                        {...methods.register('postalCode', { required: true })}
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
                        <TableHead>Admin</TableHead>
                        <TableHead>Address</TableHead>
                        <TableHead>Actions</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {warehouses?.data.map((warehouse) => (
                        <TableRow key={warehouse.id}>
                            <TableCell>{warehouse.name}</TableCell>
                            <TableCell>{warehouse.adminId}</TableCell>
                            <TableCell>{warehouse.street}, {warehouse.city}, {warehouse.province}</TableCell>
                            <TableCell>
                                <Button onClick={() => handleEdit(warehouse)}>Edit</Button>
                                <Button onClick={() => handleDelete(warehouse.id)}>Delete</Button>
                            </TableCell>
                        </TableRow>
                    ))}
                    {!warehouses?.data ||
                    (warehouses.data.length === 0 && (
                    <TableRow>
                        <TableCell colSpan={9} className="text-center">
                        No warehouses
                        </TableCell>
                    </TableRow>
                    ))}
                </TableBody>
            </Table>

            {warehouses && (
                <AppPagination
                    hasNext={warehouses?.page + 1 < warehouses?.totalPages}
                    hasPrevious={warehouses?.page >= 1}
                    onNextPage={() => setPage(page + 1)}
                    onPreviousPage={() => setPage(page - 1)}
                />
            )}
        </Container>
    );
}
