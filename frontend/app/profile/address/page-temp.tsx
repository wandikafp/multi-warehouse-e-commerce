"use client";

import { useState, useEffect } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import { useForm, FormProvider } from "react-hook-form";
import dynamic from "next/dynamic";
import L from "leaflet";
import "leaflet/dist/leaflet.css";
import { UserAddressResponse } from "@/models/user/UserAddressResponse";
import { userService } from "@/lib/services";
import { useAuthGuard } from "@/lib/auth/use-auth";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { FormItem, FormLabel, FormControl } from "@/components/ui/form";

const DynamicMap = dynamic(() => import("react-leaflet").then((mod) => mod.MapContainer), { ssr: false });
const TileLayer = dynamic(() => import("react-leaflet").then((mod) => mod.TileLayer), { ssr: false });
const Marker = dynamic(() => import("react-leaflet").then((mod) => mod.Marker), { ssr: false });
import { useMapEvents } from "react-leaflet";

const markerIcon = new L.Icon({
  iconUrl: "/images/marker-icon.png",
  shadowUrl: "/images/marker-shadow.png",
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  shadowSize: [41, 41],
});

export default function AddressDetail() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const addressId = searchParams.get("addressId");
  const methods = useForm<Omit<UserAddressResponse, "id">>();
  const [address, setAddress] = useState<UserAddressResponse | null>(null);
  const [position, setPosition] = useState<[number, number] | null>(null);
  const [isClient, setIsClient] = useState(false);

  const { user } = useAuthGuard({ middleware: "auth" });

  useEffect(() => {
    // This ensures the component renders only on the client
    setIsClient(true);
  }, []);

  const fetchAddress = async (id: string) => {
    const jwtToken = localStorage.getItem("jwtToken");
    const response = await userService.get<UserAddressResponse>(`/api/user-address/${id}`, {
      headers: { Authorization: `Bearer ${jwtToken}` },
    });
    const fetchedAddress = response.data;
    setAddress(fetchedAddress);
    setPosition([fetchedAddress.latitude, fetchedAddress.longitude]);
    methods.reset(fetchedAddress);
  };

  useEffect(() => {
    if (addressId) {
      fetchAddress(addressId);
    } else {
      methods.reset({
        street: "",
        city: "",
        province: "",
        postalCode: "",
        latitude: -6.2,
        longitude: 106.816666,
      });
      setPosition([-6.2, 106.816666]);
    }
  }, [addressId]);

  const handleSubmit = async (data: Omit<UserAddressResponse, "id">) => {
    const payload = { ...data, latitude: position?.[0], longitude: position?.[1], userId: user?.id };
    const jwtToken = localStorage.getItem("jwtToken");
    if (addressId) {
      await userService.put(`/api/user-address`, payload, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      });
    } else {
      await userService.post(`/api/user-address`, payload, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      });
    }
    alert("Address saved successfully");
    router.back();
  };

  const LocationMarker = () => {
    useMapEvents({
      click(e) {
        setPosition([e.latlng.lat, e.latlng.lng]);
      },
    });

    return position === null ? null : <Marker position={position} icon={markerIcon}></Marker>;
  };

  if (!isClient) {
    return null; // Ensure the component is client-rendered only
  }

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">{addressId ? "Edit Address" : "Add New Address"}</h1>
      <FormProvider {...methods}>
        <form onSubmit={methods.handleSubmit(handleSubmit)}>
          <FormItem>
            <FormLabel>Street</FormLabel>
            <FormControl>
              <Input
                defaultValue={address?.street || ""}
                {...methods.register("street", { required: true })}
              />
            </FormControl>
          </FormItem>
          <FormItem>
            <FormLabel>City</FormLabel>
            <FormControl>
              <Input
                defaultValue={address?.city || ""}
                {...methods.register("city", { required: true })}
              />
            </FormControl>
          </FormItem>
          <FormItem>
            <FormLabel>Province</FormLabel>
            <FormControl>
              <Input
                defaultValue={address?.province || ""}
                {...methods.register("province", { required: true })}
              />
            </FormControl>
          </FormItem>
          <FormItem>
            <FormLabel>Postal Code</FormLabel>
            <FormControl>
              <Input
                defaultValue={address?.postalCode || ""}
                {...methods.register("postalCode", { required: true })}
              />
            </FormControl>
          </FormItem>
          <div className="my-4">
            <DynamicMap center={position || [0, 0]} zoom={13} style={{ height: "400px", width: "100%" }}>
              <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
              <LocationMarker />
            </DynamicMap>
          </div>
          <Button type="submit">Save</Button>
        </form>
      </FormProvider>
    </div>
  );
}
