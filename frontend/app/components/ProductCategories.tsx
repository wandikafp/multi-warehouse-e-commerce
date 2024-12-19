import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";

export default function ProductCategories() {
  const categories = [
    {
      id: 1,
      title: "Electronics",
      description: "Discover the latest gadgets and devices.",
      icon: "📱", // Replace with an actual image or SVG if preferred
    },
    {
      id: 2,
      title: "Fashion",
      description: "Explore trending styles and accessories.",
      icon: "👗",
    },
    {
      id: 3,
      title: "Home Appliances",
      description: "Upgrade your living space with our products.",
      icon: "🏠",
    },
    {
      id: 4,
      title: "Books",
      description: "Find your next great read.",
      icon: "📚",
    },
    {
      id: 5,
      title: "Toys",
      description: "Fun and engaging toys for all ages.",
      icon: "🧸",
    },
  ];

  return (
    <div className="py-12 bg-gray-100">
      <div className="container mx-auto px-6">
        <h2 className="text-3xl font-bold text-center mb-8">Product Categories</h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {categories.map((category) => (
            <Card
              key={category.id}
              className="hover:shadow-lg transition-shadow duration-300"
            >
              <CardHeader className="text-center">
                <div
                  className="text-5xl mb-4"
                  role="img"
                  aria-label={category.title}
                >
                  {category.icon}
                </div>
                <CardTitle className="text-xl font-semibold">
                  {category.title}
                </CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-center text-gray-600">
                  {category.description}
                </p>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </div>
  );
}
