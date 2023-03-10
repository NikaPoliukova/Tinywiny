import {User} from "../model/User";
import axios from "axios";

class SessionService {
    async getSession(): Promise<User> {
        const response = await axios.get<User>(
            'http://localhost:8080/api/v1/users/userId',
            { withCredentials: true }
        );
        return response.data;
    }
}


export default new SessionService();