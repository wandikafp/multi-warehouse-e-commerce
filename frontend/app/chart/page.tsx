import React from 'react';
import LoginGuard from '@/components/login-guard';

const ChartPage: React.FC = () => {
    const items = [
        { id: 1, name: 'Item 1', quantity: 2, price: 10 },
        { id: 2, name: 'Item 2', quantity: 1, price: 20 },
        { id: 3, name: 'Item 3', quantity: 3, price: 15 },
    ];

    const total = items.reduce((acc, item) => acc + item.quantity * item.price, 0);

    return (
        <LoginGuard>
            <div>
                <h1>Shopping Cart</h1>
                <ul>
                    {items.map(item => (
                        <li key={item.id}>
                            {item.name} - Quantity: {item.quantity} - Price: ${item.price}
                        </li>
                    ))}
                </ul>
                <h2>Total: ${total}</h2>
            </div>
        </LoginGuard>
        
    );
};

export default ChartPage;