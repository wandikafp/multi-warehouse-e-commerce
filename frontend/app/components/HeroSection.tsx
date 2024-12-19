"use client";

import { Button } from "@/components/ui/button"; // Ensure this path is correct
import { cn } from "@/lib/utils"; // Ensure this path is correct
import { useState } from "react";

export default function HeroSection() {
  const [currentSlide, setCurrentSlide] = useState(0);

  const slides = [
    {
      id: 1,
      title: "Welcome to Our Store!",
      description: "Discover the best products tailored for you.",
      image: "/images/logo.png", // Replace with your image path
    },
    {
      id: 2,
      title: "Exclusive Promotions",
      description: "Up to 50% off on selected items!",
      image: "/images/logo.png",
    },
    {
      id: 3,
      title: "Shop Now",
      description: "Find the latest trends in our collection.",
      image: "/images/logo.png",
    },
  ];

  const handleNextSlide = () => {
    setCurrentSlide((prev) => (prev + 1) % slides.length);
  };

  const handlePrevSlide = () => {
    setCurrentSlide((prev) => (prev - 1 + slides.length) % slides.length);
  };

  return (
    <div className="relative bg-gray-900 text-white w-full">
      {/* Carousel */}
      <div className="relative h-96 w-full overflow-hidden">
        {slides.map((slide, index) => (
          <div
            key={slide.id}
            className={cn(
              "absolute inset-0 flex items-center justify-center transition-opacity duration-1000",
              currentSlide === index ? "opacity-100" : "opacity-0"
            )}
            style={{ backgroundImage: `url(${slide.image})`, backgroundSize: "cover", backgroundPosition: "center" }}
          >
            <div className="bg-black bg-opacity-50 p-6 text-center rounded-md">
              <h1 className="text-3xl font-bold mb-4">{slide.title}</h1>
              <p className="mb-6">{slide.description}</p>
              <Button size="lg" onClick={() => alert("Get Started!")}>
                Get Started
              </Button>
            </div>
          </div>
        ))}
        {/* Navigation Buttons */}
        <button
          className="absolute top-1/2 left-4 transform -translate-y-1/2 bg-white text-black rounded-full p-2 shadow"
          onClick={handlePrevSlide}
        >
          &lt;
        </button>
        <button
          className="absolute top-1/2 right-4 transform -translate-y-1/2 bg-white text-black rounded-full p-2 shadow"
          onClick={handleNextSlide}
        >
          &gt;
        </button>
      </div>
    </div>
  );
}
