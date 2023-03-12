import {create} from "zustand";
import SessionService from "../../services/SessionService";
import {User} from "../../model/User";

export interface SessionState {
    user: User | null;
    loading: boolean;
    getSession: () => void;
}

export const useSessionStore = create<SessionState>(set => ({
    user: null,
    loading: true,

    getSession: async () => {
        try {
            const user = await SessionService.getSession();
            console.log("юзер из сессион сторе")
            console.log(user);
            set({ user });
        } catch (error) {
            set({ user: null });
        } finally {
            set({ loading: false });
        }
    }
}));
