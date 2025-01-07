import HeroSection from "./components/HeroSection";
import ProductCategories from "./components/ProductCategories";

export default function Home() {
  return (
    <div className="flex flex-col items-center justify-between p-4">
      {/* Hero Section */}
      <HeroSection />

      {/* Product Categories */}
      <ProductCategories />
    </div>
  );
}