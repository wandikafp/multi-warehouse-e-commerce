"use client"

import { Inter as FontSans } from "next/font/google"
import { cn } from "@/lib/utils";
import "./globals.css";
import { ThemeProvider } from "@/components/theme-provider";
import { Toaster } from "@/components/ui/sonner";
import Navbar from "@/components/navbar";
import Footer from "./components/Footer";
import { useEffect } from "react";
import { userService } from "@/lib/services";

const fontSans = FontSans({
  subsets: ["latin"],
  variable: "--font-sans",
})

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  // useEffect(() => {
  //   // Initialize the csrf token
  //   userClient.get("/api/auth/csrf")
  // }, []);

  return (
    <html lang="en">
      <body
        className={cn(
          "min-h-screen bg-background font-sans antialiased",
          fontSans.variable
        )}
      >
        <ThemeProvider
            attribute="class"
            defaultTheme="system"
            enableSystem
            disableTransitionOnChange
          >
            <Navbar className="h-[70px] sticky top-0"/>
            <main>
              {children}
            </main>

        </ThemeProvider>
        <Footer />
        <Toaster />
      </body>
    </html>
  );
}
