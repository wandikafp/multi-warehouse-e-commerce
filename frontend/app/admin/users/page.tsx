"use client";

import React, { useState } from 'react';
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
import { SelectValue, Select, SelectContent, SelectTrigger, SelectItem } from '@/components/ui/select';
import useSWR from 'swr';
import { userService } from '@/lib/services';
import { PagedResponse } from '@/models/http/PagedResponse';
import { UserResponse } from '@/models/user/UserResponse';

export default function AdminUserPage() {
    const [users, setUsers] = useState<UserResponse[]>([]);
    const [page, setPage] = React.useState(0);

    const fetcher = (url: string) => {
        const jwtToken = localStorage.getItem("jwtToken");
        // if (!jwtToken) throw new Error("Unauthorized");
        return userService
            .get<PagedResponse<UserResponse>>(url, {
                headers: { Authorization: `Bearer ${jwtToken}` },
                params: { page },
            })
            .then((res) => res.data);
    };

    const { data } = useSWR(`/api/users?page=${page}`, fetcher);

    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
    const [currentUser, setCurrentUser] = useState<UserResponse | null>(null);
    const methods = useForm<Omit<UserResponse, 'id'>>();

    const handleCreate = () => {
        setCurrentUser(null);
        setIsModalOpen(true);
    };

    const handleEdit = (user: UserResponse) => {
        setCurrentUser(user);
        setIsModalOpen(true);
        methods.reset(user);
    };

    const handleDelete = (userId: string) => {
        if (data?.data) {
            setUsers(data.data.filter((user) => user.id !== userId));
        }
    };

    const handleSubmit = (data: Omit<UserResponse, 'id'>) => {
        if (currentUser) {
            setUsers((prevUsers) =>
                prevUsers.map((user) =>
                    user.id === currentUser.id ? { ...user, ...data } : user
                )
            );
        } else {
            setUsers((prevUsers) => [
                ...prevUsers,
                { id: (prevUsers.length + 1).toString(), ...data },
            ]);
        }
        setIsModalOpen(false);
    };

    return (
        <Container size="lg">
            <h1 className="text-2xl font-semibold mb-5">Manage User Data</h1>
            <Dialog open={isModalOpen} onOpenChange={setIsModalOpen}>
                <DialogTrigger asChild>
                    <Button onClick={handleCreate} className='mb-3'>Create User</Button>
                </DialogTrigger>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>
                            {currentUser ? 'Edit User' : 'Create User'}
                        </DialogTitle>
                    </DialogHeader>
                    <FormProvider {...methods}>
                        <form onSubmit={methods.handleSubmit(handleSubmit)}>
                            <FormItem>
                                <FormLabel>Name</FormLabel>
                                <FormControl>
                                    <Input
                                        defaultValue={currentUser?.fullName || ''}
                                        {...methods.register('fullName', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Email</FormLabel>
                                <FormControl>
                                    <Input
                                        type="email"
                                        defaultValue={currentUser?.email || ''}
                                        {...methods.register('email', { required: true })}
                                    />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Role</FormLabel>
                                <Select defaultValue={currentUser?.role || 'user'}
                                        {...methods.register('role', { required: true })}>
                                    <FormControl>
                                        <SelectTrigger>
                                            <SelectValue placeholder="Select a verified email to display" />
                                        </SelectTrigger>
                                    </FormControl>
                                    <SelectContent>
                                        <SelectItem value="SUPER_ADMIN">SUPER_ADMIN</SelectItem>
                                        <SelectItem value="WAREHOUSE ADMIN">WAREHOUSE ADMIN</SelectItem>
                                        <SelectItem value="CUSTOMER">CUSTOMER</SelectItem>
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
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead>Name</TableHead>
                        <TableHead>Email</TableHead>
                        <TableHead>Role</TableHead>
                        <TableHead>Actions</TableHead>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {data?.data.map((user) => (
                        <TableRow key={user.id}>
                            <TableCell>{user.fullName}</TableCell>
                            <TableCell>{user.email}</TableCell>
                            <TableCell>{user.role}</TableCell>
                            <TableCell>
                                <Button onClick={() => handleEdit(user)} disabled={user.role === 'CUSTOMER'}>Edit</Button>
                                <Button onClick={() => handleDelete(user.id)} disabled={user.role === 'CUSTOMER'}>Delete</Button>
                            </TableCell>
                        </TableRow>
                    ))}
                    {!data?.data ||
                    (data.data.length === 0 && (
                    <TableRow>
                        <TableCell colSpan={9} className="text-center">
                        No users
                        </TableCell>
                    </TableRow>
                    ))}
                </TableBody>
            </Table>

            {data && (
                <AppPagination
                    hasNext={data?.page + 1 < data?.totalPages}
                    hasPrevious={data?.page >= 1}
                    onNextPage={() => setPage(page + 1)}
                    onPreviousPage={() => setPage(page - 1)}
                />
            )}
        </Container>
    );
}
