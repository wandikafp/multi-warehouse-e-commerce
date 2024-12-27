"use client"

import ErrorFeedback from "@/components/error-feedback";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { useAuthGuard } from "@/lib/auth/use-auth";
import { userService } from "@/lib/services";
import { HttpErrorResponse } from "@/models/http/HttpErrorResponse";
import { zodResolver } from "@hookform/resolvers/zod";
import React, { useEffect } from "react";
import { useForm } from "react-hook-form";
import { toast } from "sonner";
import { z } from "zod";

const schema = z.object({
  fullName: z.string().min(2)
});

type Schema = z.infer<typeof schema>;
export default function UpdateBasicDetailsForm() {
  const { user, mutate } = useAuthGuard({ middleware: "auth" });
  const [errors, setErrors] = React.useState<HttpErrorResponse | undefined>(undefined);

  const onSubmit = (data: Schema) => {
    setErrors(undefined);
    const jwtToken = localStorage.getItem("jwtToken");
    if (!jwtToken) throw new Error("Unauthorized");
    userService
      .put("/api/users/" + user!.id, data, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      })
      .then(() => {
        toast.success("Profile updated successfully");
        mutate();
      })
      .catch((error) => {
        const errData = error.response.data as HttpErrorResponse;
        setErrors(errData);
      });
  };

  useEffect(() => {
    if (user) {
      form.setValue("fullName", user.fullName || '');
    }
  }, [user])

  const form = useForm<Schema>({
    resolver: zodResolver(schema),
    reValidateMode: "onSubmit",
    defaultValues: {
      fullName: user!.fullName,
    }
  });

  return (
    <div className="max-w-screen-sm">
      <Form {...form}>
        <form
          onSubmit={form.handleSubmit(onSubmit)}
          className="flex flex-col gap-y-2"
        >
          <FormField
            control={form.control}
            name="fullName"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Full name</FormLabel>
                <FormControl>
                  <Input {...field}></Input>
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          ></FormField>

          <Button type="submit">Update profile</Button>
        </form>
      </Form>

      <ErrorFeedback data={errors} />
    </div>
  );
}
