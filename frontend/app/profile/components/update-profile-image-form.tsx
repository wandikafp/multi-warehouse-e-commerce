"use client";

import FileUpload from "@/components/file-upload";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Label } from "@/components/ui/label";
import { useAuthGuard } from "@/lib/auth/use-auth";
import { userService } from "@/lib/services";
import React from "react";
import { toast } from "sonner";

export default function UpdateProfileImageForm() {
  const { user, mutate } = useAuthGuard({middleware: "auth"});

  const handleLogoChange = (file: File) => {
    const formData = new FormData();
    formData.append("file", file);
    const jwtToken = localStorage.getItem("jwtToken");
    if (!jwtToken) throw new Error("Unauthorized");
    userService.patch(`/api/users/${user?.id}/profile-picture`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: `Bearer ${jwtToken}`
      },
    })
      .then(() => {
        toast.success("Profile picture updated successfully");
        mutate();
      })
      .catch((error) => {
        toast.error("Failed to update profile picture");
      });
  };

  return (
    <div className="flex gap-4 flex-col">
      <Label>Logo</Label>
      <FileUpload
        onFileSelect={(file) => handleLogoChange(file)}
        maxSize={1024 * 1024}
        allowedTypes={["image/png", "image/jpg", "image/jpeg", "image/gif"]}
        onValidationError={(err) => {
          toast.error(err);
        }}
      >
        <Avatar className="w-16 h-16">
          <AvatarImage src={user?.profileImageUrl} />
          <AvatarFallback>
            {user?.fullName?.substring(0, 1)}
          </AvatarFallback>
        </Avatar>
      </FileUpload>
    </div>
  );
}
