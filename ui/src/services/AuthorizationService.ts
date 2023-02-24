import axios from 'axios';
import {User} from "../model/User";

class AuthorizationService {
    async login(user: User): Promise<number> {
        const response = await axios.post('http://localhost:8080/api/v1/login',
            user);
        return response.status;
    }
    async getUser(userName: string, password : string): Promise<User> {
        const param = {userName: userName, password: password}
        const response = await axios.post('http://localhost:8080/api/v1/users/user',{
                data: param
            }
            );
        return response.data;
    }
}

export default new AuthorizationService();