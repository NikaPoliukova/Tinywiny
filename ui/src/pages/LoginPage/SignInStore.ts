import {create} from "zustand";
import SessionService from "../../services/SessionService";

export interface SignInStore {
    userName: string;
    password: string;
    isAuthorized: boolean;
    setUserName: (userName: string) => void;
    setPassword: (password: string) => void;
    setIsAuthorized: (flag: boolean) => void;
    getToken: () => void;

}

export const useSignInStore = create<SignInStore>((set: any, get: any) => ({
    userName: '',
    password: '',
    isAuthorized: false,

    setUserName: async (userName: string) => {
        set({userName: userName})
    },
    setPassword: async (password: string) => {
        set({password: password})
    },
    setIsAuthorized: async (flag: boolean) => {
        set({isAuthorized: flag})
    },
    getToken: async () => {
        set({isAuthorized: false})
        await SessionService.getToken(get().userName, get().password)
            ? set({isAuthorized: true})
            : set({isAuthorized: true})

    }
}))
