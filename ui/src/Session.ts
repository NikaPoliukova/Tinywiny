import create from 'zustand';
import {User} from "./model/User";
import SessionService from "./services/SessionService";
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
            set({ user });
        } catch (error) {
            set({ user: null });
        } finally {
            set({ loading: false });
        }
    }
}));