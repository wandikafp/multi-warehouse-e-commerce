"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { useForm, FormProvider } from "react-hook-form";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { FormItem, FormLabel, FormControl } from "@/components/ui/form";
import axios from "axios";
import dynamic from "next/dynamic";
import { MapContainer, TileLayer, Marker, useMapEvents } from "react-leaflet";
import L from "leaflet";
import "leaflet/dist/leaflet.css";

interface Params {
  id: string;
}

const DynamicMap = dynamic(() => import("react-leaflet").then(mod => mod.MapContainer), { ssr: false });

const markerIcon = new L.Icon({
  iconUrl: "/images/marker-icon.png",
  shadowUrl: "/images/marker-shadow.png",
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  shadowSize: [41, 41],
});

export default function AddressDetail({ params }: { params: Params }) {
  const router = useRouter();
  const { id } = params;
  const methods = useForm<Omit<AddressResponse, 'id'>>();
  const [address, setAddress] = useState<AddressResponse | null>(null);
  const [position, setPosition] = useState<[number, number] | null>(null);
  const [searchQuery, setSearchQuery] = useState("");

  const fetchAddress = () => {
    // Static data for testing
    const staticAddress = {
      id: "1",
      street: "123 Main St",
      city: "Anytown",
      state: "Anystate",
      postalCode: "12345",
      latitude: -6.200000,
      longitude: 106.816666,
    };
    setAddress(staticAddress);
    setPosition([staticAddress.latitude, staticAddress.longitude]);
    methods.reset(staticAddress);
  };

  useEffect(() => {
    fetchAddress();
  }, [id]);

  const handleSubmit = async (data: Omit<AddressResponse, 'id'>) => {
    // Simulate API call
    console.log("Updated address data:", { ...data, latitude: position?.[0], longitude: position?.[1] });
    alert('Address updated successfully');
    router.back();
  };

  const handleSearch = async () => {
    const apiKey = "YOUR_OPENCAGE_API_KEY"; // Replace with your OpenCage API key
    const response = await axios.get(`https://api.opencagedata.com/geocode/v1/json?q=${encodeURIComponent(searchQuery)}&key=${apiKey}`);
    const { lat, lng } = response.data.results[0].geometry;
    setPosition([lat, lng]);
  };

  const LocationMarker = () => {
    useMapEvents({
      click(e) {
        setPosition([e.latlng.lat, e.latlng.lng]);
      },
    });

    return position === null ? null : (
      <Marker position={position} icon={markerIcon}></Marker>
    );
  };

  if (!address) {
    return (
      <>
        <div className="container mx-auto p-6">
        </div>
        <h1 className="text-2xl font-bold">Address not found</h1>
        <Button onClick={() => router.back()}>Go Back</Button></>
    );
  }

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">Edit Address</h1>
      <FormProvider {...methods}>
        <form onSubmit={methods.handleSubmit(handleSubmit)}>
          <FormItem>
            <FormLabel>Street</FormLabel>
            <FormControl>
              <Input
                defaultValue={address.street || ''}
                {...methods.register('street', { required: true })}
              />
            </FormControl>
          </FormItem>
          <FormItem>
            <FormLabel>City</FormLabel>
            <FormControl>
              <Input
                defaultValue={address.city || ''}
                {...methods.register('city', { required: true })}
              />
            </FormControl>
          </FormItem>
          <FormItem>
            <FormLabel>State</FormLabel>
            <FormControl>
              <Input
                defaultValue={address.state || ''}
                {...methods.register('state', { required: true })}
              />
            </FormControl>
          </FormItem>
          <FormItem>
            <FormLabel>Postal Code</FormLabel>
            <FormControl>
              <Input
                defaultValue={address.postalCode || ''}
                {...methods.register('postalCode', { required: true })}
              />
            </FormControl>
          </FormItem>
          <div className="my-4">
            <Input
              placeholder="Search address..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="mb-2"
            />
            <Button type="button" onClick={handleSearch}>Search</Button>
          </div>
          <div className="my-4">
            <DynamicMap center={position || [0, 0]} zoom={13} style={{ height: "400px", width: "100%" }}>
              <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
              />
              <LocationMarker />
            </DynamicMap>
          </div>
          <Button type="submit">Save</Button>
        </form>
      </FormProvider>
    </div>
  );
}
