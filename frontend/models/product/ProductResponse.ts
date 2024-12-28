export interface ProductResponse {
    id: string;
    name: string;
    description: string;
    imageUrl: string;
    price: string;
    stockQuantity: number;
    category: Category;
  }

  interface Category {
    id: string;
    name: string;
  }
  