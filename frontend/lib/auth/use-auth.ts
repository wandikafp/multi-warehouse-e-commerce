import useSWR from "swr";
import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { userService } from "../services";
import { HttpErrorResponse } from "@/models/http/HttpErrorResponse";
import { UserResponse } from "@/models/user/UserResponse";

interface AuthProps {
  middleware?: "auth" | "guest";
  redirectIfAuthenticated?: string;
}

export const useAuthGuard = ({
  middleware,
  redirectIfAuthenticated,
}: AuthProps) => {
  const router = useRouter();

  // Create a fetcher to include the JWT token in requests
  const fetcher = (url: string) => {
    const jwtToken = localStorage.getItem("jwtToken");
    if (!jwtToken) throw new Error("Unauthorized");
    return userService
      .get<UserResponse>(url, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      })
      .then((res) => res.data);
  };

  const {
    data: user,
    error,
    mutate,
  } = useSWR("/api/auth/me", fetcher);

  const login = async ({
    onError,
    props,
  }: {
    onError: (errors: HttpErrorResponse | undefined) => void;
    props: { email: string; password: string };
  }) => {
    onError(undefined);
    try {
      const res = await userService.post<{ token: string }>("/api/auth/login", {
        email: props.email,
        password: props.password,
      });

      // Save the JWT token in local storage
      localStorage.setItem("jwtToken", res.data.token);

      // Revalidate the user session
      mutate();
    } catch (err) {
      const errors = (err as any).response?.data as HttpErrorResponse;
      onError(errors);
    }
  };

  const logout = async () => {
    // Remove the JWT token from local storage
    localStorage.removeItem("jwtToken");
    mutate(undefined, false); // Clear user data from SWR
    window.location.pathname = "/auth/login";
  };

  useEffect(() => {
    // If middleware is 'guest' and we have a user, redirect
    if (middleware === "guest" && redirectIfAuthenticated && user) {
      router.push(redirectIfAuthenticated);
    }

    // If middleware is 'auth' and we have an error, redirect to login
    if (middleware === "auth" && error) {
      logout();
    }
  }, [user, error]);

  return {
    user,
    login,
    logout,
    mutate,
  };
};
