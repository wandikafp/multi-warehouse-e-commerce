"use client"

import Link from "next/link"

export default function Logo() {
  return (
    <Link href={"/"}>
      <img src="/images/logo.png" alt="hirebalkan logo" className="w-25 h-10"></img>
    </Link>
  )
}
