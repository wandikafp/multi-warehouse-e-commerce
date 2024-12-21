"use client"
import { useAuthGuard } from '@/lib/auth/use-auth'
import React from 'react'
import { Button } from "@/components/ui/button"

interface LoginGuardProps {
  children: React.ReactNode
}
export default function LoginGuard({children}: LoginGuardProps) {
  const {user} = useAuthGuard({middleware: 'auth'})
  if (user?.role) return children
  
  return (
    <div className="flex flex-col items-center justify-center h-full">
      <h1 className="text-4xl font-semibold">Permission Denied</h1>
      <p className="text-lg">You need to login to access this resource:</p>
      <Button
        onClick={() => window.location.href = '/auth/login'}
        className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
      >
        Go to Login Page
      </Button>
    </div>
  )
}
