import Axios from 'axios'
require('dotenv').config()

export const userClient = Axios.create({
  baseURL: process.env.NEXT_PUBLIC_BASE_URL!, 
  headers: {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  },
  withCredentials: true,
  xsrfCookieName: 'XSRF-TOKEN',
  withXSRFToken: true,
});
