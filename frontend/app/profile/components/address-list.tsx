import React from "react";
import { useRouter } from "next/router";
import { Separator } from "@/components/ui/separator";

const AddressList = ({ addresses }) => {
  const router = useRouter();

  const handleEdit = (addressId) => {
    router.push(`/profile/address?addressId=${addressId}`);
  };

  const handleCreate = () => {
    router.push(`/profile/address`);
  };

  return (
    <div>
      <h2 className="text-lg font-semibold mb-2">Address Book</h2>
      <div className="flex flex-col gap-y-2">
        {addresses.map((address) => (
          <div key={address.id} className="flex justify-between items-center">
            <div>
              <p>{address.street}, {address.city}, {address.state}, {address.zipCode}</p>
              <p>{address.country}</p>
            </div>
            <button onClick={() => handleEdit(address.id)} className="text-blue-500">Edit</button>
          </div>
        ))}
      </div>
      <Separator />
      <button onClick={handleCreate} className="mt-4 text-blue-500">Add New Address</button>
    </div>
  );
};

export default AddressList;
