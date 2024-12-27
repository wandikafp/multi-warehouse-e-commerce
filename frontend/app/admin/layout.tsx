import React from 'react';
import { ReactNode } from 'react';
import "../globals.css";

export default function AdminLayout({
    children,
}: Readonly<{
    children: ReactNode;
}>) {
    return (
        <div>
            <header>
                <h1>Admin Panel</h1>
            </header>
            <main>
                {children}
            </main>
        </div>
    );
};