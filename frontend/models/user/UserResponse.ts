export interface UserResponse {
    id: number;
    role: Role;
    fullName?: string;
    email: string;
    profileImageUrl?: string;
    connectedAccounts: ConnectedAccount[];
    authorities: string[]
  }
  
  interface ConnectedAccount {
    provider: 'google' | 'facebook' | 'twitter';
    connectedAt: string;
  }
  
  export enum Role {
    USER = "USER",
    ADMIN = "ADMIN"
  }