"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { Separator } from "@/components/ui/separator";
import { useAuthGuard } from "@/lib/auth/use-auth";
import { userService } from "@/lib/services";
import { UserAddressResponse } from "@/models/user/UserAddressResponse";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Trash2 } from "lucide-react";

export default function AddressList() {
  const [addresses, setAddresses] = useState<UserAddressResponse[] | null>(null);
  const router = useRouter();

  const { user } = useAuthGuard({ middleware: "auth" });

  const fetchData = () => {
    if (!user) return; // Ensure user is defined before making the API call
    const jwtToken = localStorage.getItem("jwtToken");
    userService
      .get<UserAddressResponse[]>(`/api/user-address`, {
        headers: { Authorization: `Bearer ${jwtToken}` },
        params: { userId : user.id }
      })
      .then((res) => setAddresses(res.data));
  };

  useEffect(() => {
    fetchData();
  }, [user]);

  const handleEdit = (addressId: string) => {
    router.push(`/profile-address?addressId=${addressId}`);
  };

  const handleCreate = () => {
    router.push(`/profile-address`);
  };

    function handleDelete(id: string): void {
        const jwtToken = localStorage.getItem("jwtToken");
        userService.delete(`/api/user-address/${id}`, {
            headers: { Authorization: `Bearer ${jwtToken}` },
        }).then(() => alert('Address deleted successfully'));
        fetchData();
    }

  return (
    <div>
      <h2 className="text-lg font-semibold mb-2">Address Book</h2>
      <div className="flex flex-col gap-y-4">
        {addresses?.map((address) => (
          <Card key={address.id}>
            <CardHeader>
              <CardTitle>{address.street}, {address.city}</CardTitle>
            </CardHeader>
            <CardContent>
              <p>{address.province}, {address.postalCode}</p>
              <Button variant="default" onClick={() => handleEdit(address.id)} className="mt-2">Edit</Button>
              <Button variant="destructive" onClick={() => handleDelete(address.id)} className="mt-2 ml-2">
                    <Trash2 />
                    Delete
                </Button>
            </CardContent>
          </Card>
        ))}
      </div>
      <Separator className="my-4" />
      <Button type="button" onClick={handleCreate}>Add New Address</Button>
    </div>
  );
};
