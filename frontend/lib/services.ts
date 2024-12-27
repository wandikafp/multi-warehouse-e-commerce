import Axios from 'axios';

// Ensure environment variables are loaded
if (process.env.NODE_ENV !== 'production') {
  require('dotenv').config();
}

export const userService = Axios.create({
    baseURL: process.env.NEXT_PUBLIC_USER_SERVICE_BASE_URL, 
    headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
    withCredentials: true,
    xsrfCookieName: 'XSRF-TOKEN',
    withXSRFToken: true,
});

export const productService = Axios.create({
    baseURL: process.env.NEXT_PUBLIC_PRODUCT_SERVICE_BASE_URL, 
    headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
    withCredentials: true,
    xsrfCookieName: 'XSRF-TOKEN',
    withXSRFToken: true,
});

export const orderService = Axios.create({
    baseURL: process.env.NEXT_PUBLIC_ORDER_SERVICE_BASE_URL, 
    headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
    withCredentials: true,
    xsrfCookieName: 'XSRF-TOKEN',
    withXSRFToken: true,
});

export const warehouseService = Axios.create({
    baseURL: process.env.NEXT_PUBLIC_WAREHOUSE_SERVICE_BASE_URL, 
    headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
    withCredentials: true,
    xsrfCookieName: 'XSRF-TOKEN',
    withXSRFToken: true,
});
  
export const mockClient = Axios.create({
    baseURL: process.env.NEXT_PUBLIC_MOCK, 
    headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    },
    withCredentials: true,
    xsrfCookieName: 'XSRF-TOKEN',
    withXSRFToken: true,
});