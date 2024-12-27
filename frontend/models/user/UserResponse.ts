export interface UserResponse {
    id: string;
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
    USER = "CUSTOMER",
    SUPER_ADMIN = "SUPER_ADMIN"
  }