"use client";

import React from 'react';
import { ReactNode } from 'react';
import "../globals.css";
// import { useAuthGuard } from "@/lib/auth/use-auth";
// import Loading from "@/components/loading";
// import PermissionGuard from "@/components/permission-guard";
// import RoleGuard from "@/components/role-guard";
// import { Role } from "@/models/user/UserResponse";

export default function AdminLayout({
    children,
}: Readonly<{
    children: ReactNode;
}>) {
    // const { user } = useAuthGuard({ middleware: "auth" });

    // if (!user) return <Loading />;
    // return (
    //     <>
    //         <PermissionGuard rolesAllowed={[Role.SUPER_ADMIN]}></PermissionGuard>
    //         <RoleGuard rolesAllowed={[Role.SUPER_ADMIN]}>
    //             {children}
    //         </RoleGuard>
    //     </>
    // );

    return (
        <>
            {children}
        </>
    )
};