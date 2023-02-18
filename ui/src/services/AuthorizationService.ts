import axios from 'axios';
import {User} from "../model/User";

class AuthorizationService {
    async login(user: User): Promise<number> {
        const response = await axios.post('http://localhost:8080/api/v1/login',
            user);
        return response.status;
    }
}

export default new AuthorizationService();